package com.nosiphus.yogmod.world.item.crafting;

import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.DataMapHooks;

import java.util.stream.Stream;

public class YogifierRecipe implements Recipe<CraftingInput> {

    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;

    public YogifierRecipe(Ingredient base, Ingredient addition, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.base);
        list.add(this.addition);
        return list;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        return this.base.test(craftingInput.getItem(0)) && this.addition.test(craftingInput.getItem(1));
    }

    public Ingredient getBase() {
        return this.base;
    }

    public Ingredient getAddition() {
        return this.addition;
    }

    public ItemStack getResult() {
        return this.result;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        return this.result.copy();
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
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<YogifierRecipe> {

        public static final MapCodec<YogifierRecipe> CODEC = RecordCodecBuilder.mapCodec(yogifierRecipeInstance -> yogifierRecipeInstance.group(
                Ingredient.CODEC.fieldOf("base").forGetter(YogifierRecipe::getBase),
                Ingredient.CODEC.fieldOf("addition").forGetter(YogifierRecipe::getAddition),
                ItemStack.CODEC.fieldOf("result").forGetter(YogifierRecipe::getResult)
        ).apply(yogifierRecipeInstance, YogifierRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, YogifierRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, YogifierRecipe::getBase,
                Ingredient.CONTENTS_STREAM_CODEC, YogifierRecipe::getAddition,
                ItemStack.STREAM_CODEC, YogifierRecipe::getResult,
                YogifierRecipe::new
        );

        @Override
        public MapCodec<YogifierRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, YogifierRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }
}
