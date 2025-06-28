package com.nosiphus.yogmod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class ModBlock {

    //Building Blocks
    public static Block ASPHALT;
    public static Block OAK_BRICKS;
    public static Block SPRUCE_BRICKS;
    public static Block BIRCH_BRICKS;
    public static Block JUNGLE_BRICKS;
    public static Block ACACIA_BRICKS;
    public static Block DARK_OAK_BRICKS;
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
    public static BlockSlab OAK_BRICK_SLAB;
    public static BlockSlab SPRUCE_BRICK_SLAB;
    public static BlockSlab BIRCH_BRICK_SLAB;
    public static BlockSlab JUNGLE_BRICK_SLAB;
    public static BlockSlab ACACIA_BRICK_SLAB;
    public static BlockSlab DARK_OAK_BRICK_SLAB;
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
    public static BlockStairs OAK_BRICK_STAIRS;
    public static BlockStairs SPRUCE_BRICK_STAIRS;
    public static BlockStairs BIRCH_BRICK_STAIRS;
    public static BlockStairs JUNGLE_BRICK_STAIRS;
    public static BlockStairs ACACIA_BRICK_STAIRS;
    public static BlockStairs DARK_OAK_BRICK_STAIRS;
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
    public static BlockFence OAK_BRICK_FENCE;
    public static BlockFence SPRUCE_BRICK_FENCE;
    public static BlockFence BIRCH_BRICK_FENCE;
    public static BlockFence JUNGLE_BRICK_FENCE;
    public static BlockFence ACACIA_BRICK_FENCE;
    public static BlockFence DARK_OAK_BRICK_FENCE;
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
    public static BlockButton OAK_BRICK_BUTTON;
    public static BlockButton SPRUCE_BRICK_BUTTON;
    public static BlockButton BIRCH_BRICK_BUTTON;
    public static BlockButton JUNGLE_BRICK_BUTTON;
    public static BlockButton ACACIA_BRICK_BUTTON;
    public static BlockButton DARK_OAK_BRICK_BUTTON;
    public static BlockPressurePlate OAK_BRICK_PRESSURE_PLATE;
    public static BlockPressurePlate SPRUCE_BRICK_PRESSURE_PLATE;
    public static BlockPressurePlate BIRCH_BRICK_PRESSURE_PLATE;
    public static BlockPressurePlate JUNGLE_BRICK_PRESSURE_PLATE;
    public static BlockPressurePlate ACACIA_BRICK_PRESSURE_PLATE;
    public static BlockPressurePlate DARK_OAK_BRICK_PRESSURE_PLATE;
    public static BlockDoor IRON_DOOR;
    public static BlockDoor WOODEN_DOOR;
    public static BlockTrapDoor HATCH;
    public static BlockFenceGate OAK_BRICK_FENCE_GATE;
    public static BlockFenceGate SPRUCE_BRICK_FENCE_GATE;
    public static BlockFenceGate BIRCH_BRICK_FENCE_GATE;
    public static BlockFenceGate JUNGLE_BRICK_FENCE_GATE;
    public static BlockFenceGate ACACIA_BRICK_FENCE_GATE;
    public static BlockFenceGate DARK_OAK_BRICK_FENCE_GATE;

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
        OAK_BRICKS = new YogBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("oak_bricks").setBlockTextureName("yogmod:oak_bricks");
        GameRegistry.registerBlock(OAK_BRICKS, OAK_BRICKS.getUnlocalizedName().substring(5));
        SPRUCE_BRICKS = new YogBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("spruce_bricks").setBlockTextureName("yogmod:spruce_bricks");
        GameRegistry.registerBlock(SPRUCE_BRICKS, SPRUCE_BRICKS.getUnlocalizedName().substring(5));
        BIRCH_BRICKS = new YogBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("birch_bricks").setBlockTextureName("yogmod:birch_bricks");
        GameRegistry.registerBlock(BIRCH_BRICKS, BIRCH_BRICKS.getUnlocalizedName().substring(5));
        JUNGLE_BRICKS = new YogBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("jungle_bricks").setBlockTextureName("yogmod:jungle_bricks");
        GameRegistry.registerBlock(JUNGLE_BRICKS, JUNGLE_BRICKS.getUnlocalizedName().substring(5));
        ACACIA_BRICKS = new YogBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("acacia_bricks").setBlockTextureName("yogmod:acacia_bricks");
        GameRegistry.registerBlock(ACACIA_BRICKS, ACACIA_BRICKS.getUnlocalizedName().substring(5));
        DARK_OAK_BRICKS = new YogBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("dark_oak_bricks").setBlockTextureName("yogmod:dark_oak_bricks");
        GameRegistry.registerBlock(DARK_OAK_BRICKS, DARK_OAK_BRICKS.getUnlocalizedName().substring(5));
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
