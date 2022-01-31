package com.nosiphus.yogmod.init;

import net.minecraft.item.Food;

public class ModFoods {

    //Miscellaneous
    public static final Food COFFEE = new Food.Builder().fastToEat().hunger(5).saturation(0.2f).setAlwaysEdible().build();
    public static final Food HAMBURGER = new Food.Builder().hunger(8).saturation(0.8f).setAlwaysEdible().build();

    //Foodstuffs
    public static final Food JAFFA = new Food.Builder().fastToEat().hunger(5).saturation(0.3f).setAlwaysEdible().build();

}
