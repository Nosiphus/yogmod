package com.nosiphus.yogmod.blocks.base;

import java.util.Random;

import com.nosiphus.yogmod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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