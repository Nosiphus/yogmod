package com.nosiphus.yogmod.world.entity;

import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, "yogmod");

    /*
    public static final Supplier<EntityType<PrimedDynamite>> DYNAMITE = ENTITY_TYPES.register("dynamite",
            () -> EntityType.Builder.<PrimedDynamite>of(PrimedDynamite::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .build(ResourceLocation.fromNamespaceAndPath("yogmod", "dynamite").toString()));

     */
    public static final Supplier<EntityType<ScrubberBot>> SCRUBBER_BOT = ENTITY_TYPES.register("scrubber_bot",
            () -> EntityType.Builder.<ScrubberBot>of(ScrubberBot::new, MobCategory.MISC)
                    .immuneTo(Blocks.POWDER_SNOW)
                    .sized(0.7F, 1.9F)
                    .clientTrackingRange(8)
                    .build(ResourceLocation.fromNamespaceAndPath("yogmod", "scrubber_bot").toString()));

}
