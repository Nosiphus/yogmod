package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.component.PairComponent;
import mcp.mobius.waila.plugin.vanilla.config.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public enum RedstoneProvider implements IBlockComponentProvider {
    INSTANCE;

    private RedstoneProvider() {
    }

    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(Options.REDSTONE_REPEATER) && accessor.getBlock() == ModBlocks.DIODE.get()) {
            int delay = (Integer)accessor.getBlockState().getValue(BlockStateProperties.DELAY);
            tooltip.setLine(Options.REDSTONE_REPEATER, new PairComponent(Component.translatable("tooltip.waila.delay"), Component.literal(String.valueOf(delay))));
        } else {
            if (config.getBoolean(Options.REDSTONE_LEVEL) && accessor.getBlock() == ModBlocks.WIRE.get()) {
                tooltip.setLine(Options.REDSTONE_LEVEL, new PairComponent(Component.translatable("tooltip.waila.power"), Component.literal(((Integer)accessor.getBlockState().getValue(BlockStateProperties.POWER)).toString())));
            }

        }
    }


}
