package yogmod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yogmod.blocks.BlockBase;

public class LightingBase extends BlockBase {

	public LightingBase(String name) {
		
		super(name, Material.IRON);
		
		setHardness(0.3F);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(1.0F);
		setLightOpacity(0);
		setResistance(1.5F);
		setSoundType(SoundType.GLASS);
		
	}
	
}
