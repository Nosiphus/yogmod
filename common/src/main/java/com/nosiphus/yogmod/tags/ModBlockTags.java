package com.nosiphus.yogmod.tags;

import com.nosiphus.yogmod.YogMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModBlockTags {

    public static final TagKey<Block> WOODEN_BRICKS = create("wooden_bricks");

    private ModBlockTags() {
    }

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(YogMod.MOD_ID, name));
    }

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }

}