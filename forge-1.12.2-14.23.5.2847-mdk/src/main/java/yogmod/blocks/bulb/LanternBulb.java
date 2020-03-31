package yogmod.blocks.bulb;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yogmod.Main;
import yogmod.blocks.base.BulbBase;
import yogmod.init.ModBlocks;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

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