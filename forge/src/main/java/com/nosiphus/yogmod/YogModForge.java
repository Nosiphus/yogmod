package com.nosiphus.yogmod;

import com.nosiphus.yogmod.client.gui.screens.inventory.*;
import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.client.renderer.blockentity.CrateRenderer;
import com.nosiphus.yogmod.platform.services.ForgeRegistryHelper;
import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModCreativeModeTabsForge;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.antlr.v4.runtime.misc.NotNull;

import javax.annotation.Nullable;

@Mod(YogMod.MOD_ID)
public class YogModForge {

    public YogModForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        YogMod.init();

        ForgeRegistryHelper.BLOCKS.register(eventBus);
        ForgeRegistryHelper.BLOCK_ENTITY_TYPES.register(eventBus);
        ModCreativeModeTabsForge.CREATIVE_TABS.register(eventBus);
        ForgeRegistryHelper.ENTITY_TYPES.register(eventBus);
        ForgeRegistryHelper.ITEMS.register(eventBus);
        ForgeRegistryHelper.MENU_TYPES.register(eventBus);
        ForgeRegistryHelper.RECIPE_SERIALIZERS.register(eventBus);
        ForgeRegistryHelper.RECIPE_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventBusSubscriber(modid = YogMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(YogModClient::initClient);

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

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register((blockState, blockAndTintGetter, blockPos, index) ->
                    WireBlock.colorMultiplier(blockState.getValue(WireBlock.POWER)), ModBlocks.WIRE.get());

            event.register((blockState, blockAndTintGetter, blockPos, index) ->
                            blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(blockAndTintGetter, blockPos) : -1,
                    ModBlocks.WATER_SINK.get());
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(CrateRenderer.CRATE, CrateRenderer::createSingleBodyLayer);
            event.registerLayerDefinition(CrateRenderer.DOUBLE_CRATE_LEFT, CrateRenderer::createDoubleBodyLeftLayer);
            event.registerLayerDefinition(CrateRenderer.DOUBLE_CRATE_RIGHT, CrateRenderer::createDoubleBodyRightLayer);
            event.registerLayerDefinition(ScrubberBotModel.SCRUBBER_BOT, ScrubberBotModel::createBodyLayer);
        }
    }

    @EventBusSubscriber(modid = YogMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntityType.SCRUBBER_BOT.get(), ScrubberBot.createAttributes().build());
        }
    }

    @EventBusSubscriber(modid = YogMod.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        private static final ResourceLocation CAP_KEY = new ResourceLocation(YogMod.MOD_ID, "inventory_cap");

        @SubscribeEvent
        public static void attachCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
            BlockEntity be = event.getObject();

            if (be.getType() == ModBlockEntityType.CRATE.get()
                    || be.getType() == ModBlockEntityType.DISPENSER.get()
                    || be.getType() == ModBlockEntityType.RECORD_PLAYER.get()) {
                event.addCapability(CAP_KEY, new ICapabilityProvider() {
                    private final LazyOptional<?> holder = LazyOptional.of(() -> new InvWrapper((net.minecraft.world.Container) be));

                    @Override
                    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                        return cap == ForgeCapabilities.ITEM_HANDLER ? holder.cast() : LazyOptional.empty();
                    }
                });
            } else if (be.getType() == ModBlockEntityType.OVEN.get()
                    || be.getType() == ModBlockEntityType.STORAGE_CRATE.get()) {
                event.addCapability(CAP_KEY, new ICapabilityProvider() {
                    @Override
                    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                        if (cap == ForgeCapabilities.ITEM_HANDLER) {
                            return LazyOptional.of(() -> new SidedInvWrapper((net.minecraft.world.WorldlyContainer) be, side)).cast();
                        }
                        return LazyOptional.empty();
                    }
                });
            }
        }
    }
}