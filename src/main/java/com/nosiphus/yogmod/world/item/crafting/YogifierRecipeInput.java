package com.nosiphus.yogmod.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record YogifierRecipeInput(ItemStack base, ItemStack addition) implements RecipeInput {

    @Override
    public ItemStack getItem(int item) {
        return switch (item) {
            case 0 -> this.base;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + item);
        };
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.base.isEmpty();
    }

}