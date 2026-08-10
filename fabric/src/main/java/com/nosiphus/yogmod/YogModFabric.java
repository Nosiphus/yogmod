package com.nosiphus.yogmod;

import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.PoweredMetroVoxRailBlock;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;

public class YogModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        YogMod.init();

        FabricDefaultAttributeRegistry.register(ModEntityType.SCRUBBER_BOT.get(), ScrubberBot.createAttributes());

        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> InventoryStorage.of(blockEntity, direction), ModBlockEntityType.CRATE.get());
        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> InventoryStorage.of(blockEntity, direction), ModBlockEntityType.DISPENSER.get());
        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> InventoryStorage.of(blockEntity, direction), ModBlockEntityType.RECORD_PLAYER.get());
        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> InventoryStorage.of(blockEntity, direction), ModBlockEntityType.OVEN.get());
        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> InventoryStorage.of(blockEntity, direction), ModBlockEntityType.STORAGE_CRATE.get());
    }
}
