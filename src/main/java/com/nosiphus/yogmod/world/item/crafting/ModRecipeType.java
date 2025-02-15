package com.nosiphus.yogmod.world.item.crafting;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeType {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, "yogmod");

    public static final Supplier<RecipeType<YogifierRecipe>> YOGIFIER = RECIPE_TYPES.register("yogifier",
            () -> RecipeType.<YogifierRecipe>simple(ResourceLocation.fromNamespaceAndPath("yogmod", "yogifier"))
    );

}
