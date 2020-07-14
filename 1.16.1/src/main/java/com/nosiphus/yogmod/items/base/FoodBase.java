package com.nosiphus.yogmod.items.base;

import com.nosiphus.yogmod.tabs.CreativeTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class FoodBase extends Item {

    public FoodBase() {
        super(new Item.Properties()
                .group(CreativeTab.YOGTAB)
                .food(new Food.Builder()
                        .hunger(5)
                        .saturation(0.3f)
                        .setAlwaysEdible()
                        .build())
        );
    }
}