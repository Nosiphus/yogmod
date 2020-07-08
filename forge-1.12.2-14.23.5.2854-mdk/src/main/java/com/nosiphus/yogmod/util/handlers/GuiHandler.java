package com.nosiphus.yogmod.util.handlers;

import com.nosiphus.yogmod.container.ContainerCrate;
import com.nosiphus.yogmod.gui.GuiCrate;
import com.nosiphus.yogmod.tileentity.TileEntityCrate;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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
