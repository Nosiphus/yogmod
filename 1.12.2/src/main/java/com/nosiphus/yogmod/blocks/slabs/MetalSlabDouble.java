package com.nosiphus.yogmod.blocks.slabs;

import com.nosiphus.yogmod.blocks.base.SlabDoubleBase;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MetalSlabDouble extends SlabDoubleBase {

	public MetalSlabDouble(String name, Material material, BlockSlab half) {
		
		super(name, Material.IRON, half);
		
		setHardness(5.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(30.0F);
		setSoundType(SoundType.METAL);
		
	}
	
}
