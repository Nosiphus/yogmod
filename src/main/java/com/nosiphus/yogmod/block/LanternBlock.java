package com.nosiphus.yogmod.block;

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

    protected static final int AABB_STANDING_OFFSET = 2;
    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public LanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return AABB;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        return direction == Direction.DOWN && !this.canSurvive(state, levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return canSupportCenter(levelReader, pos.below(), Direction.UP);
    }

}
