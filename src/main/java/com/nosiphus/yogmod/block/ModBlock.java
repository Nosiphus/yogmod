package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.item.ItemYogColoredBlock;
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
    public static Block PLASTIC;
    public static Block SHALE;
    public static Block REINFORCED_PANELING;
    public static Block STRIPE;
    public static Block LINOLEUM_TILE;
    public static Block CORRUGATED_STEEL;
    public static Block CLAY_TILE;
    public static Block STORAGE_CRATE;
    public static Block PIPE;
    public static BlockSlab WOODEN_BRICK_SLAB;
    public static BlockSlab DOUBLE_WOODEN_BRICK_SLAB;
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
    public static Block CAUTION_TAPE;
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
    public static Block PLASTIC_CARPET;
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
    public static Block LAMP;
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
        WOODEN_BRICKS = new YogWoodBlock(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("wooden_bricks");
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
        LIMESTONE_BRICK = new YogBlock(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockName("limestone_brick").setBlockTextureName("yogmod:limestone_brick");
        GameRegistry.registerBlock(LIMESTONE_BRICK, LIMESTONE_BRICK.getUnlocalizedName().substring(5));
        CORRUGATED_COPPER = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("corrugated_copper").setBlockTextureName("yogmod:corrugated_copper");
        GameRegistry.registerBlock(CORRUGATED_COPPER, CORRUGATED_COPPER.getUnlocalizedName().substring(5));
        IRON_STACK = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("iron_stack").setBlockTextureName("yogmod:iron_stack");
        GameRegistry.registerBlock(IRON_STACK, IRON_STACK.getUnlocalizedName().substring(5));
        RIVETED_STEEL = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("riveted_steel").setBlockTextureName("yogmod:riveted_steel");
        GameRegistry.registerBlock(RIVETED_STEEL, RIVETED_STEEL.getUnlocalizedName().substring(5));
        RUSTY_IRON = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("rusty_iron").setBlockTextureName("yogmod:rusty_iron");
        GameRegistry.registerBlock(RUSTY_IRON, RUSTY_IRON.getUnlocalizedName().substring(5));
        BLUE_PANELING = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("blue_paneling").setBlockTextureName("yogmod:blue_paneling");
        GameRegistry.registerBlock(BLUE_PANELING, BLUE_PANELING.getUnlocalizedName().substring(5));
        GLASS = (BlockGlass) new YogGlassBlock(Material.glass).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("glass").setBlockTextureName("yogmod:glass");
        GameRegistry.registerBlock(GLASS, GLASS.getUnlocalizedName().substring(5));
        TILE_MOSAIC = new YogBlock(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockName("tile_mosaic").setBlockTextureName("yogmod:tile_mosaic");
        GameRegistry.registerBlock(TILE_MOSAIC, TILE_MOSAIC.getUnlocalizedName().substring(5));
        BEIGE_PLASTIC = new YogBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("beige_plastic").setBlockTextureName("yogmod:beige_plastic");
        GameRegistry.registerBlock(BEIGE_PLASTIC, BEIGE_PLASTIC.getUnlocalizedName().substring(5));
        PLASTIC = new YogColoredBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("plastic");
        GameRegistry.registerBlock(PLASTIC, ItemYogColoredBlock.class, PLASTIC.getUnlocalizedName().substring(5));
        SHALE = new YogBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("shale").setBlockTextureName("yogmod:shale");
        GameRegistry.registerBlock(SHALE, SHALE.getUnlocalizedName().substring(5));
        REINFORCED_PANELING = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("reinforced_paneling").setBlockTextureName("yogmod:reinforced_paneling");
        GameRegistry.registerBlock(REINFORCED_PANELING, REINFORCED_PANELING.getUnlocalizedName().substring(5));
        STRIPE = new StripeBlock(Material.rock).setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName("stripe");
        GameRegistry.registerBlock(STRIPE, STRIPE.getUnlocalizedName().substring(5));
        LINOLEUM_TILE = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("linoleum_tile").setBlockTextureName("yogmod:linoleum_tile");
        GameRegistry.registerBlock(LINOLEUM_TILE, LINOLEUM_TILE.getUnlocalizedName().substring(5));
        CORRUGATED_STEEL = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("corrugated_steel").setBlockTextureName("yogmod:corrugated_steel");
        GameRegistry.registerBlock(CORRUGATED_STEEL, CORRUGATED_STEEL.getUnlocalizedName().substring(5));
        CLAY_TILE = new YogBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("clay_tile").setBlockTextureName("yogmod:clay_tile");
        GameRegistry.registerBlock(CLAY_TILE, CLAY_TILE.getUnlocalizedName().substring(5));
        //Storage Crate
        PIPE = new PipeBlock(Material.rock).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeMetal).setBlockName("pipe");
        GameRegistry.registerBlock(PIPE, PIPE.getUnlocalizedName().substring(5));
        //Wooden Slabs

        IRON_PLATE = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("iron_plate").setBlockTextureName("yogmod:iron_plate");
        GameRegistry.registerBlock(IRON_PLATE, IRON_PLATE.getUnlocalizedName().substring(5));

        CAUTION_TAPE = new CautionTapeBlock(Material.sand).setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName("caution_tape").setBlockTextureName("yogmod:caution_tape");
        GameRegistry.registerBlock(CAUTION_TAPE, CAUTION_TAPE.getUnlocalizedName().substring(5));
        FLUORESCENT_PANEL = new YogBlock(Material.glass).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setLightLevel(1.0F).setBlockName("fluorescent_panel").setBlockTextureName("yogmod:fluorescent_panel");
        GameRegistry.registerBlock(FLUORESCENT_PANEL, FLUORESCENT_PANEL.getUnlocalizedName().substring(5));


        STRIPE_INTERSECTION = new YogBlock(Material.rock).setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName("stripe_intersection").setBlockTextureName("yogmod:stripe_end");
        GameRegistry.registerBlock(STRIPE_INTERSECTION, STRIPE_INTERSECTION.getUnlocalizedName().substring(5));
        PIPE_INTERSECTION = new YogBlock(Material.rock).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeMetal).setBlockName("pipe_intersection").setBlockTextureName("yogmod:pipe_end");
        GameRegistry.registerBlock(PIPE_INTERSECTION, PIPE_INTERSECTION.getUnlocalizedName().substring(5));
        COUNTERTOP = new CountertopBlock(Material.rock).setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("countertop");
        GameRegistry.registerBlock(COUNTERTOP, COUNTERTOP.getUnlocalizedName().substring(5));
        CONCRETE = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("concrete").setBlockTextureName("yogmod:concrete");
        GameRegistry.registerBlock(CONCRETE, CONCRETE.getUnlocalizedName().substring(5));
        SMOOTH_METAL = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("smooth_metal").setBlockTextureName("yogmod:smooth_metal");
        GameRegistry.registerBlock(SMOOTH_METAL, SMOOTH_METAL.getUnlocalizedName().substring(5));



        //Decorative Blocks
        //LANTERN;
        //TECH_ACCENT;
        //LADDER;
        //CURTAINS;

        //Redstone
        //LED;
        AIR_VENT = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("air_vent").setBlockTextureName("yogmod:air_vent");
        GameRegistry.registerBlock(AIR_VENT, AIR_VENT.getUnlocalizedName().substring(5));
        //LEVER;
        //LAMP;
        //CONSOLE;
        //HATCH;

        //Not in YogTab
        BEIGE_CARPET = new YogCarpetBlock(Material.carpet).setHardness(0.1F).setStepSound(Block.soundTypeCloth).setBlockName("beige_carpet").setBlockTextureName("yogmod:beige_wool").setLightOpacity(0);
        GameRegistry.registerBlock(BEIGE_CARPET, BEIGE_CARPET.getUnlocalizedName().substring(5));
        BEIGE_WOOL = new YogBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("beige_wool").setBlockTextureName("yogmod:beige_wool");
        GameRegistry.registerBlock(BEIGE_WOOL, BEIGE_WOOL.getUnlocalizedName().substring(5));
    }


}
