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

    public OvenMenu(int ID, Inventory inventory) {
        this(ID, inventory, new SimpleContainer(3), new SimpleContainerData(4));
    }

    public OvenMenu(int ID, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuType.OVEN_MENU.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, ID, inventory, container, data);
    }

}
