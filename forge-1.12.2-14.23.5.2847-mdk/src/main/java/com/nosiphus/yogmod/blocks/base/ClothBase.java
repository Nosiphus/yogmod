package com.nosiphus.yogmod.blocks.base;

import com.nosiphus.yogmod.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ClothBase extends BlockBase {
	
	public ClothBase(String name) {
		
		super(name, Material.CLOTH);
		
		setHardness(0.8F);
		setHarvestLevel("shears", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(4.0F);
		setSoundType(SoundType.CLOTH);
		
	}
	
}