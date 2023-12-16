package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.plugin.vanilla.config.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component.Serializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;

public enum RecordPlayerProvider implements IBlockComponentProvider, IDataProvider<RecordPlayerBlockEntity> {
    INSTANCE;

    private RecordPlayerProvider() {
    }

    public void appendBody(ITooltip tooltip, IBlockAccessor blockAccessor, IPluginConfig pluginConfig) {
        if (pluginConfig.getBoolean(Options.JUKEBOX_RECORD) && blockAccessor.getData().raw().contains("record")) {
            tooltip.addLine(Serializer.fromJson(blockAccessor.getData().raw().getString("record")));
        }
    }

    public void appendData(IDataWriter dataWriter, IServerAccessor<RecordPlayerBlockEntity> serverAccessor, IPluginConfig pluginConfig) {
        if (pluginConfig.getBoolean(Options.JUKEBOX_RECORD)) {
            ItemStack itemStack = ((RecordPlayerBlockEntity) serverAccessor.getTarget()).getFirstItem();
            if (!itemStack.isEmpty()) {
                Component text = itemStack.getItem() instanceof RecordItem ? Component.translatable(itemStack.getDescriptionId() + ".desc") : itemStack.getDisplayName();
                dataWriter.raw().putString("record", Serializer.toJson((Component) text));
            }
        }
    }
}
