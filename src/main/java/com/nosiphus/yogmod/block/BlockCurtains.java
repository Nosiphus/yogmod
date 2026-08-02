package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockCurtains extends BlockVine {

    public BlockCurtains(Material material) {
        super();
        this.setTickRandomly(false);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int color) {
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess access, int posX, int posY, int posZ) {
        return 0xFFFFFF;
    }

}
