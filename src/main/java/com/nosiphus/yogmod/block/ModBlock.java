package com.nosiphus.yogmod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;

public class ModBlock {

    //Building Blocks
    public static Block ASPHALT;
    public static Block WOODEN_BRICKS;
    public static Block WHITE_MARBLE;

    public static Block AIR_VENT;
    
    public static void registerBlocks() {

        //Building Blocks
        ASPHALT = new YogBlockStone().setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("asphalt").setBlockTextureName("yogmod:asphalt");
        GameRegistry.registerBlock(ASPHALT, ASPHALT.getUnlocalizedName().substring(5));
        WOODEN_BRICKS = new YogBlockWood().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("bricks").setBlockTextureName("yogmod:bricks");
        GameRegistry.registerBlock(WOODEN_BRICKS, WOODEN_BRICKS.getUnlocalizedName().substring(5));
        WHITE_MARBLE = new YogBlockStone().setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("white_marble").setBlockTextureName("yogmod:white_marble");
        GameRegistry.registerBlock(WHITE_MARBLE, WHITE_MARBLE.getUnlocalizedName().substring(5));

        AIR_VENT = new YogBlockCompressed(MapColor.ironColor).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("air_vent").setBlockTextureName("yogmod:air_vent");
        GameRegistry.registerBlock(AIR_VENT, AIR_VENT.getUnlocalizedName().substring(5));

    }


}
