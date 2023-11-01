package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.core.SinkInteraction;
import com.nosiphus.yogmod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.function.Predicate;

public class PowderSnowSinkBlock extends LayeredSinkBlock {

    public PowderSnowSinkBlock(BlockBehaviour.Properties properties, Predicate<Biome.Precipitation> biomePrecipitation, Map<Item, SinkInteraction> sinkInteractionMap) {
        super(properties, biomePrecipitation, sinkInteractionMap);
    }

    protected void handleEntityOnFireInside(BlockState blockState, Level level, BlockPos blockPos) {
        lowerFillLevel(ModBlocks.WATER_SINK.get().defaultBlockState().setValue(LEVEL, blockState.getValue(LEVEL)), level, blockPos);
    }

}
