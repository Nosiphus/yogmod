package com.nosiphus.yogmod.items;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModItems.ITEMS.add(this);
		
	}
	
	@Override
	public void registerModels() 
	{
		YogMod.proxy.registerModel(this, 0);
	}
	
}
