package com.nosiphus.yogmod;

import com.nosiphus.yogmod.client.gui.screens.inventory.*;
import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.client.model.inventory.ModItemStackRenderer;
import com.nosiphus.yogmod.client.renderer.blockentity.CrateRenderer;
import com.nosiphus.yogmod.platform.services.NeoForgeRegistryHelper;
import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModCreativeModeTabsNeoForge;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.PoweredMetroVoxRailBlock;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@Mod(YogMod.MOD_ID)
public class YogModNeoForge {

    public YogModNeoForge(IEventBus eventBus) {
        YogMod.init();

        NeoForgeRegistryHelper.BLOCKS.register(eventBus);
        NeoForgeRegistryHelper.BLOCK_ENTITY_TYPES.register(eventBus);
        ModCreativeModeTabsNeoForge.CREATIVE_TABS.register(eventBus);
        NeoForgeRegistryHelper.ENTITY_TYPES.register(eventBus);
        NeoForgeRegistryHelper.ITEMS.register(eventBus);
        NeoForgeRegistryHelper.MENU_TYPES.register(eventBus);
        NeoForgeRegistryHelper.RECIPE_SERIALIZERS.register(eventBus);
        NeoForgeRegistryHelper.RECIPE_TYPES.register(eventBus);
    }

    @EventBusSubscriber(modid = YogMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(YogModClient::initClient);
        }

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register((blockState, blockAndTintGetter, blockPos, index) ->
                    WireBlock.colorMultiplier(blockState.getValue(WireBlock.POWER)), ModBlocks.WIRE.get());

            event.register((blockState, blockAndTintGetter, blockPos, index) ->
                            blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(blockAndTintGetter, blockPos) : -1,
                    ModBlocks.WATER_SINK.get());
        }

        @SubscribeEvent
        public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
            event.registerItem(new IClientItemExtensions() {
                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    return ModItemStackRenderer.INSTANCE;
                }
            }, ModBlocks.CRATE.get().asItem());
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(CrateRenderer.CRATE, CrateRenderer::createSingleBodyLayer);
            event.registerLayerDefinition(CrateRenderer.DOUBLE_CRATE_LEFT, CrateRenderer::createDoubleBodyLeftLayer);
            event.registerLayerDefinition(CrateRenderer.DOUBLE_CRATE_RIGHT, CrateRenderer::createDoubleBodyRightLayer);
            event.registerLayerDefinition(ScrubberBotModel.SCRUBBER_BOT, ScrubberBotModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuType.CRATE_9x1.get(), CrateScreen::new);
            event.register(ModMenuType.CRATE_9x2.get(), CrateScreen::new);
            event.register(ModMenuType.CRATE_9x3.get(), CrateScreen::new);
            event.register(ModMenuType.CRATE_9x4.get(), CrateScreen::new);
            event.register(ModMenuType.CRATE_9x5.get(), CrateScreen::new);
            event.register(ModMenuType.CRATE_9x6.get(), CrateScreen::new);
            event.register(ModMenuType.DISPENSER.get(), DispenserScreen::new);
            event.register(ModMenuType.OVEN.get(), OvenScreen::new);
            event.register(ModMenuType.STORAGE_CRATE.get(), StorageCrateScreen::new);
            event.register(ModMenuType.YOGIFIER.get(), YogifierScreen::new);
        }
    }

    @EventBusSubscriber(modid = YogMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        @SubscribeEvent
        public static void registerCapabilities(RegisterCapabilitiesEvent event) {
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityType.CRATE.get(), (blockEntity, side) -> new InvWrapper(blockEntity));
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityType.DISPENSER.get(), (blockEntity, side) -> new InvWrapper(blockEntity));
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityType.RECORD_PLAYER.get(), (blockEntity, side) -> new InvWrapper(blockEntity));
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityType.OVEN.get(), SidedInvWrapper::new);
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityType.STORAGE_CRATE.get(), SidedInvWrapper::new);
        }

        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntityType.SCRUBBER_BOT.get(), ScrubberBot.createAttributes().build());
        }
    }
}