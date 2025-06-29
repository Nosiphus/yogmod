package com.nosiphus.yogmod;

import com.nosiphus.yogmod.block.ModBlock;
import com.nosiphus.yogmod.converter.NCBConverter;
import com.nosiphus.yogmod.item.ModItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = YogMod.MODID, version = YogMod.VERSION)
public class YogMod
{
    public static final String MODID = "yogmod";
    public static final String VERSION = "2025.06.29";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ModBlock.registerBlocks();
        ModItem.registerItems();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new NCBConverter());
        if (event.getSide().isClient()) {
            ModBlock.registerRenderers();
        }
    }

}
