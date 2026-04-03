package com.nosiphus.yogmod.integration.wthit;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;

import java.text.NumberFormat;
import java.util.Locale;

public enum EnergyProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (accessor.getData().raw().contains("energy")) {
            int current = accessor.getData().raw().getInt("energy");
            int capacity = accessor.getData().raw().getInt("capacity");

            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            String text = nf.format(current) + " / " + nf.format(capacity) + " FE";
            tooltip.addLine(Component.literal(text));
        }
    }
}