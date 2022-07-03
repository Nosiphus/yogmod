package com.nosiphus.yogmod.blocks.fence;

import com.nosiphus.yogmod.blocks.base.FenceBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class WoodFence extends FenceBase {

	public WoodFence(String name) {
		
		super(name, Material.WOOD, MapColor.WOOD);
		
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(10.0F);
		setSoundType(SoundType.WOOD);
		
	}
	
}