package com.nosiphus.yogmod.blocks.slabs;

import com.nosiphus.yogmod.blocks.base.SlabHalfBase;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class StoneSlabHalf extends SlabHalfBase {
	
	public StoneSlabHalf(String name, Material material, BlockSlab half, BlockSlab doubleSlab) {
		
		super(name, Material.ROCK, half, doubleSlab);
		
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(30.0F);
		setSoundType(SoundType.STONE);
		
	}

}
