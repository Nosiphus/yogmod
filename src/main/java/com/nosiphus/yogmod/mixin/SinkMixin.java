package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.core.SinkInteraction;
import net.minecraft.server.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class SinkMixin {

    @Inject(method = "bootStrap()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/cauldron/CauldronInteraction;bootStrap()V", shift = At.Shift.AFTER))
    private static void onBootStrap(CallbackInfo ci) {
        SinkInteraction.bootStrap();
    }

}