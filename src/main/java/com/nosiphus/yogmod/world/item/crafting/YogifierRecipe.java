package com.nosiphus.yogmod.world.item.crafting;

import com.google.gson.JsonObject;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;

import java.util.stream.Stream;

public class YogifierRecipe implements Recipe<Container> {

    public final Ingredient base;
    public final ItemStack result;
    private final ResourceLocation id;

    public YogifierRecipe(ResourceLocation id, Ingredient base, ItemStack result) {
        this.id = id;
        this.base = base;
        this.result = result;
    }

    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0));
    }

    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public boolean isBaseIngredient(ItemStack itemStack) { return this.base.test(itemStack); }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.YOGIFIER.get());
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.YOGIFIER.get();
    }

    public RecipeType<?> getType() {
        return ModRecipeType.YOGIFIER.get();
    }

    public boolean isIncomplete() {
        return Stream.of(this.base).anyMatch(ForgeHooks::hasNoElements);
    }

    @Override
    public boolean isSpecial() { return true; }

    public static class Serializer implements RecipeSerializer<YogifierRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("yogmod", "yogifier");

        public YogifierRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            return new YogifierRecipe(resourceLocation, ingredient, itemstack);
        }

        public YogifierRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack itemstack = friendlyByteBuf.readItem();
            return new YogifierRecipe(resourceLocation, ingredient, itemstack);
        }

        public void toNetwork(FriendlyByteBuf friendlyByteBuf, YogifierRecipe yogifierRecipe) {
            yogifierRecipe.base.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(yogifierRecipe.result);
        }
    }

}
