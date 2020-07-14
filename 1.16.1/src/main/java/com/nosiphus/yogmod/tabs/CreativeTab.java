package com.nosiphus.yogmod.tabs;

import com.nosiphus.yogmod.init.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTab {

	public static final ItemGroup YOGTAB = new ItemGroup("YogMod") {
		
		@Override
		public ItemStack createIcon() {
			
			return new ItemStack(ModItems.PENCIL.get());
			
		}
		
	};
	
}
