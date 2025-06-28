package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.item.ItemYogWoodBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class ModBlock {

    //Building Blocks
    public static Block ASPHALT;
    public static Block WOODEN_BRICKS;
    public static Block WHITE_MARBLE;
    public static Block DARK_STONE_BRICKS;
    public static Block DECORATIVE_CONCRETE;
    public static Block PILLAR;
    public static Block CIRCUITRY;
    public static Block LIMESTONE_BRICK;
    public static Block CORRUGATED_COPPER;
    public static Block IRON_STACK;
    public static Block RIVETED_STEEL;
    public static Block RUSTY_IRON;
    public static Block BLUE_PANELING;
    public static BlockGlass GLASS;
    public static Block TILE_MOSAIC;
    public static Block BEIGE_PLASTIC;
    public static Block WHITE_PLASTIC;
    public static Block ORANGE_PLASTIC;
    public static Block MAGENTA_PLASTIC;
    public static Block LIGHT_BLUE_PLASTIC;
    public static Block GOLD_FILGAREE;
    public static Block LIME_PLASTIC;
    public static Block PINK_STUCCO;
    public static Block LIGHT_GRAY_STUCCO;
    public static Block CYAN_PLASTIC;
    public static Block VIOLET_VELVET;
    public static Block BLUE_PLASTIC;
    public static Block BROWN_STUCCO;
    public static Block GREEN_PLASTIC;
    public static Block RED_PLASTIC;
    public static Block BLACK_MARBLE;
    public static Block SHALE;
    public static Block REINFORCED_PANELING;
    public static Block STRIPE;
    public static Block LINOLEUM_TILE;
    public static Block CORRUGATED_STEEL;
    public static Block CLAY_TILE;
    public static Block STORAGE_CRATE;
    public static Block PIPE;
    public static BlockSlab WOODEN_BRICK_SLAB;
    public static BlockSlab STEP_SLAB;
    public static BlockSlab ASPHALT_SLAB;
    public static BlockSlab BRICK_SLAB;
    public static BlockSlab RAW_WOOD_SLAB;
    public static BlockSlab SMOOTH_METAL_SLAB;
    public static Block STEP;
    public static Block BRICKS;
    public static Block IRON_PLATE;
    public static BlockStairs ASPHALT_STAIRS;
    public static Block WHITE_LIGHTING;
    public static Block MONITOR;
    public static Block LIT_MONITOR;
    public static Block GRAY_AZTEC;
    public static BlockSoulSand CAUTION_TAPE;
    public static Block FLUORESCENT_PANEL;
    public static Block RAW_WOOD;
    public static Block CONCRETE_BRICKS;
    public static Block STRIPE_INTERSECTION;
    public static Block PIPE_INTERSECTION;
    public static Block COUNTERTOP;
    public static Block CONCRETE;
    public static BlockStairs BRICK_STAIRS;
    public static BlockStairs RAW_WOOD_STAIRS;
    public static Block SMOOTH_METAL;
    public static BlockStairs SMOOTH_METAL_STAIRS;
    public static Block GREEN_TILES;
    public static BlockStairs WOODEN_BRICK_STAIRS;
    public static BlockStairs IRON_PLATE_STAIRS;
    public static BlockSlab IRON_PLATE_SLAB;

    //Decoration Blocks
    public static Block LANTERN;
    public static Block CRATE;
    public static Block TECH_ACCENT;
    public static Block YOGIFIER;
    public static Block OVEN;
    public static BlockLadder LADDER;
    public static Block RECORD_PLAYER;
    public static BlockFence WOODEN_BRICK_FENCE;
    public static Block SPOTLIGHT;
    public static Block MECHANICAL_CHAIN;
    public static Block MECHANICAL_VENT;
    public static BlockPane GLASS_PANE;
    public static Block CURTAINS;
    public static BlockFence SMOOTH_METAL_FENCE;
    public static Block TABLE;
    public static Block FANCY_TABLE;
    //Fridge will go here when implemented
    public static BlockWall ASPHALT_WALL;
    public static BlockWall IRON_PLATE_WALL;
    public static BlockWall BRICK_WALL;
    public static BlockWall RAW_WOOD_WALL;
    public static BlockWall SMOOTH_METAL_WALL;
    public static Block BEIGE_PLASTIC_CARPET;
    public static Block WHITE_PLASTIC_CARPET;
    public static Block ORANGE_PLASTIC_CARPET;
    public static Block MAGENTA_PLASTIC_CARPET;
    public static Block LIGHT_BLUE_PLASTIC_CARPET;
    public static Block GOLD_FILGAREE_CARPET;
    public static Block LIME_PLASTIC_CARPET;
    public static Block PINK_STUCCO_CARPET;
    public static Block LIGHT_GRAY_STUCCO_CARPET;
    public static Block CYAN_PLASTIC_CARPET;
    public static Block VIOLET_VELVET_CARPET;
    public static Block BLUE_PLASTIC_CARPET;
    public static Block BROWN_STUCCO_CARPET;
    public static Block GREEN_PLASTIC_CARPET;
    public static Block RED_PLASTIC_CARPET;
    public static Block BLACK_MARBLE_CARPET;
    //Painting will go here when implemented
    public static Block YOG_SIGN;

    //Redstone
    public static Block WIRE;
    public static Block LED;
    public static Block DIODE;
    public static Block AIR_VENT;
    public static Block MOVING_PISTON;
    public static Block PISTON;
    public static Block PISTON_HEAD;
    public static Block STICKY_PISTON;
    public static Block DISPENSER;
    public static Block CLASSIC_LEVER;
    public static Block LEVER;
    public static Block DYNAMITE;
    public static Block CONSOLE;
    public static BlockButton WOODEN_BRICK_BUTTON;
    public static BlockPressurePlate WOODEN_BRICK_PRESSURE_PLATE;
    public static BlockDoor IRON_DOOR;
    public static BlockDoor WOODEN_DOOR;
    public static BlockTrapDoor HATCH;
    public static BlockFenceGate WOODEN_BRICK_FENCE_GATE;

    //Transportation
    public static BlockRailPowered POWERED_METROVOX_RAIL;
    public static BlockRailDetector METROVOX_DETECTOR_RAIL;
    public static BlockRail METROVOX_RAIL;
    public static BlockRail METROVOX_ACTIVATOR_RAIL;

    //Miscellaneous

    //Foodstuffs

    //Tools

    //Combat

    //Brewing
    public static BlockCauldron SINK;

    //Not in YogTab
    public static Block BEIGE_WOOL;
    public static Block BEIGE_CARPET;
    
    public static void registerBlocks() {

        //Building Blocks
        ASPHALT = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("asphalt").setBlockTextureName("yogmod:asphalt");
        GameRegistry.registerBlock(ASPHALT, ASPHALT.getUnlocalizedName().substring(5));
        WOODEN_BRICKS = new YogWoodBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("wooden_bricks").setBlockTextureName("yogmod:wooden_bricks");
        GameRegistry.registerBlock(WOODEN_BRICKS, ItemYogWoodBlock.class, WOODEN_BRICKS.getUnlocalizedName().substring(5));
        WHITE_MARBLE = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("white_marble").setBlockTextureName("yogmod:white_marble");
        GameRegistry.registerBlock(WHITE_MARBLE, WHITE_MARBLE.getUnlocalizedName().substring(5));
        DARK_STONE_BRICKS = new YogBlock(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockName("dark_stone_bricks").setBlockTextureName("yogmod:dark_stone_bricks");
        GameRegistry.registerBlock(DARK_STONE_BRICKS, DARK_STONE_BRICKS.getUnlocalizedName().substring(5));
        DECORATIVE_CONCRETE = new YogBlock(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockName("decorative_concrete").setBlockTextureName("yogmod:decorative_concrete");
        GameRegistry.registerBlock(DECORATIVE_CONCRETE, DECORATIVE_CONCRETE.getUnlocalizedName().substring(5));
        PILLAR = new YogBlock(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockName("pillar").setBlockTextureName("yogmod:pillar");
        GameRegistry.registerBlock(PILLAR, PILLAR.getUnlocalizedName().substring(5));
        CIRCUITRY = new YogBlock(Material.rock).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("circuitry").setBlockTextureName("yogmod:circuitry");
        GameRegistry.registerBlock(CIRCUITRY, CIRCUITRY.getUnlocalizedName().substring(5));

        //Decorative Blocks

        //Redstone
        AIR_VENT = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("air_vent").setBlockTextureName("yogmod:air_vent");
        GameRegistry.registerBlock(AIR_VENT, AIR_VENT.getUnlocalizedName().substring(5));

        //Not in YogTab
        BEIGE_CARPET = new YogCarpetBlock(Material.carpet).setHardness(0.1F).setStepSound(Block.soundTypeCloth).setBlockName("beige_carpet").setBlockTextureName("yogmod:beige_wool").setLightOpacity(0);
        GameRegistry.registerBlock(BEIGE_CARPET, BEIGE_CARPET.getUnlocalizedName().substring(5));
        BEIGE_WOOL = new YogBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("beige_wool").setBlockTextureName("yogmod:beige_wool");
        GameRegistry.registerBlock(BEIGE_WOOL, BEIGE_WOOL.getUnlocalizedName().substring(5));
    }


}
