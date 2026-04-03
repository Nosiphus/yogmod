package com.nosiphus.yogmod.integration.wthit.data;

import mcp.mobius.waila.api.IDataProvider;
import mcp.mobius.waila.api.IDataWriter;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public enum EnergyDataProvider implements IDataProvider<BlockEntity> {
    INSTANCE;

    @Override
    public void appendData(IDataWriter data, IServerAccessor<BlockEntity> accessor, IPluginConfig config) {
        IEnergyStorage energy = accessor.getWorld().getCapability(Capabilities.EnergyStorage.BLOCK, accessor.getTarget().getBlockPos(), null);

        if (energy != null) {
            data.raw().putInt("energy", energy.getEnergyStored());
            data.raw().putInt("capacity", energy.getMaxEnergyStored());
        }
    }
}