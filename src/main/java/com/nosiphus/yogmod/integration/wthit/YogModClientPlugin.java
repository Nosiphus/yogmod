package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.WireBlock;
import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import mcp.mobius.waila.api.IClientRegistrar;
import mcp.mobius.waila.api.IWailaClientPlugin;
import mcp.mobius.waila.api.data.FluidData;
import mcp.mobius.waila.plugin.vanilla.fluid.LavaDescriptor;
import mcp.mobius.waila.plugin.vanilla.fluid.WaterDescriptor;
import net.minecraft.world.level.block.DiodeBlock;

public class YogModClientPlugin implements IWailaClientPlugin {

    public YogModClientPlugin() {
    }

    public void register(IClientRegistrar registrar) {
        registrar.body(RecordPlayerProvider.INSTANCE, RecordPlayerBlockEntity.class);
        registrar.head(CageProvider.INSTANCE, CageBlockEntity.class);
        registrar.body(RedstoneProvider.INSTANCE, DiodeBlock.class);
        registrar.body(RedstoneProvider.INSTANCE, WireBlock.class);
        FluidData.describeCauldron(ModBlocks.WATER_SINK.get(), WaterDescriptor.INSTANCE);
        FluidData.describeCauldron(ModBlocks.LAVA_SINK.get(), LavaDescriptor.INSTANCE);
    }

}
