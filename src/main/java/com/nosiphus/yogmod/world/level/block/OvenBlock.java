package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.OvenBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
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

import javax.annotation.Nullable;

public class OvenBlock extends AbstractFurnaceBlock {

    public OvenBlock(Properties properties) {

        super(properties);

    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OvenBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, ModBlockEntityType.OVEN.get());
    }

    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof OvenBlockEntity) {
            player.openMenu((MenuProvider) blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }

}
