package com.nosiphus.yogmod.tags;

import com.nosiphus.yogmod.YogMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {

    public static final TagKey<Item> WOODEN_BRICKS = bind("wooden_bricks");

    private ModItemTags() {
    }

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(YogMod.MOD_ID, name));
    }

    public static TagKey<Item> create(final ResourceLocation name) {
        return TagKey.create(Registries.ITEM, name);
    }

}
