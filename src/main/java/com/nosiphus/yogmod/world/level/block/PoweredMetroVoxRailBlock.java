package com.nosiphus.yogmod.world.level.block;

import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.properties.RailShape;

public class PoweredMetroVoxRailBlock extends PoweredRailBlock {

    public PoweredMetroVoxRailBlock(Properties builder, boolean isPoweredRail) {
        super(builder, isPoweredRail);
        this.registerDefaultState(this.stateDefinition.any().setValue(SHAPE, RailShape.NORTH_SOUTH).setValue(POWERED, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }


}