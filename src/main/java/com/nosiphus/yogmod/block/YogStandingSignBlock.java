package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.blockentity.YogSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class YogStandingSignBlock extends StandingSignBlock {

    public YogStandingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new YogSignBlockEntity(blockPos, blockState);
    }

}
