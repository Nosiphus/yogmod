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

    private final Container container;
    private final int containerRows;

    private CrateMenu(MenuType<?> menuType, int containerId, Inventory inventory, int rows) {
        this(menuType, containerId, inventory, new SimpleContainer(9 * rows), rows);
    }

    public static CrateMenu oneRow(int containerId, Inventory inventory) {
        return new CrateMenu(ModMenuType.CRATE_9x1.get(), containerId, inventory, 1);
    }

    public static CrateMenu twoRows(int containerId, Inventory inventory) {
        return new CrateMenu(ModMenuType.CRATE_9x2.get(), containerId, inventory, 2);
    }

    public static CrateMenu threeRows(int containerId, Inventory inventory) {
        return new CrateMenu(ModMenuType.CRATE_9x3.get(), containerId, inventory, 3);
    }

    public static CrateMenu fourRows(int containerId, Inventory inventory) {
        return new CrateMenu(ModMenuType.CRATE_9x4.get(), containerId, inventory, 4);
    }

    public static CrateMenu fiveRows(int containerId, Inventory inventory) {
        return new CrateMenu(ModMenuType.CRATE_9x5.get(), containerId, inventory, 5);
    }

    public static CrateMenu sixRows(int containerId, Inventory inventory) {
        return new CrateMenu(ModMenuType.CRATE_9x6.get(), containerId, inventory, 6);
    }

    public static CrateMenu threeRows(int containerId, Inventory inventory, Container container) {
        return new CrateMenu(ModMenuType.CRATE_9x3.get(), containerId, inventory, container, 3);
    }

    public static CrateMenu sixRows(int containerId, Inventory inventory, Container container) {
        return new CrateMenu(ModMenuType.CRATE_9x6.get(), containerId, inventory, container, 6);
    }

    public CrateMenu(MenuType<?> menuType, int containerId, Inventory inventory, Container container, int rows) {
        super(menuType, containerId);
        checkContainerSize(container, rows * 9);
        this.container = container;
        this.containerRows = rows;
        container.startOpen(inventory.player);

        int rowOffset = (this.containerRows - 4) * 18;

        for (int row = 0; row < this.containerRows; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(container, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 103 + row * 18 + rowOffset));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 161 + rowOffset));
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

            int containerSize = this.containerRows * 9;
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

    public Container getContainer() {
        return this.container;
    }

    public int getRowCount() {
        return this.containerRows;
    }
}