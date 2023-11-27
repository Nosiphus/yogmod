package com.nosiphus.yogmod.world.entity.decoration;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintingVariants {

    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, "yogmod");

    public static final RegistryObject<PaintingVariant> BIG_SCREEN = PAINTINGS.register("big_screen", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> CLOCK = PAINTINGS.register("clock", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> CONTROL_METER = PAINTINGS.register("control_meter", () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> CRATE = PAINTINGS.register("crate", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> DOOR = PAINTINGS.register("door", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> GEARS = PAINTINGS.register("gears", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> GREEN_GRAPH = PAINTINGS.register("green_graph", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> MOLECULE = PAINTINGS.register("molecule", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> ORANGE_GRAPH = PAINTINGS.register("orange_graph", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> PERIODIC_TABLE = PAINTINGS.register("periodic_table", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> POSTBOARD = PAINTINGS.register("postboard", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> TOOL_SHELF = PAINTINGS.register("tool_shelf", () -> new PaintingVariant(32, 16));

}
