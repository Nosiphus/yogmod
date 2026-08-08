package com.nosiphus.yogmod.world.item.crafting;

import com.nosiphus.yogmod.platform.Services;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ModRecipeSerializer {

    public static void init() {

    }

    private static <T extends RecipeSerializer<?>> Supplier<T> register(String name, Supplier<T> serializerSupplier) {
        return Services.REGISTRY.registerRecipeSerializer(name, serializerSupplier);
    }

    public static final Supplier<RecipeSerializer<YogifierRecipe>> YOGIFIER = register("yogifier", YogifierRecipe.Serializer::new);
}