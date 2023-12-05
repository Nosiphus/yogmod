package com.nosiphus.yogmod.world.level.block.state.properties;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType YOG = WoodType.register(new WoodType("yogmod" + ":yog", BlockSetType.OAK));
}
