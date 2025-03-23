package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.plugin.vanilla.config.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum RecordPlayerProvider implements IBlockComponentProvider {
    INSTANCE;

    private RecordPlayerProvider() {
    }

    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(Options.JUKEBOX_RECORD) && accessor.getData().raw().contains("record")) {
            MutableComponent component = Component.Serializer.fromJson(accessor.getData().raw().getString("record"), accessor.getWorld().registryAccess());
            if (component != null) {
                tooltip.setLine(Options.JUKEBOX_RECORD, component);
            }
        }

    }
}
