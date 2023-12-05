package com.nosiphus.yogmod.world.level.block.piston;

import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
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
    private static final ThreadLocal<Direction> NOCLIP = ThreadLocal.withInitial(() -> {
        return null;
    });
    private float progress;
    private float progressO;
    private long lastTicked;
    private int deathTicks;

    public PistonMovingBlockEntity(BlockPos p_155901_, BlockState p_155902_) {
        super(ModBlockEntityType.PISTON.get(), p_155901_, p_155902_);
    }

    public PistonMovingBlockEntity(BlockPos p_155904_, BlockState p_155905_, BlockState p_155906_, Direction p_155907_, boolean p_155908_, boolean p_155909_) {
        this(p_155904_, p_155905_);
        this.movedState = p_155906_;
        this.direction = p_155907_;
        this.extending = p_155908_;
        this.isSourcePiston = p_155909_;
    }

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

    public float getProgress(float p_60351_) {
        if (p_60351_ > 1.0F) {
            p_60351_ = 1.0F;
        }

        return Mth.lerp(p_60351_, this.progressO, this.progress);
    }

    public float getXOff(float p_60381_) {
        return (float)this.direction.getStepX() * this.getExtendedProgress(this.getProgress(p_60381_));
    }

    public float getYOff(float p_60386_) {
        return (float)this.direction.getStepY() * this.getExtendedProgress(this.getProgress(p_60386_));
    }

    public float getZOff(float p_60389_) {
        return (float)this.direction.getStepZ() * this.getExtendedProgress(this.getProgress(p_60389_));
    }

    private float getExtendedProgress(float p_60391_) {
        return this.extending ? p_60391_ - 1.0F : 1.0F - p_60391_;
    }

    private BlockState getCollisionRelatedBlockState() {
        return !this.isExtending() && this.isSourcePiston() && this.movedState.getBlock() instanceof PistonBaseBlock ? ModBlocks.PISTON_HEAD.get().defaultBlockState().setValue(PistonHeadBlock.SHORT, Boolean.valueOf(this.progress > 0.25F)).setValue(PistonHeadBlock.TYPE, this.movedState.is(ModBlocks.STICKY_PISTON.get()) ? PistonType.STICKY : PistonType.DEFAULT).setValue(PistonHeadBlock.FACING, this.movedState.getValue(PistonBaseBlock.FACING)) : this.movedState;
    }

    private static void moveCollidedEntities(Level p_155911_, BlockPos p_155912_, float p_155913_, PistonMovingBlockEntity p_155914_) {
        Direction direction = p_155914_.getMovementDirection();
        double d0 = (double)(p_155913_ - p_155914_.progress);
        VoxelShape voxelshape = p_155914_.getCollisionRelatedBlockState().getCollisionShape(p_155911_, p_155912_);
        if (!voxelshape.isEmpty()) {
            AABB aabb = moveByPositionAndProgress(p_155912_, voxelshape.bounds(), p_155914_);
            List<Entity> list = p_155911_.getEntities((Entity)null, PistonMath.getMovementArea(aabb, direction, d0).minmax(aabb));
            if (!list.isEmpty()) {
                List<AABB> list1 = voxelshape.toAabbs();
                boolean flag = p_155914_.movedState.isSlimeBlock(); //TODO: is this patch really needed the logic of the original seems sound revisit later
                Iterator iterator = list.iterator();

                while(true) {
                    Entity entity;
                    while(true) {
                        if (!iterator.hasNext()) {
                            return;
                        }

                        entity = (Entity)iterator.next();
                        if (entity.getPistonPushReaction() != PushReaction.IGNORE) {
                            if (!flag) {
                                break;
                            }

                            if (!(entity instanceof ServerPlayer)) {
                                Vec3 vec3 = entity.getDeltaMovement();
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

                                entity.setDeltaMovement(d1, d2, d3);
                                break;
                            }
                        }
                    }

                    double d4 = 0.0D;

                    for(AABB aabb2 : list1) {
                        AABB aabb1 = PistonMath.getMovementArea(moveByPositionAndProgress(p_155912_, aabb2, p_155914_), direction, d0);
                        AABB aabb3 = entity.getBoundingBox();
                        if (aabb1.intersects(aabb3)) {
                            d4 = Math.max(d4, getMovement(aabb1, direction, aabb3));
                            if (d4 >= d0) {
                                break;
                            }
                        }
                    }

                    if (!(d4 <= 0.0D)) {
                        d4 = Math.min(d4, d0) + 0.01D;
                        moveEntityByPiston(direction, entity, d4, direction);
                        if (!p_155914_.extending && p_155914_.isSourcePiston) {
                            fixEntityWithinPistonBase(p_155912_, entity, direction, d0);
                        }
                    }
                }
            }
        }
    }

    private static void moveEntityByPiston(Direction p_60372_, Entity p_60373_, double p_60374_, Direction p_60375_) {
        NOCLIP.set(p_60372_);
        p_60373_.move(MoverType.PISTON, new Vec3(p_60374_ * (double)p_60375_.getStepX(), p_60374_ * (double)p_60375_.getStepY(), p_60374_ * (double)p_60375_.getStepZ()));
        NOCLIP.set((Direction)null);
    }

    private static void moveStuckEntities(Level level, BlockPos blockPos, float float1, PistonMovingBlockEntity pistonMovingBlockEntity) {
        if (pistonMovingBlockEntity.isStickyForEntities()) {
            Direction direction = pistonMovingBlockEntity.getMovementDirection();
            if (direction.getAxis().isHorizontal()) {
                double d0 = pistonMovingBlockEntity.movedState.getCollisionShape(level, blockPos).max(Direction.Axis.Y);
                AABB aabb = moveByPositionAndProgress(blockPos, new AABB(0.0D, d0, 0.0D, 1.0D, 1.5000000999999998D, 1.0D), pistonMovingBlockEntity);
                double d1 = (double)(float1 - pistonMovingBlockEntity.progress);

                for(Entity entity : level.getEntities((Entity)null, aabb, (p_60384_) -> {
                    return matchesStickyCritera(aabb, p_60384_);
                })) {
                    moveEntityByPiston(direction, entity, d1, direction);
                }

            }
        }
    }

    private static boolean matchesStickyCritera(AABB aabb, Entity entity) {
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

    private static AABB moveByPositionAndProgress(BlockPos blockPos, AABB aabb, PistonMovingBlockEntity pistonMovingBlockEntity) {
        double d0 = (double)pistonMovingBlockEntity.getExtendedProgress(pistonMovingBlockEntity.progress);
        return aabb.move((double)blockPos.getX() + d0 * (double)pistonMovingBlockEntity.direction.getStepX(), (double)blockPos.getY() + d0 * (double)pistonMovingBlockEntity.direction.getStepY(), (double)blockPos.getZ() + d0 * (double)pistonMovingBlockEntity.direction.getStepZ());
    }

    private static void fixEntityWithinPistonBase(BlockPos blockPos, Entity entity, Direction direction, double double1) {
        AABB aabb = entity.getBoundingBox();
        AABB aabb1 = Shapes.block().bounds().move(blockPos);
        if (aabb.intersects(aabb1)) {
            Direction direction1 = direction.getOpposite();
            double d0 = getMovement(aabb1, direction1, aabb) + 0.01D;
            double d1 = getMovement(aabb1, direction1, aabb.intersect(aabb1)) + 0.01D;
            if (Math.abs(d0 - d1) < 0.01D) {
                d0 = Math.min(d0, double1) + 0.01D;
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

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PistonMovingBlockEntity pistonMovingBlockEntity) {
        pistonMovingBlockEntity.lastTicked = level.getGameTime();
        pistonMovingBlockEntity.progressO = pistonMovingBlockEntity.progress;
        if (pistonMovingBlockEntity.progressO >= 1.0F) {
            if (level.isClientSide && pistonMovingBlockEntity.deathTicks < 5) {
                ++pistonMovingBlockEntity.deathTicks;
            } else {
                level.removeBlockEntity(blockPos);
                pistonMovingBlockEntity.setRemoved();
                if (level.getBlockState(blockPos).is(ModBlocks.MOVING_PISTON.get())) {
                    BlockState blockstate = Block.updateFromNeighbourShapes(pistonMovingBlockEntity.movedState, level, blockPos);
                    if (blockstate.isAir()) {
                        level.setBlock(blockPos, pistonMovingBlockEntity.movedState, 84);
                        Block.updateOrDestroy(pistonMovingBlockEntity.movedState, blockstate, level, blockPos, 3);
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
            float f = pistonMovingBlockEntity.progress + 0.5F;
            moveCollidedEntities(level, blockPos, f, pistonMovingBlockEntity);
            moveStuckEntities(level, blockPos, f, pistonMovingBlockEntity);
            pistonMovingBlockEntity.progress = f;
            if (pistonMovingBlockEntity.progress >= 1.0F) {
                pistonMovingBlockEntity.progress = 1.0F;
            }

        }
    }

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
