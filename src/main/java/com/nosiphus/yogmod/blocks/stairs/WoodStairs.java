package com.nosiphus.yogmod.blocks.stairs;

import com.nosiphus.yogmod.blocks.base.StairsBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

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