package com.nosiphus.yogmod.world.level.block;

import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.properties.RailShape;

public class PoweredMetroVoxRailBlock extends PoweredRailBlock {

    public PoweredMetroVoxRailBlock(Properties properties, boolean isPowered) {
        super(properties, isPowered);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false));
    }

}