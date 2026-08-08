package com.nosiphus.yogmod.integration.wthit.data;

import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import mcp.mobius.waila.api.IDataProvider;
import mcp.mobius.waila.api.IDataWriter;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerAccessor;
import mcp.mobius.waila.plugin.vanilla.config.Options;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public enum RecordPlayerDataProvider implements IDataProvider<RecordPlayerBlockEntity> {
    INSTANCE;

    @Override
    public void appendData(IDataWriter data, IServerAccessor<RecordPlayerBlockEntity> accessor, IPluginConfig config) {
        if (config.getBoolean(Options.JUKEBOX_RECORD)) {
            ItemStack stack = accessor.getTarget().getTheItem();
            if (!stack.isEmpty()) {
                Component text = stack.has(DataComponents.JUKEBOX_PLAYABLE)
                        ? Component.translatable(stack.getDescriptionId() + ".desc")
                        : stack.getHoverName();
                data.raw().putString("record", Component.Serializer.toJson(text, accessor.getWorld().registryAccess()));
            }
        }
    }
}