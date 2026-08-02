package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.List;

public class BlockColoredYogBase extends BlockYogBase {

    public static final String[] colorNames = new String[]{"white_plastic", "orange_plastic", "magenta_plastic", "light_blue_plastic", "gold_filgaree", "lime_plastic", "pink_stucco", "gray_stucco", "light_gray_stucco", "cyan_plastic", "violet_velvet", "blue_plastic", "brown_stucco", "green_plastic", "red_plastic", "black_marble"};
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public BlockColoredYogBase(int id, Material material) {
        super(id, material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata) {
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
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < colorNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IconRegister register) {
        this.icons = new Icon[colorNames.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = register.registerIcon("yogmod:" + colorNames[i]);
        }

    }

}
