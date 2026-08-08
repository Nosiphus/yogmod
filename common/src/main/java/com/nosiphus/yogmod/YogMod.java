package com.nosiphus.yogmod;

import com.nosiphus.yogmod.core.sink.SinkInteraction;
import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.inventory.ModMenuType;
import com.nosiphus.yogmod.world.item.ModItems;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeSerializer;
import com.nosiphus.yogmod.world.item.crafting.ModRecipeType;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YogMod {

    public static final String MOD_ID = "yogmod";
    public static final String MOD_NAME = "YogMod";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        ModBlocks.init();
        ModBlockEntityType.init();
        ModEntityType.init();
        ModItems.init();
        ModMenuType.init();
        ModRecipeSerializer.init();
        ModRecipeType.init();
        SinkInteraction.bootStrap();
    }

}