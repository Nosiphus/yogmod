package com.nosiphus.yogmod.platform.services;

import com.nosiphus.yogmod.world.item.CrateBlockItemForge;
import com.nosiphus.yogmod.world.level.block.PoweredMetroVoxRailBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public Block createPoweredRail(BlockBehaviour.Properties properties, boolean isPowered) {
        return new PoweredMetroVoxRailBlock(properties, isPowered);
    }

    @Override
    public BlockItem createCrateBlockItem(Block block, Item.Properties properties) {
        return new CrateBlockItemForge(block, properties);
    }
}