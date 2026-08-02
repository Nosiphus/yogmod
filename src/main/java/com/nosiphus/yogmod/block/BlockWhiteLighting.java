package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWhiteLighting extends BlockYogBase {

    @SideOnly(Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    public BlockWhiteLighting(Material material) {
        super(material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.iconSide = register.registerIcon("yogmod:white_lighting");
        this.iconTop = register.registerIcon("yogmod:white_lighting_end");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if(side == 0 || side == 1) {
            return iconTop;
        } else {
            return iconSide;
        }
    }
}
