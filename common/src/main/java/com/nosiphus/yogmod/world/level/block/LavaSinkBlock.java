package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.core.sink.SinkInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LavaSinkBlock extends AbstractSinkBlock {

    public static final MapCodec<LavaSinkBlock> CODEC = simpleCodec(LavaSinkBlock::new);

    @Override
    public MapCodec<LavaSinkBlock> codec() {
        return CODEC;
    }

    public LavaSinkBlock(BlockBehaviour.Properties properties) {
        super(properties, SinkInteraction.LAVA);
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return 0.9375;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (this.isEntityInsideContent(state, pos, entity)) {
            entity.lavaHurt();
        }
    }
}