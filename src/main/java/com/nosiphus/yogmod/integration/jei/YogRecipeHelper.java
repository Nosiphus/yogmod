package com.nosiphus.yogmod.integration.jei;

import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import net.minecraft.world.item.crafting.Ingredient;

public class YogRecipeHelper implements IYogPlatformRecipeHelper {

    public YogRecipeHelper() {
    }

    public Ingredient getYogBase(YogifierRecipe recipe) {
        return recipe.base;
    }

    public Ingredient getYogAddition(YogifierRecipe recipe) {
        return recipe.addition;
    }

}
