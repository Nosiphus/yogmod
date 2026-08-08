package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
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

    public static final MapCodec<WireDiodeBlock> CODEC = simpleCodec(WireDiodeBlock::new);
    public static final BooleanProperty LOCKED = BlockStateProperties.LOCKED;
    public static final IntegerProperty DELAY = BlockStateProperties.DELAY;

    @Override
    protected MapCodec<? extends DiodeBlock> codec() {
        return CODEC;
    }

    public WireDiodeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(DELAY, 1)
                .setValue(LOCKED, false)
                .setValue(POWERED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            level.setBlock(pos, state.cycle(DELAY), 3);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    protected int getDelay(BlockState state) {
        return state.getValue(DELAY) * 2;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState defaultState = super.getStateForPlacement(blockPlaceContext);
        return defaultState.setValue(LOCKED, this.isLocked(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), defaultState));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        return !levelAccessor.isClientSide() && direction.getAxis() != state.getValue(FACING).getAxis()
                ? state.setValue(LOCKED, this.isLocked(levelAccessor, pos, state))
                : super.updateShape(state, direction, neighborState, levelAccessor, pos, neighborPos);
    }

    @Override
    public boolean isLocked(LevelReader levelReader, BlockPos pos, BlockState state) {
        return this.getAlternateSignal(levelReader, pos, state) > 0;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (state.getValue(POWERED)) {
            Direction direction = state.getValue(FACING);
            double x = (double) pos.getX() + 0.5D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            double y = (double) pos.getY() + 0.4D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            double z = (double) pos.getZ() + 0.5D + (randomSource.nextDouble() - 0.5D) * 0.2D;

            float offset = -5.0F;
            if (randomSource.nextBoolean()) {
                offset = (float) (state.getValue(DELAY) * 2 - 1);
            }
            offset /= 16.0F;

            double dx = offset * direction.getStepX();
            double dz = offset * direction.getStepZ();
            level.addParticle(DustParticleOptions.REDSTONE, x + dx, y, z + dz, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING, DELAY, LOCKED, POWERED);
    }
}