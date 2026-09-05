package com.nosiphus.yogmod.mixin;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Sheets.class)
public interface SheetsAccessor {

    @Accessor("SIGN_MATERIALS")
    static Map<WoodType, Material> getSignMaterials() {
        throw new AssertionError();
    }
}