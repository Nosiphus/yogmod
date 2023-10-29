package com.nosiphus.yogmod.blockentity;

import com.nosiphus.yogmod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class YogSignBlockEntity extends SignBlockEntity {

    public YogSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.YOG_SIGN.get();
    }

}
