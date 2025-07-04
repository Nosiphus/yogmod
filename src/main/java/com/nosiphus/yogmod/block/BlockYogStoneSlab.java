package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockYogStoneSlab extends BlockSlab {

    public static final String[] stoneTypes = new String[]{"step", "asphalt", "brick", "raw_wood", "smooth_metal"};
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    public BlockYogStoneSlab(boolean isDouble) {
        super(isDouble, Material.rock);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        int k = metadata & 7;

        if (this.field_150004_a && (metadata & 8) != 0)
        {
            side = 1;
        }

        return k == 0 ? (side != 1 && side != 0 ? this.sideIcon : this.blockIcon) : (k == 1 ? ModBlock.ASPHALT.getBlockTextureFromSide(side) : (k == 2 ? ModBlock.BRICKS.getBlockTextureFromSide(side) : (k == 3 ? ModBlock.RAW_WOOD.getIcon(side, 0) : (k == 4 ? ModBlock.SMOOTH_METAL.getBlockTextureFromSide(1) : this.blockIcon))));
    }

    public Item getItemDropped(int side, Random random, int metadata) {
        return Item.getItemFromBlock(ModBlock.STEP_SLAB);
    }

    @Override
    protected ItemStack createStackedBlock(int metadata) {
        return new ItemStack(Item.getItemFromBlock(ModBlock.STEP_SLAB), 2, metadata & 7);
    }

    public String func_150002_b(int metadata) {
        if (metadata < 0 || metadata >= stoneTypes.length) {
            metadata = 0;
        }
        return "yogmod:" + stoneTypes[metadata] + "_slab";
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int posX, int posY, int posZ) {
        int meta = getDamageValue(world, posX, posY, posZ);
        return new ItemStack(ModBlock.STEP_SLAB, 1, meta);
    }

    public int getDamageValue(World world, int posX, int posY, int posZ) {
        return super.getDamageValue(world, posX, posY, posZ);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (item != Item.getItemFromBlock(ModBlock.DOUBLE_STEP_SLAB)) {
            for (int i = 0; i < stoneTypes.length; ++i) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon("yogmod:step");
        this.sideIcon = register.registerIcon("yogmod:step_slab");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

}
