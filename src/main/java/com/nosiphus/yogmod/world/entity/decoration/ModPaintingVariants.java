package com.nosiphus.yogmod.world.entity.decoration;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintingVariants {

    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, "yogmod");

    public static final RegistryObject<PaintingVariant> DESERT = PAINTINGS.register("desert", () -> new PaintingVariant(16, 16));

}
