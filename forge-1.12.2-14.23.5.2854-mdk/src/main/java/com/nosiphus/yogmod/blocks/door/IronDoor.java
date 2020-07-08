package com.nosiphus.yogmod.blocks.door;

import com.nosiphus.yogmod.blocks.base.DoorBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class IronDoor extends DoorBase {

	public IronDoor(String name) {
		
		super(name, Material.IRON, MapColor.IRON);
		
		setHardness(5.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(30.0F);
		setSoundType(SoundType.METAL);
		
	}
	
}