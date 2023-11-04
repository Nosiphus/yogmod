package com.nosiphus.yogmod.world.item.crafting;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeType {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, "yogmod");

    public static final RegistryObject<RecipeType<YogifierRecipe>> YOGIFIER = register("yogifier");

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String name)
    {
        return RECIPE_TYPES.register(name, () -> new RecipeType<>()
        {
            @Override
            public String toString()
            {
                return name;
            }
        });
    }

}
