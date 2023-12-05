package com.nosiphus.yogmod.block;

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

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CurtainsBlock extends Block implements net.minecraftforge.common.IForgeShearable {

    public static final BooleanProperty UP = PipeBlock.UP;
    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((property) -> {
        return property.getKey() != Direction.DOWN;
    }).collect(Util.toMap());
    protected static final float AABB_OFFSET = 1.0F;
    private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private final Map<BlockState, VoxelShape> shapesCache;

    public CurtainsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(UP, Boolean.valueOf(false)).setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)));
        this.shapesCache = ImmutableMap.copyOf(this.stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), CurtainsBlock::calculateShape)));
    }

    private static VoxelShape calculateShape(BlockState state) {
        VoxelShape voxelshape = Shapes.empty();
        if (state.getValue(UP)) {
            voxelshape = UP_AABB;
        }

        if (state.getValue(NORTH)) {
            voxelshape = Shapes.or(voxelshape, NORTH_AABB);
        }

        if (state.getValue(SOUTH)) {
            voxelshape = Shapes.or(voxelshape, SOUTH_AABB);
        }

        if (state.getValue(EAST)) {
            voxelshape = Shapes.or(voxelshape, EAST_AABB);
        }

        if (state.getValue(WEST)) {
            voxelshape = Shapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape.isEmpty() ? Shapes.block() : voxelshape;
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state);
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return this.hasFaces(this.getUpdatedState(state, reader, pos));
    }

    private boolean hasFaces(BlockState state) {
        return this.countFaces(state) > 0;
    }

    private int countFaces(BlockState state) {
        int i = 0;

        for(BooleanProperty booleanproperty : PROPERTY_BY_DIRECTION.values()) {
            if (state.getValue(booleanproperty)) {
                ++i;
            }
        }

        return i;
    }

    private boolean canSupportAtFace(BlockGetter getter, BlockPos pos, Direction direction) {
        if (direction == Direction.DOWN) {
            return false;
        } else {
            BlockPos blockpos = pos.relative(direction);
            if (isAcceptableNeighbour(getter, blockpos, direction)) {
                return true;
            } else if (direction.getAxis() == Direction.Axis.Y) {
                return false;
            } else {
                BooleanProperty booleanproperty = PROPERTY_BY_DIRECTION.get(direction);
                BlockState blockstate = getter.getBlockState(pos.above());
                return blockstate.is(this) && blockstate.getValue(booleanproperty);
            }
        }
    }

    public static boolean isAcceptableNeighbour(BlockGetter getter, BlockPos pos, Direction direction) {
        return MultifaceBlock.canAttachTo(getter, direction, pos, getter.getBlockState(pos));
    }

    private BlockState getUpdatedState(BlockState state, BlockGetter getter, BlockPos pos) {
        BlockPos blockpos = pos.above();
        if (state.getValue(UP)) {
            state = state.setValue(UP, Boolean.valueOf(isAcceptableNeighbour(getter, blockpos, Direction.DOWN)));
        }

        BlockState blockstate = null;

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BooleanProperty booleanproperty = getPropertyForFace(direction);
            if (state.getValue(booleanproperty)) {
                boolean flag = this.canSupportAtFace(getter, pos, direction);
                if (!flag) {
                    if (blockstate == null) {
                        blockstate = getter.getBlockState(blockpos);
                    }

                    flag = blockstate.is(this) && blockstate.getValue(booleanproperty);
                }

                state = state.setValue(booleanproperty, Boolean.valueOf(flag));
            }
        }

        return state;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.DOWN) {
            return super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
        } else {
            BlockState blockstate = this.getUpdatedState(state, levelAccessor, pos);
            return !this.hasFaces(blockstate) ? Blocks.AIR.defaultBlockState() : blockstate;
        }
    }

    private BlockState copyRandomFaces(BlockState state, BlockState state1, RandomSource randomSource) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if (randomSource.nextBoolean()) {
                BooleanProperty booleanproperty = getPropertyForFace(direction);
                if (state.getValue(booleanproperty)) {
                    state1 = state1.setValue(booleanproperty, Boolean.valueOf(true));
                }
            }
        }

        return state1;
    }

    private boolean hasHorizontalConnection(BlockState state) {
        return state.getValue(NORTH) || state.getValue(EAST) || state.getValue(SOUTH) || state.getValue(WEST);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return this.countFaces(blockstate) < PROPERTY_BY_DIRECTION.size();
        } else {
            return super.canBeReplaced(state, context);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        boolean flag = blockstate.is(this);
        BlockState blockstate1 = flag ? blockstate : this.defaultBlockState();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction != Direction.DOWN) {
                BooleanProperty booleanproperty = getPropertyForFace(direction);
                boolean flag1 = flag && blockstate.getValue(booleanproperty);
                if (!flag1 && this.canSupportAtFace(context.getLevel(), context.getClickedPos(), direction)) {
                    return blockstate1.setValue(booleanproperty, Boolean.valueOf(true));
                }
            }
        }

        return flag ? blockstate1 : null;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, NORTH, EAST, SOUTH, WEST);
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                return state.setValue(NORTH, state.getValue(SOUTH)).setValue(EAST, state.getValue(WEST)).setValue(SOUTH, state.getValue(NORTH)).setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return state.setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
            case CLOCKWISE_90:
                return state.setValue(NORTH, state.getValue(WEST)).setValue(EAST, state.getValue(NORTH)).setValue(SOUTH, state.getValue(EAST)).setValue(WEST, state.getValue(SOUTH));
            default:
                return state;
        }
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT:
                return state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK:
                return state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default:
                return super.mirror(state, mirror);
        }
    }

    public static BooleanProperty getPropertyForFace(Direction direction) {
        return PROPERTY_BY_DIRECTION.get(direction);
    }

}