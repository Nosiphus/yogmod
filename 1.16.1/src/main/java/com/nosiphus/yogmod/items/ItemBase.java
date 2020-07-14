package com.nosiphus.yogmod.items;

import com.nosiphus.yogmod.tabs.CreativeTab;

import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase() {
		
		super(new Item.Properties()
		
				.group(CreativeTab.YOGTAB)
				
		);
		
	}
	
}
