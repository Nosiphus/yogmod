package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nosiphus.yogmod.core.sink.SinkInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class LayeredSinkBlock extends AbstractSinkBlock {
    public static final MapCodec<LayeredSinkBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Biome.Precipitation.CODEC.fieldOf("precipitation").forGetter(layeredSink -> layeredSink.precipitationType),
                    SinkInteraction.CODEC.fieldOf("interactions").forGetter(layeredSink1 -> layeredSink1.interactions),
                    propertiesCodec()
                    )
                    .apply(instance, LayeredSinkBlock::new)
    );
    public static final int MIN_FILL_LEVEL = 1;
    public static final int MAX_FILL_LEVEL = 3;
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    private static final int BASE_CONTENT_HEIGHT = 6;
    private static final double HEIGHT_PER_LEVEL = 3.0;
    private final Biome.Precipitation precipitationType;

    @Override
    public MapCodec<LayeredSinkBlock> codec() { return CODEC; }

    public LayeredSinkBlock(Biome.Precipitation precipitationType, SinkInteraction.InteractionMap interactions, BlockBehaviour.Properties properties) {
        super(properties, interactions);
        this.precipitationType = precipitationType;
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(LEVEL) == 3;
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return fluid == Fluids.WATER && this.precipitationType == Biome.Precipitation.RAIN;
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return (6.0 + (double)state.getValue(LEVEL).intValue() * 3.0) / 16.0;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity.isOnFire() && this.isEntityInsideContent(state, pos, entity)) {
            entity.clearFire();
            if (entity.mayInteract(level, pos)) {
                this.handleEntityOnFireInside(state, level, pos);
            }
        }
    }

    private void handleEntityOnFireInside(BlockState state, Level level, BlockPos pos) {
        if (this.precipitationType == Biome.Precipitation.SNOW) {
            lowerFillLevel(ModBlocks.WATER_SINK.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL)), level, pos);
        } else {
            lowerFillLevel(state, level, pos);
        }
    }

    public static void lowerFillLevel(BlockState state, Level level, BlockPos pos) {
        int i = state.getValue(LEVEL) - 1;
        BlockState blockstate = i == 0 ? ModBlocks.SINK.get().defaultBlockState() : state.setValue(LEVEL, Integer.valueOf(i));
        level.setBlockAndUpdate(pos, blockstate);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
    }

    @Override
    public void handlePrecipitation(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation) {
        if (SinkBlock.shouldHandlePrecipitation(level, precipitation) && state.getValue(LEVEL) != 3 && precipitation == this.precipitationType) {
            BlockState blockstate = state.cycle(LEVEL);
            level.setBlockAndUpdate(pos, blockstate);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
        }
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LEVEL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
        if (!this.isFull(state)) {
            BlockState blockstate = state.setValue(LEVEL, Integer.valueOf(state.getValue(LEVEL) + 1));
            level.setBlockAndUpdate(pos, blockstate);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            level.levelEvent(1047, pos, 0);
        }
    }

}
