package com.nosiphus.yogmod.blocks.base;

import com.nosiphus.yogmod.Main;
import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class StairsBase extends BlockStairs implements IHasModel {

	public StairsBase(IBlockState modelState, String name) {
		
		super(modelState);
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		modelState.getBlock();
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
}
