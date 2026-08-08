package com.nosiphus.yogmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FinialBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public FinialBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPE;
    }
}