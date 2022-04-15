package com.nosiphus.yogmod.init;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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
    public static final RegistryObject<Block> BLUE_PANELING = BLOCKS.register("blue_paneling",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CIRCUITRY = BLOCKS.register("circuitry",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REDSTONE_BLOCK)));
    public static final RegistryObject<Block> CONCRETE = BLOCKS.register("concrete",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> SHALE = BLOCKS.register("shale",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> WHITE_LIGHTING = BLOCKS.register("white_lighting",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)));
    public static final RegistryObject<Block> MONITOR = BLOCKS.register("monitor",
            () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> CAUTION_TAPE = BLOCKS.register("caution_tape",
            () -> new SoulSandBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND).sound(SoundType.SAND)));
    public static final RegistryObject<Block> FLUORESCENT_PANEL = BLOCKS.register("fluorescent_panel",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> LIT_MONITOR = BLOCKS.register("lit_monitor",
            () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> RAW_WOOD = BLOCKS.register("raw_wood",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> COUNTERTOP = BLOCKS.register("countertop",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SMOOTH_METAL = BLOCKS.register("smooth_metal",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> GREEN_TILES = BLOCKS.register("green_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<Block> OAK_BRICK_STAIRS = BLOCKS.register("oak_brick_stairs",
            () -> new StairBlock(OAK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> SPRUCE_BRICK_STAIRS = BLOCKS.register("spruce_brick_stairs",
            () -> new StairBlock(SPRUCE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.SPRUCE_STAIRS)));
    public static final RegistryObject<Block> BIRCH_BRICK_STAIRS = BLOCKS.register("birch_brick_stairs",
            () -> new StairBlock(BIRCH_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.BIRCH_STAIRS)));
    public static final RegistryObject<Block> JUNGLE_BRICK_STAIRS = BLOCKS.register("jungle_brick_stairs",
            () -> new StairBlock(JUNGLE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.JUNGLE_STAIRS)));
    public static final RegistryObject<Block> ACACIA_BRICK_STAIRS = BLOCKS.register("acacia_brick_stairs",
            () -> new StairBlock(ACACIA_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.ACACIA_STAIRS)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_STAIRS = BLOCKS.register("dark_oak_brick_stairs",
            () -> new StairBlock(DARK_OAK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DARK_OAK_STAIRS)));
    public static final RegistryObject<Block> CRIMSON_BRICK_STAIRS = BLOCKS.register("crimson_brick_stairs",
            () -> new StairBlock(CRIMSON_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.CRIMSON_STAIRS)));
    public static final RegistryObject<Block> WARPED_BRICK_STAIRS = BLOCKS.register("warped_brick_stairs",
            () -> new StairBlock(WARPED_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.WARPED_STAIRS)));
    public static final RegistryObject<Block> ASPHALT_STAIRS = BLOCKS.register("asphalt_stairs",
            () -> new StairBlock(ASPHALT.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)));
    public static final RegistryObject<Block> IRON_PLATE_STAIRS = BLOCKS.register("iron_plate_stairs",
            () -> new StairBlock(IRON_PLATE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE_STAIRS)));
    public static final RegistryObject<Block> BRICK_STAIRS = BLOCKS.register("brick_stairs",
            () -> new StairBlock(BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.BRICK_STAIRS)));
    public static final RegistryObject<Block> RAW_WOOD_STAIRS = BLOCKS.register("raw_wood_stairs",
            () -> new StairBlock(RAW_WOOD.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final RegistryObject<Block> SMOOTH_METAL_STAIRS = BLOCKS.register("smooth_metal_stairs",
            () -> new StairBlock(SMOOTH_METAL.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_STAIRS)));

    //Decoration Blocks
    public static final RegistryObject<Block> LANTERN = BLOCKS.register("lantern",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission()
                    .instabreak()
                    .lightLevel((p_50886_) -> { return 14; })
                    .sound(SoundType.WOOD),
                    ParticleTypes.FLAME));
    public static final RegistryObject<Block> WALL_LANTERN = BLOCKS.register("wall_lantern",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission()
                    .instabreak()
                    .lightLevel((p_152607_) -> { return 14; })
                    .sound(SoundType.WOOD)
                    .lootFrom(LANTERN),
                    ParticleTypes.FLAME));
    //Crate will go here when implemented
    public static final RegistryObject<Block> TECH_ACCENT = BLOCKS.register("tech_accent",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> LADDER = BLOCKS.register("ladder",
            () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> OAK_BRICK_FENCE = BLOCKS.register("oak_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> SPRUCE_BRICK_FENCE = BLOCKS.register("spruce_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_FENCE)));
    public static final RegistryObject<Block> BIRCH_BRICK_FENCE = BLOCKS.register("birch_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_FENCE)));
    public static final RegistryObject<Block> JUNGLE_BRICK_FENCE = BLOCKS.register("jungle_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_FENCE)));
    public static final RegistryObject<Block> ACACIA_BRICK_FENCE = BLOCKS.register("acacia_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_FENCE)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_FENCE = BLOCKS.register("dark_oak_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_FENCE)));
    public static final RegistryObject<Block> CRIMSON_BRICK_FENCE = BLOCKS.register("crimson_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_FENCE)));
    public static final RegistryObject<Block> WARPED_BRICK_FENCE = BLOCKS.register("warped_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE)));
    public static final RegistryObject<Block> SMOOTH_METAL_FENCE = BLOCKS.register("smooth_metal_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICK_FENCE)));



}
