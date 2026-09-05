package com.nosiphus.yogmod.world.level.block.piston;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
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

import java.util.Iterator;
import java.util.List;

public class PistonMovingBlockEntity extends BlockEntity {
    private static final int TICKS_TO_EXTEND = 2;
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
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
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

    public float getProgress(float partialTick) {
        if (partialTick > 1.0F) {
            partialTick = 1.0F;
        }

        return Mth.lerp(partialTick, this.progressO, this.progress);
    }

    public float getXOff(float partialTick) {
        return (float)this.direction.getStepX() * this.getExtendedProgress(this.getProgress(partialTick));
    }

    public float getYOff(float partialTick) {
        return (float)this.direction.getStepY() * this.getExtendedProgress(this.getProgress(partialTick));
    }

    public float getZOff(float partialTick) {
        return (float)this.direction.getStepZ() * this.getExtendedProgress(this.getProgress(partialTick));
    }

    private float getExtendedProgress(float progress) {
        return this.extending ? progress - 1.0F : 1.0F - progress;
    }

    private BlockState getCollisionRelatedBlockState() {
        return !this.isExtending() && this.isSourcePiston() && this.movedState.getBlock() instanceof PistonBaseBlock ? ModBlocks.PISTON_HEAD.get().defaultBlockState().setValue(PistonHeadBlock.SHORT, Boolean.valueOf(this.progress > 0.25F)).setValue(PistonHeadBlock.TYPE, this.movedState.is(ModBlocks.STICKY_PISTON.get()) ? PistonType.STICKY : PistonType.DEFAULT).setValue(PistonHeadBlock.FACING, this.movedState.getValue(PistonBaseBlock.FACING)) : this.movedState;
    }

    private static void moveCollidedEntities(Level level, BlockPos blockPos, float f, PistonMovingBlockEntity entity) {
        Direction direction = entity.getMovementDirection();
        double d0 = (double)(f - entity.progress);
        VoxelShape voxelshape = entity.getCollisionRelatedBlockState().getCollisionShape(level, blockPos);
        if (!voxelshape.isEmpty()) {
            AABB aabb = moveByPositionAndProgress(blockPos, voxelshape.bounds(), entity);
            List<Entity> list = level.getEntities((Entity)null, PistonMath.getMovementArea(aabb, direction, d0).minmax(aabb));
            if (!list.isEmpty()) {
                List<AABB> list1 = voxelshape.toAabbs();
                boolean isSlime = entity.movedState.is(Blocks.SLIME_BLOCK);
                Iterator<Entity> iterator = list.iterator();

                while(true) {
                    Entity currentEntity;
                    while(true) {
                        if (!iterator.hasNext()) {
                            return;
                        }

                        currentEntity = iterator.next();
                        if (currentEntity.getPistonPushReaction() != PushReaction.IGNORE) {
                            if (!isSlime) {
                                break;
                            }

                            if (!(currentEntity instanceof ServerPlayer)) {
                                Vec3 vec3 = currentEntity.getDeltaMovement();
                                double d1 = vec3.x;
                                double d2 = vec3.y;
                                double d3 = vec3.z;
                                switch (direction.getAxis()) {
                                    case X:
                                        d1 = (double)direction.getStepX();
                                        break;
                                    case Y:
                                        d2 = (double)direction.getStepY();
                                        break;
                                    case Z:
                                        d3 = (double)direction.getStepZ();
                                }

                                currentEntity.setDeltaMovement(d1, d2, d3);
                                break;
                            }
                        }
                    }

                    double d4 = 0.0D;

                    for(AABB aabb2 : list1) {
                        AABB aabb1 = PistonMath.getMovementArea(moveByPositionAndProgress(blockPos, aabb2, entity), direction, d0);
                        AABB aabb3 = currentEntity.getBoundingBox();
                        if (aabb1.intersects(aabb3)) {
                            d4 = Math.max(d4, getMovement(aabb1, direction, aabb3));
                            if (d4 >= d0) {
                                break;
                            }
                        }
                    }

                    if (!(d4 <= 0.0D)) {
                        d4 = Math.min(d4, d0) + 0.01D;
                        moveEntityByPiston(direction, currentEntity, d4, direction);
                        if (!entity.extending && entity.isSourcePiston) {
                            fixEntityWithinPistonBase(blockPos, currentEntity, direction, d0);
                        }
                    }
                }
            }
        }
    }

    private static void moveEntityByPiston(Direction direction, Entity entity, double d, Direction facing) {
        NOCLIP.set(direction);
        entity.move(MoverType.PISTON, new Vec3(d * (double)facing.getStepX(), d * (double)facing.getStepY(), d * (double)facing.getStepZ()));
        NOCLIP.set(null);
    }

    private static void moveStuckEntities(Level level, BlockPos blockPos, float f, PistonMovingBlockEntity entity) {
        if (entity.isStickyForEntities()) {
            Direction direction = entity.getMovementDirection();
            if (direction.getAxis().isHorizontal()) {
                double d0 = entity.movedState.getCollisionShape(level, blockPos).max(Direction.Axis.Y);
                AABB aabb = moveByPositionAndProgress(blockPos, new AABB(0.0D, d0, 0.0D, 1.0D, 1.5000000999999998D, 1.0D), entity);
                double d1 = (double)(f - entity.progress);

                for(Entity targetEntity : level.getEntities((Entity)null, aabb, (target) -> matchesStickyCriteria(aabb, target))) {
                    moveEntityByPiston(direction, targetEntity, d1, direction);
                }
            }
        }
    }

    private static boolean matchesStickyCriteria(AABB aabb, Entity entity) {
        return entity.getPistonPushReaction() == PushReaction.NORMAL && entity.onGround() && entity.getX() >= aabb.minX && entity.getX() <= aabb.maxX && entity.getZ() >= aabb.minZ && entity.getZ() <= aabb.maxZ;
    }

    private boolean isStickyForEntities() {
        return this.movedState.is(Blocks.HONEY_BLOCK);
    }

    public Direction getMovementDirection() {
        return this.extending ? this.direction : this.direction.getOpposite();
    }

    private static double getMovement(AABB aabb, Direction direction, AABB aabb1) {
        switch (direction) {
            case EAST:
                return aabb.maxX - aabb1.minX;
            case WEST:
                return aabb1.maxX - aabb.minX;
            case UP:
            default:
                return aabb.maxY - aabb1.minY;
            case DOWN:
                return aabb1.maxY - aabb.minY;
            case SOUTH:
                return aabb.maxZ - aabb1.minZ;
            case NORTH:
                return aabb1.maxZ - aabb.minZ;
        }
    }

    private static AABB moveByPositionAndProgress(BlockPos blockPos, AABB aabb, PistonMovingBlockEntity entity) {
        double d0 = (double)entity.getExtendedProgress(entity.progress);
        return aabb.move((double)blockPos.getX() + d0 * (double)entity.direction.getStepX(), (double)blockPos.getY() + d0 * (double)entity.direction.getStepY(), (double)blockPos.getZ() + d0 * (double)entity.direction.getStepZ());
    }

    private static void fixEntityWithinPistonBase(BlockPos blockPos, Entity entity, Direction direction, double d) {
        AABB aabb = entity.getBoundingBox();
        AABB aabb1 = Shapes.block().bounds().move(blockPos);
        if (aabb.intersects(aabb1)) {
            Direction direction1 = direction.getOpposite();
            double d0 = getMovement(aabb1, direction1, aabb) + 0.01D;
            double d1 = getMovement(aabb1, direction1, aabb.intersect(aabb1)) + 0.01D;
            if (Math.abs(d0 - d1) < 0.01D) {
                d0 = Math.min(d0, d) + 0.01D;
                moveEntityByPiston(direction, entity, d0, direction1);
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
                BlockState blockstate;
                if (this.isSourcePiston) {
                    blockstate = Blocks.AIR.defaultBlockState();
                } else {
                    blockstate = Block.updateFromNeighbourShapes(this.movedState, this.level, this.worldPosition);
                }

                this.level.setBlock(this.worldPosition, blockstate, 3);
                this.level.neighborChanged(this.worldPosition, blockstate.getBlock(), this.worldPosition);
            }
        }
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PistonMovingBlockEntity entity) {
        entity.lastTicked = level.getGameTime();
        entity.progressO = entity.progress;
        if (entity.progressO >= 1.0F) {
            if (level.isClientSide && entity.deathTicks < 5) {
                ++entity.deathTicks;
            } else {
                level.removeBlockEntity(blockPos);
                entity.setRemoved();
                if (level.getBlockState(blockPos).is(ModBlocks.MOVING_PISTON.get())) {
                    BlockState blockstate = Block.updateFromNeighbourShapes(entity.movedState, level, blockPos);
                    if (blockstate.isAir()) {
                        level.setBlock(blockPos, entity.movedState, 84);
                        Block.updateOrDestroy(entity.movedState, blockstate, level, blockPos, 3);
                    } else {
                        if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED)) {
                            blockstate = blockstate.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false));
                        }

                        level.setBlock(blockPos, blockstate, 67);
                        level.neighborChanged(blockPos, blockstate.getBlock(), blockPos);
                    }
                }
            }
        } else {
            float f = entity.progress + 0.5F;
            moveCollidedEntities(level, blockPos, f, entity);
            moveStuckEntities(level, blockPos, f, entity);
            entity.progress = f;
            if (entity.progress >= 1.0F) {
                entity.progress = 1.0F;
            }
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        HolderGetter<Block> holderGetter = (HolderGetter<Block>) (this.level != null ? this.level.holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup());
        this.movedState = NbtUtils.readBlockState(holderGetter, compoundTag.getCompound("blockState"));
        this.direction = Direction.from3DDataValue(compoundTag.getInt("facing"));
        this.progress = compoundTag.getFloat("progress");
        this.progressO = this.progress;
        this.extending = compoundTag.getBoolean("extending");
        this.isSourcePiston = compoundTag.getBoolean("source");
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.put("blockState", NbtUtils.writeBlockState(this.movedState));
        compoundTag.putInt("facing", this.direction.get3DDataValue());
        compoundTag.putFloat("progress", this.progressO);
        compoundTag.putBoolean("extending", this.extending);
        compoundTag.putBoolean("source", this.isSourcePiston);
    }

    public VoxelShape getCollisionShape(BlockGetter blockGetter, BlockPos blockPos) {
        VoxelShape voxelshape;
        if (!this.extending && this.isSourcePiston && this.movedState.getBlock() instanceof PistonBaseBlock) {
            voxelshape = this.movedState.setValue(PistonBaseBlock.EXTENDED, Boolean.valueOf(true)).getCollisionShape(blockGetter, blockPos);
        } else {
            voxelshape = Shapes.empty();
        }

        Direction direction = NOCLIP.get();
        if ((double)this.progress < 1.0D && direction == this.getMovementDirection()) {
            return voxelshape;
        } else {
            BlockState blockstate;
            if (this.isSourcePiston()) {
                blockstate = ModBlocks.PISTON_HEAD.get().defaultBlockState().setValue(PistonHeadBlock.FACING, this.direction).setValue(PistonHeadBlock.SHORT, Boolean.valueOf(this.extending != 1.0F - this.progress < 0.25F));
            } else {
                blockstate = this.movedState;
            }

            float f = this.getExtendedProgress(this.progress);
            double d0 = (double)((float)this.direction.getStepX() * f);
            double d1 = (double)((float)this.direction.getStepY() * f);
            double d2 = (double)((float)this.direction.getStepZ() * f);
            return Shapes.or(voxelshape, blockstate.getCollisionShape(blockGetter, blockPos).move(d0, d1, d2));
        }
    }

    public long getLastTicked() {
        return this.lastTicked;
    }
}