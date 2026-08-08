package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.inventory.YogifierMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class YogifierBlock extends Block {

    public static final MapCodec<YogifierBlock> CODEC = simpleCodec(YogifierBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.yogmod.yogifier");

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public YogifierBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos blockPos) {
        return new SimpleMenuProvider((index, inventory, player) ->
                new YogifierMenu(index, inventory, ContainerLevelAccess.create(level, blockPos)), CONTAINER_TITLE);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(blockState.getMenuProvider(level, blockPos));
            return InteractionResult.CONSUME;
        }
    }
}