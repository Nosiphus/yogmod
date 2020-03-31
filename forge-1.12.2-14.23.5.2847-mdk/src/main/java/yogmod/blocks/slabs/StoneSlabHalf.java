package yogmod.blocks.slabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemSlab;
import yogmod.blocks.base.SlabHalfBase;
import yogmod.init.ModItems;

public class StoneSlabHalf extends SlabHalfBase {
	
	public StoneSlabHalf(String name, Material material, BlockSlab half, BlockSlab doubleSlab) {
		
		super(name, Material.ROCK, half, doubleSlab);
		
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(30.0F);
		setSoundType(SoundType.STONE);
		
	}

}
