package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.RecordPlayerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RecordItem.class)
public class RecordItemMixin extends Item {
    public RecordItemMixin(Properties properties) {
        super(properties);
    }

    /**
     * @author seancrain
     * @reason Discs can only be placed in normal jukeboxes. This replaces the method to allow record players to work as well.
     */
    @Overwrite
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.is(Blocks.JUKEBOX) && !blockState.getValue(JukeboxBlock.HAS_RECORD)) {
            ItemStack itemStack = context.getItemInHand();
            if (!level.isClientSide) {
                ((JukeboxBlock) Blocks.JUKEBOX).setRecord(context.getPlayer(), level, blockPos, blockState, itemStack);
                level.levelEvent((Player) null, 1010, blockPos, Item.getId(this));
                itemStack.shrink(1);
                Player player = context.getPlayer();
                if (player != null) {
                    player.awardStat(Stats.PLAY_RECORD);
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else if (blockState.is(ModBlocks.RECORD_PLAYER.get()) && !blockState.getValue(RecordPlayerBlock.HAS_RECORD)) {
            ItemStack itemStack = context.getItemInHand();
            if (!level.isClientSide) {
                ((RecordPlayerBlock) ModBlocks.RECORD_PLAYER.get()).setRecord(context.getPlayer(), level, blockPos, blockState, itemStack);
                level.levelEvent((Player) null, 1010, blockPos, Item.getId(this));
                itemStack.shrink(1);
                Player player = context.getPlayer();
                if (player != null) {
                    player.awardStat(Stats.PLAY_RECORD);
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
}


