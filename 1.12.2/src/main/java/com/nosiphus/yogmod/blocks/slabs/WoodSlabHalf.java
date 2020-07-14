package com.nosiphus.yogmod.blocks.slabs;

import com.nosiphus.yogmod.blocks.base.SlabHalfBase;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WoodSlabHalf extends SlabHalfBase {
	
	public WoodSlabHalf(String name, Material material, BlockSlab half, BlockSlab doubleSlab) {
		
		super(name, Material.ROCK, half, doubleSlab);
		
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(10.0F);
		setSoundType(SoundType.WOOD);
		
	}

}
