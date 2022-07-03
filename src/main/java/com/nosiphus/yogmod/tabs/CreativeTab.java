package com.nosiphus.yogmod.tabs;

import com.nosiphus.yogmod.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab {

	public static CreativeTabs YogTab = new CreativeTabs("YogMod")
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(ModBlocks.FLUORESCENT_PANEL));
		}
	};
	
}
