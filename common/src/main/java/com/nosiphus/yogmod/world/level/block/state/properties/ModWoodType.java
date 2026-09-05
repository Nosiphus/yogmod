package com.nosiphus.yogmod.world.level.block.state.properties;

import com.nosiphus.yogmod.mixin.WoodTypeInvoker;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {

    public static final WoodType YOG = WoodTypeInvoker.invokeRegister(new WoodType("yog", BlockSetType.OAK));

}