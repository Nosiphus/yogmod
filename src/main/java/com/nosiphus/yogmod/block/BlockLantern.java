package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockLantern extends Block {

    public BlockLantern() {
        super(Material.circuits);
        this.setTickRandomly(true);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 2;
    }

    public boolean canBePlaced(World world, int posX, int posY, int posZ)
    {
        if (World.doesBlockHaveSolidTopSurface(world, posX, posY, posZ))
        {
            return true;
        }
        else
        {
            Block block = world.getBlock(posX, posY, posZ);
            return block.canPlaceTorchOnTop(world, posX, posY, posZ);
        }
    }

    public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ)
    {
        return world.isSideSolid(posX - 1, posY, posZ, EAST,  true) ||
                world.isSideSolid(posX + 1, posY, posZ, WEST,  true) ||
                world.isSideSolid(posX, posY, posZ - 1, SOUTH, true) ||
                world.isSideSolid(posX, posY, posZ + 1, NORTH, true) ||
                canBePlaced(world, posX, posY - 1, posZ);
    }

    public int onBlockPlaced(World world, int posX, int posY, int posZ, int metadata, float hitX, float hitY, float hitZ, int index)
    {
        int j1 = index;

        if (metadata == 1 && this.canBePlaced(world, posX, posY - 1, posZ))
        {
            j1 = 5;
        }

        if (metadata == 2 && world.isSideSolid(posX, posY, posZ + 1, NORTH, true))
        {
            j1 = 4;
        }

        if (metadata == 3 && world.isSideSolid(posX, posY, posZ - 1, SOUTH, true))
        {
            j1 = 3;
        }

        if (metadata == 4 && world.isSideSolid(posX + 1, posY, posZ, WEST, true))
        {
            j1 = 2;
        }

        if (metadata == 5 && world.isSideSolid(posX - 1, posY, posZ, EAST, true))
        {
            j1 = 1;
        }

        return j1;
    }

    public void updateTick(World world, int posX, int posY, int posZ, Random random)
    {
        super.updateTick(world, posX, posY, posZ, random);

        if (world.getBlockMetadata(posX, posY, posZ) == 0)
        {
            this.onBlockAdded(world, posX, posY, posZ);
        }
    }

    public void onBlockAdded(World world, int posX, int posY, int posZ)
    {
        if (world.getBlockMetadata(posX, posY, posZ) == 0)
        {
            if (world.isSideSolid(posX - 1, posY, posZ, EAST, true))
            {
                world.setBlockMetadataWithNotify(posX, posY, posZ, 1, 2);
            }
            else if (world.isSideSolid(posX + 1, posY, posZ, WEST, true))
            {
                world.setBlockMetadataWithNotify(posX, posY, posZ, 2, 2);
            }
            else if (world.isSideSolid(posX, posY, posZ - 1, SOUTH, true))
            {
                world.setBlockMetadataWithNotify(posX, posY, posZ, 3, 2);
            }
            else if (world.isSideSolid(posX, posY, posZ + 1, NORTH, true))
            {
                world.setBlockMetadataWithNotify(posX, posY, posZ, 4, 2);
            }
            else if (this.canBePlaced(world, posX, posY - 1, posZ))
            {
                world.setBlockMetadataWithNotify(posX, posY, posZ, 5, 2);
            }
        }

        this.checkForDrop(world, posX, posY, posZ);
    }

    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block)
    {
        this.isValidSupport(world, posX, posY, posZ, block);
    }

    protected boolean isValidSupport(World world, int posX, int posY, int posZ, Block block)
    {
        if (this.checkForDrop(world, posX, posY, posZ))
        {
            int l = world.getBlockMetadata(posX, posY, posZ);
            boolean flag = false;

            if (!world.isSideSolid(posX - 1, posY, posZ, EAST, true) && l == 1)
            {
                flag = true;
            }

            if (!world.isSideSolid(posX + 1, posY, posZ, WEST, true) && l == 2)
            {
                flag = true;
            }

            if (!world.isSideSolid(posX, posY, posZ - 1, SOUTH, true) && l == 3)
            {
                flag = true;
            }

            if (!world.isSideSolid(posX, posY, posZ + 1, NORTH, true) && l == 4)
            {
                flag = true;
            }

            if (!this.canBePlaced(world, posX, posY - 1, posZ) && l == 5)
            {
                flag = true;
            }

            if (flag)
            {
                this.dropBlockAsItem(world, posX, posY, posZ, world.getBlockMetadata(posX, posY, posZ), 0);
                world.setBlockToAir(posX, posY, posZ);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    protected boolean checkForDrop(World world, int posX, int posY, int posZ)
    {
        if (!this.canPlaceBlockAt(world, posX, posY, posZ))
        {
            if (world.getBlock(posX, posY, posZ) == this)
            {
                this.dropBlockAsItem(world, posX, posY, posZ, world.getBlockMetadata(posX, posY, posZ), 0);
                world.setBlockToAir(posX, posY, posZ);
            }

            return false;
        }
        else
        {
            return true;
        }
    }

    public MovingObjectPosition collisionRayTrace(World world, int posX, int posY, int posZ, Vec3 vec3i, Vec3 vec3j)
    {
        int l = world.getBlockMetadata(posX, posY, posZ) & 7;
        float f = 0.15F;

        if (l == 1)
        {
            this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        }
        else if (l == 2)
        {
            this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        }
        else if (l == 3)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        }
        else if (l == 4)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        }
        else
        {
            f = 0.1F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }

        return super.collisionRayTrace(world, posX, posY, posZ, vec3i, vec3j);
    }

}
