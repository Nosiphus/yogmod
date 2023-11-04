package com.nosiphus.yogmod.world.item.yogifier;

import com.nosiphus.yogmod.recipe.YogifierRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "yogmod");

    public static final RegistryObject<RecipeSerializer<YogifierRecipe>> YOGIFIER = RECIPE_TYPES.register("yogifier",
            () -> YogifierRecipe.Serializer.INSTANCE);

}
