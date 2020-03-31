package yogmod.blocks.slabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yogmod.blocks.base.SlabDoubleBase;

public class WoodSlabDouble extends SlabDoubleBase {

	public WoodSlabDouble(String name, Material material, BlockSlab half) {
		
		super(name, Material.ROCK, half);
		
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(10.0F);
		setSoundType(SoundType.WOOD);
		
	}
	
}
