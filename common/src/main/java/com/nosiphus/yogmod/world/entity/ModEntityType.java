package com.nosiphus.yogmod.world.entity;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.platform.Services;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.entity.item.PrimedDynamite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class ModEntityType {

    public static void init() {

    }

    private static <T extends EntityType<?>> Supplier<T> register(String name, Supplier<T> entityTypeSupplier) {
        return Services.REGISTRY.registerEntityType(name, entityTypeSupplier);
    }

    public static final Supplier<EntityType<PrimedDynamite>> DYNAMITE = register("dynamite",
            () -> EntityType.Builder.<PrimedDynamite>of(PrimedDynamite::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98F, 0.98F)
                    .noSave()
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .build(new ResourceLocation(YogMod.MOD_ID, "dynamite").toString()));

    public static final Supplier<EntityType<ScrubberBot>> SCRUBBER_BOT = register("scrubber_bot",
            () -> EntityType.Builder.of(ScrubberBot::new, MobCategory.MISC)
                    .immuneTo(Blocks.POWDER_SNOW)
                    .sized(0.7F, 1.9F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(YogMod.MOD_ID, "scrubber_bot").toString()));

}