package com.nosiphus.yogmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBlock extends Block {
    public TableBlock(Properties properties) {
        super(properties);
    }
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12, 16);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPE;
    }

}