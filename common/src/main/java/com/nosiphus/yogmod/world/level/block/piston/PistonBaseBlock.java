package com.nosiphus.yogmod.world.level.block.piston;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Map;

public class PistonBaseBlock extends DirectionalBlock {

    public static final MapCodec<PistonBaseBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.BOOL.fieldOf("sticky").forGetter(piston -> piston.isSticky),
                    propertiesCodec()
            ).apply(instance, PistonBaseBlock::new)
    );

    public static final BooleanProperty EXTENDED = BlockStateProperties.EXTENDED;
    public static final int TRIGGER_EXTEND = 0;
    public static final int TRIGGER_CONTRACT = 1;
    public static final int TRIGGER_DROP = 2;
    public static final float PLATFORM_THICKNESS = 4.0F;

    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    private final boolean isSticky;

    @Override
    public MapCodec<PistonBaseBlock> codec() {
        return CODEC;
    }

    public PistonBaseBlock(boolean isSticky, Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(EXTENDED, false));
        this.isSticky = isSticky;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.getValue(EXTENDED)) {
            return switch (blockState.getValue(FACING)) {
                case DOWN -> DOWN_AABB;
                case UP -> UP_AABB;
                case NORTH -> NORTH_AABB;
                case SOUTH -> SOUTH_AABB;
                case WEST -> WEST_AABB;
                case EAST -> EAST_AABB;
            };
        } else {
            return Shapes.block();
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (!level.isClientSide) {
            this.checkIfExtend(level, blockPos, blockState);
        }
    }

    @Override
    protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            this.checkIfExtend(level, blockPos, blockState);
        }
    }

    @Override
    protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(blockState.getBlock()) && !level.isClientSide && level.getBlockEntity(blockPos) == null) {
            this.checkIfExtend(level, blockPos, blockState);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getNearestLookingDirection().getOpposite()).setValue(EXTENDED, false);
    }

    private void checkIfExtend(Level level, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        boolean hasSignal = this.getNeighborSignal(level, blockPos, direction);

        if (hasSignal && !blockState.getValue(EXTENDED)) {
            if ((new PistonStructureResolver(level, blockPos, direction, true)).resolve()) {
                level.blockEvent(blockPos, this, TRIGGER_EXTEND, direction.get3DDataValue());
            }
        } else if (!hasSignal && blockState.getValue(EXTENDED)) {
            BlockPos relativePos = blockPos.relative(direction, 2);
            BlockState relativeState = level.getBlockState(relativePos);
            int eventType = TRIGGER_CONTRACT;

            if (relativeState.is(ModBlocks.MOVING_PISTON.get()) && relativeState.getValue(FACING) == direction) {
                BlockEntity blockEntity = level.getBlockEntity(relativePos);
                if (blockEntity instanceof PistonMovingBlockEntity pistonMovingBlockEntity) {
                    if (pistonMovingBlockEntity.isExtending() && (pistonMovingBlockEntity.getProgress(0.0F) < 0.5F || level.getGameTime() == pistonMovingBlockEntity.getLastTicked() || ((ServerLevel) level).isHandlingTick())) {
                        eventType = TRIGGER_DROP;
                    }
                }
            }

            level.blockEvent(blockPos, this, eventType, direction.get3DDataValue());
        }
    }

    private boolean getNeighborSignal(Level level, BlockPos blockPos, Direction direction) {
        for (Direction side : Direction.values()) {
            if (side != direction && level.hasSignal(blockPos.relative(side), side)) {
                return true;
            }
        }

        if (level.hasSignal(blockPos, Direction.DOWN)) {
            return true;
        } else {
            BlockPos abovePos = blockPos.above();
            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN && level.hasSignal(abovePos.relative(side), side)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    protected boolean triggerEvent(BlockState blockState, Level level, BlockPos blockPos, int id, int param) {
        Direction direction = blockState.getValue(FACING);
        if (!level.isClientSide) {
            boolean hasSignal = this.getNeighborSignal(level, blockPos, direction);
            if (hasSignal && (id == TRIGGER_CONTRACT || id == TRIGGER_DROP)) {
                level.setBlock(blockPos, blockState.setValue(EXTENDED, true), 2);
                return false;
            }

            if (!hasSignal && id == TRIGGER_EXTEND) {
                return false;
            }
        }

        if (id == TRIGGER_EXTEND) {
            if (!this.moveBlocks(level, blockPos, direction, true)) {
                return false;
            }

            level.setBlock(blockPos, blockState.setValue(EXTENDED, true), 67);
            level.playSound(null, blockPos, SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.25F + 0.6F);
            level.gameEvent(null, GameEvent.BLOCK_ACTIVATE, blockPos);
        } else if (id == TRIGGER_CONTRACT || id == TRIGGER_DROP) {
            BlockEntity frontBlockEntity = level.getBlockEntity(blockPos.relative(direction));
            if (frontBlockEntity instanceof PistonMovingBlockEntity pistonMovingBlockEntity) {
                pistonMovingBlockEntity.finalTick();
            }

            BlockState movingPistonState = ModBlocks.MOVING_PISTON.get().defaultBlockState()
                    .setValue(MovingPistonBlock.FACING, direction)
                    .setValue(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);

            level.setBlock(blockPos, movingPistonState, 20);
            level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(blockPos, movingPistonState, this.defaultBlockState().setValue(FACING, Direction.from3DDataValue(param & 7)), direction, false, true));
            level.blockUpdated(blockPos, movingPistonState.getBlock());
            movingPistonState.updateNeighbourShapes(level, blockPos, 2);

            if (this.isSticky) {
                BlockPos pullPos = blockPos.offset(direction.getStepX() * 2, direction.getStepY() * 2, direction.getStepZ() * 2);
                BlockState pullState = level.getBlockState(pullPos);
                boolean handled = false;

                if (pullState.is(ModBlocks.MOVING_PISTON.get())) {
                    BlockEntity blockEntity = level.getBlockEntity(pullPos);
                    if (blockEntity instanceof PistonMovingBlockEntity pistonMovingBlockEntity) {
                        if (pistonMovingBlockEntity.getDirection() == direction && pistonMovingBlockEntity.isExtending()) {
                            pistonMovingBlockEntity.finalTick();
                            handled = true;
                        }
                    }
                }

                if (!handled) {
                    if (id != TRIGGER_CONTRACT || pullState.isAir() || !isPushable(pullState, level, pullPos, direction.getOpposite(), false, direction) || (pullState.getPistonPushReaction() != PushReaction.NORMAL && !pullState.is(ModBlocks.PISTON.get()) && !pullState.is(ModBlocks.STICKY_PISTON.get()))) {
                        level.removeBlock(blockPos.relative(direction), false);
                    } else {
                        this.moveBlocks(level, blockPos, direction, false);
                    }
                }
            } else {
                level.removeBlock(blockPos.relative(direction), false);
            }

            level.playSound(null, blockPos, SoundEvents.PISTON_CONTRACT, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.6F);
            level.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, blockPos);
        }

        return true;
    }

    public static boolean isPushable(BlockState blockState, Level level, BlockPos blockPos, Direction direction, boolean allowDestroy, Direction pistonDirection) {
        if (blockPos.getY() >= level.getMinBuildHeight() && blockPos.getY() <= level.getMaxBuildHeight() - 1 && level.getWorldBorder().isWithinBounds(blockPos)) {
            if (blockState.isAir()) {
                return true;
            } else if (!blockState.is(Blocks.OBSIDIAN) && !blockState.is(Blocks.CRYING_OBSIDIAN) && !blockState.is(Blocks.RESPAWN_ANCHOR) && !blockState.is(Blocks.REINFORCED_DEEPSLATE)) {
                if (direction == Direction.DOWN && blockPos.getY() == level.getMinBuildHeight()) {
                    return false;
                } else if (direction == Direction.UP && blockPos.getY() == level.getMaxBuildHeight() - 1) {
                    return false;
                } else {
                    if (!blockState.is(ModBlocks.PISTON.get()) && !blockState.is(ModBlocks.STICKY_PISTON.get())) {
                        if (blockState.getDestroySpeed(level, blockPos) == -1.0F) {
                            return false;
                        }

                        switch (blockState.getPistonPushReaction()) {
                            case BLOCK:
                                return false;
                            case DESTROY:
                                return allowDestroy;
                            case PUSH_ONLY:
                                return direction == pistonDirection;
                        }
                    } else if (blockState.getValue(EXTENDED)) {
                        return false;
                    }

                    return !blockState.hasBlockEntity();
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean moveBlocks(Level level, BlockPos blockPos, Direction direction, boolean extending) {
        BlockPos headPos = blockPos.relative(direction);
        if (!extending && level.getBlockState(headPos).is(ModBlocks.PISTON_HEAD.get())) {
            level.setBlock(headPos, Blocks.AIR.defaultBlockState(), 20);
        }

        PistonStructureResolver structureResolver = new PistonStructureResolver(level, blockPos, direction, extending);
        if (!structureResolver.resolve()) {
            return false;
        } else {
            Map<BlockPos, BlockState> map = Maps.newHashMap();
            List<BlockPos> toPush = structureResolver.getToPush();
            List<BlockState> pushedStates = Lists.newArrayList();

            for (BlockPos pos : toPush) {
                BlockState state = level.getBlockState(pos);
                pushedStates.add(state);
                map.put(pos, state);
            }

            List<BlockPos> toDestroy = structureResolver.getToDestroy();
            BlockState[] affectedStates = new BlockState[toPush.size() + toDestroy.size()];
            Direction moveDirection = extending ? direction : direction.getOpposite();
            int index = 0;

            for (int i = toDestroy.size() - 1; i >= 0; --i) {
                BlockPos destroyPos = toDestroy.get(i);
                BlockState destroyState = level.getBlockState(destroyPos);
                BlockEntity blockEntity = destroyState.hasBlockEntity() ? level.getBlockEntity(destroyPos) : null;
                dropResources(destroyState, level, destroyPos, blockEntity);
                level.setBlock(destroyPos, Blocks.AIR.defaultBlockState(), 18);
                level.gameEvent(GameEvent.BLOCK_DESTROY, destroyPos, GameEvent.Context.of(destroyState));
                if (!destroyState.is(BlockTags.FIRE)) {
                    level.addDestroyBlockEffect(destroyPos, destroyState);
                }

                affectedStates[index++] = destroyState;
            }

            for (int i = toPush.size() - 1; i >= 0; --i) {
                BlockPos pushPos = toPush.get(i);
                BlockState pushState = level.getBlockState(pushPos);
                pushPos = pushPos.relative(moveDirection);
                map.remove(pushPos);
                BlockState movingPistonState = ModBlocks.MOVING_PISTON.get().defaultBlockState().setValue(FACING, direction);
                level.setBlock(pushPos, movingPistonState, 68);
                level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(pushPos, movingPistonState, pushedStates.get(i), direction, extending, false));
                affectedStates[index++] = pushState;
            }

            if (extending) {
                PistonType pistonType = this.isSticky ? PistonType.STICKY : PistonType.DEFAULT;
                BlockState headState = ModBlocks.PISTON_HEAD.get().defaultBlockState().setValue(PistonHeadBlock.FACING, direction).setValue(PistonHeadBlock.TYPE, pistonType);
                BlockState movingPistonState = ModBlocks.MOVING_PISTON.get().defaultBlockState().setValue(MovingPistonBlock.FACING, direction).setValue(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);
                map.remove(headPos);
                level.setBlock(headPos, movingPistonState, 68);
                level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(headPos, movingPistonState, headState, direction, true, true));
            }

            BlockState airState = Blocks.AIR.defaultBlockState();
            for (BlockPos emptyPos : map.keySet()) {
                level.setBlock(emptyPos, airState, 82);
            }

            for (Map.Entry<BlockPos, BlockState> entry : map.entrySet()) {
                BlockPos pos = entry.getKey();
                BlockState state = entry.getValue();
                state.updateIndirectNeighbourShapes(level, pos, 2);
                airState.updateNeighbourShapes(level, pos, 2);
                airState.updateIndirectNeighbourShapes(level, pos, 2);
            }

            index = 0;
            for (int i = toDestroy.size() - 1; i >= 0; --i) {
                BlockState state = affectedStates[index++];
                BlockPos pos = toDestroy.get(i);
                state.updateIndirectNeighbourShapes(level, pos, 2);
                level.updateNeighborsAt(pos, state.getBlock());
            }

            for (int i = toPush.size() - 1; i >= 0; --i) {
                level.updateNeighborsAt(toPush.get(i), affectedStates[index++].getBlock());
            }

            if (extending) {
                level.updateNeighborsAt(headPos, ModBlocks.PISTON_HEAD.get());
            }

            return true;
        }
    }

    @Override
    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState rotate(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos, Rotation rotation) {
        return blockState.getValue(EXTENDED) ? blockState : super.rotate(blockState, rotation);
    }

    @Override
    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(FACING, EXTENDED);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState blockState) {
        return blockState.getValue(EXTENDED);
    }

    @Override
    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }
}