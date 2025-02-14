package com.nosiphus.yogmod.world.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> SUIT = register("suit", Util.make(new EnumMap<>(ArmorItem.Type.class), protection -> {
        protection.put(ArmorItem.Type.BOOTS, 1);
        protection.put(ArmorItem.Type.LEGGINGS, 2);
        protection.put(ArmorItem.Type.CHESTPLATE, 3);
        protection.put(ArmorItem.Type.HELMET, 1);
        protection.put(ArmorItem.Type.BODY, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.LEATHER));
    public static final Holder<ArmorMaterial> CONSTRUCTION = register("construction", Util.make(new EnumMap<>(ArmorItem.Type.class), protection -> {
        protection.put(ArmorItem.Type.BOOTS, 1);
        protection.put(ArmorItem.Type.LEGGINGS, 3);
        protection.put(ArmorItem.Type.CHESTPLATE, 5);
        protection.put(ArmorItem.Type.HELMET, 2);
        protection.put(ArmorItem.Type.BODY, 7);
    }), 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> Ingredient.of(Items.GOLD_INGOT));
    public static final Holder<ArmorMaterial> RIOT = register("riot", Util.make(new EnumMap<>(ArmorItem.Type.class), protection -> {
        protection.put(ArmorItem.Type.BOOTS, 3);
        protection.put(ArmorItem.Type.LEGGINGS, 6);
        protection.put(ArmorItem.Type.CHESTPLATE, 8);
        protection.put(ArmorItem.Type.HELMET, 3);
        protection.put(ArmorItem.Type.BODY, 11);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(Items.DIAMOND));

    public static Holder<ArmorMaterial> bootstrap(Registry<ArmorMaterial> registry) {
        return SUIT;
    }

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("yogmod", name)));
        return register(name, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngridient,
            List<ArmorMaterial.Layer> layers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, defense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.withDefaultNamespace(name),
                new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance)
        );
    }

}
