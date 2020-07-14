package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.items.ItemBase;
import com.nosiphus.yogmod.items.base.*;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
	
	//Food
	public static final RegistryObject<Item> COFFEE = ITEMS.register("coffee", FoodBase::new);
	public static final RegistryObject<Item> HAMBURGER = ITEMS.register("hamburger", FoodBase::new);
	public static final RegistryObject<Item> JAFFA = ITEMS.register("jaffa", FoodBase::new);
	
	//Items
	public static final RegistryObject<Item> PENCIL = ITEMS.register("pencil", ItemBase::new);
	
}
