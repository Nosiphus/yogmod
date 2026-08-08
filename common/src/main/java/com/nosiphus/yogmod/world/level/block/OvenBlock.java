package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.OvenBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class OvenBlock extends AbstractFurnaceBlock {

    public static final MapCodec<OvenBlock> CODEC = simpleCodec(OvenBlock::new);

    public OvenBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OvenBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, ModBlockEntityType.OVEN.get());
    }

    @Override
    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof OvenBlockEntity) {
            player.openMenu((MenuProvider) blockEntity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
}