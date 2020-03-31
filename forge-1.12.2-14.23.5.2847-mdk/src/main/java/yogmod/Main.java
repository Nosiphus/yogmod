package yogmod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import yogmod.proxy.ClientProxy;
import yogmod.proxy.CommonProxy;
import yogmod.util.Reference;
import yogmod.util.handlers.RegistryHandler;

import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    	RegistryHandler.preInitRegistries(event);
    	
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    	RegistryHandler.initRegistries(event);
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    	RegistryHandler.postInitRegistries(event);
    	
    }
}
