package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.util.Reference;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    //Food
    public static final RegistryObject<Item> COFFEE = ITEMS.register("coffee", () -> new Item(new Item.Properties().group(YogMod.YOGTAB).food(ModFoods.COFFEE)));
    public static final RegistryObject<Item> HAMBURGER = ITEMS.register("hamburger", () -> new Item(new Item.Properties().group(YogMod.YOGTAB).food(ModFoods.HAMBURGER)));
    public static final RegistryObject<Item> JAFFA = ITEMS.register("jaffa", () -> new Item(new Item.Properties().group(YogMod.YOGTAB).food(ModFoods.JAFFA)));

    //Items
    public static final RegistryObject<Item> PENCIL = ITEMS.register("pencil", () -> new Item(new Item.Properties().group(YogMod.YOGTAB)));

    //Block Items
    public static final RegistryObject<BlockItem> ACACIA_BRICKS = ITEMS.register("acacia_bricks", () -> new BlockItem(ModBlocks.ACACIA_BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_FENCE = ITEMS.register("acacia_brick_fence", () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_FENCE_GATE = ITEMS.register("acacia_brick_fence_gate", () -> new BlockItem(ModBlocks.ACACIA_BRICK_FENCE_GATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_SLAB = ITEMS.register("acacia_brick_slab", () -> new BlockItem(ModBlocks.ACACIA_BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ACACIA_BRICK_STAIRS = ITEMS.register("acacia_brick_stairs", () -> new BlockItem(ModBlocks.ACACIA_BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> AIR_VENT = ITEMS.register("air_vent", () -> new BlockItem(ModBlocks.AIR_VENT.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT = ITEMS.register("asphalt", () -> new BlockItem(ModBlocks.ASPHALT.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT_SLAB = ITEMS.register("asphalt_slab", () -> new BlockItem(ModBlocks.ASPHALT_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT_STAIRS = ITEMS.register("asphalt_stairs", () -> new BlockItem(ModBlocks.ASPHALT_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT_WALL = ITEMS.register("asphalt_wall", () -> new BlockItem(ModBlocks.ASPHALT_WALL.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BEIGE_PLASTIC = ITEMS.register("beige_plastic", () -> new BlockItem(ModBlocks.BEIGE_PLASTIC.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICKS = ITEMS.register("birch_bricks", () -> new BlockItem(ModBlocks.BIRCH_BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_FENCE = ITEMS.register("birch_brick_fence", () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_FENCE_GATE = ITEMS.register("birch_brick_fence_gate", () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE_GATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_SLAB = ITEMS.register("birch_brick_slab", () -> new BlockItem(ModBlocks.BIRCH_BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_STAIRS = ITEMS.register("birch_brick_stairs", () -> new BlockItem(ModBlocks.BIRCH_BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLACK_MARBLE = ITEMS.register("black_marble", () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PANELING = ITEMS.register("blue_paneling", () -> new BlockItem(ModBlocks.BLUE_PANELING.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PLASTIC = ITEMS.register("blue_plastic", () -> new BlockItem(ModBlocks.BLUE_PLASTIC.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICKS = ITEMS.register("bricks", () -> new BlockItem(ModBlocks.BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICK_SLAB = ITEMS.register("brick_slab", () -> new BlockItem(ModBlocks.BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICK_STAIRS = ITEMS.register("brick_stairs", () -> new BlockItem(ModBlocks.BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BRICK_WALL = ITEMS.register("brick_wall", () -> new BlockItem(ModBlocks.BRICK_WALL.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CAUTION_TAPE = ITEMS.register("caution_tape", () -> new BlockItem(ModBlocks.CAUTION_TAPE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CIRCUITRY = ITEMS.register("circuitry", () -> new BlockItem(ModBlocks.CIRCUITRY.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CLASSIC_LEVER = ITEMS.register("classic_lever", () -> new BlockItem(ModBlocks.CLASSIC_LEVER.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CLAY_TILE = ITEMS.register("clay_tile", () -> new BlockItem(ModBlocks.CLAY_TILE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CONCRETE = ITEMS.register("concrete", () -> new BlockItem(ModBlocks.CONCRETE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CONSOLE = ITEMS.register("console", () -> new BlockItem(ModBlocks.CONSOLE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CORRUGATED_COPPER = ITEMS.register("corrugated_copper", () -> new BlockItem(ModBlocks.CORRUGATED_COPPER.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CORRUGATED_STEEL = ITEMS.register("corrugated_steel", () -> new BlockItem(ModBlocks.CORRUGATED_STEEL.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> COUNTERTOP = ITEMS.register("countertop", () -> new BlockItem(ModBlocks.COUNTERTOP.get(), new Item.Properties().group(YogMod.YOGTAB)));
    //public static final RegistryObject<BlockItem> CRATE
    public static final RegistryObject<BlockItem> CRIMSON_BRICKS = ITEMS.register("crimson_bricks", () -> new BlockItem(ModBlocks.CRIMSON_BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_FENCE = ITEMS.register("crimson_brick_fence", () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_FENCE_GATE = ITEMS.register("crimson_brick_fence_gate", () -> new BlockItem(ModBlocks.CRIMSON_BRICK_FENCE_GATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_SLAB = ITEMS.register("crimson_brick_slab", () -> new BlockItem(ModBlocks.CRIMSON_BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CRIMSON_BRICK_STAIRS = ITEMS.register("crimson_brick_stairs", () -> new BlockItem(ModBlocks.CRIMSON_BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CURTAINS = ITEMS.register("curtains", () -> new BlockItem(ModBlocks.CURTAINS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> CYAN_PLASTIC = ITEMS.register("cyan_plastic", () -> new BlockItem(ModBlocks.CYAN_PLASTIC.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICKS = ITEMS.register("dark_oak_bricks", () -> new BlockItem(ModBlocks.DARK_OAK_BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_FENCE = ITEMS.register("dark_oak_brick_fence", () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_FENCE_GATE = ITEMS.register("dark_oak_brick_fence_gate", () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_FENCE_GATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_SLAB = ITEMS.register("dark_oak_brick_slab", () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_OAK_BRICK_STAIRS = ITEMS.register("dark_oak_brick_stairs", () -> new BlockItem(ModBlocks.DARK_OAK_BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_STONE_BRICKS = ITEMS.register("dark_stone_bricks", () -> new BlockItem(ModBlocks.DARK_STONE_BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DECORATIVE_CONCRETE = ITEMS.register("decorative_concrete", () -> new BlockItem(ModBlocks.DECORATIVE_CONCRETE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> FANCY_TABLE = ITEMS.register("fancy_table", () -> new BlockItem(ModBlocks.FANCY_TABLE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> FLUORESCENT_PANEL = ITEMS.register("fluorescent_panel", () -> new BlockItem(ModBlocks.FLUORESCENT_PANEL.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GLASS = ITEMS.register("glass", () -> new BlockItem(ModBlocks.GLASS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GLASS_PANE = ITEMS.register("glass_pane", () -> new BlockItem(ModBlocks.GLASS_PANE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GOLD_FILGAREE = ITEMS.register("gold_filgaree", () -> new BlockItem(ModBlocks.GOLD_FILGAREE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GREEN_PLASTIC = ITEMS.register("green_plastic", () -> new BlockItem(ModBlocks.GREEN_PLASTIC.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GREEN_TILES = ITEMS.register("green_tiles", () -> new BlockItem(ModBlocks.GREEN_TILES.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> HATCH = ITEMS.register("hatch", () -> new BlockItem(ModBlocks.HATCH.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_DOOR = ITEMS.register("iron_door", () -> new BlockItem(ModBlocks.IRON_DOOR.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_PLATE = ITEMS.register("iron_plate", () -> new BlockItem(ModBlocks.IRON_PLATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_STACK = ITEMS.register("iron_stack", () -> new BlockItem(ModBlocks.IRON_STACK.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICKS = ITEMS.register("jungle_bricks", () -> new BlockItem(ModBlocks.JUNGLE_BRICKS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_FENCE = ITEMS.register("jungle_brick_fence", () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_FENCE_GATE = ITEMS.register("jungle_brick_fence_gate", () -> new BlockItem(ModBlocks.JUNGLE_BRICK_FENCE_GATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_SLAB = ITEMS.register("jungle_brick_slab", () -> new BlockItem(ModBlocks.JUNGLE_BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> JUNGLE_BRICK_STAIRS = ITEMS.register("jungle_brick_stairs", () -> new BlockItem(ModBlocks.JUNGLE_BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LADDER = ITEMS.register("ladder", () -> new BlockItem(ModBlocks.LADDER.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LAMP = ITEMS.register("lamp", () -> new BlockItem(ModBlocks.LAMP.get(), new Item.Properties().group(YogMod.YOGTAB)));
    //public static final RegistryObject<BlockItem> LANTERN
    //public static final RegistryObject<BlockItem> LED
    public static final RegistryObject<BlockItem> LEVER = ITEMS.register("lever", () -> new BlockItem(ModBlocks.LEVER.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIMESTONE_BRICK = ITEMS.register("limestone_brick", () -> new BlockItem(ModBlocks.LIMESTONE_BRICK.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LINOLEUM_TILE = ITEMS.register("linoleum_tile", () -> new BlockItem(ModBlocks.LINOLEUM_TILE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIT_MONITOR = ITEMS.register("lit_monitor", () -> new BlockItem(ModBlocks.LIT_MONITOR.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> MONITOR = ITEMS.register("monitor", () -> new BlockItem(ModBlocks.MONITOR.get(), new Item.Properties().group(YogMod.YOGTAB)));

    public static final RegistryObject<BlockItem> STEP = ITEMS.register("step", () -> new BlockItem(ModBlocks.STEP.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> STEP_SLAB = ITEMS.register("step_slab", () -> new BlockItem(ModBlocks.STEP_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));

}
