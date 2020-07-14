package com.nosiphus.yogmod.blocks.base;

import com.nosiphus.yogmod.blocks.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ClothBase extends BlockBase {
	
	public ClothBase(String name) {
		
		super(name, Material.CLOTH);
		
		setHardness(0.8F);
		setHarvestLevel("shears", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(4.0F);
		setSoundType(SoundType.CLOTH);
		
	}
	
}