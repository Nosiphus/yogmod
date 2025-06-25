package com.nosiphus.yogmod.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItem {

    public static Item PENCIL;

    public static void registerItems() {

        PENCIL = new Item().setUnlocalizedName("pencil").setTextureName("yogmod:pencil");
        GameRegistry.registerItem(PENCIL, PENCIL.getUnlocalizedName().substring(5));

    }

}
