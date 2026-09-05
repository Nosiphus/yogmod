package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.RecordPlayerBlock;
import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecordItem.class)
public class RecordItemMixin extends Item {

    public RecordItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void yogmod$useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.is(ModBlocks.RECORD_PLAYER.get()) && !blockState.getValue(RecordPlayerBlock.HAS_RECORD)) {
            ItemStack itemStack = context.getItemInHand();
            if (!level.isClientSide) {
                Player player = context.getPlayer();
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity instanceof RecordPlayerBlockEntity) {
                    RecordPlayerBlockEntity recordPlayerBlockEntity = (RecordPlayerBlockEntity) blockEntity;
                    recordPlayerBlockEntity.setFirstItem(itemStack.copy());
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState));
                }
                itemStack.shrink(1);
                if (player != null) {
                    player.awardStat(Stats.PLAY_RECORD);
                }
            }
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
}