package yogmod.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import yogmod.render.RenderCrate;
import yogmod.tileentity.TileEntityCrate;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerModel(Item item, int metadata) 
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrate.class, new RenderCrate());
	}
}
