package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.util.Reference;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.ToolType;
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
    public static final RegistryObject<Block> CLAY_TILE = BLOCKS.register("clay_tile", () -> new Block(AbstractBlock.Properties.from(Blocks.MAGENTA_WOOL)));
    public static final RegistryObject<Block> CONCRETE = BLOCKS.register("concrete", () -> new Block(AbstractBlock.Properties.from(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> CONSOLE = BLOCKS.register("console", () -> new NoteBlock(AbstractBlock.Properties.from(Blocks.NOTE_BLOCK)));
    public static final RegistryObject<Block> CORRUGATED_COPPER = BLOCKS.register("corrugated_copper", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CORRUGATED_STEEL = BLOCKS.register("corrugated_steel", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> COUNTERTOP = BLOCKS.register("countertop", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));

}
