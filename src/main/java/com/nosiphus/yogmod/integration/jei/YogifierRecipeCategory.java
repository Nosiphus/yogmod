package com.nosiphus.yogmod.integration.jei;

import com.nosiphus.yogmod.integration.jei.IYogPlatformRecipeHelper;
import com.nosiphus.yogmod.integration.jei.JEIYogModPlugin;
import com.nosiphus.yogmod.integration.jei.YogServices;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class YogifierRecipeCategory implements IRecipeCategory<YogifierRecipe> {

    public static final ResourceLocation UID = new ResourceLocation("yogmod", "yogifier");
    public static final ResourceLocation TEXTURE = new ResourceLocation("yogmod", "textures/gui/container/yogifier.png");

    private final IDrawable background;
    private final IDrawable icon;

    public YogifierRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 26, 46, 125, 18);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.YOGIFIER.get()));
    }

    @Override
    public RecipeType<YogifierRecipe> getRecipeType() {
        return JEIYogModPlugin.YOGIFIER_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.yogmod.yogifier");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, YogifierRecipe recipe, IFocusGroup focusGroup) {


        IYogPlatformRecipeHelper recipeHelper = YogServices.PLATFORM.getYogRecipeHelper();

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipeHelper.getYogBase(recipe));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 1).addIngredients(recipeHelper.getYogAddition(recipe));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1).addItemStack(recipe.getResultItem());

    }

}
