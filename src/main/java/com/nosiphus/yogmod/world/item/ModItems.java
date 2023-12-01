package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "yogmod");

    //Building Blocks
    public static final RegistryObject<BlockItem> ASPHALT = ITEMS.register("asphalt",
            () -> new BlockItem(ModBlocks.ASPHALT.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICKS = ITEMS.register("oak_bricks",
            () -> new BlockItem(ModBlocks.OAK_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICKS = ITEMS.register("spruce_bricks",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICKS = ITEMS.register("birch_bricks",
            () -> new BlockItem(ModBlocks.BIRCH_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICKS = ITEMS.register("jungle_bricks",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICKS = ITEMS.register("acacia_bricks",
            () -> new BlockItem(ModBlocks.ACACIA_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICKS = ITEMS.register("dark_oak_bricks",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICKS = ITEMS.register("crimson_bricks",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICKS = ITEMS.register("warped_bricks",
            () -> new BlockItem(ModBlocks.WARPED_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICKS = ITEMS.register("mangrove_bricks",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WHITE_MARBLE = ITEMS.register("white_marble",
            () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_STONE_BRICKS = ITEMS.register("dark_stone_bricks",
            () -> new BlockItem(ModBlocks.DARK_STONE_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DECORATIVE_CONCRETE = ITEMS.register("decorative_concrete",
            () -> new BlockItem(ModBlocks.DECORATIVE_CONCRETE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PILLAR = ITEMS.register("pillar",
            () -> new BlockItem(ModBlocks.PILLAR.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CIRCUITRY = ITEMS.register("circuitry",
            () -> new BlockItem(ModBlocks.CIRCUITRY.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIMESTONE_BRICKS = ITEMS.register("limestone_brick",
            () -> new BlockItem(ModBlocks.LIMESTONE_BRICK.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CORRUGATED_COPPER = ITEMS.register("corrugated_copper",
            () -> new BlockItem(ModBlocks.CORRUGATED_COPPER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_STACK = ITEMS.register("iron_stack",
            () -> new BlockItem(ModBlocks.IRON_STACK.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RIVETED_STEEL = ITEMS.register("riveted_steel",
            () -> new BlockItem(ModBlocks.RIVETED_STEEL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RUSTY_IRON = ITEMS.register("rusty_iron",
            () -> new BlockItem(ModBlocks.RUSTY_IRON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PANELING = ITEMS.register("blue_paneling",
            () -> new BlockItem(ModBlocks.BLUE_PANELING.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GLASS = ITEMS.register("glass",
            () -> new BlockItem(ModBlocks.GLASS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> TILE_MOSAIC = ITEMS.register("tile_mosaic",
            () -> new BlockItem(ModBlocks.TILE_MOSAIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BEIGE_PLASTIC = ITEMS.register("beige_plastic",
            () -> new BlockItem(ModBlocks.BEIGE_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WHITE_PLASTIC = ITEMS.register("white_plastic",
            () -> new BlockItem(ModBlocks.WHITE_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ORANGE_PLASTIC = ITEMS.register("orange_plastic",
            () -> new BlockItem(ModBlocks.ORANGE_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MAGENTA_PLASTIC = ITEMS.register("magenta_plastic",
            () -> new BlockItem(ModBlocks.MAGENTA_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIGHT_BLUE_PLASTIC = ITEMS.register("light_blue_plastic",
            () -> new BlockItem(ModBlocks.LIGHT_BLUE_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GOLD_FILGAREE = ITEMS.register("gold_filgaree",
            () -> new BlockItem(ModBlocks.GOLD_FILGAREE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIME_PLASTIC = ITEMS.register("lime_plastic",
            () -> new BlockItem(ModBlocks.LIME_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PINK_STUCCO = ITEMS.register("pink_stucco",
            () -> new BlockItem(ModBlocks.PINK_STUCCO.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GRAY_STUCCO = ITEMS.register("gray_stucco",
            () -> new BlockItem(ModBlocks.GRAY_STUCCO.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIGHT_GRAY_STUCCO = ITEMS.register("light_gray_stucco",
            () -> new BlockItem(ModBlocks.LIGHT_GRAY_STUCCO.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CYAN_PLASTIC = ITEMS.register("cyan_plastic",
            () -> new BlockItem(ModBlocks.CYAN_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> VIOLET_VELVET = ITEMS.register("violet_velvet",
            () -> new BlockItem(ModBlocks.VIOLET_VELVET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PLASTIC = ITEMS.register("blue_plastic",
            () -> new BlockItem(ModBlocks.BLUE_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BROWN_STUCCO = ITEMS.register("brown_stucco",
            () -> new BlockItem(ModBlocks.BROWN_STUCCO.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GREEN_PLASTIC = ITEMS.register("green_plastic",
            () -> new BlockItem(ModBlocks.GREEN_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RED_PLASTIC = ITEMS.register("red_plastic",
            () -> new BlockItem(ModBlocks.RED_PLASTIC.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLACK_MARBLE = ITEMS.register("black_marble",
            () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SHALE = ITEMS.register("shale",
            () -> new BlockItem(ModBlocks.SHALE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> REINFORCED_PANELING = ITEMS.register("reinforced_paneling",
            () -> new BlockItem(ModBlocks.REINFORCED_PANELING.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STRIPE = ITEMS.register("stripe",
            () -> new BlockItem(ModBlocks.STRIPE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LINOLEUM_TILE = ITEMS.register("linoleum_tile",
            () -> new BlockItem(ModBlocks.LINOLEUM_TILE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CORRUGATED_STEEL = ITEMS.register("corrugated_steel",
            () -> new BlockItem(ModBlocks.CORRUGATED_STEEL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CLAY_TILE = ITEMS.register("clay_tile",
            () -> new BlockItem(ModBlocks.CLAY_TILE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STORAGE_CRATE = ITEMS.register("storage_crate",
            () -> new BlockItem(ModBlocks.STORAGE_CRATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PIPE = ITEMS.register("pipe",
            () -> new BlockItem(ModBlocks.PIPE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICK_SLAB = ITEMS.register("oak_brick_slab",
            () -> new BlockItem(ModBlocks.OAK_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICK_SLAB = ITEMS.register("spruce_brick_slab",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_SLAB = ITEMS.register("birch_brick_slab",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_SLAB = ITEMS.register("jungle_brick_slab",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_SLAB = ITEMS.register("acacia_brick_slab",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_SLAB = ITEMS.register("dark_oak_brick_slab",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_SLAB = ITEMS.register("crimson_brick_slab",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICK_SLAB = ITEMS.register("warped_brick_slab",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICK_SLAB = ITEMS.register("mangrove_brick_slab",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STEP_SLAB = ITEMS.register("step_slab",
            () -> new BlockItem(ModBlocks.STEP_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT_SLAB = ITEMS.register("asphalt_slab",
            () -> new BlockItem(ModBlocks.ASPHALT_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICK_SLAB = ITEMS.register("brick_slab",
            () -> new BlockItem(ModBlocks.BRICK_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RAW_WOOD_SLAB = ITEMS.register("raw_wood_slab",
            () -> new BlockItem(ModBlocks.RAW_WOOD_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SMOOTH_METAL_SLAB = ITEMS.register("smooth_metal_slab",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STEP = ITEMS.register("step",
            () -> new BlockItem(ModBlocks.STEP.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICKS = ITEMS.register("bricks",
            () -> new BlockItem(ModBlocks.BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new BlockItem(ModBlocks.IRON_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT_STAIRS = ITEMS.register("asphalt_stairs",
            () -> new BlockItem(ModBlocks.ASPHALT_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WHITE_LIGHTING = ITEMS.register("white_lighting",
            () -> new BlockItem(ModBlocks.WHITE_LIGHTING.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MONITOR = ITEMS.register("monitor",
            () -> new BlockItem(ModBlocks.MONITOR.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIT_MONITOR = ITEMS.register("lit_monitor",
            () -> new BlockItem(ModBlocks.LIT_MONITOR.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    //Netherrack Design will go here
    public static final RegistryObject<BlockItem> CAUTION_TAPE = ITEMS.register("caution_tape",
            () -> new BlockItem(ModBlocks.CAUTION_TAPE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> FLUORESCENT_PANEL = ITEMS.register("fluorescent_panel",
            () -> new BlockItem(ModBlocks.FLUORESCENT_PANEL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RAW_WOOD = ITEMS.register("raw_wood",
            () -> new BlockItem(ModBlocks.RAW_WOOD.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CONCRETE_BRICKS = ITEMS.register("concrete_bricks",
            () -> new BlockItem(ModBlocks.CONCRETE_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STRIPE_INTERSECTION = ITEMS.register("stripe_intersection",
            () -> new BlockItem(ModBlocks.STRIPE_INTERSECTION.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PIPE_INTERSECTION = ITEMS.register("pipe_intersection",
            () -> new BlockItem(ModBlocks.PIPE_INTERSECTION.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> COUNTERTOP = ITEMS.register("countertop",
            () -> new BlockItem(ModBlocks.COUNTERTOP.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CONCRETE = ITEMS.register("concrete",
            () -> new BlockItem(ModBlocks.CONCRETE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICK_STAIRS = ITEMS.register("brick_stairs",
            () -> new BlockItem(ModBlocks.BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RAW_WOOD_STAIRS = ITEMS.register("raw_wood_stairs",
            () -> new BlockItem(ModBlocks.RAW_WOOD_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SMOOTH_METAL = ITEMS.register("smooth_metal",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SMOOTH_METAL_STAIRS = ITEMS.register("smooth_metal_stairs",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GREEN_TILES = ITEMS.register("green_tiles",
            () -> new BlockItem(ModBlocks.GREEN_TILES.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICK_STAIRS = ITEMS.register("oak_brick_stairs",
            () -> new BlockItem(ModBlocks.OAK_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICK_STAIRS = ITEMS.register("spruce_brick_stairs",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_STAIRS = ITEMS.register("birch_brick_stairs",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_STAIRS = ITEMS.register("jungle_brick_stairs",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_STAIRS = ITEMS.register("acacia_brick_stairs",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_STAIRS = ITEMS.register("dark_oak_brick_stairs",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_STAIRS = ITEMS.register("crimson_brick_stairs",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICK_STAIRS = ITEMS.register("warped_brick_stairs",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICK_STAIRS = ITEMS.register("mangrove_brick_stairs",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_PLATE_STAIRS = ITEMS.register("iron_plate_stairs",
            () -> new BlockItem(ModBlocks.IRON_PLATE_STAIRS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_PLATE_SLAB = ITEMS.register("iron_plate_slab",
            () -> new BlockItem(ModBlocks.IRON_PLATE_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));

    //Decoration Blocks
    public static final RegistryObject<BlockItem> LANTERN = ITEMS.register("lantern",
            () -> new StandingAndWallBlockItem(ModBlocks.LANTERN.get(), ModBlocks.WALL_LANTERN.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRATE = ITEMS.register("crate",
            () -> new CrateBlockItem(ModBlocks.CRATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> TECH_ACCENT = ITEMS.register("tech_accent",
            () -> new BlockItem(ModBlocks.TECH_ACCENT.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> YOGIFIER = ITEMS.register("yogifier",
            () -> new BlockItem(ModBlocks.YOGIFIER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OVEN = ITEMS.register("oven",
            () -> new BlockItem(ModBlocks.OVEN.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LADDER = ITEMS.register("ladder",
            () -> new BlockItem(ModBlocks.LADDER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RECORD_PLAYER = ITEMS.register("record_player",
            () -> new BlockItem(ModBlocks.RECORD_PLAYER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICK_FENCE = ITEMS.register("oak_brick_fence",
            () -> new BlockItem(ModBlocks.OAK_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICK_FENCE = ITEMS.register("spruce_brick_fence",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_FENCE = ITEMS.register("birch_brick_fence",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_FENCE = ITEMS.register("jungle_brick_fence",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_FENCE = ITEMS.register("acacia_brick_fence",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_FENCE = ITEMS.register("dark_oak_brick_fence",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_FENCE = ITEMS.register("crimson_brick_fence",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICK_FENCE = ITEMS.register("warped_brick_fence",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICK_FENCE = ITEMS.register("mangrove_brick_fence",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPOTLIGHT = ITEMS.register("spotlight",
            () -> new BlockItem(ModBlocks.SPOTLIGHT.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MECHANICAL_CHAIN = ITEMS.register("mechanical_chain",
            () -> new BlockItem(ModBlocks.MECHANICAL_CHAIN.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MECHANICAL_VENT = ITEMS.register("mechanical_vent",
            () -> new BlockItem(ModBlocks.MECHANICAL_VENT.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GLASS_PANE = ITEMS.register("glass_pane",
            () -> new BlockItem(ModBlocks.GLASS_PANE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CURTAINS = ITEMS.register("curtains",
            () -> new BlockItem(ModBlocks.CURTAINS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SMOOTH_METAL_FENCE = ITEMS.register("smooth_metal_fence",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_FENCE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> TABLE = ITEMS.register("table",
            () -> new BlockItem(ModBlocks.TABLE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> FANCY_TABLE = ITEMS.register("fancy_table",
            () -> new BlockItem(ModBlocks.FANCY_TABLE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    //Fridge will go here when implemented
    public static final RegistryObject<BlockItem> ASPHALT_WALL = ITEMS.register("asphalt_wall",
            () -> new BlockItem(ModBlocks.ASPHALT_WALL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_PLATE_WALL = ITEMS.register("iron_plate_wall",
            () -> new BlockItem(ModBlocks.IRON_PLATE_WALL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICK_WALL = ITEMS.register("brick_wall",
            () -> new BlockItem(ModBlocks.BRICK_WALL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RAW_WOOD_WALL = ITEMS.register("raw_wood_wall",
            () -> new BlockItem(ModBlocks.RAW_WOOD_WALL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SMOOTH_METAL_WALL = ITEMS.register("smooth_metal_wall",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_WALL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BEIGE_PLASTIC_CARPET = ITEMS.register("beige_plastic_carpet",
            () -> new BlockItem(ModBlocks.BEIGE_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WHITE_PLASTIC_CARPET = ITEMS.register("white_plastic_carpet",
            () -> new BlockItem(ModBlocks.WHITE_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ORANGE_PLASTIC_CARPET = ITEMS.register("orange_plastic_carpet",
            () -> new BlockItem(ModBlocks.ORANGE_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MAGENTA_PLASTIC_CARPET = ITEMS.register("magenta_plastic_carpet",
            () -> new BlockItem(ModBlocks.MAGENTA_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIGHT_BLUE_PLASTIC_CARPET = ITEMS.register("light_blue_plastic_carpet",
            () -> new BlockItem(ModBlocks.LIGHT_BLUE_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GOLD_FILGAREE_CARPET = ITEMS.register("gold_filgaree_carpet",
            () -> new BlockItem(ModBlocks.GOLD_FILGAREE_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIME_PLASTIC_CARPET = ITEMS.register("lime_plastic_carpet",
            () -> new BlockItem(ModBlocks.LIME_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PINK_STUCCO_CARPET = ITEMS.register("pink_stucco_carpet",
            () -> new BlockItem(ModBlocks.PINK_STUCCO_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GRAY_STUCCO_CARPET = ITEMS.register("gray_stucco_carpet",
            () -> new BlockItem(ModBlocks.GRAY_STUCCO_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIGHT_GRAY_STUCCO_CARPET = ITEMS.register("light_gray_stucco_carpet",
            () -> new BlockItem(ModBlocks.LIGHT_GRAY_STUCCO_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CYAN_PLASTIC_CARPET = ITEMS.register("cyan_plastic_carpet",
            () -> new BlockItem(ModBlocks.CYAN_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> VIOLET_VELVET_CARPET = ITEMS.register("violet_velvet_carpet",
            () -> new BlockItem(ModBlocks.VIOLET_VELVET_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PLASTIC_CARPET = ITEMS.register("blue_plastic_carpet",
            () -> new BlockItem(ModBlocks.BLUE_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BROWN_STUCCO_CARPET = ITEMS.register("brown_stucco_carpet",
            () -> new BlockItem(ModBlocks.BROWN_STUCCO_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GREEN_PLASTIC_CARPET = ITEMS.register("green_plastic_carpet",
            () -> new BlockItem(ModBlocks.GREEN_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RED_PLASTIC_CARPET = ITEMS.register("red_plastic_carpet",
            () -> new BlockItem(ModBlocks.RED_PLASTIC_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLACK_MARBLE_CARPET = ITEMS.register("black_marble_carpet",
            () -> new BlockItem(ModBlocks.BLACK_MARBLE_CARPET.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    //Painting will go here when implemented
    public static final RegistryObject<Item> YOG_SIGN = ITEMS.register("yog_sign",
            () -> new SignItem(new Item.Properties().tab(YogMod.YOGTAB).stacksTo(16),
                    ModBlocks.YOG_SIGN.get(), ModBlocks.YOG_WALL_SIGN.get()));

    //Redstone
    public static final RegistryObject<BlockItem> WIRE = ITEMS.register("wire",
            () -> new BlockItem(ModBlocks.WIRE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LED = ITEMS.register("led",
            () -> new StandingAndWallBlockItem(ModBlocks.LED.get(), ModBlocks.WALL_LED.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DIODE = ITEMS.register("diode",
            () -> new BlockItem(ModBlocks.DIODE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> AIR_VENT = ITEMS.register("air_vent",
            () -> new BlockItem(ModBlocks.AIR_VENT.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PISTON = ITEMS.register("piston",
            () -> new BlockItem(ModBlocks.PISTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STICKY_PISTON = ITEMS.register("sticky_piston",
            () -> new BlockItem(ModBlocks.STICKY_PISTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DISPENSER = ITEMS.register("dispenser",
            () -> new BlockItem(ModBlocks.DISPENSER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CLASSIC_LEVER = ITEMS.register("classic_lever",
            () -> new BlockItem(ModBlocks.CLASSIC_LEVER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LEVER = ITEMS.register("lever",
            () -> new BlockItem(ModBlocks.LEVER.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DYNAMITE = ITEMS.register("dynamite",
            () -> new BlockItem(ModBlocks.DYNAMITE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LAMP = ITEMS.register("lamp",
            () -> new BlockItem(ModBlocks.LAMP.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CONSOLE = ITEMS.register("console",
            () -> new BlockItem(ModBlocks.CONSOLE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICK_BUTTON = ITEMS.register("oak_brick_button",
            () -> new BlockItem(ModBlocks.OAK_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICK_BUTTON = ITEMS.register("spruce_brick_button",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_BUTTON = ITEMS.register("birch_brick_button",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_BUTTON = ITEMS.register("jungle_brick_button",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_BUTTON = ITEMS.register("acacia_brick_button",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_BUTTON = ITEMS.register("dark_oak_brick_button",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_BUTTON = ITEMS.register("crimson_brick_button",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICK_BUTTON = ITEMS.register("warped_brick_button",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICK_BUTTON = ITEMS.register("mangrove_brick_button",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_BUTTON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICK_PRESSURE_PLATE = ITEMS.register("oak_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.OAK_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICK_PRESSURE_PLATE = ITEMS.register("spruce_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_PRESSURE_PLATE = ITEMS.register("birch_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_PRESSURE_PLATE = ITEMS.register("jungle_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_PRESSURE_PLATE = ITEMS.register("acacia_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_PRESSURE_PLATE = ITEMS.register("dark_oak_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_PRESSURE_PLATE = ITEMS.register("crimson_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICK_PRESSURE_PLATE = ITEMS.register("warped_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICK_PRESSURE_PLATE = ITEMS.register("mangrove_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_PRESSURE_PLATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_DOOR = ITEMS.register("iron_door",
            () -> new BlockItem(ModBlocks.IRON_DOOR.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WOODEN_DOOR = ITEMS.register("wooden_door",
            () -> new BlockItem(ModBlocks.WOODEN_DOOR.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> HATCH = ITEMS.register("hatch",
            () -> new BlockItem(ModBlocks.HATCH.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> OAK_BRICK_FENCE_GATE = ITEMS.register("oak_brick_fence_gate",
            () -> new BlockItem(ModBlocks.OAK_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> SPRUCE_BRICK_FENCE_GATE = ITEMS.register("spruce_brick_fence_gate",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_FENCE_GATE = ITEMS.register("birch_brick_fence_gate",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_FENCE_GATE = ITEMS.register("jungle_brick_fence_gate",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_FENCE_GATE = ITEMS.register("acacia_brick_fence_gate",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_FENCE_GATE = ITEMS.register("dark_oak_brick_fence_gate",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_FENCE_GATE = ITEMS.register("crimson_brick_fence_gate",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> WARPED_BRICK_FENCE_GATE = ITEMS.register("warped_brick_fence_gate",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MANGROVE_BRICK_FENCE_GATE = ITEMS.register("mangrove_brick_fence_gate",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_FENCE_GATE.get(), new Item.Properties().tab(YogMod.YOGTAB)));

    //Transportation
    public static final RegistryObject<BlockItem> POWERED_METROVOX_RAIL = ITEMS.register("powered_metrovox_rail",
            () -> new BlockItem(ModBlocks.POWERED_METROVOX_RAIL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> METROVOX_DETECTOR_RAIL = ITEMS.register("metrovox_detector_rail",
            () -> new BlockItem(ModBlocks.METROVOX_DETECTOR_RAIL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> METROVOX_RAIL = ITEMS.register("metrovox_rail",
            () -> new BlockItem(ModBlocks.METROVOX_RAIL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> METROVOX_ACTIVATOR_RAIL = ITEMS.register("metrovox_activator_rail",
            () -> new BlockItem(ModBlocks.METROVOX_ACTIVATOR_RAIL.get(), new Item.Properties().tab(YogMod.YOGTAB)));

    //Miscellaneous
    public static final RegistryObject<Item> COFFEE = ITEMS.register("coffee",
            () -> new Item(new Item.Properties().tab(YogMod.YOGTAB)
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .alwaysEat()
                            .build())));
    public static final RegistryObject<Item> HAMBURGER = ITEMS.register("hamburger",
            () -> new Item(new Item.Properties().tab(YogMod.YOGTAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.8f)
                            .alwaysEat()
                            .build())));

    //Foodstuffs
    public static final RegistryObject<Item> JAFFA = ITEMS.register("jaffa",
            () -> new Item(new Item.Properties().tab(YogMod.YOGTAB)
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(5)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build())));

    //Tools
    public static final RegistryObject<Item> PENCIL = ITEMS.register("pencil",
            () -> new Item(new Item.Properties().tab(YogMod.YOGTAB)));

    //Combat


    //Brewing
    public static final RegistryObject<BlockItem> SINK = ITEMS.register("sink",
            () -> new BlockItem(ModBlocks.SINK.get(), new Item.Properties().tab(YogMod.YOGTAB)));

    //Not in YogTab
    public static final RegistryObject<BlockItem> BEIGE_WOOL = ITEMS.register("beige_wool",
            () -> new BlockItem(ModBlocks.BEIGE_WOOL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> BEIGE_CARPET = ITEMS.register("beige_carpet",
            () -> new BlockItem(ModBlocks.BEIGE_CARPET.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BEIGE_DYE = ITEMS.register("beige_dye",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    //Methods

}
