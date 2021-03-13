package com.nosiphus.yogmod;

import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.util.Reference;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
public class YogMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup YOGTAB = new YogTab("yogtab");

    public YogMod() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::setup);

        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModTileEntities.TILE_ENTITY_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(ModBlocks.GLASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GLASS_PANE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.HATCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.IRON_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WALL_LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WALL_LED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LADDER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WOODEN_DOOR.get(), RenderType.getCutout());

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class YogTab extends ItemGroup {

        public YogTab(String label) {

            super(label);

        }

        @Override
        public ItemStack createIcon() {

            return ModItems.FLUORESCENT_PANEL.get().getDefaultInstance();

        }

    }

}