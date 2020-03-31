package yogmod.blocks.base;

import net.minecraft.block.BlockTorch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yogmod.Main;
import yogmod.init.ModBlocks;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

public class BulbBase extends BlockTorch implements IHasModel {

	public BulbBase(String name) {
		
		super();
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
		
	}
	
}