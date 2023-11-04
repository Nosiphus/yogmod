package com.nosiphus.yogmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class WireDiodeBlock extends DiodeBlock {
    public static final BooleanProperty LOCKED = BlockStateProperties.LOCKED;
    public static final IntegerProperty DELAY = BlockStateProperties.DELAY;

    public WireDiodeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(DELAY, Integer.valueOf(1)).setValue(LOCKED, Boolean.valueOf(false)).setValue(POWERED, Boolean.valueOf(false)));
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            level.setBlock(pos, state.cycle(DELAY), 3);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    protected int getDelay(BlockState state) { return state.getValue(DELAY) * 2; }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockstate = super.getStateForPlacement(blockPlaceContext);
        return blockstate.setValue(LOCKED, Boolean.valueOf(this.isLocked(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), blockstate)));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        return !levelAccessor.isClientSide() && direction.getAxis() != state.getValue(FACING).getAxis() ? state.setValue(LOCKED, Boolean.valueOf(this.isLocked(levelAccessor, pos, state))) : super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    public boolean isLocked(LevelReader levelReader, BlockPos pos, BlockState state) {
        return this.getAlternateSignal(levelReader, pos, state) > 0;
    }

    protected boolean isAlternateInput(BlockState state) {
        return isDiode(state);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (state.getValue(POWERED)) {
            Direction direction = state.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            double d1 = (double)pos.getY() + 0.4D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            double d2 = (double)pos.getZ() + 0.5D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            float f = -5.0F;
            if (randomSource.nextBoolean()) {
                f = (float)(state.getValue(DELAY) * 2 - 1);
            }

            f /= 16.0F;
            double d3 = (double)(f * (float)direction.getStepX());
            double d4 = (double)(f * (float)direction.getStepZ());
            level.addParticle(DustParticleOptions.REDSTONE, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING, DELAY, LOCKED, POWERED);
    }

}
