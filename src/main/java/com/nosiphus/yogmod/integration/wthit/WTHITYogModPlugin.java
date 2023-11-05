package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.data.FluidData;
import mcp.mobius.waila.plugin.vanilla.fluid.LavaDescriptor;
import mcp.mobius.waila.plugin.vanilla.fluid.WaterDescriptor;
import net.minecraft.world.level.block.DiodeBlock;

public class WTHITYogModPlugin implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.addComponent(YogRedstoneProvider.INSTANCE, TooltipPosition.BODY, DiodeBlock.class);
        registrar.addComponent(YogRedstoneProvider.INSTANCE, TooltipPosition.BODY, WireBlock.class);
        FluidData.describeCauldron(ModBlocks.WATER_SINK.get(), WaterDescriptor.INSTANCE);
        FluidData.describeCauldron(ModBlocks.LAVA_SINK.get(), LavaDescriptor.INSTANCE);
    }

    public WTHITYogModPlugin() {

    }

}
