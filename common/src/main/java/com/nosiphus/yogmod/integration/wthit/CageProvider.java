package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.plugin.vanilla.config.Options;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public enum CageProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendHead(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(Options.SPAWNER_TYPE)) {
            CageBlockEntity cage = (CageBlockEntity) accessor.getBlockEntity();
            Entity entity = cage != null
                    ? cage.getSpawner().getOrCreateDisplayEntity(accessor.getWorld(), accessor.getWorld().getRandom(), cage.getBlockPos())
                    : null;
            if (entity != null) {
                String name = entity.getDisplayName().getString();
                ResourceLocation objectNameTag = WailaConstants.OBJECT_NAME_TAG;
                IWailaConfig.Formatter formatter = IWailaConfig.get().getFormatter();
                String blockName = accessor.getBlock().getName().getString();
                tooltip.setLine(objectNameTag, formatter.blockName(blockName + " (" + name + ")"));
            }
        }
    }
}