package com.nosiphus.yogmod.items.base;

import com.nosiphus.yogmod.Main;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel {

	public FoodBase(String name, int amount, float saturation, boolean isAnimalFood) {
		
		super(amount, saturation, isAnimalFood);
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModItems.ITEMS.add(this);
		
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerModel(this, 0);
		
	}

}
