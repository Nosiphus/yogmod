package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.core.sink.SinkInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class SinkBlock extends AbstractSinkBlock {

    public static final MapCodec<SinkBlock> CODEC = simpleCodec(SinkBlock::new);

    @Override
    public MapCodec<SinkBlock> codec() {
        return CODEC;
    }

    public SinkBlock(BlockBehaviour.Properties properties) {
        super(properties, SinkInteraction.EMPTY);
    }

    @Override
    public boolean isFull(BlockState state) {
        return false;
    }

    protected static boolean shouldHandlePrecipitation(Level level, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return level.getRandom().nextFloat() < 0.05F;
        } else {
            return precipitation == Biome.Precipitation.SNOW && level.getRandom().nextFloat() < 0.1F;
        }
    }

    @Override
    public void handlePrecipitation(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation) {
        if (shouldHandlePrecipitation(level, precipitation)) {
            if (precipitation == Biome.Precipitation.RAIN) {
                level.setBlockAndUpdate(pos, ModBlocks.WATER_SINK.get().defaultBlockState());
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            } else if (precipitation == Biome.Precipitation.SNOW) {
                level.setBlockAndUpdate(pos, ModBlocks.POWDER_SNOW_SINK.get().defaultBlockState());
                level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            }
        }
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return true;
    }

    @Override
    protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            BlockState newState = ModBlocks.WATER_SINK.get().defaultBlockState();
            level.setBlockAndUpdate(pos, newState);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
            level.levelEvent(1047, pos, 0);
        } else if (fluid == Fluids.LAVA) {
            BlockState newState = ModBlocks.LAVA_SINK.get().defaultBlockState();
            level.setBlockAndUpdate(pos, newState);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
            level.levelEvent(1046, pos, 0);
        }
    }
}