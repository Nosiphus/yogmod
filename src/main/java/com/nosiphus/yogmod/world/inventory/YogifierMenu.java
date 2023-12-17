package com.nosiphus.yogmod.world.inventory;

import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class YogifierMenu extends ItemCombinerMenu {
    private final Level level;
    @Nullable
    private YogifierRecipe selectedRecipe;
    private final List<YogifierRecipe> recipes;

    public YogifierMenu(int index, Inventory inventory) {
        this(index, inventory, ContainerLevelAccess.NULL);
    }

    public YogifierMenu(int index, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(ModMenuType.YOGIFIER.get(), index, inventory, containerLevelAccess);
        this.level = inventory.player.level();
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(ModRecipeType.YOGIFIER.get());
    }

    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create().withSlot(0, 27, 47, (itemStack) -> {
            return this.recipes.stream().anyMatch((yogifierRecipe) -> {
                return yogifierRecipe.isBaseIngredient(itemStack);
            });
        }).withSlot(1, 76, 47, (itemStack1) -> {
            return this.recipes.stream().anyMatch((yogifierRecipe2) -> {
                return yogifierRecipe2.isAdditionIngredient(itemStack1);
            });
        }).withResultSlot(2, 134, 47).build();
    }

    protected boolean isValidBlock(BlockState blockState) {
        return blockState.is(ModBlocks.YOGIFIER.get());
    }

    protected boolean mayPickup(Player player, boolean boolean1) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
    }

    protected void onTake(Player player, ItemStack itemStack) {
        itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((level, blockPos) -> {
            level.levelEvent(1044, blockPos, 0);
        });
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1), this.inputSlots.getItem(2));
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemStack = this.inputSlots.getItem(index);
        itemStack.shrink(1);
        this.inputSlots.setItem(index, itemStack);
    }

    public void createResult() {
        List<YogifierRecipe> list = this.level.getRecipeManager().getRecipesFor(ModRecipeType.YOGIFIER.get(), this.inputSlots, this.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            this.selectedRecipe = list.get(0);
            ItemStack itemstack = this.selectedRecipe.assemble(this.inputSlots, this.level.registryAccess());
            this.resultSlots.setRecipeUsed(this.selectedRecipe);
            this.resultSlots.setItem(0, itemstack);
        }

    }

    public int getSlotToQuickMoveTo(ItemStack itemStack) {
        return this.recipes.stream().map((mapper) -> {
            return findSlotMatchingIngredient(mapper, itemStack);
        }).filter(Optional::isPresent).findFirst().orElse(Optional.of(0)).get();
    }

    private static Optional<Integer> findSlotMatchingIngredient(YogifierRecipe yogifierRecipe, ItemStack itemStack) {
        if (yogifierRecipe.isBaseIngredient(itemStack)) {
            return Optional.of(0);
        } else {
            return yogifierRecipe.isAdditionIngredient(itemStack) ? Optional.of(1) : Optional.empty();
        }
    }

    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(itemStack, slot);
    }

}
