package com.nosiphus.yogmod.world.item.crafting;

import com.google.gson.JsonObject;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class YogifierRecipe implements Recipe<Container> {

    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;
    private final ResourceLocation id;

    public YogifierRecipe(ResourceLocation id, Ingredient base, Ingredient addition, ItemStack result) {
        this.id = id;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0)) && this.addition.test(container.getItem(1));
    }

    public ItemStack assemble(Container container) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundtag = container.getItem(0).getTag();
        if (compoundtag != null) {
            itemstack.setTag(compoundtag.copy());
        }

        return itemstack;
    }

    public boolean canCraftInDimensions(int int1, int int2) {
        return int1 * int2 >= 2;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public boolean isAdditionIngredient(ItemStack itemStack) {
        return this.addition.test(itemStack);
    }

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
        return Stream.of(this.base, this.addition).anyMatch((ingredient) -> {
            return net.minecraftforge.common.ForgeHooks.hasNoElements(ingredient);
        });
    }

    public static class Serializer implements RecipeSerializer<YogifierRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("yogmod", "yogifier");

        public YogifierRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "addition"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            return new YogifierRecipe(resourceLocation, ingredient, ingredient1, itemstack);
        }

        public YogifierRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            Ingredient ingredient1 = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack itemstack = friendlyByteBuf.readItem();
            return new YogifierRecipe(resourceLocation, ingredient, ingredient1, itemstack);
        }

        public void toNetwork(FriendlyByteBuf friendlyByteBuf, YogifierRecipe yogifierRecipe) {
            yogifierRecipe.base.toNetwork(friendlyByteBuf);
            yogifierRecipe.addition.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(yogifierRecipe.result);
        }
    }

}
