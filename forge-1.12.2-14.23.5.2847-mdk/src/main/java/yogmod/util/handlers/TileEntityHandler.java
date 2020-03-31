package yogmod.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import yogmod.tileentity.TileEntityCrate;
import yogmod.util.Reference;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCrate.class, new ResourceLocation(Reference.MODID + ":crate"));
	}
}
