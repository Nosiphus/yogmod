package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuType {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "yogmod");

    public static final RegistryObject<MenuType<OvenMenu>> OVEN = registerMenuType((ID, inventory, extraData) -> new OvenMenu(ID, inventory), "oven_menu");
    public static final RegistryObject<MenuType<YogifierMenu>> YOGIFIER = registerMenuType((ID, inventory, extraData) -> new YogifierMenu(ID, inventory), "yogifier_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

}
