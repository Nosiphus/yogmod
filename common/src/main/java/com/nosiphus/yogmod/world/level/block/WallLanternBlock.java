package com.nosiphus.yogmod.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallLanternBlock extends LanternBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D),
            Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D),
            Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D),
            Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)
    ));

    public WallLanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public String getDescriptionId() {
        return this.asItem().getDescriptionId();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return getShape(state);
    }

    public static VoxelShape getShape(BlockState state) {
        return AABBS.get(state.getValue(FACING));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos wallPos = pos.relative(direction.getOpposite());
        BlockState wallState = levelReader.getBlockState(wallPos);
        return wallState.isFaceSturdy(levelReader, wallPos, direction);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState defaultState = this.defaultBlockState();
        LevelReader levelReader = context.getLevel();
        BlockPos pos = context.getClickedPos();

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                Direction opposite = direction.getOpposite();
                BlockState state = defaultState.setValue(FACING, opposite);
                if (state.canSurvive(levelReader, pos)) {
                    return state;
                }
            }
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(levelAccessor, pos)
                ? Blocks.AIR.defaultBlockState()
                : state;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }
}