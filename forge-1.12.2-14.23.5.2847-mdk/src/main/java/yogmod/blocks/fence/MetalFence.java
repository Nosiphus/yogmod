package yogmod.blocks.fence;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yogmod.Main;
import yogmod.blocks.base.FenceBase;
import yogmod.init.ModBlocks;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

public class MetalFence extends FenceBase {

	public MetalFence(String name) {
		
		super(name, Material.IRON, MapColor.IRON);
		
		setHardness(5.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(30.0F);
		setSoundType(SoundType.METAL);
		
	}
	
}