package yogmod.blocks.special;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yogmod.Main;
import yogmod.init.ModBlocks;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

public class LeverSpecial extends BlockLever implements IHasModel {

	public LeverSpecial(String name) {
		
		super();
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(10.0F);
		setSoundType(SoundType.WOOD);
		
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
}