package yogmod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yogmod.blocks.BlockBase;

public class StoneBase extends BlockBase {

	public StoneBase(String name) {
		
		super(name, Material.ROCK);
		
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(30.0F);
		setSoundType(SoundType.STONE);
		
	}
	
}