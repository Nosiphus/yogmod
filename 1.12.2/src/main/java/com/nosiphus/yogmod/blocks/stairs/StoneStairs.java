package com.nosiphus.yogmod.blocks.stairs;

import com.nosiphus.yogmod.blocks.base.StairsBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class StoneStairs extends StairsBase {
	
	public StoneStairs(IBlockState modelState, String name) {
		
		super(modelState, name);
		
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(30.0F);
		setSoundType(SoundType.STONE);
		
	}
	
}