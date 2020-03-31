package yogmod.blocks.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yogmod.Main;
import yogmod.init.ModItems;
import yogmod.util.IHasModel;

public class SlabHalfBase extends SlabBase implements IHasModel
{
	public SlabHalfBase(String name, Material material, BlockSlab half, BlockSlab doubleSlab)
	{
		super(name, material, half);
		
		ModItems.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}
	
	@Override
	public boolean isDouble() 
	{
		return false;
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}