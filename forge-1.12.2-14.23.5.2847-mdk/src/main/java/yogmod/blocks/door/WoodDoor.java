package yogmod.blocks.door;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import yogmod.blocks.base.DoorBase;

public class WoodDoor extends DoorBase {

	public WoodDoor(String name) {
		
		super(name, Material.WOOD, MapColor.WOOD);
		
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(0.0F);
		setLightOpacity(0);
		setResistance(10.0F);
		setSoundType(SoundType.WOOD);
		
	}
	
}