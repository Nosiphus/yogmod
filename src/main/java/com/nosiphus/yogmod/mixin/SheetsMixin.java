package com.nosiphus.yogmod.mixin;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static com.nosiphus.yogmod.client.renderer.ModSheets.*;

@Mixin(Sheets.class)
public class SheetsMixin {
    @Inject(method = "getAllMaterials", at = @At("TAIL"))
    private static void injected(Consumer<Material> p_110781_, CallbackInfo ci) {
        p_110781_.accept(CRATE_LOCATION);
        p_110781_.accept(CRATE_LOCATION_LEFT);
        p_110781_.accept(CRATE_LOCATION_RIGHT);
    }


}
