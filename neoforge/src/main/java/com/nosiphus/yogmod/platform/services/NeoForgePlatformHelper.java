package com.nosiphus.yogmod.platform.services;

import com.nosiphus.yogmod.world.level.block.PoweredMetroVoxRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
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
}