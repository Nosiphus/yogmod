package com.nosiphus.yogmod.item;

import com.nosiphus.yogmod.block.YogWoodSlabBlock;
import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItem {

    public static Item PENCIL;

    public static void registerItems() {

        PENCIL = new Item().setUnlocalizedName("pencil").setTextureName("yogmod:pencil").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerItem(PENCIL, PENCIL.getUnlocalizedName().substring(5));

    }

}
