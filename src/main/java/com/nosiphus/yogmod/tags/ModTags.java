package com.nosiphus.yogmod.tags;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> WOODEN_BRICKS = bind("wooden_bricks");

        private static TagKey<Item> bind(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation("yogmod", name));
        }

    }

    public static class Blocks {

        public static final TagKey<Block> WOODEN_BRICKS = create("wooden_bricks");

        private static TagKey<Block> create(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation("yogmod", name));
        }

    }


}
