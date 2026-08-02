package com.nosiphus.yogmod;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = YogMod.MODID, version = YogMod.VERSION)
public class YogMod
{
    public static final String MODID = "yogmod";
    public static final String VERSION = "2026.08.15";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Block.dirt.getUnlocalizedName());
    }
}
