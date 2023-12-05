package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class MonitorBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    @Nullable
    private BlockPattern scrubberBotBase;
    @Nullable
    private BlockPattern scrubberBotFull;
    private static final Predicate<BlockState> MONITORS_PREDICATE = (blockState) -> {
        return blockState != null && (blockState.is(ModBlocks.MONITOR.get()) || blockState.is(ModBlocks.LIT_MONITOR.get()));
    };

    public MonitorBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean canSpawn) {
        if(!blockState1.is(blockState.getBlock())) {
            this.trySpawnScrubberBot(level, blockPos);
        }
    }

    public boolean canSpawnScrubberBot(LevelReader levelReader, BlockPos blockPos) {
        return this.getOrCreateScrubberBotBase().find(levelReader, blockPos) != null;
    }

    private void trySpawnScrubberBot(Level level, BlockPos blockPos) {
        BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch = this.getOrCreateScrubberBotFull().find(level, blockPos);
        if (blockpattern$blockpatternmatch != null) {
            for (int i = 0; i< this.getOrCreateScrubberBotFull().getHeight(); ++i) {
                BlockInWorld blockInWorld = blockpattern$blockpatternmatch.getBlock(0, i, 0);
                level.setBlock(blockInWorld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                level.levelEvent(2001, blockInWorld.getPos(), Block.getId(blockInWorld.getState()));
            }
            ScrubberBot scrubberBot = ModEntityType.SCRUBBER_BOT.get().create(level);
            BlockPos blockPos1 = blockpattern$blockpatternmatch.getBlock(0, 2, 0).getPos();
            scrubberBot.moveTo((double) blockPos1.getX() + 0.5D, (double) blockPos1.getY() + 0.5D, (double) blockPos1.getZ() + 0.5D, 0.0F, 0.0F);
            level.addFreshEntity(scrubberBot);

            for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, scrubberBot.getBoundingBox().inflate(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, scrubberBot);
            }

            for (int l = 0; l < this.getOrCreateScrubberBotFull().getHeight(); ++l) {
                BlockInWorld blockInWorld1 = blockpattern$blockpatternmatch.getBlock(0, l , 0);
                level.blockUpdated(blockInWorld1.getPos(), Blocks.AIR);
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING);
    }

    private BlockPattern getOrCreateScrubberBotBase() {
        if (this.scrubberBotBase == null) {
            this.scrubberBotBase = BlockPatternBuilder.start().aisle(" ", "P", "R")
                    .where('P', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.PISTON.get())))
                    .where('R', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.RIVETED_STEEL.get())))
                    .build();
        }
        return this.scrubberBotBase;
    }

    private BlockPattern getOrCreateScrubberBotFull() {
        if (this.scrubberBotFull == null) {
            this.scrubberBotFull = BlockPatternBuilder.start().aisle("M", "P", "R")
                    .where('M', BlockInWorld.hasState(MONITORS_PREDICATE))
                    .where('P', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.PISTON.get())))
                    .where('R', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.RIVETED_STEEL.get())))
                    .build();
        }
        return this.scrubberBotFull;
    }

}
