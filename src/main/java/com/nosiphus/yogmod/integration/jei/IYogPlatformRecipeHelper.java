package com.nosiphus.yogmod.integration.jei;

import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import net.minecraft.world.item.crafting.Ingredient;

public interface IYogPlatformRecipeHelper {

    Ingredient getYogBase(YogifierRecipe var1);

    Ingredient getYogAddition(YogifierRecipe var1);

}
