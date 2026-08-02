package com.nosiphus.yogmod.item;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;

public class ModItem {

    public static ItemDoor IRON_DOOR;
    public static ItemDoor WOODEN_DOOR;
    public static Item PENCIL;

    public static void registerItems() {

        IRON_DOOR = (ItemDoor) new ItemYogDoor(Material.iron).setUnlocalizedName("iron_door_item").setTextureName("yogmod:iron_door").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerItem(IRON_DOOR, IRON_DOOR.getUnlocalizedName().substring(5));
        WOODEN_DOOR = (ItemDoor) new ItemYogDoor(Material.wood).setUnlocalizedName("wooden_door_item").setTextureName("yogmod:wooden_door").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerItem(WOODEN_DOOR, WOODEN_DOOR.getUnlocalizedName().substring(5));
        PENCIL = new Item(8000).setUnlocalizedName("pencil").setTextureName("yogmod:pencil").setCreativeTab(ModCreativeTabs.YogTab);
        GameRegistry.registerItem(PENCIL, PENCIL.getUnlocalizedName().substring(5));

    }

}
