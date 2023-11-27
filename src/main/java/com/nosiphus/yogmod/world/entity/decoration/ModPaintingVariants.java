package com.nosiphus.yogmod.world.entity.decoration;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintingVariants {

    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, "yogmod");

    public static final RegistryObject<PaintingVariant> BIG_SCREEN = PAINTINGS.register("big_screen", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> BLUE_CONTROL_PANEL = PAINTINGS.register("blue_control_panel", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> CLOCK = PAINTINGS.register("clock", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> CONTROL_METER = PAINTINGS.register("control_meter", () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> CRATE = PAINTINGS.register("crate", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> DOOR = PAINTINGS.register("door", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> DRAWER = PAINTINGS.register("drawer", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> GEARS = PAINTINGS.register("gears", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> GRAPH = PAINTINGS.register("graph", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> GREEN_GRAPH = PAINTINGS.register("green_graph", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> INFORMATION = PAINTINGS.register("information", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> JUNK_SHELF = PAINTINGS.register("junk_shelf", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> MOLECULE = PAINTINGS.register("molecule", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> NOTICE = PAINTINGS.register("notice", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> ORANGE_GRAPH = PAINTINGS.register("orange_graph", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> PANEL = PAINTINGS.register("panel", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> PERIODIC_TABLE = PAINTINGS.register("periodic_table", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> POSTBOARD = PAINTINGS.register("postboard", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> POTION_SHELF = PAINTINGS.register("potion_shelf", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> RED_CONTROL_PANEL = PAINTINGS.register("red_control_panel", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> TOOL_SHELF = PAINTINGS.register("tool_shelf", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> UNIVERSE = PAINTINGS.register("universe", () -> new PaintingVariant(64, 64));

}
