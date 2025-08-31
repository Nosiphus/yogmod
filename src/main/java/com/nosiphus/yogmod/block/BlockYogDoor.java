package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.item.ModItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockYogDoor extends BlockDoor {

    @SideOnly(Side.CLIENT)
    private IIcon[] topDoor;
    @SideOnly(Side.CLIENT)
    private IIcon[] bottomDoor;

    public BlockYogDoor(Material material) {
        super(material);
        float f = 0.5F;
        float f1 = 1.0F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.bottomDoor[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int metadata)
    {
        if (metadata != 1 && metadata != 0)
        {
            int i1 = this.func_150012_g(access, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;

            if (flag)
            {
                if (j1 == 0 && metadata == 2)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && metadata == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && metadata == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && metadata == 4)
                {
                    flag1 = !flag1;
                }
            }
            else
            {
                if (j1 == 0 && metadata == 5)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 1 && metadata == 3)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 2 && metadata == 4)
                {
                    flag1 = !flag1;
                }
                else if (j1 == 3 && metadata == 2)
                {
                    flag1 = !flag1;
                }

                if ((i1 & 16) != 0)
                {
                    flag1 = !flag1;
                }
            }

            return flag2 ? this.topDoor[flag1?1:0] : this.bottomDoor[flag1?1:0];
        }
        else
        {
            return this.bottomDoor[0];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.topDoor = new IIcon[2];
        this.bottomDoor = new IIcon[2];
        this.topDoor[0] = register.registerIcon(this.getTextureName() + "_top");
        this.bottomDoor[0] = register.registerIcon(this.getTextureName() + "_bottom");
        this.topDoor[1] = new IconFlipped(this.topDoor[0], true, false);
        this.bottomDoor[1] = new IconFlipped(this.bottomDoor[0], true, false);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int i) {
        return (metadata & 8) != 0 ? null : (this.blockMaterial == Material.iron ? ModItem.IRON_DOOR : ModItem.WOODEN_DOOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return this.blockMaterial == Material.iron ? ModItem.IRON_DOOR : ModItem.WOODEN_DOOR;
    }

}
