package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.YogMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
    public static final RegistryObject<BlockItem> WHITE_MARBLE = ITEMS.register("white_marble",
            () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> PILLAR = ITEMS.register("pillar",
            () -> new BlockItem(ModBlocks.PILLAR.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DECORATIVE_CONCRETE = ITEMS.register("decorative_concrete",
            () -> new BlockItem(ModBlocks.DECORATIVE_CONCRETE.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> DARK_STONE_BRICKS = ITEMS.register("dark_stone_bricks",
            () -> new BlockItem(ModBlocks.DARK_STONE_BRICKS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> GLASS = ITEMS.register("glass",
            () -> new BlockItem(ModBlocks.GLASS.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> LIMESTONE_BRICKS = ITEMS.register("limestone_brick",
            () -> new BlockItem(ModBlocks.LIMESTONE_BRICK.get(), new Item.Properties().tab(YogMod.YOGTAB)));
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
    public static final RegistryObject<BlockItem> RIVETED_STEEL = ITEMS.register("riveted_steel",
            () -> new BlockItem(ModBlocks.RIVETED_STEEL.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_STACK = ITEMS.register("iron_stack",
            () -> new BlockItem(ModBlocks.IRON_STACK.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> RUSTY_IRON = ITEMS.register("rusty_iron",
            () -> new BlockItem(ModBlocks.RUSTY_IRON.get(), new Item.Properties().tab(YogMod.YOGTAB)));
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
    public static final RegistryObject<BlockItem> STEP_SLAB = ITEMS.register("step_slab",
            () -> new BlockItem(ModBlocks.STEP_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> ASPHALT_SLAB = ITEMS.register("asphalt_slab",
            () -> new BlockItem(ModBlocks.ASPHALT_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
    public static final RegistryObject<BlockItem> IRON_PLATE_SLAB = ITEMS.register("iron_plate_slab",
            () -> new BlockItem(ModBlocks.IRON_PLATE_SLAB.get(), new Item.Properties().tab(YogMod.YOGTAB)));
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
    public static final RegistryObject<BlockItem> CORRUGATED_COPPER = ITEMS.register("corrugated_copper",
            () -> new BlockItem(ModBlocks.CORRUGATED_COPPER.get(), new Item.Properties().tab(YogMod.YOGTAB)));

    //Decoration Blocks

    //Redstone

    //Transportation

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

    //Brewing

}
