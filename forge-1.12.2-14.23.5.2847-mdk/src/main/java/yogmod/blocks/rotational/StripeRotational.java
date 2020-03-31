package yogmod.blocks.rotational;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yogmod.blocks.base.RotationalBase;

public class StripeRotational extends RotationalBase {
	
	public StripeRotational(String name) {
		
		super(name, Material.IRON);
		
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(0.8F);
		setHarvestLevel("shears", 0);
		setLightLevel(0.0F);
		setLightOpacity(16);
		setResistance(4.0F);
		setSoundType(SoundType.CLOTH);
		
	}
	
}