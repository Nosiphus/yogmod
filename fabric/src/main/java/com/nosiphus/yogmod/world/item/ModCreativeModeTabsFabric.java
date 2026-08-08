package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.YogMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ModCreativeModeTabsFabric {

    public static CreativeModeTab YOGMOD;

    public static void register() {
        YOGMOD = Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(YogMod.MOD_ID, "yogmod"),
                ModCreativeModeTabs.createTabBuilder().build()
        );
    }

}
