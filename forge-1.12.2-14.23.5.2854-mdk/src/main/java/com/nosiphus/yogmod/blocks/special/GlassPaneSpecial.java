package com.nosiphus.yogmod.blocks.special;

import com.nosiphus.yogmod.Main;
import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class GlassPaneSpecial extends BlockPane implements IHasModel {

	public GlassPaneSpecial(String name) {
		
		super(Material.GLASS, false);
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
		setHardness(0.3F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(1.5F);
		setSoundType(SoundType.GLASS);
		
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
}