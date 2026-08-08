package com.nosiphus.yogmod;

import com.nosiphus.yogmod.client.gui.screens.inventory.*;
import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.client.model.inventory.ModItemStackRenderer;
import com.nosiphus.yogmod.client.renderer.blockentity.CrateRenderer;
import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModCreativeModeTabsFabric;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;

public class YogModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        YogModClient.initClient();
        ModCreativeModeTabsFabric.register();

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderType.cutout(),
                ModBlocks.CAGE.get(),
                ModBlocks.DIODE.get(),
                ModBlocks.GLASS.get(),
                ModBlocks.GLASS_PANE.get(),
                ModBlocks.HATCH.get(),
                ModBlocks.IRON_DOOR.get(),
                ModBlocks.LADDER.get(),
                ModBlocks.LANTERN.get(),
                ModBlocks.WALL_LANTERN.get(),
                ModBlocks.LED.get(),
                ModBlocks.WALL_LED.get(),
                ModBlocks.MECHANICAL.get(),
                ModBlocks.METROVOX_ACTIVATOR_RAIL.get(),
                ModBlocks.METROVOX_DETECTOR_RAIL.get(),
                ModBlocks.METROVOX_RAIL.get(),
                ModBlocks.POTTED_MECHANICAL.get(),
                ModBlocks.POWERED_METROVOX_RAIL.get(),
                ModBlocks.WIRE.get(),
                ModBlocks.WOODEN_DOOR.get()
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderType.translucent(),
                ModBlocks.BEIGE_STAINED_GLASS.get(),
                ModBlocks.BEIGE_STAINED_GLASS_PANE.get(),
                ModBlocks.SINK.get(),
                ModBlocks.WATER_SINK.get(),
                ModBlocks.LAVA_SINK.get(),
                ModBlocks.POWDER_SNOW_SINK.get()
        );

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, index) ->
                WireBlock.colorMultiplier(blockState.getValue(WireBlock.POWER)), ModBlocks.WIRE.get());

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, index) ->
                        blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(blockAndTintGetter, blockPos) : -1,
                ModBlocks.WATER_SINK.get());

        BuiltinItemRendererRegistry.INSTANCE.register(
                ModBlocks.CRATE.get().asItem(),
                (stack, mode, matrices, vertexConsumers, light, overlay) ->
                        ModItemStackRenderer.INSTANCE.renderByItem(stack, mode, matrices, vertexConsumers, light, overlay)
        );

        EntityModelLayerRegistry.registerModelLayer(CrateRenderer.CRATE, CrateRenderer::createSingleBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CrateRenderer.DOUBLE_CRATE_LEFT, CrateRenderer::createDoubleBodyLeftLayer);
        EntityModelLayerRegistry.registerModelLayer(CrateRenderer.DOUBLE_CRATE_RIGHT, CrateRenderer::createDoubleBodyRightLayer);
        EntityModelLayerRegistry.registerModelLayer(ScrubberBotModel.SCRUBBER_BOT, ScrubberBotModel::createBodyLayer);

        MenuScreens.register(ModMenuType.CRATE_9x1.get(), CrateScreen::new);
        MenuScreens.register(ModMenuType.CRATE_9x2.get(), CrateScreen::new);
        MenuScreens.register(ModMenuType.CRATE_9x3.get(), CrateScreen::new);
        MenuScreens.register(ModMenuType.CRATE_9x4.get(), CrateScreen::new);
        MenuScreens.register(ModMenuType.CRATE_9x5.get(), CrateScreen::new);
        MenuScreens.register(ModMenuType.CRATE_9x6.get(), CrateScreen::new);
        MenuScreens.register(ModMenuType.DISPENSER.get(), DispenserScreen::new);
        MenuScreens.register(ModMenuType.OVEN.get(), OvenScreen::new);
        MenuScreens.register(ModMenuType.STORAGE_CRATE.get(), StorageCrateScreen::new);
        MenuScreens.register(ModMenuType.YOGIFIER.get(), YogifierScreen::new);
    }

}
