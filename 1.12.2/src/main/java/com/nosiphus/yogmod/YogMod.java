package com.nosiphus.yogmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.nosiphus.yogmod.proxy.CommonProxy;
import com.nosiphus.yogmod.util.Reference;
import com.nosiphus.yogmod.util.handlers.RegistryHandler;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class YogMod
{

    @Instance
    public static YogMod instance;

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