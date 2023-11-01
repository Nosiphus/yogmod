package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.core.SinkInteraction;
import com.nosiphus.yogmod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class SinkBlock extends AbstractSinkBlock {

    private static final float RAIN_FILL_CHANCE = 0.05F;
    private static final float POWDER_SNOW_FILL_CHANCE = 0.1F;

    public SinkBlock(BlockBehaviour.Properties properties) {
        super(properties, SinkInteraction.EMPTY);
    }

    public boolean isFull(BlockState blockState) {
        return false;
    }

    protected static boolean shouldHandlePrecipitation(Level level, Biome.Precipitation biomePrecipitation) {
        if (biomePrecipitation == Biome.Precipitation.RAIN) {
            return level.getRandom().nextFloat() < 0.05F;
        } else if (biomePrecipitation == Biome.Precipitation.SNOW) {
            return level.getRandom().nextFloat() < 0.1F;
        } else {
            return false;
        }
    }

    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation biomePrecipitation) {
        if (shouldHandlePrecipitation(level, biomePrecipitation)) {
            if (biomePrecipitation == Biome.Precipitation.RAIN) {
                level.setBlockAndUpdate(blockPos, ModBlocks.WATER_SINK.get().defaultBlockState());
                level.gameEvent((Entity) null, GameEvent.BLOCK_CHANGE, blockPos);
            } else if (biomePrecipitation == Biome.Precipitation.SNOW) {
                level.setBlockAndUpdate(blockPos, ModBlocks.POWDER_SNOW_SINK.get().defaultBlockState());
                level.gameEvent((Entity) null, GameEvent.BLOCK_CHANGE, blockPos);
            }
        }
    }

    protected boolean  canReceiveStalactiteDrip(Fluid fluid) {
        return true;
    }

    protected void receiveStalactiteDrip(BlockState blockState, Level level, BlockPos blockPos, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            BlockState blockState1 = ModBlocks.WATER_SINK.get().defaultBlockState();
            level.setBlockAndUpdate(blockPos, blockState1);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState1));
            level.levelEvent(1047, blockPos, 0);
        } else if (fluid == Fluids.LAVA) {
            BlockState blockState1 = ModBlocks.LAVA_SINK.get().defaultBlockState();
            level.setBlockAndUpdate(blockPos, blockState1);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState1));
            level.levelEvent(1046, blockPos, 0);
        }
    }

}
