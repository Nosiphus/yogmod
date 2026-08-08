package com.nosiphus.yogmod.world.item.crafting;

import com.nosiphus.yogmod.platform.Services;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipeType {

    public static void init() {

    }

    private static <T extends YogifierRecipe> Supplier<RecipeType<T>> register(String name) {
        return Services.REGISTRY.registerRecipeType(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    public static final Supplier<RecipeType<YogifierRecipe>> YOGIFIER = register("yogifier");
}