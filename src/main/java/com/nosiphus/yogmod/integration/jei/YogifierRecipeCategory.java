package com.nosiphus.yogmod.integration.jei;

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
import org.jetbrains.annotations.Nullable;

public class YogifierRecipeCategory implements IRecipeCategory<YogifierRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("yogmod", "yogifier");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("yogmod", "textures/gui/container/yogifier.png");

    public static final RecipeType<YogifierRecipe> YOGIFIER_RECIPE_RECIPE_TYPE = new RecipeType<>(UID, YogifierRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public YogifierRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 39, 41, 98, 28);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.YOGIFIER.get()));
    }

    @Override
    public RecipeType<YogifierRecipe> getRecipeType() {
        return YOGIFIER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.yogmod.yogifier");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, YogifierRecipe recipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 6, 6).addIngredients(recipe.base);
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 76, 6).addItemStack(recipe.getResultItem(null));
    }

}
