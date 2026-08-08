package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.integration.wthit.data.RecordPlayerDataProvider;
import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import mcp.mobius.waila.api.ICommonRegistrar;
import mcp.mobius.waila.api.IWailaCommonPlugin;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin(id = "yogmod:common_plugin")
public class YogModCommonPlugin implements IWailaCommonPlugin {

    @Override
    public void register(ICommonRegistrar registrar) {
        registrar.blockData(RecordPlayerDataProvider.INSTANCE, RecordPlayerBlockEntity.class);
    }
}