package com.nosiphus.yogmod.world.level.block;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class YogifierBlock extends Block {

    private static final Component CONTAINER_TITLE = Component.translatable("container.yogmod.yogifier");

    public YogifierBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /*
    public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos blockPos) {
        return new SimpleMenuProvider((index, inventory, player) -> {
            return new YogifierMenu(index, inventory, ContainerLevelAccess.create(level, blockPos));
        }, CONTAINER_TITLE);
    }

    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(blockState.getMenuProvider(level, blockPos));
            //player.awardStat(Stats.INTERACT_WITH_SMITHING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

     */

}
