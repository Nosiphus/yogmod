package com.nosiphus.yogmod.world.level.block.state.properties;

import com.nosiphus.yogmod.YogMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {

    public static final WoodType YOG = WoodType.register(new WoodType(YogMod.MOD_ID + ":yog", BlockSetType.OAK));

}