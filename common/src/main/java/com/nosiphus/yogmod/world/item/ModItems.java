package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.platform.Services;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

import static com.nosiphus.yogmod.world.item.ModCreativeModeTabs.addToTab;

public class ModItems {

    public static void init() {

    }

    private static <T extends Item> Supplier<T> register(String name, Supplier<T> itemSupplier) {
        return Services.REGISTRY.registerItem(name, itemSupplier);
    }

    // Building Blocks
    public static final Supplier<BlockItem> OAK_BRICKS = addToTab(register("oak_bricks",
            () -> new BlockItem(ModBlocks.OAK_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OAK_BRICK_STAIRS = addToTab(register("oak_brick_stairs",
            () -> new BlockItem(ModBlocks.OAK_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OAK_BRICK_SLAB = addToTab(register("oak_brick_slab",
            () -> new BlockItem(ModBlocks.OAK_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OAK_BRICK_FENCE = addToTab(register("oak_brick_fence",
            () -> new BlockItem(ModBlocks.OAK_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OAK_BRICK_FENCE_GATE = addToTab(register("oak_brick_fence_gate",
            () -> new BlockItem(ModBlocks.OAK_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OAK_BRICK_PRESSURE_PLATE = addToTab(register("oak_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.OAK_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OAK_BRICK_BUTTON = addToTab(register("oak_brick_button",
            () -> new BlockItem(ModBlocks.OAK_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICKS = addToTab(register("spruce_bricks",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICK_STAIRS = addToTab(register("spruce_brick_stairs",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICK_SLAB = addToTab(register("spruce_brick_slab",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICK_FENCE = addToTab(register("spruce_brick_fence",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICK_FENCE_GATE = addToTab(register("spruce_brick_fence_gate",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICK_PRESSURE_PLATE = addToTab(register("spruce_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPRUCE_BRICK_BUTTON = addToTab(register("spruce_brick_button",
            () -> new BlockItem(ModBlocks.SPRUCE_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICKS = addToTab(register("birch_bricks",
            () -> new BlockItem(ModBlocks.BIRCH_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICK_STAIRS = addToTab(register("birch_brick_stairs",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICK_SLAB = addToTab(register("birch_brick_slab",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICK_FENCE = addToTab(register("birch_brick_fence",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICK_FENCE_GATE = addToTab(register("birch_brick_fence_gate",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICK_PRESSURE_PLATE = addToTab(register("birch_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BIRCH_BRICK_BUTTON = addToTab(register("birch_brick_button",
            () -> new BlockItem(ModBlocks.BIRCH_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICKS = addToTab(register("jungle_bricks",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICK_STAIRS = addToTab(register("jungle_brick_stairs",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICK_SLAB = addToTab(register("jungle_brick_slab",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICK_FENCE = addToTab(register("jungle_brick_fence",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICK_FENCE_GATE = addToTab(register("jungle_brick_fence_gate",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICK_PRESSURE_PLATE = addToTab(register("jungle_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> JUNGLE_BRICK_BUTTON = addToTab(register("jungle_brick_button",
            () -> new BlockItem(ModBlocks.JUNGLE_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICKS = addToTab(register("acacia_bricks",
            () -> new BlockItem(ModBlocks.ACACIA_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICK_STAIRS = addToTab(register("acacia_brick_stairs",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICK_SLAB = addToTab(register("acacia_brick_slab",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICK_FENCE = addToTab(register("acacia_brick_fence",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICK_FENCE_GATE = addToTab(register("acacia_brick_fence_gate",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICK_PRESSURE_PLATE = addToTab(register("acacia_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ACACIA_BRICK_BUTTON = addToTab(register("acacia_brick_button",
            () -> new BlockItem(ModBlocks.ACACIA_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICKS = addToTab(register("dark_oak_bricks",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICK_STAIRS = addToTab(register("dark_oak_brick_stairs",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICK_SLAB = addToTab(register("dark_oak_brick_slab",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICK_FENCE = addToTab(register("dark_oak_brick_fence",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICK_FENCE_GATE = addToTab(register("dark_oak_brick_fence_gate",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICK_PRESSURE_PLATE = addToTab(register("dark_oak_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_OAK_BRICK_BUTTON = addToTab(register("dark_oak_brick_button",
            () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICKS = addToTab(register("mangrove_bricks",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICK_STAIRS = addToTab(register("mangrove_brick_stairs",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICK_SLAB = addToTab(register("mangrove_brick_slab",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICK_FENCE = addToTab(register("mangrove_brick_fence",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICK_FENCE_GATE = addToTab(register("mangrove_brick_fence_gate",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICK_PRESSURE_PLATE = addToTab(register("mangrove_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MANGROVE_BRICK_BUTTON = addToTab(register("mangrove_brick_button",
            () -> new BlockItem(ModBlocks.MANGROVE_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICKS = addToTab(register("cherry_bricks",
            () -> new BlockItem(ModBlocks.CHERRY_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICK_STAIRS = addToTab(register("cherry_brick_stairs",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICK_SLAB = addToTab(register("cherry_brick_slab",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICK_FENCE = addToTab(register("cherry_brick_fence",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICK_FENCE_GATE = addToTab(register("cherry_brick_fence_gate",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICK_PRESSURE_PLATE = addToTab(register("cherry_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CHERRY_BRICK_BUTTON = addToTab(register("cherry_brick_button",
            () -> new BlockItem(ModBlocks.CHERRY_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICKS = addToTab(register("bamboo_bricks",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICK_STAIRS = addToTab(register("bamboo_brick_stairs",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICK_SLAB = addToTab(register("bamboo_brick_slab",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICK_FENCE = addToTab(register("bamboo_brick_fence",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICK_FENCE_GATE = addToTab(register("bamboo_brick_fence_gate",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICK_PRESSURE_PLATE = addToTab(register("bamboo_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BAMBOO_BRICK_BUTTON = addToTab(register("bamboo_brick_button",
            () -> new BlockItem(ModBlocks.BAMBOO_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICKS = addToTab(register("crimson_bricks",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICK_STAIRS = addToTab(register("crimson_brick_stairs",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICK_SLAB = addToTab(register("crimson_brick_slab",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICK_FENCE = addToTab(register("crimson_brick_fence",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICK_FENCE_GATE = addToTab(register("crimson_brick_fence_gate",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICK_PRESSURE_PLATE = addToTab(register("crimson_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CRIMSON_BRICK_BUTTON = addToTab(register("crimson_brick_button",
            () -> new BlockItem(ModBlocks.CRIMSON_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICKS = addToTab(register("warped_bricks",
            () -> new BlockItem(ModBlocks.WARPED_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICK_STAIRS = addToTab(register("warped_brick_stairs",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICK_SLAB = addToTab(register("warped_brick_slab",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICK_FENCE = addToTab(register("warped_brick_fence",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICK_FENCE_GATE = addToTab(register("warped_brick_fence_gate",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_FENCE_GATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICK_PRESSURE_PLATE = addToTab(register("warped_brick_pressure_plate",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WARPED_BRICK_BUTTON = addToTab(register("warped_brick_button",
            () -> new BlockItem(ModBlocks.WARPED_BRICK_BUTTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WOODEN_DOOR = addToTab(register("wooden_door",
            () -> new BlockItem(ModBlocks.WOODEN_DOOR.get(), new Item.Properties())));
    public static final Supplier<BlockItem> HATCH = addToTab(register("hatch",
            () -> new BlockItem(ModBlocks.HATCH.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ASPHALT = addToTab(register("asphalt",
            () -> new BlockItem(ModBlocks.ASPHALT.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ASPHALT_STAIRS = addToTab(register("asphalt_stairs",
            () -> new BlockItem(ModBlocks.ASPHALT_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ASPHALT_SLAB = addToTab(register("asphalt_slab",
            () -> new BlockItem(ModBlocks.ASPHALT_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ASPHALT_WALL = addToTab(register("asphalt_wall",
            () -> new BlockItem(ModBlocks.ASPHALT_WALL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> IRON_PLATE = addToTab(register("iron_plate",
            () -> new BlockItem(ModBlocks.IRON_PLATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> IRON_PLATE_STAIRS = addToTab(register("iron_plate_stairs",
            () -> new BlockItem(ModBlocks.IRON_PLATE_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> IRON_PLATE_SLAB = addToTab(register("iron_plate_slab",
            () -> new BlockItem(ModBlocks.IRON_PLATE_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> IRON_PLATE_WALL = addToTab(register("iron_plate_wall",
            () -> new BlockItem(ModBlocks.IRON_PLATE_WALL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> STEP = addToTab(register("step",
            () -> new BlockItem(ModBlocks.STEP.get(), new Item.Properties())));
    public static final Supplier<BlockItem> STEP_SLAB = addToTab(register("step_slab",
            () -> new BlockItem(ModBlocks.STEP_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RAW_WOOD = addToTab(register("raw_wood",
            () -> new BlockItem(ModBlocks.RAW_WOOD.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RAW_WOOD_STAIRS = addToTab(register("raw_wood_stairs",
            () -> new BlockItem(ModBlocks.RAW_WOOD_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RAW_WOOD_SLAB = addToTab(register("raw_wood_slab",
            () -> new BlockItem(ModBlocks.RAW_WOOD_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RAW_WOOD_WALL = addToTab(register("raw_wood_wall",
            () -> new BlockItem(ModBlocks.RAW_WOOD_WALL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CONCRETE_BRICKS = addToTab(register("concrete_bricks",
            () -> new BlockItem(ModBlocks.CONCRETE_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MECHANICAL_CHAIN = addToTab(register("mechanical_chain",
            () -> new BlockItem(ModBlocks.MECHANICAL_CHAIN.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MECHANICAL_VENT = addToTab(register("mechanical_vent",
            () -> new BlockItem(ModBlocks.MECHANICAL_VENT.get(), new Item.Properties())));
    public static final Supplier<BlockItem> AIR_VENT = addToTab(register("air_vent",
            () -> new BlockItem(ModBlocks.AIR_VENT.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WHITE_MARBLE = addToTab(register("white_marble",
            () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BRICKS = addToTab(register("bricks",
            () -> new BlockItem(ModBlocks.BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BRICK_STAIRS = addToTab(register("brick_stairs",
            () -> new BlockItem(ModBlocks.BRICK_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BRICK_SLAB = addToTab(register("brick_slab",
            () -> new BlockItem(ModBlocks.BRICK_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BRICK_WALL = addToTab(register("brick_wall",
            () -> new BlockItem(ModBlocks.BRICK_WALL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SPOTLIGHT = addToTab(register("spotlight",
            () -> new BlockItem(ModBlocks.SPOTLIGHT.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GRAY_AZTEC = addToTab(register("gray_aztec",
            () -> new BlockItem(ModBlocks.GRAY_AZTEC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SMOOTH_METAL = addToTab(register("smooth_metal",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SMOOTH_METAL_STAIRS = addToTab(register("smooth_metal_stairs",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_STAIRS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SMOOTH_METAL_SLAB = addToTab(register("smooth_metal_slab",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_SLAB.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SMOOTH_METAL_WALL = addToTab(register("smooth_metal_wall",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_WALL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SMOOTH_METAL_FENCE = addToTab(register("smooth_metal_fence",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_FENCE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SMOOTH_METAL_FINIAL = addToTab(register("smooth_metal_finial",
            () -> new BlockItem(ModBlocks.SMOOTH_METAL_FINIAL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> IRON_STACK = addToTab(register("iron_stack",
            () -> new BlockItem(ModBlocks.IRON_STACK.get(), new Item.Properties())));
    public static final Supplier<BlockItem> IRON_DOOR = addToTab(register("iron_door",
            () -> new BlockItem(ModBlocks.IRON_DOOR.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LINOLEUM_TILE = addToTab(register("linoleum_tile",
            () -> new BlockItem(ModBlocks.LINOLEUM_TILE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GREEN_TILES = addToTab(register("green_tiles",
            () -> new BlockItem(ModBlocks.GREEN_TILES.get(), new Item.Properties())));
    public static final Supplier<BlockItem> TILE_MOSAIC = addToTab(register("tile_mosaic",
            () -> new BlockItem(ModBlocks.TILE_MOSAIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLUE_PANELING = addToTab(register("blue_paneling",
            () -> new BlockItem(ModBlocks.BLUE_PANELING.get(), new Item.Properties())));
    public static final Supplier<BlockItem> PIPE = addToTab(register("pipe",
            () -> new BlockItem(ModBlocks.PIPE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> PIPE_INTERSECTION = addToTab(register("pipe_intersection",
            () -> new BlockItem(ModBlocks.PIPE_INTERSECTION.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RUSTY_IRON = addToTab(register("rusty_iron",
            () -> new BlockItem(ModBlocks.RUSTY_IRON.get(), new Item.Properties())));

    // Colored Blocks
    public static final Supplier<BlockItem> WHITE_PLASTIC = addToTab(register("white_plastic",
            () -> new BlockItem(ModBlocks.WHITE_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIGHT_GRAY_STUCCO = addToTab(register("light_gray_stucco",
            () -> new BlockItem(ModBlocks.LIGHT_GRAY_STUCCO.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GRAY_STUCCO = addToTab(register("gray_stucco",
            () -> new BlockItem(ModBlocks.GRAY_STUCCO.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLACK_MARBLE = addToTab(register("black_marble",
            () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BROWN_STUCCO = addToTab(register("brown_stucco",
            () -> new BlockItem(ModBlocks.BROWN_STUCCO.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RED_PLASTIC = addToTab(register("red_plastic",
            () -> new BlockItem(ModBlocks.RED_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RED_VELVET = addToTab(register("red_velvet",
            () -> new BlockItem(ModBlocks.RED_VELVET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ORANGE_PLASTIC = addToTab(register("orange_plastic",
            () -> new BlockItem(ModBlocks.ORANGE_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_PLASTIC = addToTab(register("beige_plastic",
            () -> new BlockItem(ModBlocks.BEIGE_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GOLD_FILGAREE = addToTab(register("gold_filgaree",
            () -> new BlockItem(ModBlocks.GOLD_FILGAREE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIME_PLASTIC = addToTab(register("lime_plastic",
            () -> new BlockItem(ModBlocks.LIME_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GREEN_PLASTIC = addToTab(register("green_plastic",
            () -> new BlockItem(ModBlocks.GREEN_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GREEN_VELVET = addToTab(register("green_velvet",
            () -> new BlockItem(ModBlocks.GREEN_VELVET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CYAN_PLASTIC = addToTab(register("cyan_plastic",
            () -> new BlockItem(ModBlocks.CYAN_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIGHT_BLUE_PLASTIC = addToTab(register("light_blue_plastic",
            () -> new BlockItem(ModBlocks.LIGHT_BLUE_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLUE_PLASTIC = addToTab(register("blue_plastic",
            () -> new BlockItem(ModBlocks.BLUE_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLUE_VELVET = addToTab(register("blue_velvet",
            () -> new BlockItem(ModBlocks.BLUE_VELVET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> VIOLET_VELVET = addToTab(register("violet_velvet",
            () -> new BlockItem(ModBlocks.VIOLET_VELVET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MAGENTA_PLASTIC = addToTab(register("magenta_plastic",
            () -> new BlockItem(ModBlocks.MAGENTA_PLASTIC.get(), new Item.Properties())));
    public static final Supplier<BlockItem> PINK_STUCCO = addToTab(register("pink_stucco",
            () -> new BlockItem(ModBlocks.PINK_STUCCO.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WHITE_PLASTIC_CARPET = addToTab(register("white_plastic_carpet",
            () -> new BlockItem(ModBlocks.WHITE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIGHT_GRAY_STUCCO_CARPET = addToTab(register("light_gray_stucco_carpet",
            () -> new BlockItem(ModBlocks.LIGHT_GRAY_STUCCO_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GRAY_STUCCO_CARPET = addToTab(register("gray_stucco_carpet",
            () -> new BlockItem(ModBlocks.GRAY_STUCCO_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLACK_MARBLE_CARPET = addToTab(register("black_marble_carpet",
            () -> new BlockItem(ModBlocks.BLACK_MARBLE_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BROWN_STUCCO_CARPET = addToTab(register("brown_stucco_carpet",
            () -> new BlockItem(ModBlocks.BROWN_STUCCO_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RED_PLASTIC_CARPET = addToTab(register("red_plastic_carpet",
            () -> new BlockItem(ModBlocks.RED_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RED_VELVET_CARPET = addToTab(register("red_velvet_carpet",
            () -> new BlockItem(ModBlocks.RED_VELVET_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> ORANGE_PLASTIC_CARPET = addToTab(register("orange_plastic_carpet",
            () -> new BlockItem(ModBlocks.ORANGE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_PLASTIC_CARPET = addToTab(register("beige_plastic_carpet",
            () -> new BlockItem(ModBlocks.BEIGE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GOLD_FILGAREE_CARPET = addToTab(register("gold_filgaree_carpet",
            () -> new BlockItem(ModBlocks.GOLD_FILGAREE_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIME_PLASTIC_CARPET = addToTab(register("lime_plastic_carpet",
            () -> new BlockItem(ModBlocks.LIME_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GREEN_PLASTIC_CARPET = addToTab(register("green_plastic_carpet",
            () -> new BlockItem(ModBlocks.GREEN_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GREEN_VELVET_CARPET = addToTab(register("green_velvet_carpet",
            () -> new BlockItem(ModBlocks.GREEN_VELVET_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CYAN_PLASTIC_CARPET = addToTab(register("cyan_plastic_carpet",
            () -> new BlockItem(ModBlocks.CYAN_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIGHT_BLUE_PLASTIC_CARPET = addToTab(register("light_blue_plastic_carpet",
            () -> new BlockItem(ModBlocks.LIGHT_BLUE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLUE_PLASTIC_CARPET = addToTab(register("blue_plastic_carpet",
            () -> new BlockItem(ModBlocks.BLUE_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BLUE_VELVET_CARPET = addToTab(register("blue_velvet_carpet",
            () -> new BlockItem(ModBlocks.BLUE_VELVET_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> VIOLET_VELVET_CARPET = addToTab(register("violet_velvet_carpet",
            () -> new BlockItem(ModBlocks.VIOLET_VELVET_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MAGENTA_PLASTIC_CARPET = addToTab(register("magenta_plastic_carpet",
            () -> new BlockItem(ModBlocks.MAGENTA_PLASTIC_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> PINK_STUCCO_CARPET = addToTab(register("pink_stucco_carpet",
            () -> new BlockItem(ModBlocks.PINK_STUCCO_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CONCRETE = addToTab(register("concrete",
            () -> new BlockItem(ModBlocks.CONCRETE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> STRIPE = addToTab(register("stripe",
            () -> new BlockItem(ModBlocks.STRIPE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> STRIPE_INTERSECTION = addToTab(register("stripe_intersection",
            () -> new BlockItem(ModBlocks.STRIPE_INTERSECTION.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CORRUGATED_STEEL = addToTab(register("corrugated_steel",
            () -> new BlockItem(ModBlocks.CORRUGATED_STEEL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> STORAGE_CRATE = addToTab(register("storage_crate",
            () -> new BlockItem(ModBlocks.STORAGE_CRATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CLAY_TILE = addToTab(register("clay_tile",
            () -> new BlockItem(ModBlocks.CLAY_TILE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SHALE = addToTab(register("shale",
            () -> new BlockItem(ModBlocks.SHALE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GLASS = addToTab(register("glass",
            () -> new BlockItem(ModBlocks.GLASS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> GLASS_PANE = addToTab(register("glass_pane",
            () -> new BlockItem(ModBlocks.GLASS_PANE.get(), new Item.Properties())));

    // Natural Blocks
    public static final Supplier<BlockItem> REINFORCED_PANELING = addToTab(register("reinforced_paneling",
            () -> new BlockItem(ModBlocks.REINFORCED_PANELING.get(), new Item.Properties())));
    public static final Supplier<BlockItem> THIN_STRIPE = addToTab(register("thin_stripe",
            () -> new BlockItem(ModBlocks.THIN_STRIPE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CAUTION_TAPE = addToTab(register("caution_tape",
            () -> new BlockItem(ModBlocks.CAUTION_TAPE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DARK_STONE_BRICKS = addToTab(register("dark_stone_bricks",
            () -> new BlockItem(ModBlocks.DARK_STONE_BRICKS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DECORATIVE_CONCRETE = addToTab(register("decorative_concrete",
            () -> new BlockItem(ModBlocks.DECORATIVE_CONCRETE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> PILLAR = addToTab(register("pillar",
            () -> new BlockItem(ModBlocks.PILLAR.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CIRCUITRY = addToTab(register("circuitry",
            () -> new BlockItem(ModBlocks.CIRCUITRY.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIMESTONE_BRICK = addToTab(register("limestone_brick",
            () -> new BlockItem(ModBlocks.LIMESTONE_BRICK.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CORRUGATED_COPPER = addToTab(register("corrugated_copper",
            () -> new BlockItem(ModBlocks.CORRUGATED_COPPER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RIVETED_STEEL = addToTab(register("riveted_steel",
            () -> new BlockItem(ModBlocks.RIVETED_STEEL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> FLUORESCENT_PANEL = addToTab(register("fluorescent_panel",
            () -> new BlockItem(ModBlocks.FLUORESCENT_PANEL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MECHANICAL = addToTab(register("mechanical",
            () -> new BlockItem(ModBlocks.MECHANICAL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CURTAINS = addToTab(register("curtains",
            () -> new BlockItem(ModBlocks.CURTAINS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> COUNTERTOP = addToTab(register("countertop",
            () -> new BlockItem(ModBlocks.COUNTERTOP.get(), new Item.Properties())));
    public static final Supplier<BlockItem> WHITE_LIGHTING = addToTab(register("white_lighting",
            () -> new BlockItem(ModBlocks.WHITE_LIGHTING.get(), new Item.Properties())));
    public static final Supplier<BlockItem> MONITOR = addToTab(register("monitor",
            () -> new BlockItem(ModBlocks.MONITOR.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LIT_MONITOR = addToTab(register("lit_monitor",
            () -> new BlockItem(ModBlocks.LIT_MONITOR.get(), new Item.Properties())));

    // Functional Blocks
    public static final Supplier<BlockItem> LANTERN = addToTab(register("lantern",
            () -> new StandingAndWallBlockItem(ModBlocks.LANTERN.get(), ModBlocks.WALL_LANTERN.get(), new Item.Properties(), Direction.DOWN)));
    public static final Supplier<BlockItem> LED = addToTab(register("led",
            () -> new StandingAndWallBlockItem(ModBlocks.LED.get(), ModBlocks.WALL_LED.get(), new Item.Properties(), Direction.DOWN)));
    public static final Supplier<BlockItem> LAMP = addToTab(register("lamp",
            () -> new BlockItem(ModBlocks.LAMP.get(), new Item.Properties())));
    public static final Supplier<BlockItem> TECH_ACCENT = addToTab(register("tech_accent",
            () -> new BlockItem(ModBlocks.TECH_ACCENT.get(), new Item.Properties())));
    public static final Supplier<BlockItem> YOGIFIER = addToTab(register("yogifier",
            () -> new BlockItem(ModBlocks.YOGIFIER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> OVEN = addToTab(register("oven",
            () -> new BlockItem(ModBlocks.OVEN.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CONSOLE = addToTab(register("console",
            () -> new BlockItem(ModBlocks.CONSOLE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> RECORD_PLAYER = addToTab(register("record_player",
            () -> new BlockItem(ModBlocks.RECORD_PLAYER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> TABLE = addToTab(register("table",
            () -> new BlockItem(ModBlocks.TABLE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> SINK = addToTab(register("sink",
            () -> new BlockItem(ModBlocks.SINK.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LADDER = addToTab(register("ladder",
            () -> new BlockItem(ModBlocks.LADDER.get(), new Item.Properties())));
    public static final Supplier<Item> YOG_SIGN = addToTab(register("yog_sign",
            () -> new SignItem((new Item.Properties()).stacksTo(16), ModBlocks.YOG_SIGN.get(), ModBlocks.YOG_WALL_SIGN.get())));
    public static final Supplier<Item> CRATE = addToTab(Services.REGISTRY.registerItem("crate",
            () -> Services.PLATFORM.createCrateBlockItem(ModBlocks.CRATE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> FANCY_TABLE = addToTab(register("fancy_table",
            () -> new BlockItem(ModBlocks.FANCY_TABLE.get(), new Item.Properties())));

    // Redstone Blocks
    public static final Supplier<BlockItem> WIRE = addToTab(register("wire",
            () -> new BlockItem(ModBlocks.WIRE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DIODE = addToTab(register("diode",
            () -> new BlockItem(ModBlocks.DIODE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CORRUGATED_REDSTONE = addToTab(register("corrugated_redstone",
            () -> new BlockItem(ModBlocks.CORRUGATED_REDSTONE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> CLASSIC_LEVER = addToTab(register("classic_lever",
            () -> new BlockItem(ModBlocks.CLASSIC_LEVER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> LEVER = addToTab(register("lever",
            () -> new BlockItem(ModBlocks.LEVER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> PISTON = addToTab(register("piston",
            () -> new BlockItem(ModBlocks.PISTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> STICKY_PISTON = addToTab(register("sticky_piston",
            () -> new BlockItem(ModBlocks.STICKY_PISTON.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DISPENSER = addToTab(register("dispenser",
            () -> new BlockItem(ModBlocks.DISPENSER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> METROVOX_RAIL = addToTab(register("metrovox_rail",
            () -> new BlockItem(ModBlocks.METROVOX_RAIL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> POWERED_METROVOX_RAIL = addToTab(register("powered_metrovox_rail",
            () -> new BlockItem(ModBlocks.POWERED_METROVOX_RAIL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> METROVOX_DETECTOR_RAIL = addToTab(register("metrovox_detector_rail",
            () -> new BlockItem(ModBlocks.METROVOX_DETECTOR_RAIL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> METROVOX_ACTIVATOR_RAIL = addToTab(register("metrovox_activator_rail",
            () -> new BlockItem(ModBlocks.METROVOX_ACTIVATOR_RAIL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> DYNAMITE = addToTab(register("dynamite",
            () -> new BlockItem(ModBlocks.DYNAMITE.get(), new Item.Properties())));

    // Tools & Utilities
    public static final Supplier<Item> PENCIL = addToTab(register("pencil",
            () -> new AxeItem(ModTiers.PENCIL, 6.0F, -3.2F, new Item.Properties())));

    // Combat
    public static final Supplier<Item> LASER = addToTab(register("laser",
            () -> new SwordItem(ModTiers.LASER, 3, -2.4F, new Item.Properties())));
    public static final Supplier<ArmorItem> SUNGLASSES = addToTab(register("sunglasses",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> SUIT_JACKET = addToTab(register("suit_jacket",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> SUIT_PANTS = addToTab(register("suit_pants",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> SPATTERDASH_SHOES = addToTab(register("spatterdash_shoes",
            () -> new ArmorItem(ModArmorMaterials.SUIT, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> HARD_HAT = addToTab(register("hard_hat",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> SAFETY_VEST = addToTab(register("safety_vest",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> SEWER_WADERS = addToTab(register("sewer_waders",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> STEELTOE_BOOTS = addToTab(register("steeltoe_boots",
            () -> new ArmorItem(ModArmorMaterials.CONSTRUCTION, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> RIOT_HELMET = addToTab(register("riot_helmet",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> RIOT_VEST = addToTab(register("riot_vest",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> RIOT_PANTS = addToTab(register("riot_pants",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))));
    public static final Supplier<ArmorItem> RIOT_BOOTS = addToTab(register("riot_boots",
            () -> new ArmorItem(ModArmorMaterials.RIOT, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1))));

    // Food & Drinks
    public static final Supplier<Item> COFFEE = addToTab(register("coffee",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .alwaysEat()
                            .build()))));
    public static final Supplier<Item> HAMBURGER = addToTab(register("hamburger",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.8f)
                            .alwaysEat()
                            .build()))));
    public static final Supplier<Item> JAFFA = addToTab(register("jaffa",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(5)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()))));

    // Spawn Eggs
    public static final Supplier<Item> CAGE = addToTab(register("cage",
            () -> new BlockItem(ModBlocks.CAGE.get(), new Item.Properties())));

    // Not in YogTab
    public static final Supplier<BlockItem> BEIGE_WOOL = addToTab(register("beige_wool",
            () -> new BlockItem(ModBlocks.BEIGE_WOOL.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_CARPET = addToTab(register("beige_carpet",
            () -> new BlockItem(ModBlocks.BEIGE_CARPET.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_TERRACOTTA = addToTab(register("beige_terracotta",
            () -> new BlockItem(ModBlocks.BEIGE_TERRACOTTA.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_CONCRETE = addToTab(register("beige_concrete",
            () -> new BlockItem(ModBlocks.BEIGE_CONCRETE.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_CONCRETE_POWDER = addToTab(register("beige_concrete_powder",
            () -> new BlockItem(ModBlocks.BEIGE_CONCRETE_POWDER.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_STAINED_GLASS = addToTab(register("beige_stained_glass",
            () -> new BlockItem(ModBlocks.BEIGE_STAINED_GLASS.get(), new Item.Properties())));
    public static final Supplier<BlockItem> BEIGE_STAINED_GLASS_PANE = addToTab(register("beige_stained_glass_pane",
            () -> new BlockItem(ModBlocks.BEIGE_STAINED_GLASS_PANE.get(), new Item.Properties())));
    public static final Supplier<Item> BEIGE_DYE = addToTab(register("beige_dye",
            () -> new Item(new Item.Properties())));
}