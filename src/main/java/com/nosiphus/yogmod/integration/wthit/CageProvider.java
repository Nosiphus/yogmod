package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.plugin.vanilla.config.Options;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public enum CageProvider implements IBlockComponentProvider {
    INSTANCE;

    public void appendHead(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(Options.SPAWNER_TYPE)) {
            CageBlockEntity cage = (CageBlockEntity) accessor.getBlockEntity();
            Entity entity = cage != null ? cage.getSpawner().getOrCreateDisplayEntity(accessor.getWorld(), cage.getBlockPos()) : null;
            if (entity != null) {
                String name = entity.getDisplayName().getString();
                ResourceLocation var10001 = WailaConstants.OBJECT_NAME_TAG;
                IWailaConfig.Formatter var10002 = IWailaConfig.get().getFormatter();
                String var10003 = accessor.getBlock().getName().getString();
                tooltip.setLine(var10001, var10002.blockName(var10003 + " (" + name + ")"));
            }
        }
    }
}
