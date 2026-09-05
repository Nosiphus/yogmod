package com.nosiphus.yogmod.world.inventory;

import com.nosiphus.yogmod.platform.Services;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public class ModMenuType {

    public static void init() {

    }

    private static <T extends MenuType<?>> Supplier<T> register(String name, Supplier<T> menuTypeSupplier) {
        return Services.REGISTRY.registerMenuType(name, menuTypeSupplier);
    }

    public static final Supplier<MenuType<CrateMenu>> CRATE_9x1 = register("crate_9x1", () -> new MenuType<>(CrateMenu::oneRow, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<CrateMenu>> CRATE_9x2 = register("crate_9x2", () -> new MenuType<>(CrateMenu::twoRows, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<CrateMenu>> CRATE_9x3 = register("crate_9x3", () -> new MenuType<>(CrateMenu::threeRows, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<CrateMenu>> CRATE_9x4 = register("crate_9x4", () -> new MenuType<>(CrateMenu::fourRows, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<CrateMenu>> CRATE_9x5 = register("crate_9x5", () -> new MenuType<>(CrateMenu::fiveRows, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<CrateMenu>> CRATE_9x6 = register("crate_9x6", () -> new MenuType<>(CrateMenu::sixRows, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<DispenserMenu>> DISPENSER = register("dispenser", () -> new MenuType<>(DispenserMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<OvenMenu>> OVEN = register("oven", () -> new MenuType<>(OvenMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<StorageCrateMenu>> STORAGE_CRATE = register("storage_crate", () -> new MenuType<>(StorageCrateMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<YogifierMenu>> YOGIFIER = register("yogifier", () -> new MenuType<>(YogifierMenu::new, FeatureFlags.DEFAULT_FLAGS));

}