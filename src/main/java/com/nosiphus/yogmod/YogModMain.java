package com.nosiphus.yogmod;

import com.mojang.logging.LogUtils;
import com.nosiphus.yogmod.client.gui.screens.inventory.OvenScreen;
import com.nosiphus.yogmod.client.gui.screens.inventory.StorageCrateScreen;
import com.nosiphus.yogmod.client.gui.screens.inventory.YogifierScreen;
import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModCreativeModeTabs;
import com.nosiphus.yogmod.world.item.ModItems;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeSerializer;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

@Mod("yogmod")
public class YogModMain {

    private static final Logger LOGGER = LogUtils.getLogger();
    public YogModMain(IEventBus eventBus, ModContainer container) {

        ModBlockEntityType.BLOCK_ENTITIES.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModCreativeModeTabs.CREATIVE_TABS.register(eventBus);
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



        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
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

    }



}