package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockYogWoodSlab extends BlockSlab {

    public static final String[] woodTypes = new String[]{"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"};

    public BlockYogWoodSlab(boolean isDouble) {
        super(isDouble, Material.wood);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return ModBlock.WOODEN_BRICKS.getIcon(side, metadata & 7);
    }

    public Item getItemDropped(int side, Random random, int metadata) {
        return Item.getItemFromBlock(ModBlock.WOODEN_BRICK_SLAB);
    }

    @Override
    protected ItemStack createStackedBlock(int metadata) {
        return new ItemStack(Item.getItemFromBlock(ModBlock.WOODEN_BRICK_SLAB), 2, metadata & 7);
    }

    public String func_150002_b(int metadata) {
        if (metadata < 0 || metadata >= woodTypes.length) {
            metadata = 0;
        }
        return "yogmod:" + woodTypes[metadata] + "_brick_slab";
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int posX, int posY, int posZ) {
        int meta = getDamageValue(world, posX, posY, posZ);
        return new ItemStack(ModBlock.WOODEN_BRICK_SLAB, 1, meta);
    }

    public int getDamageValue(World world, int posX, int posY, int posZ) {
        return super.getDamageValue(world, posX, posY, posZ);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (item != Item.getItemFromBlock(ModBlock.DOUBLE_WOODEN_BRICK_SLAB)) {
            for (int i = 0; i < woodTypes.length; ++i) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {}

}
