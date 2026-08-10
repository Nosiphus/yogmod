package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.world.level.block.PoweredMetroVoxRailBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

    @Shadow
    public abstract void activateMinecart(int x, int y, int z, boolean powered);

    @Inject(method = "moveAlongTrack", at = @At("HEAD"))
    private void handleCustomActivatorRail(BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.getBlock() instanceof PoweredMetroVoxRailBlock rail && !rail.isPowered()) {
            boolean hasRedstonePower = state.getValue(PoweredMetroVoxRailBlock.POWERED);
            this.activateMinecart(pos.getX(), pos.getY(), pos.getZ(), hasRedstonePower);
        }
    }

    @Redirect(
            method = "moveAlongTrack",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    private boolean redirectIsPoweredRail(BlockState state, Block targetBlock) {
        if (targetBlock == Blocks.POWERED_RAIL && state.getBlock() instanceof PoweredMetroVoxRailBlock rail) {
            return rail.isPowered();
        }
        return state.is(targetBlock);
    }

}