package com.nosiphus.yogmod.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;

public class YogifierContainer implements Container, StackedContentsCompatible {
    private final NonNullList<ItemStack> items;
    private final int width;
    private final int height;
    private final AbstractContainerMenu menu;

    public YogifierContainer(AbstractContainerMenu abstractContainerMenu, int imageWidth, int imageHeight) {
        this.items = NonNullList.withSize(imageWidth * imageHeight, ItemStack.EMPTY);
        this.menu = abstractContainerMenu;
        this.width = imageWidth;
        this.height = imageHeight;
    }

    public int getContainerSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemStack : this.items) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public ItemStack getItem(int index) {
        return index >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(index);
    }

    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    public ItemStack removeItem(int int1, int int2) {
        ItemStack itemStack = ContainerHelper.removeItem(this.items, int1, int2);
        if (!itemStack.isEmpty()) {
            this.menu.slotsChanged(this);
        }
        return itemStack;
    }

    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
        this.menu.slotsChanged(this);
    }

    public void setChanged() {
    }

    public boolean stillValid(Player player) {
        return true;
    }

    public void clearContent() {
        this.items.clear();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void fillStackedContents(StackedContents stackedContainers) {
        for(ItemStack itemStack : this.items) {
            stackedContainers.accountSimpleStack(itemStack);
        }

    }

}
