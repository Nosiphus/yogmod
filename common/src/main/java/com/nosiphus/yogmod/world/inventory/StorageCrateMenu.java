package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class StorageCrateMenu extends AbstractContainerMenu {

    private final Container container;

    public StorageCrateMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(27));
    }

    public StorageCrateMenu(int containerId, Inventory inventory, Container container) {
        super(ModMenuType.STORAGE_CRATE.get(), containerId);
        checkContainerSize(container, 27);
        this.container = container;
        container.startOpen(inventory.player);

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new StorageCrateSlot(container, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 86 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 144));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemStack = slotStack.copy();

            int containerSize = this.container.getContainerSize();
            if (index < containerSize) {
                if (!this.moveItemStackTo(slotStack, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}