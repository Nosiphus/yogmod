package com.nosiphus.yogmod;

import com.nosiphus.yogmod.block.ModBlock;
import com.nosiphus.yogmod.item.ModItem;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;

@Mod(modid = YogMod.MODID, version = YogMod.VERSION)
public class YogMod
{
    public static final String MODID = "yogmod";
    public static final String VERSION = "2025.07.05";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ModBlock.registerBlocks();
        ModItem.registerItems();

    }

}
