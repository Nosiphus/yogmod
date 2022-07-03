package com.nosiphus.yogmod.blocks.rotational;

import com.nosiphus.yogmod.blocks.base.RotationalBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class StripeRotational extends RotationalBase {
	
	public StripeRotational(String name) {
		
		super(name, Material.IRON);
		
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(0.8F);
		setHarvestLevel("shears", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(4.0F);
		setSoundType(SoundType.CLOTH);
		
	}
	
}
