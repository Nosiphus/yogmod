package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.nosiphus.yogmod.world.item.ModCreativeModeTabs.addToTab;

public class ModItems {

    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems("yogmod");

    //Building Blocks
    public static final DeferredItem<BlockItem> OAK_BRICKS = addToTab(ITEMS.register("oak_bricks",
            () -> new BlockItem(ModBlocks.OAK_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OAK_BRICK_STAIRS = addToTab(ITEMS.register("oak_brick_stairs",
            () -> new BlockItem(ModBlocks.OAK_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OAK_BRICK_SLAB = addToTab(ITEMS.register("oak_brick_slab",
            () -> new BlockItem(ModBlocks.OAK_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OAK_BRICK_FENCE = addToTab(ITEMS.register("oak_brick_fence",
            () -> new BlockItem(ModBlocks.OAK_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OAK_BRICK_FENCE_GATE = addToTab(ITEMS.register("oak_brick_fence_gate",
            () -> new BlockItem(ModBlocks.OAK_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OAK_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("oak_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.OAK_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OAK_BRICK_BUTTON = addToTab(ITEMS.register("oak_brick_button",
            () -> new BlockItem(ModBlocks.OAK_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICKS = addToTab(ITEMS.register("spruce_bricks",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICK_STAIRS = addToTab(ITEMS.register("spruce_brick_stairs",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICK_SLAB = addToTab(ITEMS.register("spruce_brick_slab",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICK_FENCE = addToTab(ITEMS.register("spruce_brick_fence",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICK_FENCE_GATE = addToTab(ITEMS.register("spruce_brick_fence_gate",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("spruce_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPRUCE_BRICK_BUTTON = addToTab(ITEMS.register("spruce_brick_button",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICKS = addToTab(ITEMS.register("birch_bricks",
            () -> new BlockItem(ModBlocks.BIRCH_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICK_STAIRS = addToTab(ITEMS.register("birch_brick_stairs",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICK_SLAB = addToTab(ITEMS.register("birch_brick_slab",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICK_FENCE = addToTab(ITEMS.register("birch_brick_fence",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICK_FENCE_GATE = addToTab(ITEMS.register("birch_brick_fence_gate",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("birch_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BIRCH_BRICK_BUTTON = addToTab(ITEMS.register("birch_brick_button",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICKS = addToTab(ITEMS.register("jungle_bricks",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICK_STAIRS = addToTab(ITEMS.register("jungle_brick_stairs",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICK_SLAB = addToTab(ITEMS.register("jungle_brick_slab",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICK_FENCE = addToTab(ITEMS.register("jungle_brick_fence",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICK_FENCE_GATE = addToTab(ITEMS.register("jungle_brick_fence_gate",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("jungle_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> JUNGLE_BRICK_BUTTON = addToTab(ITEMS.register("jungle_brick_button",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICKS = addToTab(ITEMS.register("acacia_bricks",
            () -> new BlockItem(ModBlocks.ACACIA_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICK_STAIRS = addToTab(ITEMS.register("acacia_brick_stairs",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICK_SLAB = addToTab(ITEMS.register("acacia_brick_slab",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICK_FENCE = addToTab(ITEMS.register("acacia_brick_fence",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICK_FENCE_GATE = addToTab(ITEMS.register("acacia_brick_fence_gate",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("acacia_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ACACIA_BRICK_BUTTON = addToTab(ITEMS.register("acacia_brick_button",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICKS = addToTab(ITEMS.register("dark_oak_bricks",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICK_STAIRS = addToTab(ITEMS.register("dark_oak_brick_stairs",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICK_SLAB = addToTab(ITEMS.register("dark_oak_brick_slab",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICK_FENCE = addToTab(ITEMS.register("dark_oak_brick_fence",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICK_FENCE_GATE = addToTab(ITEMS.register("dark_oak_brick_fence_gate",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("dark_oak_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_OAK_BRICK_BUTTON = addToTab(ITEMS.register("dark_oak_brick_button",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICKS = addToTab(ITEMS.register("mangrove_bricks",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICK_STAIRS = addToTab(ITEMS.register("mangrove_brick_stairs",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICK_SLAB = addToTab(ITEMS.register("mangrove_brick_slab",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICK_FENCE = addToTab(ITEMS.register("mangrove_brick_fence",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICK_FENCE_GATE = addToTab(ITEMS.register("mangrove_brick_fence_gate",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("mangrove_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MANGROVE_BRICK_BUTTON = addToTab(ITEMS.register("mangrove_brick_button",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICKS = addToTab(ITEMS.register("cherry_bricks",
            () -> new BlockItem(ModBlocks.CHERRY_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICK_STAIRS = addToTab(ITEMS.register("cherry_brick_stairs",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICK_SLAB = addToTab(ITEMS.register("cherry_brick_slab",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICK_FENCE = addToTab(ITEMS.register("cherry_brick_fence",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICK_FENCE_GATE = addToTab(ITEMS.register("cherry_brick_fence_gate",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("cherry_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CHERRY_BRICK_BUTTON = addToTab(ITEMS.register("cherry_brick_button",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICKS = addToTab(ITEMS.register("bamboo_bricks",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICK_STAIRS = addToTab(ITEMS.register("bamboo_brick_stairs",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICK_SLAB = addToTab(ITEMS.register("bamboo_brick_slab",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICK_FENCE = addToTab(ITEMS.register("bamboo_brick_fence",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICK_FENCE_GATE = addToTab(ITEMS.register("bamboo_brick_fence_gate",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("bamboo_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BAMBOO_BRICK_BUTTON = addToTab(ITEMS.register("bamboo_brick_button",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICKS = addToTab(ITEMS.register("crimson_bricks",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICK_STAIRS = addToTab(ITEMS.register("crimson_brick_stairs",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICK_SLAB = addToTab(ITEMS.register("crimson_brick_slab",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICK_FENCE = addToTab(ITEMS.register("crimson_brick_fence",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICK_FENCE_GATE = addToTab(ITEMS.register("crimson_brick_fence_gate",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("crimson_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CRIMSON_BRICK_BUTTON = addToTab(ITEMS.register("crimson_brick_button",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICKS = addToTab(ITEMS.register("warped_bricks",
            () -> new BlockItem(ModBlocks.WARPED_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICK_STAIRS = addToTab(ITEMS.register("warped_brick_stairs",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICK_SLAB = addToTab(ITEMS.register("warped_brick_slab",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICK_FENCE = addToTab(ITEMS.register("warped_brick_fence",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICK_FENCE_GATE = addToTab(ITEMS.register("warped_brick_fence_gate",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICK_PRESSURE_PLATE = addToTab(ITEMS.register("warped_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WARPED_BRICK_BUTTON = addToTab(ITEMS.register("warped_brick_button",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_BUTTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WOODEN_DOOR = addToTab(ITEMS.register("wooden_door",
            () -> new BlockItem(ModBlocks.WOODEN_DOOR.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> HATCH = addToTab(ITEMS.register("hatch",
            () -> new BlockItem(ModBlocks.HATCH.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ASPHALT = addToTab(ITEMS.register("asphalt",
            () -> new BlockItem(ModBlocks.ASPHALT.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ASPHALT_STAIRS = addToTab(ITEMS.register("asphalt_stairs",
            () -> new BlockItem(ModBlocks.ASPHALT_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ASPHALT_SLAB = addToTab(ITEMS.register("asphalt_slab",
            () -> new BlockItem(ModBlocks.ASPHALT_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ASPHALT_WALL = addToTab(ITEMS.register("asphalt_wall",
            () -> new BlockItem(ModBlocks.ASPHALT_WALL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> IRON_PLATE = addToTab(ITEMS.register("iron_plate",
            () -> new BlockItem(ModBlocks.IRON_PLATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> IRON_PLATE_STAIRS = addToTab(ITEMS.register("iron_plate_stairs",
            () -> new BlockItem(ModBlocks.IRON_PLATE_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> IRON_PLATE_SLAB = addToTab(ITEMS.register("iron_plate_slab",
            () -> new BlockItem(ModBlocks.IRON_PLATE_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> IRON_PLATE_WALL = addToTab(ITEMS.register("iron_plate_wall",
            () -> new BlockItem(ModBlocks.IRON_PLATE_WALL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> STEP = addToTab(ITEMS.register("step",
            () -> new BlockItem(ModBlocks.STEP.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> STEP_SLAB = addToTab(ITEMS.register("step_slab",
            () -> new BlockItem(ModBlocks.STEP_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RAW_WOOD = addToTab(ITEMS.register("raw_wood",
            () -> new BlockItem(ModBlocks.RAW_WOOD.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RAW_WOOD_STAIRS = addToTab(ITEMS.register("raw_wood_stairs",
            () -> new BlockItem(ModBlocks.RAW_WOOD_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RAW_WOOD_SLAB = addToTab(ITEMS.register("raw_wood_slab",
            () -> new BlockItem(ModBlocks.RAW_WOOD_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RAW_WOOD_WALL = addToTab(ITEMS.register("raw_wood_wall",
            () -> new BlockItem(ModBlocks.RAW_WOOD_WALL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CONCRETE_BRICKS = addToTab(ITEMS.register("concrete_bricks",
            () -> new BlockItem(ModBlocks.CONCRETE_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MECHANICAL_CHAIN = addToTab(ITEMS.register("mechanical_chain",
            () -> new BlockItem(ModBlocks.MECHANICAL_CHAIN.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MECHANICAL_VENT = addToTab(ITEMS.register("mechanical_vent",
            () -> new BlockItem(ModBlocks.MECHANICAL_VENT.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> AIR_VENT = addToTab(ITEMS.register("air_vent",
            () -> new BlockItem(ModBlocks.AIR_VENT.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WHITE_MARBLE = addToTab(ITEMS.register("white_marble",
            () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BRICKS = addToTab(ITEMS.register("bricks",
            () -> new BlockItem(ModBlocks.BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BRICK_STAIRS = addToTab(ITEMS.register("brick_stairs",
            () -> new BlockItem(ModBlocks.BRICK_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BRICK_SLAB = addToTab(ITEMS.register("brick_slab",
            () -> new BlockItem(ModBlocks.BRICK_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BRICK_WALL = addToTab(ITEMS.register("brick_wall",
            () -> new BlockItem(ModBlocks.BRICK_WALL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SPOTLIGHT = addToTab(ITEMS.register("spotlight",
            () -> new BlockItem(ModBlocks.SPOTLIGHT.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GRAY_AZTEC = addToTab(ITEMS.register("gray_aztec",
            () -> new BlockItem(ModBlocks.GRAY_AZTEC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SMOOTH_METAL = addToTab(ITEMS.register("smooth_metal",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SMOOTH_METAL_STAIRS = addToTab(ITEMS.register("smooth_metal_stairs",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_STAIRS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SMOOTH_METAL_SLAB = addToTab(ITEMS.register("smooth_metal_slab",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_SLAB.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SMOOTH_METAL_WALL = addToTab(ITEMS.register("smooth_metal_wall",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_WALL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SMOOTH_METAL_FENCE = addToTab(ITEMS.register("smooth_metal_fence",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_FENCE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SMOOTH_METAL_FINIAL = addToTab(ITEMS.register("smooth_metal_finial",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_FINIAL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> IRON_STACK = addToTab(ITEMS.register("iron_stack",
            () -> new BlockItem(ModBlocks.IRON_STACK.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> IRON_DOOR = addToTab(ITEMS.register("iron_door",
            () -> new BlockItem(ModBlocks.IRON_DOOR.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LINOLEUM_TILE = addToTab(ITEMS.register("linoleum_tile",
            () -> new BlockItem(ModBlocks.LINOLEUM_TILE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GREEN_TILES = addToTab(ITEMS.register("green_tiles",
            () -> new BlockItem(ModBlocks.GREEN_TILES.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> TILE_MOSAIC = addToTab(ITEMS.register("tile_mosaic",
            () -> new BlockItem(ModBlocks.TILE_MOSAIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLUE_PANELING = addToTab(ITEMS.register("blue_paneling",
            () -> new BlockItem(ModBlocks.BLUE_PANELING.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> PIPE = addToTab(ITEMS.register("pipe",
            () -> new BlockItem(ModBlocks.PIPE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> PIPE_INTERSECTION = addToTab(ITEMS.register("pipe_intersection",
            () -> new BlockItem(ModBlocks.PIPE_INTERSECTION.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RUSTY_IRON = addToTab(ITEMS.register("rusty_iron",
            () -> new BlockItem(ModBlocks.RUSTY_IRON.get(), new Item.Properties())));

    //Colored Blocks
    public static final DeferredItem<BlockItem> WHITE_PLASTIC = addToTab(ITEMS.register("white_plastic",
            () -> new BlockItem(ModBlocks.WHITE_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIGHT_GRAY_STUCCO = addToTab(ITEMS.register("light_gray_stucco",
            () -> new BlockItem(ModBlocks.LIGHT_GRAY_STUCCO.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GRAY_STUCCO = addToTab(ITEMS.register("gray_stucco",
            () -> new BlockItem(ModBlocks.GRAY_STUCCO.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLACK_MARBLE = addToTab(ITEMS.register("black_marble",
            () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BROWN_STUCCO = addToTab(ITEMS.register("brown_stucco",
            () -> new BlockItem(ModBlocks.BROWN_STUCCO.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RED_PLASTIC = addToTab(ITEMS.register("red_plastic",
            () -> new BlockItem(ModBlocks.RED_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RED_VELVET = addToTab(ITEMS.register("red_velvet",
            () -> new BlockItem(ModBlocks.RED_VELVET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ORANGE_PLASTIC = addToTab(ITEMS.register("orange_plastic",
            () -> new BlockItem(ModBlocks.ORANGE_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_PLASTIC = addToTab(ITEMS.register("beige_plastic",
            () -> new BlockItem(ModBlocks.BEIGE_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GOLD_FILGAREE = addToTab(ITEMS.register("gold_filgaree",
            () -> new BlockItem(ModBlocks.GOLD_FILGAREE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIME_PLASTIC = addToTab(ITEMS.register("lime_plastic",
            () -> new BlockItem(ModBlocks.LIME_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GREEN_PLASTIC = addToTab(ITEMS.register("green_plastic",
            () -> new BlockItem(ModBlocks.GREEN_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GREEN_VELVET = addToTab(ITEMS.register("green_velvet",
            () -> new BlockItem(ModBlocks.GREEN_VELVET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CYAN_PLASTIC = addToTab(ITEMS.register("cyan_plastic",
            () -> new BlockItem(ModBlocks.CYAN_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIGHT_BLUE_PLASTIC = addToTab(ITEMS.register("light_blue_plastic",
            () -> new BlockItem(ModBlocks.LIGHT_BLUE_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLUE_PLASTIC = addToTab(ITEMS.register("blue_plastic",
            () -> new BlockItem(ModBlocks.BLUE_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLUE_VELVET = addToTab(ITEMS.register("blue_velvet",
            () -> new BlockItem(ModBlocks.BLUE_VELVET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> VIOLET_VELVET = addToTab(ITEMS.register("violet_velvet",
            () -> new BlockItem(ModBlocks.VIOLET_VELVET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MAGENTA_PLASTIC = addToTab(ITEMS.register("magenta_plastic",
            () -> new BlockItem(ModBlocks.MAGENTA_PLASTIC.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> PINK_STUCCO = addToTab(ITEMS.register("pink_stucco",
            () -> new BlockItem(ModBlocks.PINK_STUCCO.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WHITE_PLASTIC_CARPET = addToTab(ITEMS.register("white_plastic_carpet",
            () -> new BlockItem(ModBlocks.WHITE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIGHT_GRAY_STUCCO_CARPET = addToTab(ITEMS.register("light_gray_stucco_carpet",
            () -> new BlockItem(ModBlocks.LIGHT_GRAY_STUCCO_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GRAY_STUCCO_CARPET = addToTab(ITEMS.register("gray_stucco_carpet",
            () -> new BlockItem(ModBlocks.GRAY_STUCCO_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLACK_MARBLE_CARPET = addToTab(ITEMS.register("black_marble_carpet",
            () -> new BlockItem(ModBlocks.BLACK_MARBLE_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BROWN_STUCCO_CARPET = addToTab(ITEMS.register("brown_stucco_carpet",
            () -> new BlockItem(ModBlocks.BROWN_STUCCO_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RED_PLASTIC_CARPET = addToTab(ITEMS.register("red_plastic_carpet",
            () -> new BlockItem(ModBlocks.RED_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RED_VELVET_CARPET = addToTab(ITEMS.register("red_velvet_carpet",
            () -> new BlockItem(ModBlocks.RED_VELVET_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> ORANGE_PLASTIC_CARPET = addToTab(ITEMS.register("orange_plastic_carpet",
            () -> new BlockItem(ModBlocks.ORANGE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_PLASTIC_CARPET = addToTab(ITEMS.register("beige_plastic_carpet",
            () -> new BlockItem(ModBlocks.BEIGE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GOLD_FILGAREE_CARPET = addToTab(ITEMS.register("gold_filgaree_carpet",
            () -> new BlockItem(ModBlocks.GOLD_FILGAREE_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIME_PLASTIC_CARPET = addToTab(ITEMS.register("lime_plastic_carpet",
            () -> new BlockItem(ModBlocks.LIME_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GREEN_PLASTIC_CARPET = addToTab(ITEMS.register("green_plastic_carpet",
            () -> new BlockItem(ModBlocks.GREEN_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GREEN_VELVET_CARPET = addToTab(ITEMS.register("green_velvet_carpet",
            () -> new BlockItem(ModBlocks.GREEN_VELVET_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CYAN_PLASTIC_CARPET = addToTab(ITEMS.register("cyan_plastic_carpet",
            () -> new BlockItem(ModBlocks.CYAN_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIGHT_BLUE_PLASTIC_CARPET = addToTab(ITEMS.register("light_blue_plastic_carpet",
            () -> new BlockItem(ModBlocks.LIGHT_BLUE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLUE_PLASTIC_CARPET = addToTab(ITEMS.register("blue_plastic_carpet",
            () -> new BlockItem(ModBlocks.BLUE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BLUE_VELVET_CARPET = addToTab(ITEMS.register("blue_velvet_carpet",
            () -> new BlockItem(ModBlocks.BLUE_VELVET_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> VIOLET_VELVET_CARPET = addToTab(ITEMS.register("violet_velvet_carpet",
            () -> new BlockItem(ModBlocks.VIOLET_VELVET_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MAGENTA_PLASTIC_CARPET = addToTab(ITEMS.register("magenta_plastic_carpet",
            () -> new BlockItem(ModBlocks.MAGENTA_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> PINK_STUCCO_CARPET = addToTab(ITEMS.register("pink_stucco_carpet",
            () -> new BlockItem(ModBlocks.PINK_STUCCO_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CONCRETE = addToTab(ITEMS.register("concrete",
            () -> new BlockItem(ModBlocks.CONCRETE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> STRIPE = addToTab(ITEMS.register("stripe",
            () -> new BlockItem(ModBlocks.STRIPE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> STRIPE_INTERSECTION = addToTab(ITEMS.register("stripe_intersection",
            () -> new BlockItem(ModBlocks.STRIPE_INTERSECTION.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CORRUGATED_STEEL = addToTab(ITEMS.register("corrugated_steel",
            () -> new BlockItem(ModBlocks.CORRUGATED_STEEL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> STORAGE_CRATE = addToTab(ITEMS.register("storage_crate",
            () -> new BlockItem(ModBlocks.STORAGE_CRATE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CLAY_TILE = addToTab(ITEMS.register("clay_tile",
            () -> new BlockItem(ModBlocks.CLAY_TILE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SHALE = addToTab(ITEMS.register("shale",
            () -> new BlockItem(ModBlocks.SHALE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GLASS = addToTab(ITEMS.register("glass",
            () -> new BlockItem(ModBlocks.GLASS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> GLASS_PANE = addToTab(ITEMS.register("glass_pane",
            () -> new BlockItem(ModBlocks.GLASS_PANE.get(), new Item.Properties())));

    //Natural Blocks
    public static final DeferredItem<BlockItem> REINFORCED_PANELING = addToTab(ITEMS.register("reinforced_paneling",
            () -> new BlockItem(ModBlocks.REINFORCED_PANELING.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> THIN_STRIPE = addToTab(ITEMS.register("thin_stripe",
            () -> new BlockItem(ModBlocks.THIN_STRIPE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CAUTION_TAPE = addToTab(ITEMS.register("caution_tape",
            () -> new BlockItem(ModBlocks.CAUTION_TAPE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DARK_STONE_BRICKS = addToTab(ITEMS.register("dark_stone_bricks",
            () -> new BlockItem(ModBlocks.DARK_STONE_BRICKS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DECORATIVE_CONCRETE = addToTab(ITEMS.register("decorative_concrete",
            () -> new BlockItem(ModBlocks.DECORATIVE_CONCRETE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> PILLAR = addToTab(ITEMS.register("pillar",
            () -> new BlockItem(ModBlocks.PILLAR.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CIRCUITRY = addToTab(ITEMS.register("circuitry",
            () -> new BlockItem(ModBlocks.CIRCUITRY.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIMESTONE_BRICK = addToTab(ITEMS.register("limestone_brick",
            () -> new BlockItem(ModBlocks.LIMESTONE_BRICK.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CORRUGATED_COPPER = addToTab(ITEMS.register("corrugated_copper",
            () -> new BlockItem(ModBlocks.CORRUGATED_COPPER.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> RIVETED_STEEL = addToTab(ITEMS.register("riveted_steel",
            () -> new BlockItem(ModBlocks.RIVETED_STEEL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> FLUORESCENT_PANEL = addToTab(ITEMS.register("fluorescent_panel",
            () -> new BlockItem(ModBlocks.FLUORESCENT_PANEL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MECHANICAL = addToTab(ITEMS.register("mechanical",
            () -> new BlockItem(ModBlocks.MECHANICAL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CURTAINS = addToTab(ITEMS.register("curtains",
            () -> new BlockItem(ModBlocks.CURTAINS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> COUNTERTOP = addToTab(ITEMS.register("countertop",
            () -> new BlockItem(ModBlocks.COUNTERTOP.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> WHITE_LIGHTING = addToTab(ITEMS.register("white_lighting",
            () -> new BlockItem(ModBlocks.WHITE_LIGHTING.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> MONITOR = addToTab(ITEMS.register("monitor",
            () -> new BlockItem(ModBlocks.MONITOR.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LIT_MONITOR = addToTab(ITEMS.register("lit_monitor",
            () -> new BlockItem(ModBlocks.LIT_MONITOR.get(), new Item.Properties())));

    //Functional Blocks
    public static final DeferredItem<BlockItem> LANTERN = addToTab(ITEMS.register("lantern",
            () -> new StandingAndWallBlockItem(ModBlocks.LANTERN.get(), ModBlocks.WALL_LANTERN.get(), new Item.Properties(), Direction.DOWN)));
    public static final DeferredItem<BlockItem> LED = addToTab(ITEMS.register("led",
            () -> new StandingAndWallBlockItem(ModBlocks.LED.get(), ModBlocks.WALL_LED.get(), new Item.Properties(), Direction.DOWN)));
    public static final DeferredItem<BlockItem> LAMP = addToTab(ITEMS.register("lamp",
            () -> new BlockItem(ModBlocks.LAMP.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> TECH_ACCENT = addToTab(ITEMS.register("tech_accent",
            () -> new BlockItem(ModBlocks.TECH_ACCENT.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> YOGIFIER = addToTab(ITEMS.register("yogifier",
            () -> new BlockItem(ModBlocks.YOGIFIER.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> OVEN = addToTab(ITEMS.register("oven",
            () -> new BlockItem(ModBlocks.OVEN.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CONSOLE = addToTab(ITEMS.register("console",
            () -> new BlockItem(ModBlocks.CONSOLE.get(), new Item.Properties())));
    //Record Player will go here
    public static final DeferredItem<BlockItem> TABLE = addToTab(ITEMS.register("table",
            () -> new BlockItem(ModBlocks.TABLE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> SINK = addToTab(ITEMS.register("sink",
            () -> new BlockItem(ModBlocks.SINK.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LADDER = addToTab(ITEMS.register("ladder",
            () -> new BlockItem(ModBlocks.LADDER.get(), new Item.Properties())));

    //Redstone Blocks
    public static final DeferredItem<BlockItem> WIRE = addToTab(ITEMS.register("wire",
            () -> new BlockItem(ModBlocks.WIRE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DIODE = addToTab(ITEMS.register("diode",
            () -> new BlockItem(ModBlocks.DIODE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CORRUGATED_REDSTONE = addToTab(ITEMS.register("corrugated_redstone",
            () -> new BlockItem(ModBlocks.CORRUGATED_REDSTONE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> CLASSIC_LEVER = addToTab(ITEMS.register("classic_lever",
            () -> new BlockItem(ModBlocks.CLASSIC_LEVER.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> LEVER = addToTab(ITEMS.register("lever",
            () -> new BlockItem(ModBlocks.LEVER.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> PISTON = addToTab(ITEMS.register("piston",
            () -> new BlockItem(ModBlocks.PISTON.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> STICKY_PISTON = addToTab(ITEMS.register("sticky_piston",
            () -> new BlockItem(ModBlocks.STICKY_PISTON.get(), new Item.Properties())));

    public static final DeferredItem<BlockItem> METROVOX_RAIL = addToTab(ITEMS.register("metrovox_rail",
            () -> new BlockItem(ModBlocks.METROVOX_RAIL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> POWERED_METROVOX_RAIL = addToTab(ITEMS.register("powered_metrovox_rail",
            () -> new BlockItem(ModBlocks.POWERED_METROVOX_RAIL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> METROVOX_DETECTOR_RAIL = addToTab(ITEMS.register("metrovox_detector_rail",
            () -> new BlockItem(ModBlocks.METROVOX_DETECTOR_RAIL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> METROVOX_ACTIVATOR_RAIL = addToTab(ITEMS.register("metrovox_activator_rail",
            () -> new BlockItem(ModBlocks.METROVOX_ACTIVATOR_RAIL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> DYNAMITE = addToTab(ITEMS.register("dynamite",
            () -> new BlockItem(ModBlocks.DYNAMITE.get(), new Item.Properties())));

    //Tools & Utilities
    public static final DeferredItem<Item> PENCIL = addToTab(ITEMS.register("pencil",
            () -> new AxeItem(ModTiers.PENCIL, new Item.Properties().attributes(AxeItem.createAttributes(Tiers.WOOD, 6.0F, -3.2F)))));

    //Combat
    public static final DeferredItem<Item> LASER = addToTab(ITEMS.register("laser",
            () -> new SwordItem(ModTiers.LASER, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.4F)))));
    public static final DeferredItem<ArmorItem> SUNGLASSES = addToTab(ITEMS.register("sunglasses",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> SUIT_JACKET = addToTab(ITEMS.register("suit_jacket",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> SUIT_PANTS = addToTab(ITEMS.register("suit_pants",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> SPATTERDASH_SHOES = addToTab(ITEMS.register("spatterdash_shoes",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> HARD_HAT = addToTab(ITEMS.register("hard_hat",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> SAFETY_VEST = addToTab(ITEMS.register("safety_vest",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> SEWER_WADERS = addToTab(ITEMS.register("sewer_waders",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> STEELTOE_BOOTS = addToTab(ITEMS.register("steeltoe_boots",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> RIOT_HELMET = addToTab(ITEMS.register("riot_helmet",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> RIOT_VEST = addToTab(ITEMS.register("riot_vest",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> RIOT_PANTS = addToTab(ITEMS.register("riot_pants",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))));
    public static final DeferredItem<ArmorItem> RIOT_BOOTS = addToTab(ITEMS.register("riot_boots",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1))));

    //Food & Drinks
    public static final DeferredItem<Item> COFFEE = addToTab(ITEMS.register("coffee",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(5)
                            .saturationModifier(0.2f)
                            .alwaysEdible()
                            .build()))));
    public static final DeferredItem<Item> HAMBURGER = addToTab(ITEMS.register("hamburger",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.8f)
                            .alwaysEdible()
                            .build()))));
    public static final DeferredItem<Item> JAFFA = addToTab(ITEMS.register("jaffa",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(5)
                            .saturationModifier(0.3f)
                            .alwaysEdible()
                            .build()))));

    //Not in YogTab
    public static final DeferredItem<BlockItem> BEIGE_WOOL = addToTab(ITEMS.register("beige_wool",
            () -> new BlockItem(ModBlocks.BEIGE_WOOL.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_CARPET = addToTab(ITEMS.register("beige_carpet",
            () -> new BlockItem(ModBlocks.BEIGE_CARPET.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_TERRACOTTA = addToTab(ITEMS.register("beige_terracotta",
            () -> new BlockItem(ModBlocks.BEIGE_TERRACOTTA.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_CONCRETE = addToTab(ITEMS.register("beige_concrete",
            () -> new BlockItem(ModBlocks.BEIGE_CONCRETE.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_CONCRETE_POWDER = addToTab(ITEMS.register("beige_concrete_powder",
            () -> new BlockItem(ModBlocks.BEIGE_CONCRETE_POWDER.get(), new Item.Properties())));
    //Beige Glazed Terracotta
    public static final DeferredItem<BlockItem> BEIGE_STAINED_GLASS = addToTab(ITEMS.register("beige_stained_glass",
            () -> new BlockItem(ModBlocks.BEIGE_STAINED_GLASS.get(), new Item.Properties())));
    public static final DeferredItem<BlockItem> BEIGE_STAINED_GLASS_PANE = addToTab(ITEMS.register("beige_stained_glass_pane",
            () -> new BlockItem(ModBlocks.BEIGE_STAINED_GLASS_PANE.get(), new Item.Properties())));
    //Beige Shulker Box
    //Beige Bed
    //Beige Candle
    //Beige Banner
    public static final DeferredItem<Item> BEIGE_DYE = addToTab(ITEMS.register("beige_dye",
            () -> new Item(new Item.Properties())));

    //Methods

}
