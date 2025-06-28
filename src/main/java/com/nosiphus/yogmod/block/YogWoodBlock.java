package com.nosiphus.yogmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class YogWoodBlock extends YogBlock {

    public static final String[] woodTypes = new String[]{"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"};
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public YogWoodBlock(Material material) {
        super(material);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (metadata < 0 || metadata >= this.icons.length) {
            metadata = 0;
        }
        return this.icons[metadata];
    }

    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public int getDamageValue(World world, int posX, int posY, int posZ) {
        return world.getBlockMetadata(posX, posY, posZ);
    }

    @Override
    public int onBlockPlaced(World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ, int metadata) {
        return metadata;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < woodTypes.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.icons = new IIcon[woodTypes.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = register.registerIcon("yogmod:" + woodTypes[i] + "_bricks");
        }


    }
}
