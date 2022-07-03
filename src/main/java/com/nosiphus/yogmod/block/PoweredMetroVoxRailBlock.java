package com.nosiphus.yogmod.block;

import net.minecraft.block.PoweredRailBlock;
import net.minecraft.state.properties.RailShape;

public class PoweredMetroVoxRailBlock extends PoweredRailBlock {

    public PoweredMetroVoxRailBlock(Properties builder, boolean isPoweredRail) {
        super(builder, isPoweredRail);
        this.setDefaultState(this.stateContainer.getBaseState().with(SHAPE, RailShape.NORTH_SOUTH).with(POWERED, Boolean.valueOf(false)));
    }


}
