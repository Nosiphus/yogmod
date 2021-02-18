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

    public static final RegistryObject<Block> ACACIA_BRICK = BLOCKS.register("acacia_brick", () -> new Block(AbstractBlock.Properties.from(Blocks.ACACIA_PLANKS)));
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
    public static final RegistryObject<Block> BIRCH_BRICK = BLOCKS.register("birch_brick", () -> new Block(AbstractBlock.Properties.from(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> BIRCH_BRICK_FENCE = BLOCKS.register("birch_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.BIRCH_FENCE)));
    public static final RegistryObject<Block> BIRCH_BRICK_FENCE_GATE = BLOCKS.register("birch_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.BIRCH_FENCE_GATE)));
    public static final RegistryObject<Block> BIRCH_BRICK_SLAB = BLOCKS.register("birch_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.BIRCH_SLAB)));
    public static final RegistryObject<Block> BIRCH_BRICK_STAIRS = BLOCKS.register("birch_brick_stairs", () -> new StairsBlock(Blocks.BIRCH_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.BIRCH_STAIRS)));
    public static final RegistryObject<Block> BLACK_MARBLE = BLOCKS.register("black_marble", () -> new Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> BLUE_PANELING = BLOCKS.register("blue_paneling", () -> new Block(AbstractBlock.Properties.from(Blocks.DIAMOND_BLOCK)));
}
