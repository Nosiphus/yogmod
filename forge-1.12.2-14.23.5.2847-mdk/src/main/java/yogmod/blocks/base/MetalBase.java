package yogmod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yogmod.blocks.BlockBase;

public class MetalBase extends BlockBase {

	public MetalBase(String name) {
		
		super(name, Material.IRON);
		
		setHardness(5.0F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(30.0F);
		setSoundType(SoundType.METAL);
		
	}
	
}
