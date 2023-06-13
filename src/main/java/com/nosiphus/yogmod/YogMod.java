package com.nosiphus.yogmod;

import com.nosiphus.yogmod.block.WireBlock;
import com.nosiphus.yogmod.client.menu.screen.OvenMenuScreen;
import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.init.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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
    public static final String MOD_ID = "yogmod";

    public YogMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

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

    public void onRegisterCreativeTab(CreativeModeTabEvent.Register event) {

        event.registerCreativeModeTab(new ResourceLocation(YogMod.MOD_ID, "yogmod"), builder -> {
            builder.title(Component.translatable("itemGroup." + YogMod.MOD_ID));
            builder.icon(() -> new ItemStack(ModBlocks.FLUORESCENT_PANEL.get()));
            builder.displayItems((flags, output, permission) -> ModItems.ITEMS.getEntries().forEach(registryObject -> output.accept(registryObject.get())));
        });

    }

}