package com.nosiphus.yogmod.world.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, "yogmod");

    public static final Supplier<RecipeSerializer<YogifierRecipe>> YOGIFIER =
            RECIPE_SERIALIZER.register("yogifier", YogifierRecipe.Serializer::new);

}
