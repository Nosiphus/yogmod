package com.nosiphus.yogmod.blocks.base;

import com.nosiphus.yogmod.Main;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.util.IHasModel;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;

public class SlabHalfBase extends SlabBase implements IHasModel
{
	public SlabHalfBase(String name, Material material, BlockSlab half, BlockSlab doubleSlab)
	{
		super(name, material, half);
		
		ModItems.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}
	
	@Override
	public boolean isDouble() 
	{
		return false;
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}