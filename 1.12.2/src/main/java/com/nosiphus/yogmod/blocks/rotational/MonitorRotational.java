package com.nosiphus.yogmod.blocks.rotational;

import com.nosiphus.yogmod.blocks.base.RotationalBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class MonitorRotational extends RotationalBase {
	
	public MonitorRotational(String name) {
		
		super(name, Material.IRON);
		
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(0.3F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(1.5F);
		setSoundType(SoundType.GLASS);
		
	}
	
}
