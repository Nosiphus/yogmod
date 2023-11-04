package com.nosiphus.yogmod.world.inventory;

import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.item.crafting.YogifierRecipe;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

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
        this.level = inventory.player.level;
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(ModRecipeType.YOGIFIER.get());
    }

    protected boolean isValidBlock(BlockState blockState) {
        return blockState.is(ModBlocks.YOGIFIER.get());
    }

    protected boolean mayPickup(Player player, boolean boolean1) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
    }

    protected void onTake(Player player, ItemStack itemStack) {
        itemStack.onCraftedBy(player.level, player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player);
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((level, blockPos) -> {
            level.levelEvent(1044, blockPos, 0);
        });
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
            ItemStack itemstack = this.selectedRecipe.assemble(this.inputSlots);
            this.resultSlots.setRecipeUsed(this.selectedRecipe);
            this.resultSlots.setItem(0, itemstack);
        }

    }

    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack itemStack) {
        return this.recipes.stream().anyMatch((yogifierRecipe) -> {
            return yogifierRecipe.isAdditionIngredient(itemStack);
        });
    }

    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(itemStack, slot);
    }

}
