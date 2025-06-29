package com.nosiphus.yogmod.converter;

import com.nosiphus.yogmod.block.ModBlock;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NCBConverter {

   private static class BlockConversionKey {
       public final String registryName;
       public final int metadata;

       public BlockConversionKey(String registryName, int metadata) {
           this.registryName = registryName;
           this.metadata = metadata;
       }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           BlockConversionKey that = (BlockConversionKey) o;
           return metadata == that.metadata && Objects.equals(registryName, that.registryName);
       }

       @Override
       public int hashCode() {
           return Objects.hash(registryName, metadata);
       }
   }

   private static class BlockConversionValue {
       public final Block newBlock;
       public final int newMetadata;

       public BlockConversionValue(Block newBlock, int newMetadata) {
           this.newBlock = newBlock;
           this.newMetadata = newMetadata;
       }
   }

   private static final Map<BlockConversionKey, BlockConversionValue> CONVERSION_MAP = new HashMap<>();

   static {
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsBeigePlastic", 0), new BlockConversionValue(ModBlock.BEIGE_PLASTIC, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:BlackMarble", 0), new BlockConversionValue(ModBlock.PLASTIC, 15));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsBluePaneling", 0), new BlockConversionValue(ModBlock.BLUE_PANELING, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsBluePlastic", 0), new BlockConversionValue(ModBlock.PLASTIC, 11));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCautionTape", 0), new BlockConversionValue(ModBlock.CAUTION_TAPE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCircuitry", 0), new BlockConversionValue(ModBlock.CIRCUITRY, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsClayTile", 0), new BlockConversionValue(ModBlock.CLAY_TILE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsConcrete", 0), new BlockConversionValue(ModBlock.CONCRETE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsConsole", 0), new BlockConversionValue(ModBlock.CONSOLE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCorrugatedCopper", 0), new BlockConversionValue(ModBlock.CORRUGATED_COPPER, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCorrugatedSteel", 0), new BlockConversionValue(ModBlock.CORRUGATED_STEEL, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCountertop", 0), new BlockConversionValue(ModBlock.COUNTERTOP, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCurtains", 2), new BlockConversionValue(ModBlock.CURTAINS, 2));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCurtains", 3), new BlockConversionValue(ModBlock.CURTAINS, 3));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCurtains", 4), new BlockConversionValue(ModBlock.CURTAINS, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsCurtains", 5), new BlockConversionValue(ModBlock.CURTAINS, 5));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsDarkStoneBricks", 0), new BlockConversionValue(ModBlock.DARK_STONE_BRICKS, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsDecorativeConcrete", 0), new BlockConversionValue(ModBlock.DECORATIVE_CONCRETE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsFluorescentPanel", 0), new BlockConversionValue(ModBlock.FLUORESCENT_PANEL, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsGoldFilgaree", 0), new BlockConversionValue(ModBlock.PLASTIC, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsGreenPlastic", 0), new BlockConversionValue(ModBlock.PLASTIC, 13));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 8), new BlockConversionValue(ModBlock.HATCH, 8));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 9), new BlockConversionValue(ModBlock.HATCH, 9));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 10), new BlockConversionValue(ModBlock.HATCH, 10));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 11), new BlockConversionValue(ModBlock.HATCH, 11));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 12), new BlockConversionValue(ModBlock.HATCH, 12));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 13), new BlockConversionValue(ModBlock.HATCH, 13));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 14), new BlockConversionValue(ModBlock.HATCH, 14));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHatch", 15), new BlockConversionValue(ModBlock.HATCH, 15));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHorizontalPipe", 0), new BlockConversionValue(ModBlock.PIPE, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsHorizontalStripe", 0), new BlockConversionValue(ModBlock.STRIPE, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsIronPlate", 0), new BlockConversionValue(ModBlock.IRON_PLATE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsIronStack", 0), new BlockConversionValue(ModBlock.IRON_STACK, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLantern", 1), new BlockConversionValue(ModBlock.LANTERN, 1));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLantern", 2), new BlockConversionValue(ModBlock.LANTERN, 2));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLantern", 3), new BlockConversionValue(ModBlock.LANTERN, 3));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLantern", 4), new BlockConversionValue(ModBlock.LANTERN, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLantern", 5), new BlockConversionValue(ModBlock.LANTERN, 5));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLED", 1), new BlockConversionValue(ModBlock.LED_ON, 1));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLED", 2), new BlockConversionValue(ModBlock.LED_ON, 2));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLED", 3), new BlockConversionValue(ModBlock.LED_ON, 3));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLED", 4), new BlockConversionValue(ModBlock.LED_ON, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLED", 5), new BlockConversionValue(ModBlock.LED_ON, 5));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLimestoneBrick", 0), new BlockConversionValue(ModBlock.LIMESTONE_BRICK, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLinoleumTile", 0), new BlockConversionValue(ModBlock.LINOLEUM_TILE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsNosLabsGlass", 0), new BlockConversionValue(ModBlock.GLASS, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsNosLabsLadder", 2), new BlockConversionValue(ModBlock.LADDER, 2));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsNosLabsLadder", 3), new BlockConversionValue(ModBlock.LADDER, 3));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsNosLabsLadder", 4), new BlockConversionValue(ModBlock.LADDER, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsNosLabsLadder", 5), new BlockConversionValue(ModBlock.LADDER, 5));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsNosLabsLamp", 0), new BlockConversionValue(ModBlock.LIT_LAMP, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 0), new BlockConversionValue(ModBlock.LEVER, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 1), new BlockConversionValue(ModBlock.LEVER, 1));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 2), new BlockConversionValue(ModBlock.LEVER, 2));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 3), new BlockConversionValue(ModBlock.LEVER, 3));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 4), new BlockConversionValue(ModBlock.LEVER, 4));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 5), new BlockConversionValue(ModBlock.LEVER, 5));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 6), new BlockConversionValue(ModBlock.LEVER, 6));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 7), new BlockConversionValue(ModBlock.LEVER, 7));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 8), new BlockConversionValue(ModBlock.LEVER, 8));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 9), new BlockConversionValue(ModBlock.LEVER, 9));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 10), new BlockConversionValue(ModBlock.LEVER, 10));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 11), new BlockConversionValue(ModBlock.LEVER, 11));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 12), new BlockConversionValue(ModBlock.LEVER, 12));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 13), new BlockConversionValue(ModBlock.LEVER, 13));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 14), new BlockConversionValue(ModBlock.LEVER, 14));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsLever", 15), new BlockConversionValue(ModBlock.LEVER, 15));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsOrangePlastic", 0), new BlockConversionValue(ModBlock.PLASTIC, 1));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsPillar", 0), new BlockConversionValue(ModBlock.PILLAR, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsPinkStucco", 0), new BlockConversionValue(ModBlock.PLASTIC, 6));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsRedPlastic", 0), new BlockConversionValue(ModBlock.PLASTIC, 14));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsReinforcedPaneling", 0), new BlockConversionValue(ModBlock.REINFORCED_PANELING, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsRivetedSteel", 0), new BlockConversionValue(ModBlock.CONCRETE_BRICKS, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsRug", 0), new BlockConversionValue(ModBlock.PLASTIC, 3));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsShale", 0), new BlockConversionValue(ModBlock.THIN_STRIPE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsSmoothMetal", 0), new BlockConversionValue(ModBlock.SMOOTH_METAL, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsStripeIntersection", 0), new BlockConversionValue(ModBlock.STRIPE_INTERSECTION, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsTechAccent", 0), new BlockConversionValue(ModBlock.TECH_ACCENT, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsTileMosaic", 0), new BlockConversionValue(ModBlock.TILE_MOSAIC, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsVerticalPipe", 0), new BlockConversionValue(ModBlock.PIPE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsVerticalStripe", 0), new BlockConversionValue(ModBlock.STRIPE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsVioletVelvet", 0), new BlockConversionValue(ModBlock.PLASTIC, 10));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsWhiteMarble", 0), new BlockConversionValue(ModBlock.WHITE_MARBLE, 0));
       CONVERSION_MAP.put(new BlockConversionKey("NosLabs:NosLabsWhitePlastic", 0), new BlockConversionValue(ModBlock.PLASTIC, 0));
   }

   @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {

       if(event.world.isRemote) {
           return;
       }

       Chunk chunk = event.getChunk();
       boolean chunkModified = false;

       for (int x = 0; x < 16; x++) {
           for (int z = 0; z < 16; z++) {
               for (int y = 0; y < 256; y++) {
                   Block block = chunk.getBlock(x, y, z);
                   int metadata = chunk.getBlockMetadata(x, y, z);
                   String registryName = GameRegistry.findUniqueIdentifierFor(block).toString();

                   BlockConversionKey key = new BlockConversionKey(registryName, metadata);

                   if (CONVERSION_MAP.containsKey(key)) {
                       BlockConversionValue conversionResult = CONVERSION_MAP.get(key);
                       Block newBlock = conversionResult.newBlock;
                       int newMetadata = conversionResult.newMetadata;

                       chunk.func_150807_a(x, y, z, newBlock, newMetadata);
                       chunkModified = true;

                        System.out.println("Converted block at " + (chunk.xPosition * 16 + x) + "," + y + "," + (chunk.zPosition * 16 + z) +
                                           " from " + registryName + ":" + metadata +
                                           " to " + newBlock.getUnlocalizedName() + ":" + newMetadata);
                   }
               }
           }
       }

       if (chunkModified) {
           chunk.setChunkModified();
       }

   }

}
