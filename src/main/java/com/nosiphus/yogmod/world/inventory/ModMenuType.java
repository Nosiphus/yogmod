package com.nosiphus.yogmod.world.inventory;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuType {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "yogmod");

    public static final RegistryObject<MenuType<CrateMenu>> GENERIC_9x1 = MENU_TYPES.register("generic_9x1", () -> new MenuType<>(CrateMenu::oneRow, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<CrateMenu>> GENERIC_9x2 = MENU_TYPES.register("generic_9x2", () -> new MenuType<>(CrateMenu::twoRows, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<CrateMenu>> GENERIC_9x3 = MENU_TYPES.register("generic_9x3", () -> new MenuType<>(CrateMenu::threeRows, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<CrateMenu>> GENERIC_9x4 = MENU_TYPES.register("generic_9x4", () -> new MenuType<>(CrateMenu::fourRows, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<CrateMenu>> GENERIC_9x5 = MENU_TYPES.register("generic_9x5", () -> new MenuType<>(CrateMenu::fiveRows, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<CrateMenu>> GENERIC_9x6 = MENU_TYPES.register("generic_9x6", () -> new MenuType<>(CrateMenu::sixRows, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<OvenMenu>> OVEN = MENU_TYPES.register("oven", () -> new MenuType<>(OvenMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<StorageCrateMenu>> STORAGE_CRATE = MENU_TYPES.register("storage_crate", () -> new MenuType<>(StorageCrateMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<YogifierMenu>> YOGIFIER = MENU_TYPES.register("yogifier", () -> new MenuType<>(YogifierMenu::new, FeatureFlags.DEFAULT_FLAGS));

}
