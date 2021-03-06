package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.block.PoweredMetroVoxRailBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "yogmod");

	//Building Blocks
	public static final RegistryObject<Block> ASPHALT = BLOCKS.register("asphalt", () -> new Block(AbstractBlock.Properties.from(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> OAK_BRICKS = BLOCKS.register("oak_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> SPRUCE_BRICKS = BLOCKS.register("spruce_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.SPRUCE_PLANKS)));
	public static final RegistryObject<Block> BIRCH_BRICKS = BLOCKS.register("birch_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.BIRCH_PLANKS)));
	public static final RegistryObject<Block> JUNGLE_BRICKS = BLOCKS.register("jungle_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.JUNGLE_PLANKS)));
	public static final RegistryObject<Block> ACACIA_BRICKS = BLOCKS.register("acacia_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.ACACIA_PLANKS)));
	public static final RegistryObject<Block> DARK_OAK_BRICKS = BLOCKS.register("dark_oak_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.DARK_OAK_PLANKS)));
	public static final RegistryObject<Block> CRIMSON_BRICKS = BLOCKS.register("crimson_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
	public static final RegistryObject<Block> WARPED_BRICKS = BLOCKS.register("warped_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.WARPED_PLANKS)));
	public static final RegistryObject<Block> WHITE_MARBLE = BLOCKS.register("white_marble", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Block> PILLAR = BLOCKS.register("pillar", () -> new Block(AbstractBlock.Properties.from(Blocks.QUARTZ_PILLAR)));
	public static final RegistryObject<Block> DECORATIVE_CONCRETE = BLOCKS.register("decorative_concrete", () -> new Block(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_CONCRETE)));
	public static final RegistryObject<Block> DARK_STONE_BRICKS = BLOCKS.register("dark_stone_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.BRICKS)));
	public static final RegistryObject<Block> GLASS = BLOCKS.register("glass", () -> new GlassBlock(AbstractGlassBlock.Properties.from(Blocks.GLASS).notSolid()));
	public static final RegistryObject<Block> LIMESTONE_BRICK = BLOCKS.register("limestone_brick", () -> new Block(AbstractBlock.Properties.from(Blocks.BRICKS)));
	public static final RegistryObject<Block> TILE_MOSAIC = BLOCKS.register("tile_mosaic", () -> new Block(AbstractBlock.Properties.from(Blocks.LAPIS_BLOCK)));
	public static final RegistryObject<Block> BEIGE_PLASTIC = BLOCKS.register("beige_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
	public static final RegistryObject<Block> WHITE_PLASTIC = BLOCKS.register("white_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
	public static final RegistryObject<Block> ORANGE_PLASTIC = BLOCKS.register("orange_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.ORANGE_WOOL)));
	public static final RegistryObject<Block> MAGENTA_PLASTIC = BLOCKS.register("magenta_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.MAGENTA_WOOL)));
	public static final RegistryObject<Block> LIGHT_BLUE_PLASTIC = BLOCKS.register("light_blue_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.LIGHT_BLUE_WOOL)));
	public static final RegistryObject<Block> GOLD_FILGAREE = BLOCKS.register("gold_filgaree", () -> new Block(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
	public static final RegistryObject<Block> LIME_PLASTIC = BLOCKS.register("lime_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.LIME_WOOL)));
	public static final RegistryObject<Block> PINK_STUCCO = BLOCKS.register("pink_stucco", () -> new Block(AbstractBlock.Properties.from(Blocks.PINK_WOOL)));
	public static final RegistryObject<Block> GRAY_STUCCO = BLOCKS.register("gray_stucco", () -> new Block(AbstractBlock.Properties.from(Blocks.GRAY_WOOL)));
	public static final RegistryObject<Block> LIGHT_GRAY_STUCCO = BLOCKS.register("light_gray_stucco", () -> new Block(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_WOOL)));
	public static final RegistryObject<Block> CYAN_PLASTIC = BLOCKS.register("cyan_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.CYAN_WOOL)));
	public static final RegistryObject<Block> VIOLET_VELVET = BLOCKS.register("violet_velvet", () -> new Block(AbstractBlock.Properties.from(Blocks.PURPLE_WOOL)));
	public static final RegistryObject<Block> BLUE_PLASTIC = BLOCKS.register("blue_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.BLUE_WOOL)));
	public static final RegistryObject<Block> BROWN_STUCCO = BLOCKS.register("brown_stucco", () -> new Block(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
	public static final RegistryObject<Block> GREEN_PLASTIC = BLOCKS.register("green_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.GREEN_WOOL)));
	public static final RegistryObject<Block> RED_PLASTIC = BLOCKS.register("red_plastic", () -> new Block(AbstractBlock.Properties.from(Blocks.RED_WOOL)));
	public static final RegistryObject<Block> BLACK_MARBLE = BLOCKS.register("black_marble", () -> new Block(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
	public static final RegistryObject<Block> REINFORCED_PANELING = BLOCKS.register("reinforced_paneling", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> STRIPE = BLOCKS.register("stripe", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.QUARTZ_PILLAR)));
	public static final RegistryObject<Block> LINOLEUM_TILE = BLOCKS.register("linoleum_tile", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Block> CORRUGATED_STEEL = BLOCKS.register("corrugated_steel", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> CLAY_TILE = BLOCKS.register("clay_tile", () -> new Block(AbstractBlock.Properties.from(Blocks.MAGENTA_WOOL)));
	public static final RegistryObject<Block> STORAGE_CRATE = BLOCKS.register("storage_crate", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> PIPE = BLOCKS.register("pipe", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.QUARTZ_PILLAR)));
	public static final RegistryObject<Block> RIVETED_STEEL = BLOCKS.register("riveted_steel", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> IRON_STACK = BLOCKS.register("iron_stack", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> RUSTY_IRON = BLOCKS.register("rusty_iron", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> OAK_BRICK_SLAB = BLOCKS.register("oak_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> SPRUCE_BRICK_SLAB = BLOCKS.register("spruce_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.SPRUCE_SLAB)));
	public static final RegistryObject<Block> BIRCH_BRICK_SLAB = BLOCKS.register("birch_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.BIRCH_SLAB)));
	public static final RegistryObject<Block> JUNGLE_BRICK_SLAB = BLOCKS.register("jungle_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_SLAB)));
	public static final RegistryObject<Block> ACACIA_BRICK_SLAB = BLOCKS.register("acacia_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.ACACIA_SLAB)));
	public static final RegistryObject<Block> DARK_OAK_BRICK_SLAB = BLOCKS.register("dark_oak_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_SLAB)));
	public static final RegistryObject<Block> CRIMSON_BRICK_SLAB = BLOCKS.register("crimson_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_SLAB)));
	public static final RegistryObject<Block> WARPED_BRICK_SLAB = BLOCKS.register("warped_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.WARPED_SLAB)));
	public static final RegistryObject<Block> STEP_SLAB = BLOCKS.register("step_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE_SLAB)));
	public static final RegistryObject<Block> ASPHALT_SLAB = BLOCKS.register("asphalt_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_SLAB)));
	public static final RegistryObject<Block> IRON_PLATE_SLAB = BLOCKS.register("iron_plate_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.MOSSY_COBBLESTONE_SLAB)));
	public static final RegistryObject<Block> BRICK_SLAB = BLOCKS.register("brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.BRICK_SLAB)));
	public static final RegistryObject<Block> RAW_WOOD_SLAB = BLOCKS.register("raw_wood_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE_BRICK_SLAB)));
	public static final RegistryObject<Block> SMOOTH_METAL_SLAB = BLOCKS.register("smooth_metal_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.NETHER_BRICK_SLAB)));
	public static final RegistryObject<Block> STEP = BLOCKS.register("step", () -> new Block(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> BRICKS = BLOCKS.register("bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.BRICKS)));
	public static final RegistryObject<Block> IRON_PLATE = BLOCKS.register("iron_plate", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> CORRUGATED_COPPER = BLOCKS.register("corrugated_copper", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> BLUE_PANELING = BLOCKS.register("blue_paneling", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> CIRCUITRY = BLOCKS.register("circuitry", () -> new Block(AbstractBlock.Properties.from(Blocks.REDSTONE_BLOCK)));
	public static final RegistryObject<Block> CONCRETE = BLOCKS.register("concrete", () -> new Block(AbstractBlock.Properties.from(Blocks.WHITE_CONCRETE)));
	public static final RegistryObject<Block> SHALE = BLOCKS.register("shale", () -> new Block(AbstractBlock.Properties.from(Blocks.SNOW_BLOCK)));
	public static final RegistryObject<Block> WHITE_LIGHTING = BLOCKS.register("white_lighting", () -> new Block(AbstractBlock.Properties.from(Blocks.GLOWSTONE)));
	public static final RegistryObject<Block> MONITOR = BLOCKS.register("monitor", () -> new CarvedPumpkinBlock(AbstractBlock.Properties.from(Blocks.PUMPKIN).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> CAUTION_TAPE = BLOCKS.register("caution_tape", () -> new SoulSandBlock(AbstractBlock.Properties.from(Blocks.SOUL_SAND).sound(SoundType.SAND)));
	public static final RegistryObject<Block> FLUORESCENT_PANEL = BLOCKS.register("fluorescent_panel", () -> new Block(AbstractBlock.Properties.from(Blocks.GLOWSTONE).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> LIT_MONITOR = BLOCKS.register("lit_monitor", () -> new CarvedPumpkinBlock(AbstractBlock.Properties.from(Blocks.JACK_O_LANTERN).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> RAW_WOOD = BLOCKS.register("raw_wood", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> COUNTERTOP = BLOCKS.register("countertop", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Block> SMOOTH_METAL = BLOCKS.register("smooth_metal", () -> new Block(AbstractBlock.Properties.from(Blocks.NETHER_BRICKS)));
	public static final RegistryObject<Block> GREEN_TILES = BLOCKS.register("green_tiles", () -> new Block(AbstractBlock.Properties.from(Blocks.EMERALD_BLOCK)));
	public static final RegistryObject<Block> OAK_BRICK_STAIRS = BLOCKS.register("oak_brick_stairs", () -> new StairsBlock(Blocks.OAK_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.OAK_STAIRS)));
	public static final RegistryObject<Block> SPRUCE_BRICK_STAIRS = BLOCKS.register("spruce_brick_stairs", () -> new StairsBlock(Blocks.SPRUCE_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.SPRUCE_STAIRS)));
	public static final RegistryObject<Block> BIRCH_BRICK_STAIRS = BLOCKS.register("birch_brick_stairs", () -> new StairsBlock(Blocks.BIRCH_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.BIRCH_STAIRS)));
	public static final RegistryObject<Block> JUNGLE_BRICK_STAIRS = BLOCKS.register("jungle_brick_stairs", () -> new StairsBlock(Blocks.JUNGLE_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.JUNGLE_STAIRS)));
	public static final RegistryObject<Block> ACACIA_BRICK_STAIRS = BLOCKS.register("acacia_brick_stairs", () -> new StairsBlock(Blocks.ACACIA_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.ACACIA_STAIRS)));
	public static final RegistryObject<Block> DARK_OAK_BRICK_STAIRS = BLOCKS.register("dark_oak_brick_stairs", () -> new StairsBlock(Blocks.CRIMSON_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.DARK_OAK_STAIRS)));
	public static final RegistryObject<Block> CRIMSON_BRICK_STAIRS = BLOCKS.register("crimson_brick_stairs", () -> new StairsBlock(Blocks.CRIMSON_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.CRIMSON_STAIRS)));
	public static final RegistryObject<Block> WARPED_BRICK_STAIRS = BLOCKS.register("warped_brick_stairs", () -> new StairsBlock(Blocks.WARPED_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.WARPED_STAIRS)));
	public static final RegistryObject<Block> ASPHALT_STAIRS = BLOCKS.register("asphalt_stairs", () -> new StairsBlock(Blocks.COBBLESTONE_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.COBBLESTONE_STAIRS)));
	public static final RegistryObject<Block> IRON_PLATE_STAIRS = BLOCKS.register("iron_plate_stairs", () -> new StairsBlock(Blocks.MOSSY_COBBLESTONE_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.MOSSY_COBBLESTONE_STAIRS)));
	public static final RegistryObject<Block> BRICK_STAIRS = BLOCKS.register("brick_stairs", () -> new StairsBlock(Blocks.BRICK_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.BRICK_STAIRS)));
	public static final RegistryObject<Block> RAW_WOOD_STAIRS = BLOCKS.register("raw_wood_stairs", () -> new StairsBlock(Blocks.STONE_BRICK_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE_BRICK_STAIRS)));
	public static final RegistryObject<Block> SMOOTH_METAL_STAIRS = BLOCKS.register("smooth_metal_stairs", () -> new StairsBlock(Blocks.NETHER_BRICK_STAIRS.getDefaultState(), AbstractBlock.Properties.from(Blocks.NETHER_BRICK_STAIRS)));
	
	//Decoration Blocks
	public static final RegistryObject<Block> LANTERN = BLOCKS.register("lantern", () -> new TorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) -> {
        return 14;
    }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> WALL_LANTERN = BLOCKS.register("wall_lantern", () -> new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) -> {
        return 14;
    }).sound(SoundType.WOOD).lootFrom(LANTERN), ParticleTypes.FLAME));
	//Crate will go here when implemented
	public static final RegistryObject<Block> TECH_ACCENT = BLOCKS.register("tech_accent", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> LADDER = BLOCKS.register("ladder", () -> new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
	public static final RegistryObject<Block> OAK_BRICK_FENCE = BLOCKS.register("oak_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.OAK_FENCE)));
	public static final RegistryObject<Block> SPRUCE_BRICK_FENCE = BLOCKS.register("spruce_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.SPRUCE_FENCE)));
	public static final RegistryObject<Block> BIRCH_BRICK_FENCE = BLOCKS.register("birch_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.BIRCH_FENCE)));
	public static final RegistryObject<Block> JUNGLE_BRICK_FENCE = BLOCKS.register("jungle_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_FENCE)));
	public static final RegistryObject<Block> ACACIA_BRICK_FENCE = BLOCKS.register("acacia_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.ACACIA_FENCE)));
	public static final RegistryObject<Block> DARK_OAK_BRICK_FENCE = BLOCKS.register("dark_oak_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_FENCE)));
	public static final RegistryObject<Block> CRIMSON_BRICK_FENCE = BLOCKS.register("crimson_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FENCE)));
	public static final RegistryObject<Block> WARPED_BRICK_FENCE = BLOCKS.register("warped_brick_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.WARPED_FENCE)));
	public static final RegistryObject<Block> SMOOTH_METAL_FENCE = BLOCKS.register("smooth_metal_fence", () -> new FenceBlock(AbstractBlock.Properties.from(Blocks.NETHER_BRICK_FENCE)));
	public static final RegistryObject<Block> AIR_VENT = BLOCKS.register("air_vent", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> GLASS_PANE = BLOCKS.register("glass_pane", () -> new PaneBlock(AbstractGlassBlock.Properties.from(Blocks.GLASS_PANE).notSolid()));
	public static final RegistryObject<Block> CURTAINS = BLOCKS.register("curtains", () -> new VineBlock(AbstractBlock.Properties.from(Blocks.VINE)));
	public static final RegistryObject<Block> TABLE = BLOCKS.register("table", () -> new EnchantingTableBlock(AbstractBlock.Properties.from(Blocks.ENCHANTING_TABLE)) { @Override public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) { return ActionResultType.FAIL; }});
	public static final RegistryObject<Block> FANCY_TABLE = BLOCKS.register("fancy_table", () -> new EndPortalFrameBlock(AbstractBlock.Properties.from(Blocks.END_PORTAL_FRAME)));
	//Fridge will go here when implemented
	public static final RegistryObject<Block> ASPHALT_WALL = BLOCKS.register("asphalt_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_WALL)));
	public static final RegistryObject<Block> IRON_PLATE_WALL = BLOCKS.register("iron_plate_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.MOSSY_COBBLESTONE_WALL)));
	public static final RegistryObject<Block> BRICK_WALL = BLOCKS.register("brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.BRICK_WALL)));
	public static final RegistryObject<Block> RAW_WOOD_WALL = BLOCKS.register("raw_wood_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE_BRICK_WALL)));
	public static final RegistryObject<Block> SMOOTH_METAL_WALL = BLOCKS.register("smooth_metal_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.NETHER_BRICK_WALL)));
	public static final RegistryObject<Block> BEIGE_PLASTIC_CARPET = BLOCKS.register("beige_plastic_carpet", () -> new CarpetBlock(DyeColor.YELLOW, AbstractBlock.Properties.from(Blocks.YELLOW_CARPET)));
	public static final RegistryObject<Block> WHITE_PLASTIC_CARPET = BLOCKS.register("white_plastic_carpet", () -> new CarpetBlock(DyeColor.WHITE, AbstractBlock.Properties.from(Blocks.WHITE_CARPET)));
	public static final RegistryObject<Block> ORANGE_PLASTIC_CARPET = BLOCKS.register("orange_plastic_carpet", () -> new CarpetBlock(DyeColor.ORANGE, AbstractBlock.Properties.from(Blocks.ORANGE_CARPET)));
	public static final RegistryObject<Block> MAGENTA_PLASTIC_CARPET = BLOCKS.register("magenta_plastic_carpet", () -> new CarpetBlock(DyeColor.MAGENTA, AbstractBlock.Properties.from(Blocks.MAGENTA_CARPET)));
	public static final RegistryObject<Block> LIGHT_BLUE_PLASTIC_CARPET = BLOCKS.register("light_blue_plastic_carpet", () -> new CarpetBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.from(Blocks.LIGHT_BLUE_CARPET)));
	public static final RegistryObject<Block> GOLD_FILGAREE_CARPET = BLOCKS.register("gold_filgaree_carpet", () -> new CarpetBlock(DyeColor.YELLOW, AbstractBlock.Properties.from(Blocks.YELLOW_CARPET)));
	public static final RegistryObject<Block> LIME_PLASTIC_CARPET = BLOCKS.register("lime_plastic_carpet", () -> new CarpetBlock(DyeColor.LIME, AbstractBlock.Properties.from(Blocks.LIME_CARPET)));
	public static final RegistryObject<Block> PINK_STUCCO_CARPET = BLOCKS.register("pink_stucco_carpet", () -> new CarpetBlock(DyeColor.PINK, AbstractBlock.Properties.from(Blocks.PINK_CARPET)));
	public static final RegistryObject<Block> GRAY_STUCCO_CARPET = BLOCKS.register("gray_stucco_carpet", () -> new CarpetBlock(DyeColor.GRAY, AbstractBlock.Properties.from(Blocks.GRAY_CARPET)));
	public static final RegistryObject<Block> LIGHT_GRAY_STUCCO_CARPET = BLOCKS.register("light_gray_stucco_carpet", () -> new CarpetBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_CARPET)));
	public static final RegistryObject<Block> CYAN_PLASTIC_CARPET = BLOCKS.register("cyan_plastic_carpet", () -> new CarpetBlock(DyeColor.CYAN, AbstractBlock.Properties.from(Blocks.CYAN_CARPET)));
	public static final RegistryObject<Block> VIOLET_VELVET_CARPET = BLOCKS.register("violet_velvet_carpet", () -> new CarpetBlock(DyeColor.PURPLE, AbstractBlock.Properties.from(Blocks.PURPLE_CARPET)));
	public static final RegistryObject<Block> BLUE_PLASTIC_CARPET = BLOCKS.register("blue_plastic_carpet", () -> new CarpetBlock(DyeColor.BLUE, AbstractBlock.Properties.from(Blocks.BLUE_CARPET)));
	public static final RegistryObject<Block> BROWN_STUCCO_CARPET = BLOCKS.register("brown_stucco_carpet", () -> new CarpetBlock(DyeColor.BROWN, AbstractBlock.Properties.from(Blocks.BROWN_CARPET)));
	public static final RegistryObject<Block> GREEN_PLASTIC_CARPET = BLOCKS.register("green_plastic_carpet", () -> new CarpetBlock(DyeColor.GREEN, AbstractBlock.Properties.from(Blocks.GREEN_CARPET)));
	public static final RegistryObject<Block> RED_PLASTIC_CARPET = BLOCKS.register("red_plastic_carpet", () -> new CarpetBlock(DyeColor.RED, AbstractBlock.Properties.from(Blocks.RED_CARPET)));
	public static final RegistryObject<Block> BLACK_MARBLE_CARPET = BLOCKS.register("black_marble_carpet", () -> new CarpetBlock(DyeColor.BLACK, AbstractBlock.Properties.from(Blocks.BLACK_CARPET)));
	//Sign when implemented
	
	//Redstone
	public static final RegistryObject<Block> CONSOLE = BLOCKS.register("console", () -> new NoteBlock(AbstractBlock.Properties.from(Blocks.NOTE_BLOCK)));
	//Piston when implemented
	//Sticky Piston when implemented
	public static final RegistryObject<Block> CLASSIC_LEVER = BLOCKS.register("classic_lever", () -> new LeverBlock(AbstractBlock.Properties.from(Blocks.LEVER)));
	public static final RegistryObject<Block> LEVER = BLOCKS.register("lever", () -> new LeverBlock(AbstractBlock.Properties.from(Blocks.LEVER)));
	//Pressure Plates when implemented
	public static final RegistryObject<Block> LED = BLOCKS.register("led", () -> new RedstoneTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(getLightValueLit(7)).sound(SoundType.WOOD)));
    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> {
            return state.get(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
    public static final RegistryObject<Block> WALL_LED = BLOCKS.register("wall_led", () -> new RedstoneWallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(getLightValueLit(7)).sound(SoundType.WOOD).lootFrom(LED)));
	public static final RegistryObject<Block> HATCH = BLOCKS.register("hatch", () -> new TrapDoorBlock(AbstractBlock.Properties.from(Blocks.OAK_TRAPDOOR)));
	public static final RegistryObject<Block> OAK_BRICK_FENCE_GATE = BLOCKS.register("oak_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.OAK_FENCE_GATE)));
	public static final RegistryObject<Block> SPRUCE_BRICK_FENCE_GATE = BLOCKS.register("spruce_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.SPRUCE_FENCE_GATE)));
	public static final RegistryObject<Block> BIRCH_BRICK_FENCE_GATE = BLOCKS.register("birch_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.BIRCH_FENCE_GATE)));
	public static final RegistryObject<Block> JUNGLE_BRICK_FENCE_GATE = BLOCKS.register("jungle_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_FENCE_GATE)));
	public static final RegistryObject<Block> ACACIA_BRICK_FENCE_GATE = BLOCKS.register("acacia_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.ACACIA_FENCE_GATE)));
	public static final RegistryObject<Block> DARK_OAK_BRICK_FENCE_GATE = BLOCKS.register("dark_oak_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_FENCE_GATE)));
	public static final RegistryObject<Block> CRIMSON_BRICK_FENCE_GATE = BLOCKS.register("crimson_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FENCE_GATE)));
	public static final RegistryObject<Block> WARPED_BRICK_FENCE_GATE = BLOCKS.register("warped_brick_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(Blocks.WARPED_FENCE_GATE)));
	public static final RegistryObject<Block> LAMP = BLOCKS.register("lamp", () -> new RedstoneLampBlock(AbstractBlock.Properties.from(Blocks.REDSTONE_LAMP)));
	public static final RegistryObject<Block> IRON_DOOR = BLOCKS.register("iron_door", () -> new DoorBlock(AbstractBlock.Properties.from(Blocks.IRON_DOOR)));
	public static final RegistryObject<Block> WOODEN_DOOR = BLOCKS.register("wooden_door", () -> new DoorBlock(AbstractBlock.Properties.from(Blocks.OAK_DOOR)));
	//Repeater when implemented
	//Wire when implemented

    //Transportation
	public static final RegistryObject<Block> POWERED_METROVOX_RAIL = BLOCKS.register("powered_metrovox_rail", () -> new PoweredMetroVoxRailBlock(AbstractRailBlock.Properties.from(Blocks.POWERED_RAIL), true));
    public static final RegistryObject<Block> METROVOX_DETECTOR_RAIL = BLOCKS.register("metrovox_detector_rail", () -> new DetectorRailBlock(AbstractBlock.Properties.from(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> METROVOX_RAIL = BLOCKS.register("metrovox_rail", () -> new RailBlock(AbstractRailBlock.Properties.from(Blocks.RAIL)));
	public static final RegistryObject<Block> METROVOX_ACTIVATOR_RAIL = BLOCKS.register("metrovox_activator_rail", () -> new PoweredMetroVoxRailBlock(AbstractRailBlock.Properties.from(Blocks.ACTIVATOR_RAIL), false));
    
    //Brewing
    public static final RegistryObject<Block> SINK = BLOCKS.register("sink", () -> new CauldronBlock(AbstractBlock.Properties.from(Blocks.CAULDRON)));
    
    
    

}
