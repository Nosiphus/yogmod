package com.nosiphus.yogmod.integration;

import com.nosiphus.yogmod.world.inventory.YogifierMenu;
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
import mezz.jei.common.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class YogifierRecipeCategory implements IRecipeCategory<YogifierRecipe> {

    public static final ResourceLocation UID = new ResourceLocation("yogmod", "yogifier");
    public static final ResourceLocation TEXTURE = new ResourceLocation("yogmod", "textures/gui/container/yogifier.png");

    private final IDrawable background;
    private final IDrawable icon;

    public YogifierRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
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

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 1).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1).addItemStack(recipe.getResultItem());

    }

}
