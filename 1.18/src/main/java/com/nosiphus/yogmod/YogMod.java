package com.nosiphus.yogmod;

import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::setup);

        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASS.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASS_PANE.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.HATCH.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.IRON_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_LANTERN.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.LED.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_LED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LADDER.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.METROVOX_ACTIVATOR_RAIL.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.METROVOX_DETECTOR_RAIL.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.METROVOX_RAIL.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.POWERED_METROVOX_RAIL.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.WOODEN_DOOR.get(), RenderType.cutout());

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

}