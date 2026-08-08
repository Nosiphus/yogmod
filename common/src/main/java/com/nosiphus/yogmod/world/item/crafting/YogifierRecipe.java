package com.nosiphus.yogmod.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class YogifierRecipe implements Recipe<YogifierRecipeInput> {

    public final Ingredient base;
    final ItemStack result;

    public YogifierRecipe(Ingredient base, ItemStack result) {
        this.base = base;
        this.result = result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public boolean matches(YogifierRecipeInput input, Level level) {
        return this.base.test(input.base());
    }

    @Override
    public ItemStack assemble(YogifierRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public boolean isBaseIngredient(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.YOGIFIER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.YOGIFIER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeType.YOGIFIER.get();
    }

    @Override
    public boolean isIncomplete() {
        return this.base.getItems().length == 0;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<YogifierRecipe> {

        private static final MapCodec<YogifierRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                ).apply(instance, YogifierRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, YogifierRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<YogifierRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, YogifierRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static YogifierRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack itemStack = ItemStack.STREAM_CODEC.decode(buffer);
            return new YogifierRecipe(ingredient, itemStack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, YogifierRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}