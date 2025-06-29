package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.client.renderer.LeverRenderer;
import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import com.nosiphus.yogmod.item.ItemYogColoredBlock;
import com.nosiphus.yogmod.item.ItemYogWoodBlock;
import com.nosiphus.yogmod.item.ItemYogWoodSlabBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class ModBlock {

    //Dummy Blocks
    public static BlockGlass DUMMY_GLASS;
    public static BlockLadder DUMMY_LADDER;
    public static BlockLever DUMMY_LEVER;

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
    public static BlockSlab DOUBLE_WOODEN_BRICK_SLAB;
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

        //Dummy Blocks
        DUMMY_GLASS = (BlockGlass) new YogGlassBlock(Material.glass).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("glass").setBlockTextureName("yogmod:glass").setCreativeTab(null);
        GameRegistry.registerBlock(DUMMY_GLASS, DUMMY_GLASS.getUnlocalizedName().substring(5));
        DUMMY_LADDER = (BlockLadder) new LadderBlock(Material.wood).setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("ladder").setBlockTextureName("yogmod:ladder").setCreativeTab(null);
        GameRegistry.registerBlock(DUMMY_LADDER, DUMMY_LADDER.getUnlocalizedName().substring(5));
        DUMMY_LEVER = (BlockLever) new LeverBlock(Material.wood).setHardness(0.5F).setStepSound(Block.soundTypeWood).setBlockName("lever").setBlockTextureName("yogmod:lever").setCreativeTab(null);
        GameRegistry.registerBlock(DUMMY_LEVER, DUMMY_LEVER.getUnlocalizedName().substring(5));

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
        GLASS = (BlockGlass) new YogGlassBlock(Material.glass).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("yog_glass").setBlockTextureName("yogmod:glass");
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
        DOUBLE_WOODEN_BRICK_SLAB = (BlockSlab) new YogWoodSlabBlock(true).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("double_wooden_brick_slab");
        GameRegistry.registerBlock(DOUBLE_WOODEN_BRICK_SLAB, ItemYogWoodSlabBlock.class, DOUBLE_WOODEN_BRICK_SLAB.getUnlocalizedName().substring(5));
        WOODEN_BRICK_SLAB = (BlockSlab) new YogWoodSlabBlock(false).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("wooden_brick_slab");
        GameRegistry.registerBlock(WOODEN_BRICK_SLAB, ItemYogWoodSlabBlock.class, WOODEN_BRICK_SLAB.getUnlocalizedName().substring(5));

        IRON_PLATE = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("iron_plate").setBlockTextureName("yogmod:iron_plate");
        GameRegistry.registerBlock(IRON_PLATE, IRON_PLATE.getUnlocalizedName().substring(5));

        THIN_STRIPE = new YogBlock(Material.cloth).setHardness(0.2F).setStepSound(Block.soundTypeSnow).setBlockName("thin_stripe").setBlockTextureName("yogmod:thin_stripe");
        GameRegistry.registerBlock(THIN_STRIPE, THIN_STRIPE.getUnlocalizedName().substring(5));
        CAUTION_TAPE = new CautionTapeBlock(Material.sand).setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName("caution_tape").setBlockTextureName("yogmod:caution_tape");
        GameRegistry.registerBlock(CAUTION_TAPE, CAUTION_TAPE.getUnlocalizedName().substring(5));
        FLUORESCENT_PANEL = new YogBlock(Material.glass).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setLightLevel(1.0F).setBlockName("fluorescent_panel").setBlockTextureName("yogmod:fluorescent_panel");
        GameRegistry.registerBlock(FLUORESCENT_PANEL, FLUORESCENT_PANEL.getUnlocalizedName().substring(5));
        CONCRETE_BRICKS = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("concrete_bricks").setBlockTextureName("yogmod:concrete_bricks");
        GameRegistry.registerBlock(CONCRETE_BRICKS, CONCRETE_BRICKS.getUnlocalizedName().substring(5));

        STRIPE_INTERSECTION = new YogBlock(Material.rock).setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName("stripe_intersection").setBlockTextureName("yogmod:stripe_end");
        GameRegistry.registerBlock(STRIPE_INTERSECTION, STRIPE_INTERSECTION.getUnlocalizedName().substring(5));
        PIPE_INTERSECTION = new YogBlock(Material.rock).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeMetal).setBlockName("pipe_intersection").setBlockTextureName("yogmod:pipe_end");
        GameRegistry.registerBlock(PIPE_INTERSECTION, PIPE_INTERSECTION.getUnlocalizedName().substring(5));
        COUNTERTOP = new CountertopBlock(Material.wood).setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("countertop");
        GameRegistry.registerBlock(COUNTERTOP, COUNTERTOP.getUnlocalizedName().substring(5));
        CONCRETE = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("concrete").setBlockTextureName("yogmod:concrete");
        GameRegistry.registerBlock(CONCRETE, CONCRETE.getUnlocalizedName().substring(5));
        SMOOTH_METAL = new YogBlock(Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("smooth_metal").setBlockTextureName("yogmod:smooth_metal");
        GameRegistry.registerBlock(SMOOTH_METAL, SMOOTH_METAL.getUnlocalizedName().substring(5));



        //Decorative Blocks
        LANTERN = new LanternBlock().setHardness(0.0F).setLightLevel(0.9375F).setStepSound(Block.soundTypeGlass).setBlockName("lantern").setBlockTextureName("yogmod:lantern");
        GameRegistry.registerBlock(LANTERN, LANTERN.getUnlocalizedName().substring(5));
        TECH_ACCENT = new YogBlock(Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("tech_accent").setBlockTextureName("yogmod:tech_accent");
        GameRegistry.registerBlock(TECH_ACCENT, TECH_ACCENT.getUnlocalizedName().substring(5));
        LADDER = (BlockLadder) new LadderBlock(Material.wood).setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("yog_ladder").setBlockTextureName("yogmod:ladder");
        GameRegistry.registerBlock(LADDER, LADDER.getUnlocalizedName().substring(5));
        CURTAINS = (BlockVine) new CurtainsBlock(Material.cloth).setHardness(0.2F).setStepSound(YogBlock.soundTypeCloth).setBlockName("curtains").setBlockTextureName("yogmod:curtains");
        GameRegistry.registerBlock(CURTAINS, CURTAINS.getUnlocalizedName().substring(5));

        //Redstone
        LED = new LEDBlock(false).setHardness(0.0F).setHardness(0.0F).setStepSound(Block.soundTypeGlass).setBlockName("led").setBlockTextureName("yogmod:led");
        GameRegistry.registerBlock(LED, LED.getUnlocalizedName().substring(5));
        LED_ON = new LEDBlock(true).setHardness(0.0F).setHardness(0.0F).setLightLevel(0.5F).setStepSound(Block.soundTypeGlass).setBlockName("led_on").setBlockTextureName("yogmod:led_on").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerBlock(LED_ON, LED_ON.getUnlocalizedName().substring(5));
        AIR_VENT = new YogBlock(Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("air_vent").setBlockTextureName("yogmod:air_vent");
        GameRegistry.registerBlock(AIR_VENT, AIR_VENT.getUnlocalizedName().substring(5));
        CLASSIC_LEVER = (BlockLever) new LeverBlock(Material.wood).setHardness(0.5F).setStepSound(Block.soundTypeWood).setBlockName("classic_lever").setBlockTextureName("yogmod:classic_lever");
        GameRegistry.registerBlock(CLASSIC_LEVER, CLASSIC_LEVER.getUnlocalizedName().substring(5));
        LEVER = (BlockLever) new LeverBlock(Material.wood).setHardness(0.5F).setStepSound(Block.soundTypeWood).setBlockName("yog_lever").setBlockTextureName("yogmod:lever");
        GameRegistry.registerBlock(LEVER, LEVER.getUnlocalizedName().substring(5));
        LAMP = new LampBlock(false).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("lamp").setBlockTextureName("yogmod:lamp").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerBlock(LAMP, LAMP.getUnlocalizedName().substring(5));
        LIT_LAMP = new LampBlock(true).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setBlockName("lamp_on").setBlockTextureName("yogmod:lamp_on");
        GameRegistry.registerBlock(LIT_LAMP, LIT_LAMP.getUnlocalizedName().substring(5));
        CONSOLE = (BlockNote) new ConsoleBlock(Material.wood).setHardness(0.8F).setBlockName("console").setBlockTextureName("yogmod:console");
        GameRegistry.registerBlock(CONSOLE, CONSOLE.getUnlocalizedName().substring(5));
        HATCH = (BlockTrapDoor) new HatchBlock(Material.wood).setHardness(3.0F).setStepSound(Block.soundTypeWood).setBlockName("hatch").setBlockTextureName("yogmod:hatch");
        GameRegistry.registerBlock(HATCH, HATCH.getUnlocalizedName().substring(5));

        //Not in YogTab
        BEIGE_CARPET = new YogCarpetBlock(Material.carpet).setHardness(0.1F).setStepSound(Block.soundTypeCloth).setBlockName("beige_carpet").setBlockTextureName("yogmod:beige_wool").setLightOpacity(0);
        GameRegistry.registerBlock(BEIGE_CARPET, BEIGE_CARPET.getUnlocalizedName().substring(5));
        BEIGE_WOOL = new YogBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth).setBlockName("beige_wool").setBlockTextureName("yogmod:beige_wool");
        GameRegistry.registerBlock(BEIGE_WOOL, BEIGE_WOOL.getUnlocalizedName().substring(5));
    }

    public static void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new LeverRenderer());
    }


}
