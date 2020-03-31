package yogmod.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import yogmod.container.ContainerCrate;
import yogmod.gui.GuiCrate;
import yogmod.tileentity.TileEntityCrate;
import yogmod.util.Reference;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_CRATE) return new ContainerCrate(player.inventory, (TileEntityCrate)world.getTileEntity(new BlockPos(x,y,z)), player);
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_CRATE) return new GuiCrate(player.inventory, (TileEntityCrate)world.getTileEntity(new BlockPos(x,y,z)), player);
		return null;
	}
}
