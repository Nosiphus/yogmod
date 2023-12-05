package com.nosiphus.yogmod.world.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ModArmorMaterials {

    public static final ArmorMaterial CONSTRUCTION = new ModArmorMaterial(
            "construction",
            7,
            new int[] { 1, 3, 5, 2 },
            25,
            SoundEvents.ARMOR_EQUIP_GOLD,
            0.0F,
            0.0F,
            () -> {
                return Ingredient.of(Items.GOLD_INGOT);
            }
    );

    public static final ArmorMaterial RIOT = new ModArmorMaterial(
            "riot",
            33,
            new int[] { 3, 6, 8, 3 },
            10,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.0F,
            0.0F,
            () -> {
                return Ingredient.of(Items.DIAMOND);
            }
    );

    public static final ArmorMaterial SUIT = new ModArmorMaterial(
            "suit",
            5,
            new int[] { 1, 2, 3, 1 },
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> {
                return Ingredient.of(Items.LEATHER);
            }
    );

}
