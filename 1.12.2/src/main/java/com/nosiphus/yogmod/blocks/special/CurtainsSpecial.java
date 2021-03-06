package com.nosiphus.yogmod.blocks.special;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class CurtainsSpecial extends BlockLadder implements IHasModel {

	public CurtainsSpecial(String name) {
		
		super();
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
		setHardness(0.2F);
		setHarvestLevel("shears", 0);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(2.0F);
		setSoundType(SoundType.CLOTH);
		
	}
	
	@Override
	public void registerModels() 
	{
		YogMod.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
}