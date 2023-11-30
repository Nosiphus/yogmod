package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CrateMenu extends AbstractContainerMenu {
    private static final int SLOTS_PER_ROW = 9;
    private final Container container;
    private final int containerRows;

    private CrateMenu(MenuType<?> menuType, int ID, Inventory inventory, int rows) {
        this(menuType, ID, inventory, new SimpleContainer(9 * rows), rows);
    }

    public static CrateMenu oneRow(int ID, Inventory inventory) {
        return new CrateMenu(MenuType.GENERIC_9x1, ID, inventory, 1);
    }

    public static CrateMenu twoRows(int ID, Inventory inventory) {
        return new CrateMenu(MenuType.GENERIC_9x2, ID, inventory, 2);
    }

    public static CrateMenu threeRows(int ID, Inventory inventory) {
        return new CrateMenu(MenuType.GENERIC_9x3, ID, inventory, 3);
    }

    public static CrateMenu fourRows(int ID, Inventory inventory) {
        return new CrateMenu(MenuType.GENERIC_9x4, ID, inventory, 4);
    }

    public static CrateMenu fiveRows(int ID, Inventory inventory) {
        return new CrateMenu(MenuType.GENERIC_9x5, ID, inventory, 5);
    }

    public static CrateMenu sixRows(int ID, Inventory inventory) {
        return new CrateMenu(MenuType.GENERIC_9x6, ID, inventory, 6);
    }

    public static CrateMenu threeRows(int ID, Inventory inventory, Container container) {
        return new CrateMenu(MenuType.GENERIC_9x3, ID, inventory, container, 3);
    }

    public static CrateMenu sixRows(int ID, Inventory inventory, Container container) {
        return new CrateMenu(MenuType.GENERIC_9x6, ID, inventory, container, 6);
    }

    public CrateMenu(MenuType<?> menuType, int ID, Inventory inventory, Container container, int rows) {
        super(menuType, ID);
        checkContainerSize(container, rows * 9);
        this.container = container;
        this.containerRows = rows;
        container.startOpen(inventory.player);
        int i = (this.containerRows - 4) * 18;

        for (int j = 0; j < this.containerRows; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(container, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 161 + i));
        }

    }

    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public ItemStack quickMoveStack(Player player, int int1) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(int1);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (int1 < this.containerRows * 9) {
                if (!this.moveItemStackTo(itemStack1, this.containerRows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, 0, this.containerRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public Container getContainer() {
        return this.container;
    }

    public int getRowCount() {
        return this.containerRows;
    }

}
