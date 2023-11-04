package com.nosiphus.yogmod.world.item.crafting;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "yogmod");

    public static final RegistryObject<RecipeSerializer<YogifierRecipe>> YOGIFIER = RECIPE_SERIALIZER.register("yogifier",
            () -> YogifierRecipe.Serializer.INSTANCE);

}
