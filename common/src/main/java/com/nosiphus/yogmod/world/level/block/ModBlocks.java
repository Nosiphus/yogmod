package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.core.sink.SinkInteraction;
import com.nosiphus.yogmod.platform.Services;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.StorageCrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.piston.MovingPistonBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonBaseBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonHeadBlock;
import com.nosiphus.yogmod.world.level.block.state.properties.ModWoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {

    public static void init() {

    }

    private static <T extends Block> Supplier<T> register(String name, Supplier<T> blockSupplier) {
        return Services.REGISTRY.registerBlock(name, blockSupplier);
    }

    // Building Blocks
    public static final Supplier<Block> OAK_BRICKS = register("oak_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Supplier<Block> OAK_BRICK_STAIRS = register("oak_brick_stairs",
            () -> new StairBlock(OAK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final Supplier<Block> OAK_BRICK_SLAB = register("oak_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final Supplier<Block> OAK_BRICK_FENCE = register("oak_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final Supplier<Block> OAK_BRICK_FENCE_GATE = register("oak_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final Supplier<Block> OAK_BRICK_PRESSURE_PLATE = register("oak_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Supplier<Block> OAK_BRICK_BUTTON = register("oak_brick_button",
            () -> woodenButton(BlockSetType.OAK));

    public static final Supplier<Block> SPRUCE_BRICKS = register("spruce_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    public static final Supplier<Block> SPRUCE_BRICK_STAIRS = register("spruce_brick_stairs",
            () -> new StairBlock(SPRUCE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS)));
    public static final Supplier<Block> SPRUCE_BRICK_SLAB = register("spruce_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)));
    public static final Supplier<Block> SPRUCE_BRICK_FENCE = register("spruce_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE)));
    public static final Supplier<Block> SPRUCE_BRICK_FENCE_GATE = register("spruce_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.SPRUCE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE)));
    public static final Supplier<Block> SPRUCE_BRICK_PRESSURE_PLATE = register("spruce_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE)));
    public static final Supplier<Block> SPRUCE_BRICK_BUTTON = register("spruce_brick_button",
            () -> woodenButton(BlockSetType.SPRUCE));

    public static final Supplier<Block> BIRCH_BRICKS = register("birch_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)));
    public static final Supplier<Block> BIRCH_BRICK_STAIRS = register("birch_brick_stairs",
            () -> new StairBlock(BIRCH_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_STAIRS)));
    public static final Supplier<Block> BIRCH_BRICK_SLAB = register("birch_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB)));
    public static final Supplier<Block> BIRCH_BRICK_FENCE = register("birch_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_FENCE)));
    public static final Supplier<Block> BIRCH_BRICK_FENCE_GATE = register("birch_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.BIRCH, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_FENCE_GATE)));
    public static final Supplier<Block> BIRCH_BRICK_PRESSURE_PLATE = register("birch_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.BIRCH, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PRESSURE_PLATE)));
    public static final Supplier<Block> BIRCH_BRICK_BUTTON = register("birch_brick_button",
            () -> woodenButton(BlockSetType.BIRCH));

    public static final Supplier<Block> JUNGLE_BRICKS = register("jungle_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)));
    public static final Supplier<Block> JUNGLE_BRICK_STAIRS = register("jungle_brick_stairs",
            () -> new StairBlock(JUNGLE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS)));
    public static final Supplier<Block> JUNGLE_BRICK_SLAB = register("jungle_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB)));
    public static final Supplier<Block> JUNGLE_BRICK_FENCE = register("jungle_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE)));
    public static final Supplier<Block> JUNGLE_BRICK_FENCE_GATE = register("jungle_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.JUNGLE, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE_GATE)));
    public static final Supplier<Block> JUNGLE_BRICK_PRESSURE_PLATE = register("jungle_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.JUNGLE, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PRESSURE_PLATE)));
    public static final Supplier<Block> JUNGLE_BRICK_BUTTON = register("jungle_brick_button",
            () -> woodenButton(BlockSetType.JUNGLE));

    public static final Supplier<Block> ACACIA_BRICKS = register("acacia_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final Supplier<Block> ACACIA_BRICK_STAIRS = register("acacia_brick_stairs",
            () -> new StairBlock(ACACIA_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
    public static final Supplier<Block> ACACIA_BRICK_SLAB = register("acacia_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
    public static final Supplier<Block> ACACIA_BRICK_FENCE = register("acacia_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
    public static final Supplier<Block> ACACIA_BRICK_FENCE_GATE = register("acacia_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
    public static final Supplier<Block> ACACIA_BRICK_PRESSURE_PLATE = register("acacia_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final Supplier<Block> ACACIA_BRICK_BUTTON = register("acacia_brick_button",
            () -> woodenButton(BlockSetType.ACACIA));

    public static final Supplier<Block> DARK_OAK_BRICKS = register("dark_oak_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)));
    public static final Supplier<Block> DARK_OAK_BRICK_STAIRS = register("dark_oak_brick_stairs",
            () -> new StairBlock(DARK_OAK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)));
    public static final Supplier<Block> DARK_OAK_BRICK_SLAB = register("dark_oak_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)));
    public static final Supplier<Block> DARK_OAK_BRICK_FENCE = register("dark_oak_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE)));
    public static final Supplier<Block> DARK_OAK_BRICK_FENCE_GATE = register("dark_oak_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.DARK_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE_GATE)));
    public static final Supplier<Block> DARK_OAK_BRICK_PRESSURE_PLATE = register("dark_oak_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.DARK_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PRESSURE_PLATE)));
    public static final Supplier<Block> DARK_OAK_BRICK_BUTTON = register("dark_oak_brick_button",
            () -> woodenButton(BlockSetType.DARK_OAK));

    public static final Supplier<Block> MANGROVE_BRICKS = register("mangrove_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)));
    public static final Supplier<Block> MANGROVE_BRICK_STAIRS = register("mangrove_brick_stairs",
            () -> new StairBlock(MANGROVE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS)));
    public static final Supplier<Block> MANGROVE_BRICK_SLAB = register("mangrove_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB)));
    public static final Supplier<Block> MANGROVE_BRICK_FENCE = register("mangrove_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE)));
    public static final Supplier<Block> MANGROVE_BRICK_FENCE_GATE = register("mangrove_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.MANGROVE, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE)));
    public static final Supplier<Block> MANGROVE_BRICK_PRESSURE_PLATE = register("mangrove_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.MANGROVE, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE)));
    public static final Supplier<Block> MANGROVE_BRICK_BUTTON = register("mangrove_brick_button",
            () -> woodenButton(BlockSetType.MANGROVE));

    public static final Supplier<Block> CHERRY_BRICKS = register("cherry_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)));
    public static final Supplier<Block> CHERRY_BRICK_STAIRS = register("cherry_brick_stairs",
            () -> new StairBlock(CHERRY_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_STAIRS)));
    public static final Supplier<Block> CHERRY_BRICK_SLAB = register("cherry_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB)));
    public static final Supplier<Block> CHERRY_BRICK_FENCE = register("cherry_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE)));
    public static final Supplier<Block> CHERRY_BRICK_FENCE_GATE = register("cherry_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE_GATE)));
    public static final Supplier<Block> CHERRY_BRICK_PRESSURE_PLATE = register("cherry_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PRESSURE_PLATE)));
    public static final Supplier<Block> CHERRY_BRICK_BUTTON = register("cherry_brick_button",
            () -> woodenButton(BlockSetType.CHERRY));

    public static final Supplier<Block> BAMBOO_BRICKS = register("bamboo_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)));
    public static final Supplier<Block> BAMBOO_BRICK_STAIRS = register("bamboo_brick_stairs",
            () -> new StairBlock(BAMBOO_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_STAIRS)));
    public static final Supplier<Block> BAMBOO_BRICK_SLAB = register("bamboo_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB)));
    public static final Supplier<Block> BAMBOO_BRICK_FENCE = register("bamboo_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE)));
    public static final Supplier<Block> BAMBOO_BRICK_FENCE_GATE = register("bamboo_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE_GATE)));
    public static final Supplier<Block> BAMBOO_BRICK_PRESSURE_PLATE = register("bamboo_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PRESSURE_PLATE)));
    public static final Supplier<Block> BAMBOO_BRICK_BUTTON = register("bamboo_brick_button",
            () -> woodenButton(BlockSetType.BAMBOO));

    public static final Supplier<Block> CRIMSON_BRICKS = register("crimson_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)));
    public static final Supplier<Block> CRIMSON_BRICK_STAIRS = register("crimson_brick_stairs",
            () -> new StairBlock(CRIMSON_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS)));
    public static final Supplier<Block> CRIMSON_BRICK_SLAB = register("crimson_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));
    public static final Supplier<Block> CRIMSON_BRICK_FENCE = register("crimson_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE)));
    public static final Supplier<Block> CRIMSON_BRICK_FENCE_GATE = register("crimson_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE)));
    public static final Supplier<Block> CRIMSON_BRICK_PRESSURE_PLATE = register("crimson_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE)));
    public static final Supplier<Block> CRIMSON_BRICK_BUTTON = register("crimson_brick_button",
            () -> woodenButton(BlockSetType.CRIMSON));

    public static final Supplier<Block> WARPED_BRICKS = register("warped_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)));
    public static final Supplier<Block> WARPED_BRICK_STAIRS = register("warped_brick_stairs",
            () -> new StairBlock(WARPED_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS)));
    public static final Supplier<Block> WARPED_BRICK_SLAB = register("warped_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB)));
    public static final Supplier<Block> WARPED_BRICK_FENCE = register("warped_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE)));
    public static final Supplier<Block> WARPED_BRICK_FENCE_GATE = register("warped_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE_GATE)));
    public static final Supplier<Block> WARPED_BRICK_PRESSURE_PLATE = register("warped_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PRESSURE_PLATE)));
    public static final Supplier<Block> WARPED_BRICK_BUTTON = register("warped_brick_button",
            () -> woodenButton(BlockSetType.WARPED));

    public static final Supplier<Block> WOODEN_DOOR = register("wooden_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final Supplier<Block> HATCH = register("hatch",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final Supplier<Block> ASPHALT = register("asphalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> ASPHALT_STAIRS = register("asphalt_stairs",
            () -> new StairBlock(ASPHALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)));
    public static final Supplier<Block> ASPHALT_SLAB = register("asphalt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB)));
    public static final Supplier<Block> ASPHALT_WALL = register("asphalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)));
    public static final Supplier<Block> IRON_PLATE = register("iron_plate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> IRON_PLATE_STAIRS = register("iron_plate_stairs",
            () -> new StairBlock(IRON_PLATE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_STAIRS)));
    public static final Supplier<Block> IRON_PLATE_SLAB = register("iron_plate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB)));
    public static final Supplier<Block> IRON_PLATE_WALL = register("iron_plate_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_WALL)));
    public static final Supplier<Block> STEP = register("step",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));
    public static final Supplier<Block> STEP_SLAB = register("step_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB)));
    public static final Supplier<Block> RAW_WOOD = register("raw_wood",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final Supplier<Block> RAW_WOOD_STAIRS = register("raw_wood_stairs",
            () -> new StairBlock(RAW_WOOD.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).sound(SoundType.WOOD)));
    public static final Supplier<Block> RAW_WOOD_SLAB = register("raw_wood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).sound(SoundType.WOOD)));
    public static final Supplier<Block> RAW_WOOD_WALL = register("raw_wood_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).sound(SoundType.WOOD)));
    public static final Supplier<Block> CONCRETE_BRICKS = register("concrete_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE)));
    public static final Supplier<Block> MECHANICAL_CHAIN = register("mechanical_chain",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> MECHANICAL_VENT = register("mechanical_vent",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> AIR_VENT = register("air_vent",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WHITE_MARBLE = register("white_marble",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> BRICKS = register("bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final Supplier<Block> BRICK_STAIRS = register("brick_stairs",
            () -> new StairBlock(BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS)));
    public static final Supplier<Block> BRICK_SLAB = register("brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB)));
    public static final Supplier<Block> BRICK_WALL = register("brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL)));
    public static final Supplier<Block> SPOTLIGHT = register("spotlight",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE)));
    public static final Supplier<Block> GRAY_AZTEC = register("gray_aztec",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final Supplier<Block> SMOOTH_METAL = register("smooth_metal",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final Supplier<Block> SMOOTH_METAL_STAIRS = register("smooth_metal_stairs",
            () -> new StairBlock(SMOOTH_METAL.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS)));
    public static final Supplier<Block> SMOOTH_METAL_SLAB = register("smooth_metal_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB)));
    public static final Supplier<Block> SMOOTH_METAL_WALL = register("smooth_metal_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL)));
    public static final Supplier<Block> SMOOTH_METAL_FENCE = register("smooth_metal_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
    public static final Supplier<Block> SMOOTH_METAL_FINIAL = register("smooth_metal_finial",
            () -> new FinialBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final Supplier<Block> IRON_STACK = register("iron_stack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> IRON_DOOR = register("iron_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));
    public static final Supplier<Block> LINOLEUM_TILE = register("linoleum_tile",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> GREEN_TILES = register("green_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final Supplier<Block> TILE_MOSAIC = register("tile_mosaic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final Supplier<Block> BLUE_PANELING = register("blue_paneling",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> PIPE = register("pipe",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));
    public static final Supplier<Block> PIPE_INTERSECTION = register("pipe_intersection",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final Supplier<Block> RUSTY_IRON = register("rusty_iron",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    // Colored Blocks
    public static final Supplier<Block> WHITE_PLASTIC = register("white_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Supplier<Block> LIGHT_GRAY_STUCCO = register("light_gray_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL)));
    public static final Supplier<Block> GRAY_STUCCO = register("gray_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL)));
    public static final Supplier<Block> BLACK_MARBLE = register("black_marble",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)));
    public static final Supplier<Block> BROWN_STUCCO = register("brown_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL)));
    public static final Supplier<Block> RED_PLASTIC = register("red_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)));
    public static final Supplier<Block> RED_VELVET = register("red_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)));
    public static final Supplier<Block> ORANGE_PLASTIC = register("orange_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)));
    public static final Supplier<Block> BEIGE_PLASTIC = register("beige_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final Supplier<Block> GOLD_FILGAREE = register("gold_filgaree",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final Supplier<Block> LIME_PLASTIC = register("lime_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL)));
    public static final Supplier<Block> GREEN_PLASTIC = register("green_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL)));
    public static final Supplier<Block> GREEN_VELVET = register("green_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL)));
    public static final Supplier<Block> CYAN_PLASTIC = register("cyan_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL)));
    public static final Supplier<Block> LIGHT_BLUE_PLASTIC = register("light_blue_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL)));
    public static final Supplier<Block> BLUE_PLASTIC = register("blue_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
    public static final Supplier<Block> BLUE_VELVET = register("blue_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
    public static final Supplier<Block> VIOLET_VELVET = register("violet_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL)));
    public static final Supplier<Block> MAGENTA_PLASTIC = register("magenta_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)));
    public static final Supplier<Block> PINK_STUCCO = register("pink_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL)));
    public static final Supplier<Block> WHITE_PLASTIC_CARPET = register("white_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET)));
    public static final Supplier<Block> LIGHT_GRAY_STUCCO_CARPET = register("light_gray_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CARPET)));
    public static final Supplier<Block> GRAY_STUCCO_CARPET = register("gray_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CARPET)));
    public static final Supplier<Block> BLACK_MARBLE_CARPET = register("black_marble_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET)));
    public static final Supplier<Block> BROWN_STUCCO_CARPET = register("brown_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_CARPET)));
    public static final Supplier<Block> RED_PLASTIC_CARPET = register("red_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET)));
    public static final Supplier<Block> RED_VELVET_CARPET = register("red_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET)));
    public static final Supplier<Block> ORANGE_PLASTIC_CARPET = register("orange_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CARPET)));
    public static final Supplier<Block> BEIGE_PLASTIC_CARPET = register("beige_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET)));
    public static final Supplier<Block> GOLD_FILGAREE_CARPET = register("gold_filgaree_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET)));
    public static final Supplier<Block> LIME_PLASTIC_CARPET = register("lime_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_CARPET)));
    public static final Supplier<Block> GREEN_PLASTIC_CARPET = register("green_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET)));
    public static final Supplier<Block> GREEN_VELVET_CARPET = register("green_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET)));
    public static final Supplier<Block> CYAN_PLASTIC_CARPET = register("cyan_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_CARPET)));
    public static final Supplier<Block> LIGHT_BLUE_PLASTIC_CARPET = register("light_blue_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_CARPET)));
    public static final Supplier<Block> BLUE_PLASTIC_CARPET = register("blue_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET)));
    public static final Supplier<Block> BLUE_VELVET_CARPET = register("blue_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET)));
    public static final Supplier<Block> VIOLET_VELVET_CARPET = register("violet_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CARPET)));
    public static final Supplier<Block> MAGENTA_PLASTIC_CARPET = register("magenta_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_CARPET)));
    public static final Supplier<Block> PINK_STUCCO_CARPET = register("pink_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_CARPET)));
    public static final Supplier<Block> CONCRETE = register("concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE)));
    public static final Supplier<Block> STRIPE = register("stripe",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));
    public static final Supplier<Block> STRIPE_INTERSECTION = register("stripe_intersection",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final Supplier<Block> CORRUGATED_STEEL = register("corrugated_steel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> STORAGE_CRATE = register("storage_crate",
            () -> storageCrate(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)));
    public static final Supplier<Block> CLAY_TILE = register("clay_tile",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)));
    public static final Supplier<Block> SHALE = register("shale",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> GLASS = register("glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Supplier<Block> GLASS_PANE = register("glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    // Natural Blocks
    public static final Supplier<Block> REINFORCED_PANELING = register("reinforced_paneling",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> THIN_STRIPE = register("thin_stripe",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final Supplier<Block> CAUTION_TAPE = register("caution_tape",
            () -> new SoulSandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_SAND).sound(SoundType.SAND)));
    public static final Supplier<Block> DARK_STONE_BRICKS = register("dark_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final Supplier<Block> DECORATIVE_CONCRETE = register("decorative_concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final Supplier<Block> PILLAR = register("pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));
    public static final Supplier<Block> CIRCUITRY = register("circuitry",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_BLOCK)));
    public static final Supplier<Block> LIMESTONE_BRICK = register("limestone_brick",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final Supplier<Block> CORRUGATED_COPPER = register("corrugated_copper",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RIVETED_STEEL = register("riveted_steel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> FLUORESCENT_PANEL = register("fluorescent_panel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).sound(SoundType.GLASS)));
    public static final Supplier<Block> MECHANICAL = register("mechanical",
            () -> new MushroomBlock(TreeFeatures.HUGE_BROWN_MUSHROOM, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GLASS)
                    .lightLevel((value -> 1))
                    .hasPostProcess(ModBlocks::always)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> POTTED_MECHANICAL = register("potted_mechanical",
            () -> flowerPot(MECHANICAL.get()));
    public static final Supplier<Block> CURTAINS = register("curtains",
            () -> new CurtainsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.WOOL)));
    public static final Supplier<Block> COUNTERTOP = register("countertop",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> WHITE_LIGHTING = register("white_lighting",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE)));
    public static final Supplier<Block> MONITOR = register("monitor",
            () -> new MonitorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN).sound(SoundType.GLASS)));
    public static final Supplier<Block> LIT_MONITOR = register("lit_monitor",
            () -> new MonitorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).sound(SoundType.GLASS)));

    // Functional Blocks
    public static final Supplier<Block> LANTERN = register("lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(value -> 14)
                    .sound(SoundType.GLASS)));
    public static final Supplier<Block> WALL_LANTERN = register("wall_lantern",
            () -> new WallLanternBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(value -> 14)
                    .sound(SoundType.GLASS)));
    public static final Supplier<Block> LED = register("led",
            () -> new RedstoneTorchBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel((getLightValueLit(7)))
                    .sound(SoundType.GLASS)));
    public static final Supplier<Block> WALL_LED = register("wall_led",
            () -> new RedstoneWallTorchBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel((getLightValueLit(7)))
                    .sound(SoundType.GLASS)));
    public static final Supplier<Block> LAMP = register("lamp",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)));
    public static final Supplier<Block> TECH_ACCENT = register("tech_accent",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
    public static final Supplier<Block> YOGIFIER = register("yogifier",
            () -> new YogifierBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
    public static final Supplier<Block> OVEN = register("oven",
            () -> new OvenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE)));
    public static final Supplier<Block> CONSOLE = register("console",
            () -> new NoteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NOTE_BLOCK)));
    public static final Supplier<Block> RECORD_PLAYER = register("record_player",
            () -> new RecordPlayerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUKEBOX)));
    public static final Supplier<Block> TABLE = register("table",
            () -> new TableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> SINK = register("sink",
            () -> new SinkBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> WATER_SINK = register("water_sink",
            () -> new LayeredSinkBlock(Biome.Precipitation.RAIN, SinkInteraction.WATER, BlockBehaviour.Properties.ofLegacyCopy(Blocks.CAULDRON)));
    public static final Supplier<Block> LAVA_SINK = register("lava_sink",
            () -> new LavaSinkBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.CAULDRON).lightLevel((blockState) -> 15)));
    public static final Supplier<Block> POWDER_SNOW_SINK = register("powder_snow_sink",
            () -> new LayeredSinkBlock(Biome.Precipitation.SNOW, SinkInteraction.POWDER_SNOW, BlockBehaviour.Properties.ofLegacyCopy(Blocks.CAULDRON)));
    public static final Supplier<Block> LADDER = register("ladder",
            () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER)));
    public static final Supplier<Block> YOG_SIGN = registerBlockWithoutBlockItem("yog_sign",
            () -> new YogStandingSignBlock(ModWoodType.YOG, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final Supplier<Block> YOG_WALL_SIGN = registerBlockWithoutBlockItem("yog_wall_sign",
            () -> new YogWallSignBlock(ModWoodType.YOG, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final Supplier<Block> CRATE = register("crate",
            () -> new CrateBlock(BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.BASS)
                    .mapColor(MapColor.WOOD)
                    .strength(2.5F)
                    .sound(SoundType.WOOD), () -> ModBlockEntityType.CRATE.get()));
    public static final Supplier<Block> FANCY_TABLE = register("fancy_table",
            () -> new FancyTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Redstone Blocks
    public static final Supplier<Block> WIRE = register("wire",
            () -> new WireBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()));
    public static final Supplier<Block> DIODE = register("diode",
            () -> new WireDiodeBlock(BlockBehaviour.Properties.of()
                    .instabreak()
                    .sound(SoundType.WOOD)));
    public static final Supplier<Block> CORRUGATED_REDSTONE = register("corrugated_redstone",
            () -> new PoweredBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .isRedstoneConductor(ModBlocks::never)));
    public static final Supplier<Block> CLASSIC_LEVER = register("classic_lever",
            () -> new LeverBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LEVER)));
    public static final Supplier<Block> LEVER = register("lever",
            () -> new LeverBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LEVER)));
    public static final Supplier<Block> MOVING_PISTON = register("moving_piston",
            () -> new MovingPistonBlock(BlockBehaviour.Properties.of()
                    .strength(-1.0F)
                    .dynamicShape()
                    .noLootTable()
                    .noOcclusion()
                    .isRedstoneConductor(ModBlocks::never)
                    .isSuffocating(ModBlocks::never)
                    .isViewBlocking(ModBlocks::never)
            ));
    public static final Supplier<Block> PISTON = register("piston", () -> pistonBase(false));
    public static final Supplier<Block> PISTON_HEAD = register("piston_head",
            () -> new PistonHeadBlock(BlockBehaviour.Properties.of()
                    .strength(1.5F)
                    .noLootTable()
            ));
    public static final Supplier<Block> STICKY_PISTON = register("sticky_piston",
            () -> pistonBase(true));
    public static final Supplier<Block> DISPENSER = register("dispenser",
            () -> new DispenserBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DISPENSER)));
    public static final Supplier<Block> METROVOX_RAIL = register("metrovox_rail",
            () -> new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
    public static final Supplier<Block> POWERED_METROVOX_RAIL = register("powered_metrovox_rail",
            () -> new PoweredMetroVoxRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POWERED_RAIL)));
    public static final Supplier<Block> METROVOX_DETECTOR_RAIL = register("metrovox_detector_rail",
            () -> new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL)));
    public static final Supplier<Block> METROVOX_ACTIVATOR_RAIL = register("metrovox_activator_rail",
            () -> new PoweredMetroVoxRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACTIVATOR_RAIL)));
    public static final Supplier<Block> DYNAMITE = register("dynamite",
            () -> new DynamiteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)));

    // Spawn Eggs
    public static final Supplier<Block> CAGE = register("cage",
            () -> new CageBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPAWNER)));

    // Not in YogTab
    public static final Supplier<Block> BEIGE_WOOL = register("beige_wool",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final Supplier<Block> BEIGE_CARPET = register("beige_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET)));
    public static final Supplier<Block> BEIGE_TERRACOTTA = register("beige_terracotta",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_TERRACOTTA)));
    public static final Supplier<Block> BEIGE_CONCRETE = register("beige_concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CONCRETE)));
    public static final Supplier<Block> BEIGE_CONCRETE_POWDER = register("beige_concrete_powder",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CONCRETE_POWDER)));
    public static final Supplier<Block> BEIGE_STAINED_GLASS = register("beige_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS)));
    public static final Supplier<Block> BEIGE_STAINED_GLASS_PANE = register("beige_stained_glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS_PANE)));

    // Methods
    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (blockState) -> blockState.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static <T extends Block> Supplier<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return register(name, block);
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static Block flowerPot(Block potted) {
        return new FlowerPotBlock(potted, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    private static Block pistonBase(boolean isSticky) {
        BlockBehaviour.StatePredicate blockbehaviour$statepredicate = (state, getter, pos) -> !state.getValue(net.minecraft.world.level.block.piston.PistonBaseBlock.EXTENDED);
        return new PistonBaseBlock(
                isSticky,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .strength(1.5F)
                        .isRedstoneConductor(ModBlocks::never)
                        .isSuffocating(blockbehaviour$statepredicate)
                        .isViewBlocking(blockbehaviour$statepredicate)
                        .pushReaction(PushReaction.BLOCK)
        );
    }

    private static StorageCrateBlock storageCrate(BlockBehaviour.Properties properties) {
        BlockBehaviour.StatePredicate blockbehaviour$statepredicate = (state, getter, pos) -> {
            BlockEntity blockEntity = getter.getBlockEntity(pos);
            return blockEntity instanceof StorageCrateBlockEntity;
        };
        return new StorageCrateBlock(properties.forceSolidOn().strength(2.0F).isSuffocating(blockbehaviour$statepredicate).isViewBlocking(blockbehaviour$statepredicate).pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::always));
    }

    private static Block woodenButton(BlockSetType type) {
        return new ButtonBlock(type, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY));
    }

}