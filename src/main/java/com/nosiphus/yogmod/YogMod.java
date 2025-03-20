package com.nosiphus.yogmod;

import com.mojang.logging.LogUtils;
import com.nosiphus.yogmod.client.gui.screens.inventory.OvenScreen;
import com.nosiphus.yogmod.client.gui.screens.inventory.StorageCrateScreen;
import com.nosiphus.yogmod.client.gui.screens.inventory.YogifierScreen;
import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.client.renderer.blockentity.PistonHeadRenderer;
import com.nosiphus.yogmod.client.renderer.entity.DynamiteRenderer;
import com.nosiphus.yogmod.client.renderer.entity.ScrubberBotRenderer;
import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.entity.item.PrimedDynamite;
import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModCreativeModeTabs;
import com.nosiphus.yogmod.world.item.ModItems;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeSerializer;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.state.properties.ModWoodType;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.slf4j.Logger;

@Mod("yogmod")
public class YogMod {

    private static final Logger LOGGER = LogUtils.getLogger();
    public YogMod(IEventBus eventBus, ModContainer container) {

        ModBlockEntityType.BLOCK_ENTITIES.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModCreativeModeTabs.CREATIVE_TABS.register(eventBus);
        ModEntityType.ENTITY_TYPES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModMenuType.MENU_TYPES.register(eventBus);
        ModRecipeSerializer.RECIPE_SERIALIZER.register(eventBus);
        ModRecipeType.RECIPE_TYPES.register(eventBus);

        //NeoForge.EVENT_BUS.register(this);

    }

    @EventBusSubscriber(modid = "yogmod", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            WoodType.register(ModWoodType.YOG);
            Sheets.addWoodType(ModWoodType.YOG);
            registerBlockEntityRenderers();
            registerEntityRenderers();

        }

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register((blockState, blockAndTintGetter, blockPos, index) -> {
                return WireBlock.colorMultiplier(blockState.getValue(WireBlock.POWER));
            }, ModBlocks.WIRE.get());
            event.register((blockState, blockAndTintGetter, blockPos, index) -> {
                return blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(blockAndTintGetter, blockPos) : -1;
            }, ModBlocks.WATER_SINK.get());
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ScrubberBotModel.SCRUBBER_BOT, ScrubberBotModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuType.OVEN.get(), OvenScreen::new);
            event.register(ModMenuType.STORAGE_CRATE.get(), StorageCrateScreen::new);
            event.register(ModMenuType.YOGIFIER.get(), YogifierScreen::new);
        }

    }

    @EventBusSubscriber(modid = "yogmod", bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {

        }

        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntityType.SCRUBBER_BOT.get(), ScrubberBot.createAttributes().build());
        }

    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(ModBlockEntityType.PISTON.get(), PistonHeadRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.YOG_SIGN.get(), SignRenderer::new);
    }

    private static void registerEntityRenderers() {
        EntityRenderers.register(ModEntityType.DYNAMITE.get(), DynamiteRenderer::new);
        EntityRenderers.register(ModEntityType.SCRUBBER_BOT.get(), ScrubberBotRenderer::new);
    }

}