package com.nosiphus.yogmod.blocks;

import com.nosiphus.yogmod.tabs.CreativeTab;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockBase extends Block {

	public BlockBase() {
		
		super(new Block.Properties(material, null)
		
				.group(CreativeTab.YOGTAB)
				
		);
		
	}
	
}
