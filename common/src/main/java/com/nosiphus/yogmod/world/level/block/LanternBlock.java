package com.nosiphus.yogmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LanternBlock extends Block {

    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public LanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return AABB;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, levelAccessor, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, direction, neighborState, levelAccessor, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return canSupportCenter(levelReader, pos.below(), Direction.UP);
    }
}