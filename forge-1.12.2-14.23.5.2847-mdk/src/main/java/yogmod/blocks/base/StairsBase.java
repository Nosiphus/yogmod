package yogmod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yogmod.Main;
import yogmod.init.ModBlocks;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

public class StairsBase extends BlockStairs implements IHasModel {

	private final Block modelBlock;
	private final IBlockState modelState;
	
	public StairsBase(IBlockState modelState, String name) {
		
		super(modelState);
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		this.modelBlock = modelState.getBlock();
		this.modelState = modelState;
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
}
