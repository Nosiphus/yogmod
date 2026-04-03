package com.nosiphus.yogmod.integration.wthit;

import com.nosiphus.yogmod.integration.wthit.data.EnergyDataProvider;
import com.nosiphus.yogmod.integration.wthit.data.RecordPlayerDataProvider;
import com.nosiphus.yogmod.world.level.block.entity.PoweredStripeBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.PoweredStripeIntersectionBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import mcp.mobius.waila.api.ICommonRegistrar;
import mcp.mobius.waila.api.IWailaCommonPlugin;

public class YogModCommonPlugin implements IWailaCommonPlugin {
    public YogModCommonPlugin() {
    }

    public void register(ICommonRegistrar registrar) {
        registrar.blockData(RecordPlayerDataProvider.INSTANCE, RecordPlayerBlockEntity.class);
        registrar.blockData(EnergyDataProvider.INSTANCE, PoweredStripeBlockEntity.class);
        registrar.blockData(EnergyDataProvider.INSTANCE, PoweredStripeIntersectionBlockEntity.class);
    }

}
