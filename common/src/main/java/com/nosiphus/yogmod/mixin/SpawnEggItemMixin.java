package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnEggItem.class)
public abstract class SpawnEggItemMixin extends Item {

    @Shadow
    public abstract EntityType<?> getType(ItemStack stack);

    public SpawnEggItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void yogmod$useOnCage(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel)) {
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else {
            ItemStack itemStack = context.getItemInHand();
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = level.getBlockState(blockPos);

            if (blockState.is(ModBlocks.CAGE.get())) {
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity instanceof CageBlockEntity cageBlockEntity) {
                    EntityType<?> entityType = this.getType(itemStack);
                    cageBlockEntity.setEntityId(entityType, level.getRandom());
                    blockEntity.setChanged();
                    level.sendBlockUpdated(blockPos, blockState, blockState, 3);
                    level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockPos);
                    itemStack.shrink(1);
                    cir.setReturnValue(InteractionResult.CONSUME);
                }
            }
        }
    }
}