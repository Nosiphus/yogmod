package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.client.renderer.LeverRenderer;
import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLever;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class LeverBlock extends BlockLever {

    @SideOnly(Side.CLIENT)
    public IIcon leverBaseIcon;
    @SideOnly(Side.CLIENT)
    public IIcon leverHandleIcon;

    public LeverBlock(Material material) {
        super();
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.leverBaseIcon = register.registerIcon("yogmod:asphalt");
        this.leverHandleIcon = register.registerIcon("yogmod:lever");
    }

    @Override
    public int getRenderType() {
        return LeverRenderer.renderId;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return this.leverHandleIcon;
    }

}
