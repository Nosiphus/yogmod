package com.nosiphus.yogmod.world.entity;

import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.entity.item.PrimedDynamite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "yogmod");

    public static final RegistryObject<EntityType<PrimedDynamite>> DYNAMITE = ENTITY_TYPES.register("dynamite",
            () -> EntityType.Builder.<PrimedDynamite>of(PrimedDynamite::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .build(new ResourceLocation("yogmod", "dynamite").toString()));
    public static final RegistryObject<EntityType<ScrubberBot>> SCRUBBER_BOT = ENTITY_TYPES.register("scrubber_bot",
            () -> EntityType.Builder.<ScrubberBot>of(ScrubberBot::new, MobCategory.MISC)
                    .immuneTo(Blocks.POWDER_SNOW)
                    .sized(0.7F, 1.9F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation("yogmod", "scrubber_bot").toString()));

}
