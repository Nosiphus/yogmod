package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.StorageCrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.piston.MovingPistonBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonBaseBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonHeadBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.ToIntFunction;

public class ModBlocks {

    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("yogmod");

    //Building Blocks
    public static final DeferredBlock<Block> OAK_BRICKS = BLOCKS.register("oak_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> OAK_BRICK_STAIRS = BLOCKS.register("oak_brick_stairs",
            () -> new StairBlock(OAK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<Block> OAK_BRICK_SLAB = BLOCKS.register("oak_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<Block> OAK_BRICK_FENCE = BLOCKS.register("oak_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> OAK_BRICK_FENCE_GATE = BLOCKS.register("oak_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> OAK_BRICK_PRESSURE_PLATE = BLOCKS.register("oak_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> OAK_BRICK_BUTTON = BLOCKS.register("oak_brick_button",
            () -> woodenButton(BlockSetType.OAK));
    public static final DeferredBlock<Block> SPRUCE_BRICKS = BLOCKS.register("spruce_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    public static final DeferredBlock<Block> SPRUCE_BRICK_STAIRS = BLOCKS.register("spruce_brick_stairs",
            () -> new StairBlock(SPRUCE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS)));
    public static final DeferredBlock<Block> SPRUCE_BRICK_SLAB = BLOCKS.register("spruce_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)));
    public static final DeferredBlock<Block> SPRUCE_BRICK_FENCE = BLOCKS.register("spruce_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE)));
    public static final DeferredBlock<Block> SPRUCE_BRICK_FENCE_GATE = BLOCKS.register("spruce_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.SPRUCE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE)));
    public static final DeferredBlock<Block> SPRUCE_BRICK_PRESSURE_PLATE = BLOCKS.register("spruce_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> SPRUCE_BRICK_BUTTON = BLOCKS.register("spruce_brick_button",
            () -> woodenButton(BlockSetType.SPRUCE));
    public static final DeferredBlock<Block> BIRCH_BRICKS = BLOCKS.register("birch_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)));
    public static final DeferredBlock<Block> BIRCH_BRICK_STAIRS = BLOCKS.register("birch_brick_stairs",
            () -> new StairBlock(BIRCH_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_STAIRS)));
    public static final DeferredBlock<Block> BIRCH_BRICK_SLAB = BLOCKS.register("birch_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB)));
    public static final DeferredBlock<Block> BIRCH_BRICK_FENCE = BLOCKS.register("birch_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_FENCE)));
    public static final DeferredBlock<Block> BIRCH_BRICK_FENCE_GATE = BLOCKS.register("birch_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.BIRCH, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_FENCE_GATE)));
    public static final DeferredBlock<Block> BIRCH_BRICK_PRESSURE_PLATE = BLOCKS.register("birch_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.BIRCH, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> BIRCH_BRICK_BUTTON = BLOCKS.register("birch_brick_button",
            () -> woodenButton(BlockSetType.BIRCH));
    public static final DeferredBlock<Block> JUNGLE_BRICKS = BLOCKS.register("jungle_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)));
    public static final DeferredBlock<Block> JUNGLE_BRICK_STAIRS = BLOCKS.register("jungle_brick_stairs",
            () -> new StairBlock(JUNGLE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS)));
    public static final DeferredBlock<Block> JUNGLE_BRICK_SLAB = BLOCKS.register("jungle_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB)));
    public static final DeferredBlock<Block> JUNGLE_BRICK_FENCE = BLOCKS.register("jungle_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE)));
    public static final DeferredBlock<Block> JUNGLE_BRICK_FENCE_GATE = BLOCKS.register("jungle_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.JUNGLE, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE_GATE)));
    public static final DeferredBlock<Block> JUNGLE_BRICK_PRESSURE_PLATE = BLOCKS.register("jungle_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.JUNGLE, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> JUNGLE_BRICK_BUTTON = BLOCKS.register("jungle_brick_button",
            () -> woodenButton(BlockSetType.JUNGLE));
    public static final DeferredBlock<Block> ACACIA_BRICKS = BLOCKS.register("acacia_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final DeferredBlock<Block> ACACIA_BRICK_STAIRS = BLOCKS.register("acacia_brick_stairs",
            () -> new StairBlock(ACACIA_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
    public static final DeferredBlock<Block> ACACIA_BRICK_SLAB = BLOCKS.register("acacia_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
    public static final DeferredBlock<Block> ACACIA_BRICK_FENCE = BLOCKS.register("acacia_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
    public static final DeferredBlock<Block> ACACIA_BRICK_FENCE_GATE = BLOCKS.register("acacia_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
    public static final DeferredBlock<Block> ACACIA_BRICK_PRESSURE_PLATE = BLOCKS.register("acacia_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> ACACIA_BRICK_BUTTON = BLOCKS.register("acacia_brick_button",
            () -> woodenButton(BlockSetType.ACACIA));
    public static final DeferredBlock<Block> DARK_OAK_BRICKS = BLOCKS.register("dark_oak_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)));
    public static final DeferredBlock<Block> DARK_OAK_BRICK_STAIRS = BLOCKS.register("dark_oak_brick_stairs",
            () -> new StairBlock(DARK_OAK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)));
    public static final DeferredBlock<Block> DARK_OAK_BRICK_SLAB = BLOCKS.register("dark_oak_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)));
    public static final DeferredBlock<Block> DARK_OAK_BRICK_FENCE = BLOCKS.register("dark_oak_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE)));
    public static final DeferredBlock<Block> DARK_OAK_BRICK_FENCE_GATE = BLOCKS.register("dark_oak_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.DARK_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> DARK_OAK_BRICK_PRESSURE_PLATE = BLOCKS.register("dark_oak_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.DARK_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> DARK_OAK_BRICK_BUTTON = BLOCKS.register("dark_oak_brick_button",
            () -> woodenButton(BlockSetType.DARK_OAK));
    public static final DeferredBlock<Block> MANGROVE_BRICKS = BLOCKS.register("mangrove_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)));
    public static final DeferredBlock<Block> MANGROVE_BRICK_STAIRS = BLOCKS.register("mangrove_brick_stairs",
            () -> new StairBlock(MANGROVE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS)));
    public static final DeferredBlock<Block> MANGROVE_BRICK_SLAB = BLOCKS.register("mangrove_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB)));
    public static final DeferredBlock<Block> MANGROVE_BRICK_FENCE = BLOCKS.register("mangrove_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE)));
    public static final DeferredBlock<Block> MANGROVE_BRICK_FENCE_GATE = BLOCKS.register("mangrove_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.MANGROVE, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE)));
    public static final DeferredBlock<Block> MANGROVE_BRICK_PRESSURE_PLATE = BLOCKS.register("mangrove_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.MANGROVE, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> MANGROVE_BRICK_BUTTON = BLOCKS.register("mangrove_brick_button",
            () -> woodenButton(BlockSetType.MANGROVE));
    public static final DeferredBlock<Block> CHERRY_BRICKS = BLOCKS.register("cherry_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)));
    public static final DeferredBlock<Block> CHERRY_BRICK_STAIRS = BLOCKS.register("cherry_brick_stairs",
            () -> new StairBlock(CHERRY_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_STAIRS)));
    public static final DeferredBlock<Block> CHERRY_BRICK_SLAB = BLOCKS.register("cherry_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB)));
    public static final DeferredBlock<Block> CHERRY_BRICK_FENCE = BLOCKS.register("cherry_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE)));
    public static final DeferredBlock<Block> CHERRY_BRICK_FENCE_GATE = BLOCKS.register("cherry_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE_GATE)));
    public static final DeferredBlock<Block> CHERRY_BRICK_PRESSURE_PLATE = BLOCKS.register("cherry_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> CHERRY_BRICK_BUTTON = BLOCKS.register("cherry_brick_button",
            () -> woodenButton(BlockSetType.CHERRY));
    public static final DeferredBlock<Block> BAMBOO_BRICKS = BLOCKS.register("bamboo_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)));
    public static final DeferredBlock<Block> BAMBOO_BRICK_STAIRS = BLOCKS.register("bamboo_brick_stairs",
            () -> new StairBlock(BAMBOO_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_STAIRS)));
    public static final DeferredBlock<Block> BAMBOO_BRICK_SLAB = BLOCKS.register("bamboo_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB)));
    public static final DeferredBlock<Block> BAMBOO_BRICK_FENCE = BLOCKS.register("bamboo_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE)));
    public static final DeferredBlock<Block> BAMBOO_BRICK_FENCE_GATE = BLOCKS.register("bamboo_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE_GATE)));
    public static final DeferredBlock<Block> BAMBOO_BRICK_PRESSURE_PLATE = BLOCKS.register("bamboo_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> BAMBOO_BRICK_BUTTON = BLOCKS.register("bamboo_brick_button",
            () -> woodenButton(BlockSetType.BAMBOO));
    public static final DeferredBlock<Block> CRIMSON_BRICKS = BLOCKS.register("crimson_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)));
    public static final DeferredBlock<Block> CRIMSON_BRICK_STAIRS = BLOCKS.register("crimson_brick_stairs",
            () -> new StairBlock(CRIMSON_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS)));
    public static final DeferredBlock<Block> CRIMSON_BRICK_SLAB = BLOCKS.register("crimson_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB)));
    public static final DeferredBlock<Block> CRIMSON_BRICK_FENCE = BLOCKS.register("crimson_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE)));
    public static final DeferredBlock<Block> CRIMSON_BRICK_FENCE_GATE = BLOCKS.register("crimson_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE)));
    public static final DeferredBlock<Block> CRIMSON_BRICK_PRESSURE_PLATE = BLOCKS.register("crimson_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> CRIMSON_BRICK_BUTTON = BLOCKS.register("crimson_brick_button",
            () -> woodenButton(BlockSetType.CRIMSON));
    public static final DeferredBlock<Block> WARPED_BRICKS = BLOCKS.register("warped_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)));
    public static final DeferredBlock<Block> WARPED_BRICK_STAIRS = BLOCKS.register("warped_brick_stairs",
            () -> new StairBlock(WARPED_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS)));
    public static final DeferredBlock<Block> WARPED_BRICK_SLAB = BLOCKS.register("warped_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB)));
    public static final DeferredBlock<Block> WARPED_BRICK_FENCE = BLOCKS.register("warped_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE)));
    public static final DeferredBlock<Block> WARPED_BRICK_FENCE_GATE = BLOCKS.register("warped_brick_fence_gate",
            () -> new FenceGateBlock(WoodType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE_GATE)));
    public static final DeferredBlock<Block> WARPED_BRICK_PRESSURE_PLATE = BLOCKS.register("warped_brick_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.WARPED, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> WARPED_BRICK_BUTTON = BLOCKS.register("warped_brick_button",
            () -> woodenButton(BlockSetType.WARPED));
    public static final DeferredBlock<Block> WOODEN_DOOR = BLOCKS.register("wooden_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<Block> HATCH = BLOCKS.register("hatch",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<Block> ASPHALT = BLOCKS.register("asphalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final DeferredBlock<Block> ASPHALT_STAIRS = BLOCKS.register("asphalt_stairs",
            () -> new StairBlock(ASPHALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_STAIRS)));
    public static final DeferredBlock<Block> ASPHALT_SLAB = BLOCKS.register("asphalt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB)));
    public static final DeferredBlock<Block> ASPHALT_WALL = BLOCKS.register("asphalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)));
    public static final DeferredBlock<Block> IRON_PLATE = BLOCKS.register("iron_plate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> IRON_PLATE_STAIRS = BLOCKS.register("iron_plate_stairs",
            () -> new StairBlock(IRON_PLATE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_STAIRS)));
    public static final DeferredBlock<Block> IRON_PLATE_SLAB = BLOCKS.register("iron_plate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB)));
    public static final DeferredBlock<Block> IRON_PLATE_WALL = BLOCKS.register("iron_plate_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_WALL)));
    public static final DeferredBlock<Block> STEP = BLOCKS.register("step",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));
    public static final DeferredBlock<Block> STEP_SLAB = BLOCKS.register("step_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB)));
    public static final DeferredBlock<Block> RAW_WOOD = BLOCKS.register("raw_wood",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> RAW_WOOD_STAIRS = BLOCKS.register("raw_wood_stairs",
            () -> new StairBlock(RAW_WOOD.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> RAW_WOOD_SLAB = BLOCKS.register("raw_wood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> RAW_WOOD_WALL = BLOCKS.register("raw_wood_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> CONCRETE_BRICKS = BLOCKS.register("concrete_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE)));
    public static final DeferredBlock<Block> MECHANICAL_CHAIN = BLOCKS.register("mechanical_chain",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> MECHANICAL_VENT = BLOCKS.register("mechanical_vent",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> AIR_VENT = BLOCKS.register("air_vent",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> WHITE_MARBLE = BLOCKS.register("white_marble",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BRICKS = BLOCKS.register("bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> BRICK_STAIRS = BLOCKS.register("brick_stairs",
            () -> new StairBlock(BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS)));
    public static final DeferredBlock<Block> BRICK_SLAB = BLOCKS.register("brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB)));
    public static final DeferredBlock<Block> BRICK_WALL = BLOCKS.register("brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL)));
    public static final DeferredBlock<Block> SPOTLIGHT = BLOCKS.register("spotlight",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE)));
    public static final DeferredBlock<Block> GRAY_AZTEC = BLOCKS.register("gray_aztec",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final DeferredBlock<Block> SMOOTH_METAL = BLOCKS.register("smooth_metal",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> SMOOTH_METAL_STAIRS = BLOCKS.register("smooth_metal_stairs",
            () -> new StairBlock(SMOOTH_METAL.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS)));
    public static final DeferredBlock<Block> SMOOTH_METAL_SLAB = BLOCKS.register("smooth_metal_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB)));
    public static final DeferredBlock<Block> SMOOTH_METAL_WALL = BLOCKS.register("smooth_metal_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL)));
    public static final DeferredBlock<Block> SMOOTH_METAL_FENCE = BLOCKS.register("smooth_metal_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
    public static final DeferredBlock<Block> SMOOTH_METAL_FINIAL = BLOCKS.register("smooth_metal_finial",
            () -> new FinialBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> IRON_STACK = BLOCKS.register("iron_stack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> IRON_DOOR = BLOCKS.register("iron_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));
    public static final DeferredBlock<Block> LINOLEUM_TILE = BLOCKS.register("linoleum_tile",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> GREEN_TILES = BLOCKS.register("green_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final DeferredBlock<Block> TILE_MOSAIC = BLOCKS.register("tile_mosaic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final DeferredBlock<Block> BLUE_PANELING = BLOCKS.register("blue_paneling",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> PIPE = BLOCKS.register("pipe",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));
    public static final DeferredBlock<Block> PIPE_INTERSECTION = BLOCKS.register("pipe_intersection",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<Block> RUSTY_IRON = BLOCKS.register("rusty_iron",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    //Colored Blocks
    public static final DeferredBlock<Block> WHITE_PLASTIC = BLOCKS.register("white_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> LIGHT_GRAY_STUCCO = BLOCKS.register("light_gray_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL)));
    public static final DeferredBlock<Block> GRAY_STUCCO = BLOCKS.register("gray_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL)));
    public static final DeferredBlock<Block> BLACK_MARBLE = BLOCKS.register("black_marble",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)));
    public static final DeferredBlock<Block> BROWN_STUCCO = BLOCKS.register("brown_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL)));
    public static final DeferredBlock<Block> RED_PLASTIC = BLOCKS.register("red_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)));
    public static final DeferredBlock<Block> RED_VELVET = BLOCKS.register("red_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)));
    public static final DeferredBlock<Block> ORANGE_PLASTIC = BLOCKS.register("orange_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)));
    public static final DeferredBlock<Block> BEIGE_PLASTIC = BLOCKS.register("beige_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final DeferredBlock<Block> GOLD_FILGAREE = BLOCKS.register("gold_filgaree",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final DeferredBlock<Block> LIME_PLASTIC = BLOCKS.register("lime_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL)));
    public static final DeferredBlock<Block> GREEN_PLASTIC = BLOCKS.register("green_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL)));
    public static final DeferredBlock<Block> GREEN_VELVET = BLOCKS.register("green_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL)));
    public static final DeferredBlock<Block> CYAN_PLASTIC = BLOCKS.register("cyan_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL)));
    public static final DeferredBlock<Block> LIGHT_BLUE_PLASTIC = BLOCKS.register("light_blue_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL)));
    public static final DeferredBlock<Block> BLUE_PLASTIC = BLOCKS.register("blue_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
    public static final DeferredBlock<Block> BLUE_VELVET = BLOCKS.register("blue_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
    public static final DeferredBlock<Block> VIOLET_VELVET = BLOCKS.register("violet_velvet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL)));
    public static final DeferredBlock<Block> MAGENTA_PLASTIC = BLOCKS.register("magenta_plastic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)));
    public static final DeferredBlock<Block> PINK_STUCCO = BLOCKS.register("pink_stucco",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL)));
    public static final DeferredBlock<Block> WHITE_PLASTIC_CARPET = BLOCKS.register("white_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET)));
    public static final DeferredBlock<Block> LIGHT_GRAY_STUCCO_CARPET = BLOCKS.register("light_gray_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CARPET)));
    public static final DeferredBlock<Block> GRAY_STUCCO_CARPET = BLOCKS.register("gray_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CARPET)));
    public static final DeferredBlock<Block> BLACK_MARBLE_CARPET = BLOCKS.register("black_marble_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET)));
    public static final DeferredBlock<Block> BROWN_STUCCO_CARPET = BLOCKS.register("brown_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_CARPET)));
    public static final DeferredBlock<Block> RED_PLASTIC_CARPET = BLOCKS.register("red_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET)));
    public static final DeferredBlock<Block> RED_VELVET_CARPET = BLOCKS.register("red_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET)));
    public static final DeferredBlock<Block> ORANGE_PLASTIC_CARPET = BLOCKS.register("orange_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CARPET)));
    public static final DeferredBlock<Block> BEIGE_PLASTIC_CARPET = BLOCKS.register("beige_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET)));
    public static final DeferredBlock<Block> GOLD_FILGAREE_CARPET = BLOCKS.register("gold_filgaree_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET)));
    public static final DeferredBlock<Block> LIME_PLASTIC_CARPET = BLOCKS.register("lime_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_CARPET)));
    public static final DeferredBlock<Block> GREEN_PLASTIC_CARPET = BLOCKS.register("green_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET)));
    public static final DeferredBlock<Block> GREEN_VELVET_CARPET = BLOCKS.register("green_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET)));
    public static final DeferredBlock<Block> CYAN_PLASTIC_CARPET = BLOCKS.register("cyan_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_CARPET)));
    public static final DeferredBlock<Block> LIGHT_BLUE_PLASTIC_CARPET = BLOCKS.register("light_blue_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_CARPET)));
    public static final DeferredBlock<Block> BLUE_PLASTIC_CARPET = BLOCKS.register("blue_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET)));
    public static final DeferredBlock<Block> BLUE_VELVET_CARPET = BLOCKS.register("blue_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET)));
    public static final DeferredBlock<Block> VIOLET_VELVET_CARPET = BLOCKS.register("violet_velvet_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CARPET)));
    public static final DeferredBlock<Block> MAGENTA_PLASTIC_CARPET = BLOCKS.register("magenta_plastic_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_CARPET)));
    public static final DeferredBlock<Block> PINK_STUCCO_CARPET = BLOCKS.register("pink_stucco_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_CARPET)));
    public static final DeferredBlock<Block> CONCRETE = BLOCKS.register("concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE)));
    public static final DeferredBlock<Block> STRIPE = BLOCKS.register("stripe",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));
    public static final DeferredBlock<Block> STRIPE_INTERSECTION = BLOCKS.register("stripe_intersection",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<Block> CORRUGATED_STEEL = BLOCKS.register("corrugated_steel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> STORAGE_CRATE = BLOCKS.register("storage_crate",
            () -> storageCrate(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)));
    public static final DeferredBlock<Block> CLAY_TILE = BLOCKS.register("clay_tile",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)));
    public static final DeferredBlock<Block> SHALE = BLOCKS.register("shale",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> GLASS = BLOCKS.register("glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final DeferredBlock<Block> GLASS_PANE = BLOCKS.register("glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    //Natural Blocks
    public static final DeferredBlock<Block> REINFORCED_PANELING = BLOCKS.register("reinforced_paneling",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> THIN_STRIPE = BLOCKS.register("thin_stripe",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final DeferredBlock<Block> CAUTION_TAPE = BLOCKS.register("caution_tape",
            () -> new SoulSandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_SAND).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> DARK_STONE_BRICKS = BLOCKS.register("dark_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> DECORATIVE_CONCRETE = BLOCKS.register("decorative_concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final DeferredBlock<Block> PILLAR = BLOCKS.register("pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));
    public static final DeferredBlock<Block> CIRCUITRY = BLOCKS.register("circuitry",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_BLOCK)));
    public static final DeferredBlock<Block> LIMESTONE_BRICK = BLOCKS.register("limestone_brick",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> CORRUGATED_COPPER = BLOCKS.register("corrugated_copper",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> RIVETED_STEEL = BLOCKS.register("riveted_steel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> FLUORESCENT_PANEL = BLOCKS.register("fluorescent_panel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> MECHANICAL = BLOCKS.register("mechanical",
            () -> new MushroomBlock(TreeFeatures.HUGE_BROWN_MUSHROOM, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GLASS)
                    .lightLevel((value -> 1))
                    .hasPostProcess(ModBlocks::always)
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> POTTED_MECHANICAL = BLOCKS.register("potted_mechanical",
            () -> flowerPot(MECHANICAL.get()));
    public static final DeferredBlock<Block> CURTAINS = BLOCKS.register("curtains",
            () -> new CurtainsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> COUNTERTOP = BLOCKS.register("countertop",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> WHITE_LIGHTING = BLOCKS.register("white_lighting",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE)));
    public static final DeferredBlock<Block> MONITOR = BLOCKS.register("monitor",
            () -> new MonitorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN).sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> LIT_MONITOR = BLOCKS.register("lit_monitor",
            () -> new MonitorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN).sound(SoundType.GLASS)));

    //Functional Blocks
    public static final DeferredBlock<Block> LANTERN = BLOCKS.register("lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(value -> 14)
                    .sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> WALL_LANTERN = BLOCKS.register("wall_lantern",
            () -> new WallLanternBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(value -> 14)
                    .sound(SoundType.GLASS)
                    .lootFrom(LANTERN)));
    public static final DeferredBlock<Block> LED = BLOCKS.register("led",
            () -> new RedstoneTorchBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel((getLightValueLit(7)))
                    .sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> WALL_LED = BLOCKS.register("wall_led",
            () -> new RedstoneWallTorchBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel((getLightValueLit(7)))
                    .sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> LAMP = BLOCKS.register("lamp",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)));
    public static final DeferredBlock<Block> TECH_ACCENT = BLOCKS.register("tech_accent",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
    public static final DeferredBlock<Block> YOGIFIER = BLOCKS.register("yogifier",
            () -> new YogifierBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
    public static final DeferredBlock<Block> OVEN = BLOCKS.register("oven",
            () -> new OvenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE)));

    //Redstone Blocks

    public static final DeferredBlock<Block> MOVING_PISTON = BLOCKS.register("moving_piston",
            () -> new MovingPistonBlock(BlockBehaviour.Properties.of()
                    .strength(-1.0F)
                    .dynamicShape()
                    .noLootTable()
                    .noOcclusion()
                    .isRedstoneConductor(ModBlocks::never)
                    .isSuffocating(ModBlocks::never)
                    .isViewBlocking(ModBlocks::never)
            ));
    public static final DeferredBlock<Block> PISTON = BLOCKS.register("piston", () -> pistonBase(false));
    public static final DeferredBlock<Block> PISTON_HEAD = BLOCKS.register("piston_head",
            () -> new PistonHeadBlock(BlockBehaviour.Properties.of()
                    .strength(1.5F)
                    .noLootTable()
            ));
    public static final DeferredBlock<Block> STICKY_PISTON = BLOCKS.register("sticky_piston",
            () -> pistonBase(true));

    //Tools & Utilities

    //Combat

    //Not in YogTab
    public static final DeferredBlock<Block> BEIGE_WOOL = BLOCKS.register("beige_wool",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final DeferredBlock<Block> BEIGE_CARPET = BLOCKS.register("beige_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET)));
    public static final DeferredBlock<Block> BEIGE_TERRACOTTA = BLOCKS.register("beige_terracotta",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_TERRACOTTA)));
    public static final DeferredBlock<Block> BEIGE_CONCRETE = BLOCKS.register("beige_concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CONCRETE)));
    public static final DeferredBlock<Block> BEIGE_CONCRETE_POWDER = BLOCKS.register("beige_concrete_powder",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CONCRETE_POWDER)));
    //Beige Glazed Terracotta
    public static final DeferredBlock<Block> BEIGE_STAINED_GLASS = BLOCKS.register("beige_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS)));
    public static final DeferredBlock<Block> BEIGE_STAINED_GLASS_PANE = BLOCKS.register("beige_stained_glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS_PANE)));
    //Beige Shulker Box
    //Beige Bed
    //Beige Candle
    //Beige Banner

    //Methods
    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (blockState) -> {
            return blockState.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
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
        BlockBehaviour.StatePredicate blockbehaviour$statepredicate = (p_152641_, p_152642_, p_152643_) -> !p_152641_.getValue(net.minecraft.world.level.block.piston.PistonBaseBlock.EXTENDED);
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
            if(blockEntity instanceof StorageCrateBlockEntity storageCrateBlockEntity) {
                return true;
            }
            return true;
        };
        return new StorageCrateBlock(properties.forceSolidOn().strength(2.0F).isSuffocating(blockbehaviour$statepredicate).isViewBlocking(blockbehaviour$statepredicate).pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::always));
    }

    private static Block woodenButton(BlockSetType type) {
        return new ButtonBlock(type, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY));
    }

}
