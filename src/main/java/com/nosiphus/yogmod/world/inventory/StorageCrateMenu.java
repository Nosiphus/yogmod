package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class StorageCrateMenu extends AbstractContainerMenu {

    private static final int CONTAINER_SIZE = 27;
    private final Container container;

    public StorageCrateMenu(int ID, Inventory inventory) {
        this(ID, inventory, new SimpleContainer(27));
    }

    public StorageCrateMenu(int ID, Inventory inventory, Container container) {
        super(ModMenuType.STORAGE_CRATE.get(), ID);
        checkContainerSize(container, 27);
        this.container = container;
        container.startOpen(inventory.player);
        boolean $$3 = true;
        boolean $$4 = true;

        int $$9;
        int $$8;
        for($$9 = 0; $$9 < 3; ++$$9) {
            for($$8 = 0; $$8 < 9; ++$$8) {
                this.addSlot(new StorageCrateSlot(container, $$8 + $$9 * 9, 8 + $$8 * 18, 18 + $$9 * 18));
            }
        }

        for($$9 = 0; $$9 < 3; ++$$9) {
            for($$8 = 0; $$8 < 9; ++$$8) {
                this.addSlot(new Slot(inventory, $$8 + $$9 * 9 + 9, 8 + $$8 * 18, 86 + $$9 * 18));
            }
        }

        for($$9 = 0; $$9 < 9; ++$$9) {
            this.addSlot(new Slot(inventory, $$9, 8 + $$9 * 18, 144));
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
            if (int1 < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemStack1, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, 0, this.container.getContainerSize(), false)) {
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

}
