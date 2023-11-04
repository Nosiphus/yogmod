package com.nosiphus.yogmod;

import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModItems;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeSerializer;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import com.nosiphus.yogmod.client.gui.screens.inventory.OvenMenuScreen;
import com.nosiphus.yogmod.client.renderer.blockentity.PistonHeadRenderer;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.state.properties.ModWoodType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.WoodType;
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

        ModBlockEntityType.BLOCK_ENTITIES.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModMenuType.MENU_TYPES.register(eventBus);
        ModRecipeSerializer.RECIPE_SERIALIZER.register(eventBus);
        ModRecipeType.RECIPE_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    @Mod.EventBusSubscriber(modid = "yogmod", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            WoodType.register(ModWoodType.YOG);
            Sheets.addWoodType(ModWoodType.YOG);
            registerBlockEntityRenderers();

            MenuScreens.register(ModMenuType.OVEN_MENU.get(), OvenMenuScreen::new);

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
    }

    @Mod.EventBusSubscriber(modid = "yogmod", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(ModBlockEntityType.PISTON.get(), PistonHeadRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.YOG_SIGN.get(), SignRenderer::new);
    }

}