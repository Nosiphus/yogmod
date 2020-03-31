package yogmod.blocks.special;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSoulSand;
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

public class CautionTapeSpecial extends BlockSoulSand implements IHasModel {

	public CautionTapeSpecial(String name) {
		
		super();
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
		setHardness(0.5F);
		setHarvestLevel("shovel", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(2.5F);
		setSoundType(SoundType.SAND);
		
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
}