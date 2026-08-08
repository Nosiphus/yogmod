package com.nosiphus.yogmod.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record YogifierRecipeInput(ItemStack base, ItemStack addition) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> this.base;
            case 1 -> this.addition;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        };
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return this.base.isEmpty() && this.addition.isEmpty();
    }
}