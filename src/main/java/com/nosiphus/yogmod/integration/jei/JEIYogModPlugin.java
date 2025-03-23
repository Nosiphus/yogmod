package com.nosiphus.yogmod.integration.jei;

import com.nosiphus.yogmod.client.gui.screens.inventory.YogifierScreen;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIYogModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath("yogmod", "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new YogifierRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<YogifierRecipe> yogifierRecipes = recipeManager.getAllRecipesFor(ModRecipeType.YOGIFIER.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(YogifierRecipeCategory.YOGIFIER_RECIPE_RECIPE_TYPE, yogifierRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(YogifierScreen.class, 74, 45, 28, 21, YogifierRecipeCategory.YOGIFIER_RECIPE_RECIPE_TYPE);
    }
}
