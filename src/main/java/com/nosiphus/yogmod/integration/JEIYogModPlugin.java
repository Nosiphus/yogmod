package com.nosiphus.yogmod.integration;

import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIYogModPlugin implements IModPlugin {

    public static RecipeType<YogifierRecipe> YOGIFIER_TYPE = new RecipeType<>(YogifierRecipeCategory.UID, YogifierRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("yogmod", "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new YogifierRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level.getRecipeManager());
        List<YogifierRecipe> recipeList = recipeManager.getAllRecipesFor(ModRecipeType.YOGIFIER.get());
        registration.addRecipes(YOGIFIER_TYPE, recipeList);
    }

}
