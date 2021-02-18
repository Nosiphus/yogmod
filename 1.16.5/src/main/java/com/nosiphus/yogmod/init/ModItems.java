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
    public static final RegistryObject<BlockItem> ACACIA_BRICK = ITEMS.register("acacia_brick", () -> new BlockItem(ModBlocks.ACACIA_BRICK.get(), new Item.Properties().group(YogMod.YOGTAB)));
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
    public static final RegistryObject<BlockItem> BIRCH_BRICK = ITEMS.register("birch_brick", () -> new BlockItem(ModBlocks.BIRCH_BRICK.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_FENCE = ITEMS.register("birch_brick_fence", () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_FENCE_GATE = ITEMS.register("birch_brick_fence_gate", () -> new BlockItem(ModBlocks.BIRCH_BRICK_FENCE_GATE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_SLAB = ITEMS.register("birch_brick_slab", () -> new BlockItem(ModBlocks.BIRCH_BRICK_SLAB.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BIRCH_BRICK_STAIRS = ITEMS.register("birch_brick_stairs", () -> new BlockItem(ModBlocks.BIRCH_BRICK_STAIRS.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLACK_MARBLE = ITEMS.register("black_marble", () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PANELING = ITEMS.register("blue_paneling", () -> new BlockItem(ModBlocks.BLUE_PANELING.get(), new Item.Properties().group(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> BLUE_PLASTIC = ITEMS.register("blue_plastic", () -> new BlockItem(ModBlocks.BLUE_PLASTIC.get(), new Item.Properties().group(YogMod.YOGTAB)));

}
