package com.nosiphus.yogmod.blocks.stairs;

import com.nosiphus.yogmod.Main;
import com.nosiphus.yogmod.blocks.base.StairsBase;
import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class WoodStairs extends StairsBase {
	
	public WoodStairs(IBlockState modelState, String name) {
		
		super(modelState, name);
		
        setHardness(2.0F);
        setHarvestLevel("axe", 0);
        setLightLevel(0.0F);
        setLightOpacity(0);
        setResistance(10.0F);
        setSoundType(SoundType.WOOD);
		
	}
	
}