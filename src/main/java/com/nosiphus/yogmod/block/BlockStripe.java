package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockStripe extends BlockRotatedPillar {

    @SideOnly(Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    public BlockStripe(Material material) {
        super(material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.iconSide = register.registerIcon("yogmod:stripe");
        this.iconTop = register.registerIcon("yogmod:stripe_end");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        int k = metadata & 12;
        return k == 0 && (side == 1 || side == 0) ? this.iconTop : k == 4 && (side == 5 || side == 4) ? this.iconTop : k == 8 && (side == 2 || side == 3) ? this.iconTop : this.iconSide;
    }

    @Override
    protected IIcon getSideIcon(int metadata) {
        return iconSide;
    }

}
