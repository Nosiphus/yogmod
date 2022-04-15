package com.nosiphus.yogmod.init;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "yogmod");

    //Building Blocks
    public static final RegistryObject<Block> ASPHALT = BLOCKS.register("asphalt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> OAK_BRICKS = BLOCKS.register("oak_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_BRICKS = BLOCKS.register("spruce_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<Block> BIRCH_BRICKS = BLOCKS.register("birch_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_BRICKS = BLOCKS.register("jungle_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> ACACIA_BRICKS = BLOCKS.register("acacia_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_BRICKS = BLOCKS.register("dark_oak_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_BRICKS = BLOCKS.register("crimson_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> WARPED_BRICKS = BLOCKS.register("warped_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> WHITE_MARBLE = BLOCKS.register("white_marble",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> PILLAR = BLOCKS.register("pillar",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR)));
    public static final RegistryObject<Block> DECORATIVE_CONCRETE = BLOCKS.register("decorative_concrete",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> DARK_STONE_BRICKS = BLOCKS.register("dark_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistryObject<Block> GLASS = BLOCKS.register("glass",
            () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistryObject<Block> LIMESTONE_BRICK = BLOCKS.register("limestone_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistryObject<Block> TILE_MOSAIC = BLOCKS.register("tile_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LAPIS_BLOCK)));
    public static final RegistryObject<Block> BEIGE_PLASTIC = BLOCKS.register("beige_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    public static final RegistryObject<Block> WHITE_PLASTIC = BLOCKS.register("white_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> ORANGE_PLASTIC = BLOCKS.register("orange_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ORANGE_WOOL)));
    public static final RegistryObject<Block> MAGENTA_PLASTIC = BLOCKS.register("magenta_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL)));
    public static final RegistryObject<Block> LIGHT_BLUE_PLASTIC = BLOCKS.register("light_blue_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_WOOL)));
    public static final RegistryObject<Block> GOLD_FILGAREE = BLOCKS.register("gold_filgaree",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    public static final RegistryObject<Block> LIME_PLASTIC = BLOCKS.register("lime_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIME_WOOL)));
    public static final RegistryObject<Block> PINK_STUCCO = BLOCKS.register("pink_stucco",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PINK_WOOL)));
    public static final RegistryObject<Block> GRAY_STUCCO = BLOCKS.register("gray_stucco",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAY_WOOL)));
    public static final RegistryObject<Block> LIGHT_GRAY_STUCCO = BLOCKS.register("light_gray_stucco",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_WOOL)));
    public static final RegistryObject<Block> CYAN_PLASTIC = BLOCKS.register("cyan_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CYAN_WOOL)));
    public static final RegistryObject<Block> VIOLET_VELVET = BLOCKS.register("violet_velvet",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPLE_WOOL)));
    public static final RegistryObject<Block> BLUE_PLASTIC = BLOCKS.register("blue_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL)));
    public static final RegistryObject<Block> BROWN_STUCCO = BLOCKS.register("brown_stucco",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL)));
    public static final RegistryObject<Block> GREEN_PLASTIC = BLOCKS.register("green_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL)));
    public static final RegistryObject<Block> RED_PLASTIC = BLOCKS.register("red_plastic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistryObject<Block> BLACK_MARBLE = BLOCKS.register("black_marble",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistryObject<Block> REINFORCED_PANELING = BLOCKS.register("reinforced_paneling",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> STRIPE = BLOCKS.register("stripe",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR)));
    public static final RegistryObject<Block> LINOLEUM_TILE = BLOCKS.register("linoleum_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> CORRUGATED_STEEL = BLOCKS.register("corrugated_steel",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CLAY_TILE = BLOCKS.register("clay_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL)));
    public static final RegistryObject<Block> STORAGE_CRATE = BLOCKS.register("storage_crate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PIPE = BLOCKS.register("pipe",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR)));
    public static final RegistryObject<Block> RIVETED_STEEL = BLOCKS.register("riveted_steel",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> IRON_STACK = BLOCKS.register("iron_stack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RUSTY_IRON = BLOCKS.register("rusty_iron",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> OAK_BRICK_SLAB = BLOCKS.register("oak_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> SPRUCE_BRICK_SLAB = BLOCKS.register("spruce_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SLAB)));
    public static final RegistryObject<Block> BIRCH_BRICK_SLAB = BLOCKS.register("birch_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_SLAB)));
    public static final RegistryObject<Block> JUNGLE_BRICK_SLAB = BLOCKS.register("jungle_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_SLAB)));
    public static final RegistryObject<Block> ACACIA_BRICK_SLAB = BLOCKS.register("acacia_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_SLAB)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_SLAB = BLOCKS.register("dark_oak_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SLAB)));
    public static final RegistryObject<Block> CRIMSON_BRICK_SLAB = BLOCKS.register("crimson_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_SLAB)));
    public static final RegistryObject<Block> WARPED_BRICK_SLAB = BLOCKS.register("warped_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_SLAB)));
    public static final RegistryObject<Block> STEP_SLAB = BLOCKS.register("step_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE_SLAB)));
    public static final RegistryObject<Block> ASPHALT_SLAB = BLOCKS.register("asphalt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_SLAB)));
    public static final RegistryObject<Block> IRON_PLATE_SLAB = BLOCKS.register("iron_plate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE_SLAB)));
    public static final RegistryObject<Block> BRICK_SLAB = BLOCKS.register("brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BRICK_SLAB)));
    public static final RegistryObject<Block> RAW_WOOD_SLAB = BLOCKS.register("raw_wood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));
    public static final RegistryObject<Block> SMOOTH_METAL_SLAB = BLOCKS.register("smooth_metal_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_SLAB)));
    public static final RegistryObject<Block> STEP = BLOCKS.register("step",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistryObject<Block> BRICKS = BLOCKS.register("bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistryObject<Block> IRON_PLATE = BLOCKS.register("iron_plate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CORRUGATED_COPPER = BLOCKS.register("corrugated_copper",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

}
