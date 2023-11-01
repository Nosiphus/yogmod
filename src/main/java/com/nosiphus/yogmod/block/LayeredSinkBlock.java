package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.Map;
import java.util.function.Predicate;

public class LayeredSinkBlock extends AbstractCauldronBlock {
    public static final int MIN_FILL_LEVEL = 1;
    public static final int MAX_FILL_LEVEL = 3;
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    private static final int BASE_CONTENT_HEIGHT = 6;
    private static final double HEIGHT_PER_LEVEL = 3.0D;
    public static final Predicate<Biome.Precipitation> RAIN = (biomePrecipitation) -> {
        return biomePrecipitation == Biome.Precipitation.RAIN;
    };
    public static final Predicate<Biome.Precipitation> SNOW = (biomePrecipitation) -> {
        return biomePrecipitation == Biome.Precipitation.SNOW;
    };
    private final Predicate<Biome.Precipitation> fillPredicate;

    public LayeredSinkBlock(BlockBehaviour.Properties properties, Predicate<Biome.Precipitation> biomePrecipitation, Map<Item, CauldronInteraction> itemCauldronInteractionMap) {
        super(properties, itemCauldronInteractionMap);
        this.fillPredicate = biomePrecipitation;
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

    public boolean isFull(BlockState blockState) {
        return blockState.getValue(LEVEL) == 3;
    }

    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return fluid == Fluids.WATER && this.fillPredicate == RAIN;
    }

    protected double getContentHeight(BlockState blockState) {
        return (6.0D + (double) blockState.getValue(LEVEL).intValue() * 3.0D) / 16.0D;
    }

    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (!level.isClientSide && entity.isOnFire() && this.isEntityInsideContent(blockState, blockPos, entity)) {
            entity.clearFire();
            if (entity.mayInteract(level, blockPos)) {
                this.handleEntityOnFireInside(blockState, level, blockPos);
            }
        }
    }

    protected void handleEntityOnFireInside(BlockState blockState, Level level, BlockPos blockPos) {
        lowerFillLevel(blockState, level, blockPos);
    }

    public static void lowerFillLevel(BlockState blockState, Level level, BlockPos blockPos) {
        int i = blockState.getValue(LEVEL) - 1;
        BlockState blockState1 = i == 0 ? ModBlocks.SINK.get().defaultBlockState() : blockState.setValue(LEVEL, Integer.valueOf(i));
        level.setBlockAndUpdate(blockPos, blockState1);
        level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState1));
    }

    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation biomePrecipitation) {
        if (SinkBlock.shouldHandlePrecipitation(level, biomePrecipitation) && blockState.getValue(LEVEL) != 3 && this.fillPredicate.test(biomePrecipitation)) {
            BlockState blockState1 = blockState.cycle(LEVEL);
            level.setBlockAndUpdate(blockPos, blockState1);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState1));
        }
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return blockState.getValue(LEVEL);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(LEVEL);
    }

    protected void receiveStalactiteDrip(BlockState blockState, Level level, BlockPos blockPos, Fluid fluid) {
        if (!this.isFull(blockState)) {
            BlockState blockState1 = blockState.setValue(LEVEL, Integer.valueOf(blockState.getValue(LEVEL) + 1));
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState1));
            level.levelEvent(1047, blockPos, 0);
        }
    }

}
