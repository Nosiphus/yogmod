package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.RecordPlayerBlock;
import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.Stats;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxPlayable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JukeboxPlayable.class)
public class RecordPlayerPlayableMixin {

    @Inject(method = "tryInsertIntoJukebox", at = @At("HEAD"), cancellable = true)
    private static void yogmod$tryInsertIntoRecordPlayer(Level level, BlockPos pos, ItemStack stack, Player player, CallbackInfoReturnable<ItemInteractionResult> cir) {
        JukeboxPlayable jukeboxPlayable = stack.get(DataComponents.JUKEBOX_PLAYABLE);
        if (jukeboxPlayable == null) {
            cir.setReturnValue(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
        } else {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.is(ModBlocks.RECORD_PLAYER.get()) && !blockState.getValue(RecordPlayerBlock.HAS_RECORD)) {
                if (!level.isClientSide) {
                    ItemStack itemStack = stack.consumeAndReturn(1, player);
                    if (level.getBlockEntity(pos) instanceof RecordPlayerBlockEntity recordPlayerBlockEntity) {
                        recordPlayerBlockEntity.setTheItem(itemStack);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                    }

                    player.awardStat(Stats.PLAY_RECORD);
                }

                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }
}