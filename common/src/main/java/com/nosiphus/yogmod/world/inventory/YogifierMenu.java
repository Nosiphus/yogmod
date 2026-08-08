package com.nosiphus.yogmod.world.inventory;

import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipeInput;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.OptionalInt;

public class YogifierMenu extends ItemCombinerMenu {

    public static final int BASE_SLOT = 0;
    public static final int ADDITIONAL_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int BASE_SLOT_X_PLACEMENT = 27;
    public static final int ADDITIONAL_SLOT_X_PLACEMENT = 76;
    public static final int RESULT_SLOT_X_PLACEMENT = 134;
    public static final int SLOT_Y_PLACEMENT = 47;

    private final Level level;
    @Nullable
    private RecipeHolder<YogifierRecipe> selectedRecipe;
    private final List<RecipeHolder<YogifierRecipe>> recipes;

    public YogifierMenu(int index, Inventory inventory) {
        this(index, inventory, ContainerLevelAccess.NULL);
    }

    public YogifierMenu(int index, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(ModMenuType.YOGIFIER.get(), index, inventory, containerLevelAccess);
        this.level = inventory.player.level();
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(ModRecipeType.YOGIFIER.get());
    }

    @Override
    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 45, 47, baseStack -> this.recipes.stream().anyMatch(base -> base.value().isBaseIngredient(baseStack)))
                .withResultSlot(1, 115, 47)
                .build();
    }

    @Override
    protected boolean isValidBlock(BlockState blockState) {
        return blockState.is(ModBlocks.YOGIFIER.get());
    }

    @Override
    protected boolean mayPickup(Player player, boolean hasStack) {
        return this.selectedRecipe != null && this.selectedRecipe.value().matches(this.createRecipeInput(), this.level);
    }

    @Override
    protected void onTake(Player player, ItemStack stack) {
        stack.onCraftedBy(player.level(), player, stack.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((level, pos) -> level.levelEvent(1044, pos, 0));
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    private YogifierRecipeInput createRecipeInput() {
        return new YogifierRecipeInput(this.inputSlots.getItem(0), this.inputSlots.getItem(1));
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemStack = this.inputSlots.getItem(index);
        if (!itemStack.isEmpty()) {
            itemStack.shrink(1);
            this.inputSlots.setItem(index, itemStack);
        }
    }

    @Override
    public void createResult() {
        YogifierRecipeInput yogifierRecipeInput = this.createRecipeInput();
        List<RecipeHolder<YogifierRecipe>> list = this.level.getRecipeManager().getRecipesFor(ModRecipeType.YOGIFIER.get(), yogifierRecipeInput, this.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            RecipeHolder<YogifierRecipe> recipeHolder = list.get(0);
            ItemStack itemStack = recipeHolder.value().assemble(yogifierRecipeInput, this.level.registryAccess());
            if (itemStack.isItemEnabled(this.level.enabledFeatures())) {
                this.selectedRecipe = recipeHolder;
                this.resultSlots.setRecipeUsed(recipeHolder);
                this.resultSlots.setItem(0, itemStack);
            }
        }
    }

    @Override
    public int getSlotToQuickMoveTo(ItemStack stack) {
        return this.findSlotToQuickMoveTo(stack).orElse(0);
    }

    private static OptionalInt findSlotMatchingIngredient(YogifierRecipe recipe, ItemStack stack) {
        return OptionalInt.of(0);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public boolean canMoveIntoInputSlots(ItemStack stack) {
        return this.findSlotToQuickMoveTo(stack).isPresent();
    }

    private OptionalInt findSlotToQuickMoveTo(ItemStack stack) {
        return this.recipes
                .stream()
                .flatMapToInt(recipeHolder -> findSlotMatchingIngredient(recipeHolder.value(), stack).stream())
                .filter(slot -> !this.getSlot(slot).hasItem())
                .findFirst();
    }
}