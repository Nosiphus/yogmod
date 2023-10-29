package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.blockentity.YogSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class YogWallSignBlock extends WallSignBlock {

    public YogWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new YogSignBlockEntity(blockPos, blockState);
    }

}
