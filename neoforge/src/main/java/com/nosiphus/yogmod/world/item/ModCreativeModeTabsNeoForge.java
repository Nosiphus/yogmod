package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.YogMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabsNeoForge {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YogMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> YOGMOD =
            CREATIVE_TABS.register("yogmod", () -> ModCreativeModeTabs.createTabBuilder().build());
}