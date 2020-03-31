package yogmod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yogmod.init.ModBlocks;

public class CreativeTab {

	public static CreativeTabs YogTab = new CreativeTabs("YogMod")
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(ModBlocks.FLUORESCENT_PANEL));
		}
	};
	
}
