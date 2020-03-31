package yogmod.blocks.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yogmod.init.ModBlocks;
import yogmod.tabs.CreativeTab;
import yogmod.util.Reference;

public abstract class SlabBase extends BlockSlab
{

	public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);
	
	public SlabBase(String name, Material material, BlockSlab half) 
	{
		super(material);
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		this.useNeighborBrightness = !this.isDouble();
		
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		if(!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		setDefaultState(state);
		
		ModBlocks.BLOCKS.add(this);
	}
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state, BlockSlab half) 
	{
		return new ItemStack(half);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune, BlockSlab half)
	{
		return Item.getItemFromBlock(half);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		if(!this.isDouble()) state = state.withProperty(HALF, ((meta&8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
		return state;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int meta = 0;
		if(!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) meta |= 8;
		return meta;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		if(!this.isDouble()) return new BlockStateContainer(this, new IProperty[] {VARIANT,HALF});
		else return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	@Override
	public String getUnlocalizedName(int meta) 
	{
		return super.getUnlocalizedName();
	}
	
	@Override
	public IProperty<?> getVariantProperty()
	{
		return VARIANT;
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) 
	{
		return Variant.DEFAULT;
	}
	
	public static enum Variant implements IStringSerializable
	{
		DEFAULT;
		
		@Override
		public String getName()
		{
			return "default";
		}
	}
}