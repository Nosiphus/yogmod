package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.block.MonitorBlock;
import com.nosiphus.yogmod.util.Reference;
import net.minecraft.block.*;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<Block> ACACIA_BRICKS = BLOCKS.register("acacia_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> ACACIA_BRICK_FENCE = BLOCKS.register("acacia_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.ACACIA_FENCE)));
    public static final RegistryObject<Block> ACACIA_BRICK_FENCE_GATE = BLOCKS.register("acacia_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.ACACIA_FENCE_GATE)));
    public static final RegistryObject<Block> ACACIA_BRICK_SLAB = BLOCKS.register("acacia_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.ACACIA_SLAB)));
    public static final RegistryObject<Block> ACACIA_BRICK_STAIRS = BLOCKS.register("acacia_brick_stairs", () -> new StairsBlock(Blocks.ACACIA_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.ACACIA_STAIRS)));
    public static final RegistryObject<Block> AIR_VENT = BLOCKS.register("air_vent", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ASPHALT = BLOCKS.register("asphalt", () -> new Block(AbstractBlock.Properties.from(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> ASPHALT_SLAB = BLOCKS.register("asphalt_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_SLAB)));
    public static final RegistryObject<Block> ASPHALT_STAIRS = BLOCKS.register("asphalt_stairs", () -> new StairsBlock(Blocks.COBBLESTONE_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.COBBLESTONE_STAIRS)));
    public static final RegistryObject<Block> ASPHALT_WALL = BLOCKS.register("asphalt_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_WALL)));
    public static final RegistryObject<Block> BEIGE_PLASTIC = BLOCKS.register("beige_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static final RegistryObject<Block> BIRCH_BRICKS = BLOCKS.register("birch_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> BIRCH_BRICK_FENCE = BLOCKS.register("birch_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.BIRCH_FENCE)));
    public static final RegistryObject<Block> BIRCH_BRICK_FENCE_GATE = BLOCKS.register("birch_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.BIRCH_FENCE_GATE)));
    public static final RegistryObject<Block> BIRCH_BRICK_SLAB = BLOCKS.register("birch_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.BIRCH_SLAB)));
    public static final RegistryObject<Block> BIRCH_BRICK_STAIRS = BLOCKS.register("birch_brick_stairs", () -> new StairsBlock(Blocks.BIRCH_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.BIRCH_STAIRS)));
    public static final RegistryObject<Block> BLACK_MARBLE = BLOCKS.register("black_marble", () -> new Block(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static final RegistryObject<Block> BLUE_PANELING = BLOCKS.register("blue_paneling", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> BLUE_PLASTIC = BLOCKS.register("blue_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.BLUE_WOOL)));
    public static final RegistryObject<Block> BRICKS = BLOCKS.register("bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.BRICKS)));
    public static final RegistryObject<Block> BRICK_SLAB = BLOCKS.register("brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.BRICK_SLAB)));
    public static final RegistryObject<Block> BRICK_STAIRS = BLOCKS.register("brick_stairs", () -> new StairsBlock(Blocks.BRICK_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.BRICK_STAIRS)));
    public static final RegistryObject<Block> BRICK_WALL = BLOCKS.register("brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.BRICK_WALL)));
    public static final RegistryObject<Block> CAUTION_TAPE = BLOCKS.register("caution_tape", () -> new SoulSandBlock(AbstractBlock.Properties.from(Blocks.SOUL_SAND).sound(SoundType.SAND)));
    public static final RegistryObject<Block> CIRCUITRY = BLOCKS.register("circuitry", () -> new Block(AbstractBlock.Properties.from(Blocks.REDSTONE_BLOCK)));
    public static final RegistryObject<Block> CLASSIC_LEVER = BLOCKS.register("classic_lever", () -> new LeverBlock(AbstractBlock.Properties.from(Blocks.LEVER)));
    public static final RegistryObject<Block> CLAY_TILE = BLOCKS.register("clay_tile", () -> new Block(AbstractBlock.Properties.from(Blocks.MAGENTA_WOOL)));
    public static final RegistryObject<Block> CONCRETE = BLOCKS.register("concrete", () -> new Block(AbstractBlock.Properties.from(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> CONSOLE = BLOCKS.register("console", () -> new NoteBlock(AbstractBlock.Properties.from(Blocks.NOTE_BLOCK)));
    public static final RegistryObject<Block> CORRUGATED_COPPER = BLOCKS.register("corrugated_copper", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CORRUGATED_STEEL = BLOCKS.register("corrugated_steel", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> COUNTERTOP = BLOCKS.register("countertop", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
    //public static final RegistryObject<Block> CRATE
    public static final RegistryObject<Block> CRIMSON_BRICKS = BLOCKS.register("crimson_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_BRICK_FENCE = BLOCKS.register("crimson_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FENCE)));
    public static final RegistryObject<Block> CRIMSON_BRICK_FENCE_GATE = BLOCKS.register("crimson_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FENCE_GATE)));
    public static final RegistryObject<Block> CRIMSON_BRICK_SLAB = BLOCKS.register("crimson_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_SLAB)));
    public static final RegistryObject<Block> CRIMSON_BRICK_STAIRS = BLOCKS.register("crimson_brick_stairs", () -> new StairsBlock(Blocks.CRIMSON_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.CRIMSON_STAIRS)));
    public static final RegistryObject<Block> CURTAINS = BLOCKS.register("curtains", () -> new VineBlock(AbstractBlock.Properties.from(Blocks.VINE)));
    public static final RegistryObject<Block> CYAN_PLASTIC = BLOCKS.register("cyan_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.CYAN_WOOL)));
    public static final RegistryObject<Block> DARK_OAK_BRICKS = BLOCKS.register("dark_oak_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_FENCE = BLOCKS.register("dark_oak_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_FENCE)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_FENCE_GATE = BLOCKS.register("dark_oak_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_FENCE_GATE)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_SLAB = BLOCKS.register("dark_oak_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_SLAB)));
    public static final RegistryObject<Block> DARK_OAK_BRICK_STAIRS = BLOCKS.register("dark_oak_brick_stairs", () -> new StairsBlock(Blocks.CRIMSON_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.DARK_OAK_STAIRS)));
    public static final RegistryObject<Block> DARK_STONE_BRICKS = BLOCKS.register("dark_stone_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.BRICKS)));
    public static final RegistryObject<Block> DECORATIVE_CONCRETE = BLOCKS.register("decorative_concrete", () -> new Block(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> FANCY_TABLE = BLOCKS.register("fancy_table", () -> new EndPortalFrameBlock(AbstractBlock.Properties.from(Blocks.END_PORTAL_FRAME)));
    public static final RegistryObject<Block> FLUORESCENT_PANEL = BLOCKS.register("fluorescent_panel", () -> new Block(AbstractBlock.Properties.from(Blocks.GLOWSTONE).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> GLASS = BLOCKS.register("glass", () -> new GlassBlock(AbstractGlassBlock.Properties.from(Blocks.GLASS).notSolid()));
    public static final RegistryObject<Block> GLASS_PANE = BLOCKS.register("glass_pane", () -> new PaneBlock(AbstractGlassBlock.Properties.from(Blocks.GLASS_PANE).notSolid()));
    public static final RegistryObject<Block> GOLD_FILGAREE = BLOCKS.register("gold_filgaree", () -> new Block(AbstractBlock.Properties.from(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> GREEN_PLASTIC = BLOCKS.register("green_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.GREEN_WOOL)));
    public static final RegistryObject<Block> GREEN_TILES = BLOCKS.register("green_tiles", () -> new Block(AbstractBlock.Properties.from(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<Block> HATCH = BLOCKS.register("hatch", () -> new TrapDoorBlock(AbstractBlock.Properties.from(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> IRON_DOOR = BLOCKS.register("iron_door", () -> new DoorBlock(AbstractBlock.Properties.from(Blocks.IRON_DOOR)));
    public static final RegistryObject<Block> IRON_PLATE = BLOCKS.register("iron_plate", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> IRON_STACK = BLOCKS.register("iron_stack", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> JUNGLE_BRICKS = BLOCKS.register("jungle_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_BRICK_FENCE = BLOCKS.register("jungle_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_FENCE)));
    public static final RegistryObject<Block> JUNGLE_BRICK_FENCE_GATE = BLOCKS.register("jungle_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_FENCE_GATE)));
    public static final RegistryObject<Block> JUNGLE_BRICK_SLAB = BLOCKS.register("jungle_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_SLAB)));
    public static final RegistryObject<Block> JUNGLE_BRICK_STAIRS = BLOCKS.register("jungle_brick_stairs", () -> new StairsBlock(Blocks.JUNGLE_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.JUNGLE_STAIRS)));
    public static final RegistryObject<Block> LADDER = BLOCKS.register("ladder", () -> new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static final RegistryObject<Block> LAMP = BLOCKS.register("lamp", () -> new RedstoneLampBlock(AbstractBlock.Properties.from(Blocks.REDSTONE_LAMP)));
    //public static final RegistryObject<Block> LANTERN
    //public static final RegistryObject<Block> LED
    public static final RegistryObject<Block> LEVER = BLOCKS.register("lever", () -> new LeverBlock(AbstractBlock.Properties.from(Blocks.LEVER)));
    public static final RegistryObject<Block> LIMESTONE_BRICK = BLOCKS.register("limestone_brick", () -> new Block(AbstractBlock.Properties.from(Blocks.BRICKS)));
    public static final RegistryObject<Block> LINOLEUM_TILE = BLOCKS.register("linoleum_tile", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
    public static final RegistryObject<Block> LIT_MONITOR = BLOCKS.register("lit_monitor", () -> new MonitorBlock(Block.Properties.from(Blocks.IRON_BLOCK).sound(SoundType.GLASS).setLightLevel((state) -> {

        return 15;

    })));
    public static final RegistryObject<Block> MONITOR = BLOCKS.register("monitor", () -> new MonitorBlock(Block.Properties.from(Blocks.IRON_BLOCK).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> STEP = BLOCKS.register("step", () -> new Block(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE)));
    public static final RegistryObject<Block> STEP_SLAB = BLOCKS.register("step_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE_SLAB)));

}
