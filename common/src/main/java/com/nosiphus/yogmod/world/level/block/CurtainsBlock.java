package com.nosiphus.yogmod.world.level.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CurtainsBlock extends Block {

    public static final BooleanProperty UP = PipeBlock.UP;
    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;

    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream()
            .filter(entry -> entry.getKey() != Direction.DOWN)
            .collect(Util.toMap());

    private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

    private final Map<BlockState, VoxelShape> shapesCache;

    public CurtainsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false));
        this.shapesCache = ImmutableMap.copyOf(this.stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), CurtainsBlock::calculateShape)));
    }

    private static VoxelShape calculateShape(BlockState state) {
        VoxelShape voxelShape = Shapes.empty();
        if (state.getValue(UP)) {
            voxelShape = UP_AABB;
        }
        if (state.getValue(NORTH)) {
            voxelShape = Shapes.or(voxelShape, NORTH_AABB);
        }
        if (state.getValue(SOUTH)) {
            voxelShape = Shapes.or(voxelShape, SOUTH_AABB);
        }
        if (state.getValue(EAST)) {
            voxelShape = Shapes.or(voxelShape, EAST_AABB);
        }
        if (state.getValue(WEST)) {
            voxelShape = Shapes.or(voxelShape, WEST_AABB);
        }

        return voxelShape.isEmpty() ? Shapes.block() : voxelShape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return this.hasFaces(this.getUpdatedState(state, reader, pos));
    }

    private boolean hasFaces(BlockState state) {
        return this.countFaces(state) > 0;
    }

    private int countFaces(BlockState state) {
        int count = 0;
        for (BooleanProperty property : PROPERTY_BY_DIRECTION.values()) {
            if (state.getValue(property)) {
                count++;
            }
        }
        return count;
    }

    private boolean canSupportAtFace(BlockGetter getter, BlockPos pos, Direction direction) {
        if (direction == Direction.DOWN) {
            return false;
        } else {
            BlockPos relativePos = pos.relative(direction);
            if (isAcceptableNeighbour(getter, relativePos, direction)) {
                return true;
            } else if (direction.getAxis() == Direction.Axis.Y) {
                return false;
            } else {
                BooleanProperty property = PROPERTY_BY_DIRECTION.get(direction);
                BlockState aboveState = getter.getBlockState(pos.above());
                return aboveState.is(this) && aboveState.getValue(property);
            }
        }
    }

    public static boolean isAcceptableNeighbour(BlockGetter getter, BlockPos pos, Direction direction) {
        return MultifaceBlock.canAttachTo(getter, direction, pos, getter.getBlockState(pos));
    }

    private BlockState getUpdatedState(BlockState state, BlockGetter getter, BlockPos pos) {
        BlockPos abovePos = pos.above();
        if (state.getValue(UP)) {
            state = state.setValue(UP, isAcceptableNeighbour(getter, abovePos, Direction.DOWN));
        }

        BlockState aboveState = null;

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BooleanProperty property = getPropertyForFace(direction);
            if (state.getValue(property)) {
                boolean canSupport = this.canSupportAtFace(getter, pos, direction);
                if (!canSupport) {
                    if (aboveState == null) {
                        aboveState = getter.getBlockState(abovePos);
                    }
                    canSupport = aboveState.is(this) && aboveState.getValue(property);
                }
                state = state.setValue(property, canSupport);
            }
        }

        return state;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN) {
            return super.updateShape(state, direction, neighborState, levelAccessor, pos, neighborPos);
        } else {
            BlockState updatedState = this.getUpdatedState(state, levelAccessor, pos);
            return !this.hasFaces(updatedState) ? Blocks.AIR.defaultBlockState() : updatedState;
        }
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if (blockState.is(this)) {
            return this.countFaces(blockState) < PROPERTY_BY_DIRECTION.size();
        } else {
            return super.canBeReplaced(state, context);
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        boolean isCurtain = blockState.is(this);
        BlockState defaultState = isCurtain ? blockState : this.defaultBlockState();

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction != Direction.DOWN) {
                BooleanProperty property = getPropertyForFace(direction);
                boolean alreadyHasFace = isCurtain && blockState.getValue(property);
                if (!alreadyHasFace && this.canSupportAtFace(context.getLevel(), context.getClickedPos(), direction)) {
                    return defaultState.setValue(property, true);
                }
            }
        }

        return isCurtain ? defaultState : null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case CLOCKWISE_180 -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(EAST, state.getValue(WEST)).setValue(SOUTH, state.getValue(NORTH)).setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90 -> state.setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
            case CLOCKWISE_90 -> state.setValue(NORTH, state.getValue(WEST)).setValue(EAST, state.getValue(NORTH)).setValue(SOUTH, state.getValue(EAST)).setValue(WEST, state.getValue(SOUTH));
            default -> state;
        };
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case LEFT_RIGHT -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK -> state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default -> super.mirror(state, mirror);
        };
    }

    public static BooleanProperty getPropertyForFace(Direction direction) {
        return PROPERTY_BY_DIRECTION.get(direction);
    }
}