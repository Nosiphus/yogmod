package com.nosiphus.yogmod;

import com.nosiphus.yogmod.block.WireBlock;
import com.nosiphus.yogmod.client.menu.screen.OvenMenuScreen;
import com.nosiphus.yogmod.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("yogmod")
public class YogMod {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab YOGTAB = new CreativeModeTab("yogmod") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.FLUORESCENT_PANEL.get().getDefaultInstance();
        }
    };

    public YogMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModMenuTypes.MENU_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    @Mod.EventBusSubscriber(modid = "yogmod", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(ModMenuTypes.OVEN_MENU.get(), OvenMenuScreen::new);

        }

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register((blockstate, lightreader, pos, index) -> {
                return WireBlock.colorMultiplier(blockstate.getValue(WireBlock.POWER));
            }, ModBlocks.WIRE.get());
        }
    }

    @Mod.EventBusSubscriber(modid = "yogmod", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        //future events may be added here
    }

}