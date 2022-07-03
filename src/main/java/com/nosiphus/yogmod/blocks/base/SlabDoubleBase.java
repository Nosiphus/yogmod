package com.nosiphus.yogmod.blocks.base;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class SlabDoubleBase extends SlabBase
{
	public SlabDoubleBase(String name, Material material, BlockSlab half) 
	{
		super(name, material, half);
	}

	@Override
	public boolean isDouble() 
	{	
		return true;
	}
	
}