package com.nosiphus.yogmod.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> WOODEN_BRICKS = tag("wooden_bricks");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("yogmod", name));
        }

    }

    public static class Blocks {

        public static final TagKey<Block> WOODEN_BRICKS = tag("wooden_bricks");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("yogmod", name));
        }

    }


}
