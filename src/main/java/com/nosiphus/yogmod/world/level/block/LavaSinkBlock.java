package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.core.SinkInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LavaSinkBlock extends AbstractSinkBlock {

    public LavaSinkBlock(BlockBehaviour.Properties properties) {
        super(properties, SinkInteraction.LAVA);
    }

    protected double getContentHeight(BlockState blockState) {
        return 0.9375D;
    }

    public boolean isFull(BlockState blockState) {
        return true;
    }

    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (this.isEntityInsideContent(blockState, blockPos, entity)) {
            entity.lavaHurt();
        }
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return 3;
    }

}
