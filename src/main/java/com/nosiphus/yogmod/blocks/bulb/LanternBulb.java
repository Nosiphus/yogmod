package com.nosiphus.yogmod.blocks.bulb;

import com.nosiphus.yogmod.blocks.base.BulbBase;
import net.minecraft.block.SoundType;

public class LanternBulb extends BulbBase {

	public LanternBulb(String name) {
		
		super(name);
		
		setHardness(0.5F);
		setHarvestLevel("hand", 0);
		setLightLevel(1.0F);
		setLightOpacity(0);
		setResistance(0.5F);
		setSoundType(SoundType.GLASS);
		
	}
	
}