package yogmod.blocks.slabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yogmod.blocks.base.SlabDoubleBase;

public class StoneSlabDouble extends SlabDoubleBase {

	public StoneSlabDouble(String name, Material material, BlockSlab half) {
		
		super(name, Material.ROCK, half);
		
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(30.0F);
		setSoundType(SoundType.STONE);
		
	}
	
}
