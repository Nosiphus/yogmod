package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DispenserMenu extends AbstractContainerMenu {
    private static final int SLOT_COUNT = 9;
    private static final int INV_SLOT_START = 9;
    private static final int INV_SLOT_END = 36;
    private static final int USE_ROW_SLOT_START = 36;
    private static final int USE_ROW_SLOT_END = 45;
    private final Container dispenser;

    public DispenserMenu(int ID, Inventory inventory) {
        this(ID, inventory, new SimpleContainer(9));
    }

    public DispenserMenu(int ID, Inventory inventory, Container container) {
        super(ModMenuType.DISPENSER.get(), ID);
        checkContainerSize(container, 9);
        this.dispenser = container;
        container.startOpen(inventory.player);

        int $$7;
        int $$6;
        for($$7 = 0; $$7 < 3; ++$$7) {
            for($$6 = 0; $$6 < 3; ++$$6) {
                this.addSlot(new Slot(container, $$6 + $$7 * 3, 62 + $$6 * 18, 17 + $$7 * 18));
            }
        }

        for($$7 = 0; $$7 < 3; ++$$7) {
            for($$6 = 0; $$6 < 9; ++$$6) {
                this.addSlot(new Slot(inventory, $$6 + $$7 * 9 + 9, 8 + $$6 * 18, 84 + $$7 * 18));
            }
        }

        for($$7 = 0; $$7 < 9; ++$$7) {
            this.addSlot(new Slot(inventory, $$7, 8 + $$7 * 18, 142));
        }

    }

    public boolean stillValid(Player player) {
        return this.dispenser.stillValid(player);
    }

    public ItemStack quickMoveStack(Player player, int int1) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(int1);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if (int1 < 9) {
                if (!this.moveItemStackTo(itemStack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack);
        }

        return stack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.dispenser.stopOpen(player);
    }


}
