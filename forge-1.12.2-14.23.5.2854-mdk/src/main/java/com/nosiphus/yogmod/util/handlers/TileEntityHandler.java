package com.nosiphus.yogmod.util.handlers;

import com.nosiphus.yogmod.tileentity.TileEntityCrate;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCrate.class, new ResourceLocation(Reference.MODID + ":crate"));
	}
}
