package com.nosiphus.yogmod.blocks.fence;

import com.nosiphus.yogmod.blocks.base.FenceBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class StoneFence extends FenceBase {

	public StoneFence(String name) {
		
		super(name, Material.ROCK, MapColor.STONE);
		
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(30.0F);
		setSoundType(SoundType.STONE);
		
	}
	
}