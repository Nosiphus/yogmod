package com.nosiphus.yogmod.blocks.base;

import com.nosiphus.yogmod.blocks.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WoodBase extends BlockBase {

	public WoodBase(String name) {
		
		super(name, Material.WOOD);
		
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(10.0F);
		setSoundType(SoundType.WOOD);
		
	}
	
}