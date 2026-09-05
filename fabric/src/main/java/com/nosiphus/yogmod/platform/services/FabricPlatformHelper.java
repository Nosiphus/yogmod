package com.nosiphus.yogmod.platform.services;

import com.nosiphus.yogmod.world.item.CrateBlockItem;
import com.nosiphus.yogmod.world.level.block.PoweredMetroVoxRailBlock;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Block createPoweredRail(BlockBehaviour.Properties properties, boolean isPowered) {
        return new PoweredMetroVoxRailBlock(properties, isPowered);
    }

    @Override
    public BlockItem createCrateBlockItem(Block block, Item.Properties properties) {
        return new CrateBlockItem(block, properties);
    }
}
