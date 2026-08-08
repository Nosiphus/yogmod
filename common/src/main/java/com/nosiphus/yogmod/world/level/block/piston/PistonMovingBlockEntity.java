package com.nosiphus.yogmod.world.level.block.piston;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonMath;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class PistonMovingBlockEntity extends BlockEntity {

    private static final double PUSH_OFFSET = 0.01D;
    public static final double TICK_MOVEMENT = 0.51D;

    private BlockState movedState = Blocks.AIR.defaultBlockState();
    private Direction direction;
    private boolean extending;
    private boolean isSourcePiston;

    private static final ThreadLocal<Direction> NOCLIP = ThreadLocal.withInitial(() -> null);

    private float progress;
    private float progressO;
    private long lastTicked;
    private int deathTicks;

    public PistonMovingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.PISTON.get(), pos, state);
    }

    public PistonMovingBlockEntity(BlockPos pos, BlockState state, BlockState movedState, Direction direction, boolean extending, boolean isSourcePiston) {
        this(pos, state);
        this.movedState = movedState;
        this.direction = direction;
        this.extending = extending;
        this.isSourcePiston = isSourcePiston;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }

    public boolean isExtending() {
        return this.extending;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isSourcePiston() {
        return this.isSourcePiston;
    }

    public float getProgress(float partialTicks) {
        if (partialTicks > 1.0F) {
            partialTicks = 1.0F;
        }
        return Mth.lerp(partialTicks, this.progressO, this.progress);
    }

    public float getXOff(float partialTicks) {
        return (float) this.direction.getStepX() * this.getExtendedProgress(this.getProgress(partialTicks));
    }

    public float getYOff(float partialTicks) {
        return (float) this.direction.getStepY() * this.getExtendedProgress(this.getProgress(partialTicks));
    }

    public float getZOff(float partialTicks) {
        return (float) this.direction.getStepZ() * this.getExtendedProgress(this.getProgress(partialTicks));
    }

    private float getExtendedProgress(float progress) {
        return this.extending ? progress - 1.0F : 1.0F - progress;
    }

    private BlockState getCollisionRelatedBlockState() {
        return !this.isExtending() && this.isSourcePiston() && this.movedState.getBlock() instanceof PistonBaseBlock
                ? ModBlocks.PISTON_HEAD.get().defaultBlockState()
                .setValue(PistonHeadBlock.SHORT, this.progress > 0.25F)
                .setValue(PistonHeadBlock.TYPE, this.movedState.is(ModBlocks.STICKY_PISTON.get()) ? PistonType.STICKY : PistonType.DEFAULT)
                .setValue(PistonHeadBlock.FACING, this.movedState.getValue(PistonBaseBlock.FACING))
                : this.movedState;
    }

    private static void moveCollidedEntities(Level level, BlockPos pos, float progress, PistonMovingBlockEntity blockEntity) {
        Direction direction = blockEntity.getMovementDirection();
        double deltaProgress = progress - blockEntity.progress;
        VoxelShape collisionShape = blockEntity.getCollisionRelatedBlockState().getCollisionShape(level, pos);

        if (!collisionShape.isEmpty()) {
            AABB bounds = moveByPositionAndProgress(pos, collisionShape.bounds(), blockEntity);
            List<Entity> entities = level.getEntities(null, PistonMath.getMovementArea(bounds, direction, deltaProgress).minmax(bounds));

            if (!entities.isEmpty()) {
                List<AABB> aabbs = collisionShape.toAabbs();
                boolean isSlime = blockEntity.movedState.is(Blocks.SLIME_BLOCK);

                for (Entity entity : entities) {
                    if (entity.getPistonPushReaction() != PushReaction.IGNORE) {
                        if (isSlime && !(entity instanceof ServerPlayer)) {
                            Vec3 delta = entity.getDeltaMovement();
                            double dx = delta.x;
                            double dy = delta.y;
                            double dz = delta.z;

                            switch (direction.getAxis()) {
                                case X -> dx = direction.getStepX();
                                case Y -> dy = direction.getStepY();
                                case Z -> dz = direction.getStepZ();
                            }

                            entity.setDeltaMovement(dx, dy, dz);
                        }

                        double maxMovement = 0.0D;
                        for (AABB box : aabbs) {
                            AABB movementArea = PistonMath.getMovementArea(moveByPositionAndProgress(pos, box, blockEntity), direction, deltaProgress);
                            AABB entityBox = entity.getBoundingBox();
                            if (movementArea.intersects(entityBox)) {
                                maxMovement = Math.max(maxMovement, getMovement(movementArea, direction, entityBox));
                                if (maxMovement >= deltaProgress) {
                                    break;
                                }
                            }
                        }

                        if (maxMovement > 0.0D) {
                            maxMovement = Math.min(maxMovement, deltaProgress) + PUSH_OFFSET;
                            moveEntityByPiston(direction, entity, maxMovement, direction);
                            if (!blockEntity.extending && blockEntity.isSourcePiston) {
                                fixEntityWithinPistonBase(pos, entity, direction, deltaProgress);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void moveEntityByPiston(Direction noclipDirection, Entity entity, double distance, Direction moveDirection) {
        NOCLIP.set(noclipDirection);
        entity.move(MoverType.PISTON, new Vec3(distance * moveDirection.getStepX(), distance * moveDirection.getStepY(), distance * moveDirection.getStepZ()));
        NOCLIP.set(null);
    }

    private static void moveStuckEntities(Level level, BlockPos pos, float progress, PistonMovingBlockEntity blockEntity) {
        if (blockEntity.isStickyForEntities()) {
            Direction direction = blockEntity.getMovementDirection();
            if (direction.getAxis().isHorizontal()) {
                double maxShapeY = blockEntity.movedState.getCollisionShape(level, pos).max(Direction.Axis.Y);
                AABB stuckBox = moveByPositionAndProgress(pos, new AABB(0.0D, maxShapeY, 0.0D, 1.0D, 1.5D, 1.0D), blockEntity);
                double deltaProgress = progress - blockEntity.progress;

                for (Entity entity : level.getEntities((Entity) null, stuckBox, e -> matchesStickyCritera(stuckBox, e))) {
                    moveEntityByPiston(direction, entity, deltaProgress, direction);
                }
            }
        }
    }

    private static boolean matchesStickyCritera(AABB box, Entity entity) {
        return entity.getPistonPushReaction() == PushReaction.NORMAL && entity.onGround() && entity.getX() >= box.minX && entity.getX() <= box.maxX && entity.getZ() >= box.minZ && entity.getZ() <= box.maxZ;
    }

    private boolean isStickyForEntities() {
        return this.movedState.is(Blocks.HONEY_BLOCK);
    }

    public Direction getMovementDirection() {
        return this.extending ? this.direction : this.direction.getOpposite();
    }

    private static double getMovement(AABB bounds1, Direction direction, AABB bounds2) {
        return switch (direction) {
            case EAST -> bounds1.maxX - bounds2.minX;
            case WEST -> bounds2.maxX - bounds1.minX;
            case UP -> bounds1.maxY - bounds2.minY;
            case DOWN -> bounds2.maxY - bounds1.minY;
            case SOUTH -> bounds1.maxZ - bounds2.minZ;
            case NORTH -> bounds2.maxZ - bounds1.minZ;
        };
    }

    private static AABB moveByPositionAndProgress(BlockPos pos, AABB box, PistonMovingBlockEntity blockEntity) {
        double progress = blockEntity.getExtendedProgress(blockEntity.progress);
        return box.move(
                pos.getX() + progress * blockEntity.direction.getStepX(),
                pos.getY() + progress * blockEntity.direction.getStepY(),
                pos.getZ() + progress * blockEntity.direction.getStepZ()
        );
    }

    private static void fixEntityWithinPistonBase(BlockPos pos, Entity entity, Direction direction, double distance) {
        AABB entityBox = entity.getBoundingBox();
        AABB baseBox = Shapes.block().bounds().move(pos);

        if (entityBox.intersects(baseBox)) {
            Direction opposite = direction.getOpposite();
            double d0 = getMovement(baseBox, opposite, entityBox) + PUSH_OFFSET;
            double d1 = getMovement(baseBox, opposite, entityBox.intersect(baseBox)) + PUSH_OFFSET;

            if (Math.abs(d0 - d1) < PUSH_OFFSET) {
                d0 = Math.min(d0, distance) + PUSH_OFFSET;
                moveEntityByPiston(direction, entity, d0, opposite);
            }
        }
    }

    public BlockState getMovedState() {
        return this.movedState;
    }

    public void finalTick() {
        if (this.level != null && (this.progressO < 1.0F || this.level.isClientSide)) {
            this.progress = 1.0F;
            this.progressO = this.progress;
            this.level.removeBlockEntity(this.worldPosition);
            this.setRemoved();

            if (this.level.getBlockState(this.worldPosition).is(ModBlocks.MOVING_PISTON.get())) {
                BlockState newState = this.isSourcePiston
                        ? Blocks.AIR.defaultBlockState()
                        : Block.updateFromNeighbourShapes(this.movedState, this.level, this.worldPosition);

                this.level.setBlock(this.worldPosition, newState, 3);
                this.level.neighborChanged(this.worldPosition, newState.getBlock(), this.worldPosition);
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, PistonMovingBlockEntity blockEntity) {
        blockEntity.lastTicked = level.getGameTime();
        blockEntity.progressO = blockEntity.progress;

        if (blockEntity.progressO >= 1.0F) {
            if (level.isClientSide && blockEntity.deathTicks < 5) {
                ++blockEntity.deathTicks;
            } else {
                level.removeBlockEntity(pos);
                blockEntity.setRemoved();

                if (level.getBlockState(pos).is(ModBlocks.MOVING_PISTON.get())) {
                    BlockState newState = Block.updateFromNeighbourShapes(blockEntity.movedState, level, pos);
                    if (newState.isAir()) {
                        level.setBlock(pos, blockEntity.movedState, 84);
                        Block.updateOrDestroy(blockEntity.movedState, newState, level, pos, 3);
                    } else {
                        if (newState.hasProperty(BlockStateProperties.WATERLOGGED) && newState.getValue(BlockStateProperties.WATERLOGGED)) {
                            newState = newState.setValue(BlockStateProperties.WATERLOGGED, false);
                        }

                        level.setBlock(pos, newState, 67);
                        level.neighborChanged(pos, newState.getBlock(), pos);
                    }
                }
            }
        } else {
            float nextProgress = blockEntity.progress + 0.5F;
            moveCollidedEntities(level, pos, nextProgress, blockEntity);
            moveStuckEntities(level, pos, nextProgress, blockEntity);
            blockEntity.progress = nextProgress;
            if (blockEntity.progress >= 1.0F) {
                blockEntity.progress = 1.0F;
            }
        }
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        HolderGetter<Block> holderGetter = this.level != null ? this.level.holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup();
        this.movedState = NbtUtils.readBlockState(holderGetter, tag.getCompound("blockState"));
        this.direction = Direction.from3DDataValue(tag.getInt("facing"));
        this.progress = tag.getFloat("progress");
        this.progressO = this.progress;
        this.extending = tag.getBoolean("extending");
        this.isSourcePiston = tag.getBoolean("source");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("blockState", NbtUtils.writeBlockState(this.movedState));
        tag.putInt("facing", this.direction.get3DDataValue());
        tag.putFloat("progress", this.progressO);
        tag.putBoolean("extending", this.extending);
        tag.putBoolean("source", this.isSourcePiston);
    }

    public VoxelShape getCollisionShape(BlockGetter blockGetter, BlockPos pos) {
        VoxelShape baseShape;
        if (!this.extending && this.isSourcePiston && this.movedState.getBlock() instanceof PistonBaseBlock) {
            baseShape = this.movedState.setValue(PistonBaseBlock.EXTENDED, true).getCollisionShape(blockGetter, pos);
        } else {
            baseShape = Shapes.empty();
        }

        Direction noclipDir = NOCLIP.get();
        if (this.progress < 1.0F && noclipDir == this.getMovementDirection()) {
            return baseShape;
        } else {
            BlockState headState = this.isSourcePiston()
                    ? ModBlocks.PISTON_HEAD.get().defaultBlockState()
                    .setValue(PistonHeadBlock.FACING, this.direction)
                    .setValue(PistonHeadBlock.SHORT, this.extending != 1.0F - this.progress < 0.25F)
                    : this.movedState;

            float extendedProgress = this.getExtendedProgress(this.progress);
            double dx = (float) this.direction.getStepX() * extendedProgress;
            double dy = (float) this.direction.getStepY() * extendedProgress;
            double dz = (float) this.direction.getStepZ() * extendedProgress;

            return Shapes.or(baseShape, headState.getCollisionShape(blockGetter, pos).move(dx, dy, dz));
        }
    }

    public long getLastTicked() {
        return this.lastTicked;
    }
}