package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.client.renderer.LeverRenderer;
import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import com.nosiphus.yogmod.item.ItemYogColored;
import com.nosiphus.yogmod.item.ItemYogStoneSlab;
import com.nosiphus.yogmod.item.ItemYogWood;
import com.nosiphus.yogmod.item.ItemYogWoodSlab;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
    public static BlockYogWoodSlab DOUBLE_WOODEN_BRICK_SLAB;
    public static BlockYogWoodSlab WOODEN_BRICK_SLAB;
    public static BlockYogStoneSlab DOUBLE_STEP_SLAB;
    public static BlockYogStoneSlab STEP_SLAB;
    public static Block STEP;
    public static Block BRICKS;
    public static Block IRON_PLATE;
    public static BlockStairs STEP_STAIRS;
    public static Block WHITE_LIGHTING;
    public static Block THIN_STRIPE;
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
    public static Block SMOOTH_METAL;
    public static Block GREEN_TILES;
    public static BlockStairs WOODEN_BRICK_STAIRS;

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
    public static BlockVine CURTAINS;
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
    public static Block LED_ON;
    public static Block DIODE;
    public static Block AIR_VENT;
    public static Block MOVING_PISTON;
    public static Block PISTON;
    public static Block PISTON_HEAD;
    public static Block STICKY_PISTON;
    public static Block DISPENSER;
    public static BlockLever CLASSIC_LEVER;
    public static BlockLever LEVER;
    public static Block DYNAMITE;
    public static Block LAMP;
    public static Block LIT_LAMP;
    public static BlockNote CONSOLE;
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
        ASPHALT = new BlockYogBase(2700, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("asphalt").setTextureName("yogmod:asphalt");
        GameRegistry.registerBlock(ASPHALT, ASPHALT.getUnlocalizedName().substring(5));
        WOODEN_BRICKS = new BlockYogWood(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wooden_bricks");
        GameRegistry.registerBlock(WOODEN_BRICKS, ItemYogWood.class, WOODEN_BRICKS.getUnlocalizedName().substring(5));
        WHITE_MARBLE = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("white_marble").setTextureName("yogmod:white_marble");
        GameRegistry.registerBlock(WHITE_MARBLE, WHITE_MARBLE.getUnlocalizedName().substring(5));
        DARK_STONE_BRICKS = new BlockYogBase(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("dark_stone_bricks").setTextureName("yogmod:dark_stone_bricks");
        GameRegistry.registerBlock(DARK_STONE_BRICKS, DARK_STONE_BRICKS.getUnlocalizedName().substring(5));
        DECORATIVE_CONCRETE = new BlockYogBase(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decorative_concrete").setTextureName("yogmod:decorative_concrete");
        GameRegistry.registerBlock(DECORATIVE_CONCRETE, DECORATIVE_CONCRETE.getUnlocalizedName().substring(5));
        PILLAR = new BlockYogBase(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("pillar").setTextureName("yogmod:pillar");
        GameRegistry.registerBlock(PILLAR, PILLAR.getUnlocalizedName().substring(5));
        CIRCUITRY = new BlockYogBase(Material.rock).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("circuitry").setTextureName("yogmod:circuitry");
        GameRegistry.registerBlock(CIRCUITRY, CIRCUITRY.getUnlocalizedName().substring(5));
        LIMESTONE_BRICK = new BlockYogBase(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("limestone_brick").setTextureName("yogmod:limestone_brick");
        GameRegistry.registerBlock(LIMESTONE_BRICK, LIMESTONE_BRICK.getUnlocalizedName().substring(5));
        CORRUGATED_COPPER = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("corrugated_copper").setTextureName("yogmod:corrugated_copper");
        GameRegistry.registerBlock(CORRUGATED_COPPER, CORRUGATED_COPPER.getUnlocalizedName().substring(5));
        IRON_STACK = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("iron_stack").setTextureName("yogmod:iron_stack");
        GameRegistry.registerBlock(IRON_STACK, IRON_STACK.getUnlocalizedName().substring(5));
        RIVETED_STEEL = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("riveted_steel").setTextureName("yogmod:riveted_steel");
        GameRegistry.registerBlock(RIVETED_STEEL, RIVETED_STEEL.getUnlocalizedName().substring(5));
        RUSTY_IRON = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("rusty_iron").setTextureName("yogmod:rusty_iron");
        GameRegistry.registerBlock(RUSTY_IRON, RUSTY_IRON.getUnlocalizedName().substring(5));
        BLUE_PANELING = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blue_paneling").setTextureName("yogmod:blue_paneling");
        GameRegistry.registerBlock(BLUE_PANELING, BLUE_PANELING.getUnlocalizedName().substring(5));
        GLASS = (BlockGlass) new BlockYogGlass(Material.glass).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("yog_glass").setTextureName("yogmod:glass");
        GameRegistry.registerBlock(GLASS, GLASS.getUnlocalizedName().substring(5));
        TILE_MOSAIC = new BlockYogBase(Material.rock).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("tile_mosaic").setTextureName("yogmod:tile_mosaic");
        GameRegistry.registerBlock(TILE_MOSAIC, TILE_MOSAIC.getUnlocalizedName().substring(5));
        BEIGE_PLASTIC = new BlockYogBase(Material.cloth).setHardness(0.8F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("beige_plastic").setTextureName("yogmod:beige_plastic");
        GameRegistry.registerBlock(BEIGE_PLASTIC, BEIGE_PLASTIC.getUnlocalizedName().substring(5));
        PLASTIC = new BlockColoredYogBase(Material.cloth).setHardness(0.8F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("plastic");
        GameRegistry.registerBlock(PLASTIC, ItemYogColored.class, PLASTIC.getUnlocalizedName().substring(5));
        SHALE = new BlockYogBase(Material.cloth).setHardness(0.8F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("shale").setTextureName("yogmod:shale");
        GameRegistry.registerBlock(SHALE, SHALE.getUnlocalizedName().substring(5));
        REINFORCED_PANELING = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("reinforced_paneling").setTextureName("yogmod:reinforced_paneling");
        GameRegistry.registerBlock(REINFORCED_PANELING, REINFORCED_PANELING.getUnlocalizedName().substring(5));
        STRIPE = new BlockStripe(Material.rock).setHardness(0.8F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stripe");
        GameRegistry.registerBlock(STRIPE, STRIPE.getUnlocalizedName().substring(5));
        LINOLEUM_TILE = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("linoleum_tile").setTextureName("yogmod:linoleum_tile");
        GameRegistry.registerBlock(LINOLEUM_TILE, LINOLEUM_TILE.getUnlocalizedName().substring(5));
        CORRUGATED_STEEL = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("corrugated_steel").setTextureName("yogmod:corrugated_steel");
        GameRegistry.registerBlock(CORRUGATED_STEEL, CORRUGATED_STEEL.getUnlocalizedName().substring(5));
        CLAY_TILE = new BlockYogBase(Material.cloth).setHardness(0.8F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("clay_tile").setTextureName("yogmod:clay_tile");
        GameRegistry.registerBlock(CLAY_TILE, CLAY_TILE.getUnlocalizedName().substring(5));
        STORAGE_CRATE = new BlockYogBase(Material.wood).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("storage_crate").setTextureName("yogmod:storage_crate");
        GameRegistry.registerBlock(STORAGE_CRATE, STORAGE_CRATE.getUnlocalizedName().substring(5));
        PIPE = new BlockPipe(Material.rock).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("pipe");
        GameRegistry.registerBlock(PIPE, PIPE.getUnlocalizedName().substring(5));
        WOODEN_BRICK_SLAB = (BlockYogWoodSlab) new BlockYogWoodSlab(false).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wooden_brick_slab");
        DOUBLE_WOODEN_BRICK_SLAB = (BlockYogWoodSlab) new BlockYogWoodSlab(true).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("double_wooden_brick_slab");
        ItemYogWoodSlab.singleSlabRef = WOODEN_BRICK_SLAB;
        ItemYogWoodSlab.doubleSlabRef = DOUBLE_WOODEN_BRICK_SLAB;
        GameRegistry.registerBlock(WOODEN_BRICK_SLAB, ItemYogWoodSlab.class, WOODEN_BRICK_SLAB.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(DOUBLE_WOODEN_BRICK_SLAB, DOUBLE_WOODEN_BRICK_SLAB.getUnlocalizedName().substring(5));
        STEP_SLAB = (BlockYogStoneSlab) new BlockYogStoneSlab(false).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("step_slab");
        DOUBLE_STEP_SLAB = (BlockYogStoneSlab) new BlockYogStoneSlab(true).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("double_step_slab");
        ItemYogStoneSlab.singleSlabRef = STEP_SLAB;
        ItemYogStoneSlab.doubleSlabRef = DOUBLE_STEP_SLAB;
        GameRegistry.registerBlock(STEP_SLAB, ItemYogStoneSlab.class, STEP_SLAB.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(DOUBLE_STEP_SLAB, DOUBLE_STEP_SLAB.getUnlocalizedName().substring(5));
        STEP = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("step").setTextureName("yogmod:step");
        GameRegistry.registerBlock(STEP, STEP.getUnlocalizedName().substring(5));
        BRICKS = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bricks").setTextureName("yogmod:bricks");
        GameRegistry.registerBlock(BRICKS, BRICKS.getUnlocalizedName().substring(5));
        IRON_PLATE = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("iron_plate").setTextureName("yogmod:iron_plate");
        GameRegistry.registerBlock(IRON_PLATE, IRON_PLATE.getUnlocalizedName().substring(5));
        //Stone Stairs
        WHITE_LIGHTING = new BlockWhiteLighting(Material.glass).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setLightLevel(1.0F).setUnlocalizedName("white_lighting");
        GameRegistry.registerBlock(WHITE_LIGHTING, WHITE_LIGHTING.getUnlocalizedName().substring(5));
        THIN_STRIPE = new BlockYogBase(Material.cloth).setHardness(0.2F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("thin_stripe").setTextureName("yogmod:thin_stripe");
        GameRegistry.registerBlock(THIN_STRIPE, THIN_STRIPE.getUnlocalizedName().substring(5));
        MONITOR = new BlockMonitor(false).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("monitor");
        GameRegistry.registerBlock(MONITOR, MONITOR.getUnlocalizedName().substring(5));
        LIT_MONITOR = new BlockMonitor(true).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setLightLevel(1.0F).setUnlocalizedName("lit_monitor");
        GameRegistry.registerBlock(LIT_MONITOR, LIT_MONITOR.getUnlocalizedName().substring(5));
        GRAY_AZTEC = new BlockYogBase(Material.rock).setHardness(0.4F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("gray_aztec").setTextureName("yogmod:gray_aztec");
        GameRegistry.registerBlock(GRAY_AZTEC, GRAY_AZTEC.getUnlocalizedName().substring(5));
        CAUTION_TAPE = new BlockYogBaseCautionTape(Material.sand).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("caution_tape").setTextureName("yogmod:caution_tape");
        GameRegistry.registerBlock(CAUTION_TAPE, CAUTION_TAPE.getUnlocalizedName().substring(5));
        FLUORESCENT_PANEL = new BlockYogBase(Material.glass).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setLightLevel(1.0F).setUnlocalizedName("fluorescent_panel").setTextureName("yogmod:fluorescent_panel");
        GameRegistry.registerBlock(FLUORESCENT_PANEL, FLUORESCENT_PANEL.getUnlocalizedName().substring(5));
        RAW_WOOD = new BlockYogBase(Material.wood).setHardness(1.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("raw_wood").setTextureName("yogmod:raw_wood");
        GameRegistry.registerBlock(RAW_WOOD, RAW_WOOD.getUnlocalizedName().substring(5));
        CONCRETE_BRICKS = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("concrete_bricks").setTextureName("yogmod:concrete_bricks");
        GameRegistry.registerBlock(CONCRETE_BRICKS, CONCRETE_BRICKS.getUnlocalizedName().substring(5));
        STRIPE_INTERSECTION = new BlockYogBase(Material.rock).setHardness(0.8F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stripe_intersection").setTextureName("yogmod:stripe_end");
        GameRegistry.registerBlock(STRIPE_INTERSECTION, STRIPE_INTERSECTION.getUnlocalizedName().substring(5));
        PIPE_INTERSECTION = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("pipe_intersection").setTextureName("yogmod:pipe_end");
        GameRegistry.registerBlock(PIPE_INTERSECTION, PIPE_INTERSECTION.getUnlocalizedName().substring(5));
        COUNTERTOP = new BlockCountertop(Material.wood).setHardness(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("countertop");
        GameRegistry.registerBlock(COUNTERTOP, COUNTERTOP.getUnlocalizedName().substring(5));
        CONCRETE = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("concrete").setTextureName("yogmod:concrete");
        GameRegistry.registerBlock(CONCRETE, CONCRETE.getUnlocalizedName().substring(5));
        SMOOTH_METAL = new BlockYogBase(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("smooth_metal").setTextureName("yogmod:smooth_metal");
        GameRegistry.registerBlock(SMOOTH_METAL, SMOOTH_METAL.getUnlocalizedName().substring(5));
        GREEN_TILES = new BlockYogBase(Material.rock).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("green_tiles").setTextureName("yogmod:green_tiles");
        GameRegistry.registerBlock(GREEN_TILES, GREEN_TILES.getUnlocalizedName().substring(5));
        //Wooden Brick Stairs

        //Decorative Blocks
        LANTERN = new BlockLantern().setHardness(0.0F).setLightLevel(0.9375F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("lantern").setTextureName("yogmod:lantern");
        GameRegistry.registerBlock(LANTERN, LANTERN.getUnlocalizedName().substring(5));
        TECH_ACCENT = new BlockYogBase(Material.wood).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("tech_accent").setTextureName("yogmod:tech_accent");
        GameRegistry.registerBlock(TECH_ACCENT, TECH_ACCENT.getUnlocalizedName().substring(5));
        LADDER = (BlockLadder) new BlockYogLadder(Material.wood).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("yog_ladder").setTextureName("yogmod:ladder");
        GameRegistry.registerBlock(LADDER, LADDER.getUnlocalizedName().substring(5));
        CURTAINS = (BlockVine) new BlockCurtains(Material.cloth).setHardness(0.2F).setStepSound(BlockYogBase.soundClothFootstep).setUnlocalizedName("curtains").setTextureName("yogmod:curtains");
        GameRegistry.registerBlock(CURTAINS, CURTAINS.getUnlocalizedName().substring(5));

        //Redstone
        LED = new BlockLED(false).setHardness(0.0F).setHardness(0.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("led").setTextureName("yogmod:led");
        GameRegistry.registerBlock(LED, LED.getUnlocalizedName().substring(5));
        LED_ON = new BlockLED(true).setHardness(0.0F).setHardness(0.0F).setLightLevel(0.5F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("led_on").setTextureName("yogmod:led_on").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerBlock(LED_ON, LED_ON.getUnlocalizedName().substring(5));
        AIR_VENT = new BlockYogBase(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("air_vent").setTextureName("yogmod:air_vent");
        GameRegistry.registerBlock(AIR_VENT, AIR_VENT.getUnlocalizedName().substring(5));
        CLASSIC_LEVER = (BlockLever) new BlockYogLever(Material.wood).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("classic_lever").setTextureName("yogmod:classic_lever");
        GameRegistry.registerBlock(CLASSIC_LEVER, CLASSIC_LEVER.getUnlocalizedName().substring(5));
        LEVER = (BlockLever) new BlockYogLever(Material.wood).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("yog_lever").setTextureName("yogmod:lever");
        GameRegistry.registerBlock(LEVER, LEVER.getUnlocalizedName().substring(5));
        LAMP = new BlockLamp(false).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("lamp").setTextureName("yogmod:lamp").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerBlock(LAMP, LAMP.getUnlocalizedName().substring(5));
        LIT_LAMP = new BlockLamp(true).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("lamp_on").setTextureName("yogmod:lamp_on");
        GameRegistry.registerBlock(LIT_LAMP, LIT_LAMP.getUnlocalizedName().substring(5));
        CONSOLE = (BlockNote) new BlockConsole(Material.wood).setHardness(0.8F).setUnlocalizedName("console").setTextureName("yogmod:console");
        GameRegistry.registerBlock(CONSOLE, CONSOLE.getUnlocalizedName().substring(5));
        IRON_DOOR = (BlockDoor) new BlockYogDoor(Material.iron).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("iron_door").disableStats().setTextureName("yogmod:iron_door");
        GameRegistry.registerBlock(IRON_DOOR, IRON_DOOR.getUnlocalizedName().substring(5));
        WOODEN_DOOR = (BlockDoor) new BlockYogDoor(Material.wood).setHardness(3.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wooden_door").disableStats().setTextureName("yogmod:wooden_door");
        GameRegistry.registerBlock(WOODEN_DOOR, WOODEN_DOOR.getUnlocalizedName().substring(5));
        HATCH = (BlockTrapDoor) new BlockHatch(Material.wood).setHardness(3.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hatch").setTextureName("yogmod:hatch");
        GameRegistry.registerBlock(HATCH, HATCH.getUnlocalizedName().substring(5));

        //Not in YogTab
        BEIGE_CARPET = new BlockCarpetYogBase(Material.carpet).setHardness(0.1F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("beige_carpet").setTextureName("yogmod:beige_wool").setLightOpacity(0);
        GameRegistry.registerBlock(BEIGE_CARPET, BEIGE_CARPET.getUnlocalizedName().substring(5));
        BEIGE_WOOL = new BlockYogBase(Material.cloth).setHardness(0.8F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("beige_wool").setTextureName("yogmod:beige_wool");
        GameRegistry.registerBlock(BEIGE_WOOL, BEIGE_WOOL.getUnlocalizedName().substring(5));
    }

    public static void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new LeverRenderer());
    }

}
