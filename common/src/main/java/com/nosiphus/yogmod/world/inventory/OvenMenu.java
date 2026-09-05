package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.crafting.RecipeType;

public class OvenMenu extends AbstractFurnaceMenu {

    public OvenMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(3), new SimpleContainerData(4));
    }

    public OvenMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuType.OVEN.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, containerId, inventory, container, data);
    }
}