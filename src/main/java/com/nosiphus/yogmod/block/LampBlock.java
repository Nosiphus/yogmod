package com.nosiphus.yogmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class LampBlock extends Block {

    private final boolean isIlluminated;

    public LampBlock(boolean isIlluminated) {
        super(Material.redstoneLight);
        this.isIlluminated = isIlluminated;

        if (isIlluminated) {
            this.setLightLevel(1.0F);
        }
    }

    public void onBlockAdded(World world, int posX, int posY, int posZ)
    {
        if (!world.isRemote)
        {
            if (this.isIlluminated && !world.isBlockIndirectlyGettingPowered(posX, posY, posZ))
            {
                world.scheduleBlockUpdate(posX, posY, posZ, this, 4);
            }
            else if (!this.isIlluminated && world.isBlockIndirectlyGettingPowered(posX, posY, posZ))
            {
                world.setBlock(posX, posY, posZ, ModBlock.LIT_LAMP, 0, 2);
            }
        }
    }

    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block)
    {
        if (!world.isRemote)
        {
            if (this.isIlluminated && !world.isBlockIndirectlyGettingPowered(posX, posY, posZ))
            {
                world.scheduleBlockUpdate(posX, posY, posZ, this, 4);
            }
            else if (!this.isIlluminated && world.isBlockIndirectlyGettingPowered(posX, posY, posZ))
            {
                world.setBlock(posX, posY, posZ, ModBlock.LIT_LAMP, 0, 2);
            }
        }
    }

    public void updateTick(World world, int posX, int posY, int posZ, Random random)
    {
        if (!world.isRemote && this.isIlluminated && !world.isBlockIndirectlyGettingPowered(posX, posY, posZ))
        {
            world.setBlock(posX, posY, posZ, ModBlock.LAMP, 0, 2);
        }
    }

    public Item getItemDropped(int side, Random random, int metadata)
    {
        return Item.getItemFromBlock(ModBlock.LAMP);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int posX, int posY, int posZ)
    {
        return Item.getItemFromBlock(ModBlock.LAMP);
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(ModBlock.LAMP);
    }

}
