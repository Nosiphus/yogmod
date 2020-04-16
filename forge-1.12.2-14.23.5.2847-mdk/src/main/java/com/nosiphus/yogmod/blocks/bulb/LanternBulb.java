package com.nosiphus.yogmod.blocks.bulb;

import com.nosiphus.yogmod.Main;
import com.nosiphus.yogmod.blocks.base.BulbBase;
import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.init.ModItems;
import com.nosiphus.yogmod.tabs.CreativeTab;
import com.nosiphus.yogmod.util.IHasModel;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

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