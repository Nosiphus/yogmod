package yogmod.blocks.stairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yogmod.Main;
import yogmod.blocks.base.StairsBase;
import yogmod.init.ModBlocks;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

public class WoodStairs extends StairsBase {
	
	public WoodStairs(IBlockState modelState, String name) {
		
		super(modelState, name);
		
        setHardness(2.0F);
        setHarvestLevel("axe", 0);
        setLightLevel(0.0F);
        setLightOpacity(0);
        setResistance(10.0F);
        setSoundType(SoundType.WOOD);
		
	}
	
}