package com.nosiphus.yogmod.world.inventory;

import com.nosiphus.yogmod.world.item.yogifier.ModRecipeTypes;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

public class YogifierMenu extends RecipeBookMenu<YogifierContainer> {
    public static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_END = 10;
    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private static final int USE_ROW_SLOT_START = 37;
    private static final int USE_ROW_SLOT_END = 46;
    private final YogifierContainer yogifierSlots = new YogifierContainer(this, 3, 3);
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public YogifierMenu(int index, Inventory inventory) {
        this(index, inventory, ContainerLevelAccess.NULL);
    }

    public YogifierMenu(int index, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(ModMenuType.YOGIFIER_MENU.get(), index);
        this.access = containerLevelAccess;
        this.player = inventory.player;
        this.addSlot(new ResultSlot(inventory.player, this.yogifierSlots, this.resultSlots, 0, 124, 35));
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.yogifierSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));
        }
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu abstractContainerMenu, Level level, Player player, YogifierContainer yogifierContainer, ResultContainer resultContainer) {
        if (!level.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(ModRecipeTypes.YOGIFIER.get(), yogifierContainer, level);
            if (optional.isPresent()) {
                CraftingRecipe craftingrecipe = optional.get();
                if (resultContainer.setRecipeUsed(level, serverplayer, craftingrecipe)) {
                    itemstack = craftingrecipe.assemble(yogifierContainer);
                }
            }

            resultContainer.setItem(0, itemstack);
            abstractContainerMenu.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(abstractContainerMenu.containerId, abstractContainerMenu.incrementStateId(), 0, itemstack));
        }
    }

    public void slotsChanged(Container container) {
        this.access.execute((level, blockPos) -> {
            slotChangedCraftingGrid(this, level, this.player, this.yogifierSlots, this.resultSlots);
        });
    }

    public void fillyogifierSlotsStackedContents(StackedContents stackedContents) {
        this.yogifierSlots.fillStackedContents(stackedContents);
    }

    public void clearCraftingContent() {
        this.yogifierSlots.clearContent();
        this.resultSlots.clearContent();
    }

    public boolean recipeMatches(Recipe<? super YogifierContainer> recipe) {
        return recipe.matches(this.yogifierSlots, this.player.level);
    }

    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_39371_, p_39372_) -> {
            this.clearContainer(player, this.yogifierSlots);
        });
    }

    public boolean stillValid(Player player) {
        return stillValid(this.access, player, Blocks.CRAFTING_TABLE);
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                this.access.execute((level, blockPos) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, level, player);
                });
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= 10 && index < 46) {
                if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
                    if (index < 37) {
                        if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(itemStack, slot);
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.yogifierSlots.getWidth();
    }

    public int getGridHeight() {
        return this.yogifierSlots.getHeight();
    }

    public int getSize() {
        return 10;
    }

    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    public boolean shouldMoveToInventory(int p_150553_) {
        return p_150553_ != this.getResultSlotIndex();
    }

}
