package com.nosiphus.yogmod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;

public class ModBlock {

    public static Block AIR_VENT;
    
    public static void registerBlocks() {

        AIR_VENT = new YogBlockCompressed(MapColor.ironColor).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("air_vent").setBlockTextureName("yogmod:air_vent");
        GameRegistry.registerBlock(AIR_VENT, AIR_VENT.getUnlocalizedName().substring(5));

    }


}
