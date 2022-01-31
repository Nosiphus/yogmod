package com.nosiphus.yogmod;

import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod(Reference.MOD_ID)
public class YogMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup YOGTAB = new YogTab("yogtab");

    public YogMod() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::setup);

        //ModItems.ITEMS.register(bus);
        //ModBlocks.BLOCKS.register(bus);

        ModBlocks.REGISTER.register(bus);
        ModItems.REGISTER.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event) {


        RenderTypeLookup.setRenderLayer(ModBlocks.GLASS.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.GLASS_PANE.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.HATCH.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.IRON_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WALL_LANTERN.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.LED.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.WALL_LED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LADDER.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.METROVOX_ACTIVATOR_RAIL.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.METROVOX_DETECTOR_RAIL.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.METROVOX_RAIL.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.POWERED_METROVOX_RAIL.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(ModBlocks.WOODEN_DOOR.get(), RenderType.getCutout());

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class YogTab extends ItemGroup {

        public YogTab(String label) {

            super(label);

        }

        @Override
        public ItemStack createIcon() {

            return new ItemStack(ModBlocks.FLUORESCENT_PANEL.get());

        }

    }

}