package com.nosiphus.yogmod.world.entity.decoration;

import com.nosiphus.yogmod.platform.Services;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.function.Supplier;

public class ModPaintingVariants {

    public static void init() {

    }

    private static <T extends PaintingVariant> Supplier<T> register(String name, Supplier<T> paintingSupplier) {
        return Services.REGISTRY.registerPaintingVariant(name, paintingSupplier);
    }

    public static final Supplier<PaintingVariant> BIG_SCREEN = register("big_screen", () -> new PaintingVariant(64, 64));
    public static final Supplier<PaintingVariant> BLUE_CONTROL_PANEL = register("blue_control_panel", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> CIRCUITS = register("circuits", () -> new PaintingVariant(64, 32));
    public static final Supplier<PaintingVariant> CLOCK = register("clock", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> CONTROL_METER = register("control_meter", () -> new PaintingVariant(16, 32));
    public static final Supplier<PaintingVariant> CRATE = register("crate", () -> new PaintingVariant(32, 32));
    public static final Supplier<PaintingVariant> DOOR = register("door", () -> new PaintingVariant(32, 32));
    public static final Supplier<PaintingVariant> DRAWER = register("drawer", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> GEARS = register("gears", () -> new PaintingVariant(64, 48));
    public static final Supplier<PaintingVariant> GRAPH = register("graph", () -> new PaintingVariant(64, 64));
    public static final Supplier<PaintingVariant> GREEN_GRAPH = register("green_graph", () -> new PaintingVariant(32, 16));
    public static final Supplier<PaintingVariant> INFORMATION = register("information", () -> new PaintingVariant(32, 32));
    public static final Supplier<PaintingVariant> JUNK_SHELF = register("junk_shelf", () -> new PaintingVariant(32, 16));
    public static final Supplier<PaintingVariant> MOLECULE = register("molecule", () -> new PaintingVariant(32, 32));
    public static final Supplier<PaintingVariant> NOTICE = register("notice", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> ORANGE_GRAPH = register("orange_graph", () -> new PaintingVariant(32, 16));
    public static final Supplier<PaintingVariant> PANEL = register("panel", () -> new PaintingVariant(32, 16));
    public static final Supplier<PaintingVariant> PERIODIC_TABLE = register("periodic_table", () -> new PaintingVariant(64, 48));
    public static final Supplier<PaintingVariant> POSTBOARD = register("postboard", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> POTION_SHELF = register("potion_shelf", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> RED_CONTROL_PANEL = register("red_control_panel", () -> new PaintingVariant(16, 16));
    public static final Supplier<PaintingVariant> TOOL_SHELF = register("tool_shelf", () -> new PaintingVariant(32, 16));
    public static final Supplier<PaintingVariant> UNIVERSE = register("universe", () -> new PaintingVariant(64, 64));

}