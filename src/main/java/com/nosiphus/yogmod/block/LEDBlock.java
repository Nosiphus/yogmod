package com.nosiphus.yogmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.*;

public class LEDBlock extends LanternBlock {

    private boolean isIlluminated;
    private static Map toggles = new HashMap<>();

    private boolean isBurntOut(World world, int posX, int posY, int posZ, boolean checkTime) {
        if (!toggles.containsKey(world)) {
            toggles.put(world, new ArrayList());
        }

        List list = (List)toggles.get(world);

        if (checkTime) {
            list.add(new LEDBlock.Toggle(posX, posY, posZ, world.getTotalWorldTime()));
        }

        int l = 0;

        for (int i1 = 0; i1 < list.size(); ++i1) {
            LEDBlock.Toggle toggle = (LEDBlock.Toggle)list.get(i1);

            if (toggle.positionX == posX && toggle.positionY == posY && toggle.positionZ == posZ) {
                ++l;

                if (l >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    public LEDBlock(boolean isIlluminated) {
        this.isIlluminated = isIlluminated;
        this.setTickRandomly(true);
        this.setCreativeTab((CreativeTabs) null);
    }

    public int tickRate(World world) {
        return 2;
    }

    public void onBlockAdded(World world, int posX, int posY, int posZ) {
        if (world.getBlockMetadata(posX, posY, posZ) == 0) {
            super.onBlockAdded(world, posX, posY, posZ);
        }

        if (this.isIlluminated) {
            world.notifyBlocksOfNeighborChange(posX, posY - 1, posZ, this);
            world.notifyBlocksOfNeighborChange(posX, posY + 1, posZ, this);
            world.notifyBlocksOfNeighborChange(posX - 1, posY, posZ, this);
            world.notifyBlocksOfNeighborChange(posX + 1, posY, posZ, this);
            world.notifyBlocksOfNeighborChange(posX, posY, posZ - 1, this);
            world.notifyBlocksOfNeighborChange(posX, posY, posZ + 1, this);
        }
    }

    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int side) {
        if (this.isIlluminated) {
            world.notifyBlocksOfNeighborChange(posX, posY - 1, posZ, this);
            world.notifyBlocksOfNeighborChange(posX, posY + 1, posZ, this);
            world.notifyBlocksOfNeighborChange(posX - 1, posY, posZ, this);
            world.notifyBlocksOfNeighborChange(posX + 1, posY, posZ, this);
            world.notifyBlocksOfNeighborChange(posX, posY, posZ - 1, this);
            world.notifyBlocksOfNeighborChange(posX, posY, posZ + 1, this);
        }
    }

    public int isProvidingWeakPower(IBlockAccess access, int posX, int posY, int posZ, int side) {
        if (!this.isIlluminated) {
            return 0;
        }
        else {
            int i1 = access.getBlockMetadata(posX, posY, posZ);
            return i1 == 5 && side == 1 ? 0 : (i1 == 3 && side == 3 ? 0 : (i1 == 4 && side == 2 ? 0 : (i1 == 1 && side == 5 ? 0 : (i1 == 2 && side == 4 ? 0 : 15))));
        }
    }

    private boolean isBeingPowered(World world, int posX, int posY, int posZ) {
        int l = world.getBlockMetadata(posX, posY, posZ);
        return l == 5 && world.getIndirectPowerOutput(posX, posY - 1, posZ, 0) ? true : (l == 3 && world.getIndirectPowerOutput(posX, posY, posZ - 1, 2) ? true : (l == 4 && world.getIndirectPowerOutput(posX, posY, posZ + 1, 3) ? true : (l == 1 && world.getIndirectPowerOutput(posX - 1, posY, posZ, 4) ? true : l == 2 && world.getIndirectPowerOutput(posX + 1, posY, posZ, 5))));
    }

    public void updateTick(World world, int posX, int posY, int posZ, Random random) {
        boolean flag = this.isBeingPowered(world, posX, posY, posZ);
        List list = (List)toggles.get(world);

        while (list != null && !list.isEmpty() && world.getTotalWorldTime() - ((LEDBlock.Toggle)list.get(0)).toggleTime > 60L) {
            list.remove(0);
        }

        if (this.isIlluminated) {
            if (flag) {
                world.setBlock(posX, posY, posZ, ModBlock.LED, world.getBlockMetadata(posX, posY, posZ), 3);

                if (this.isBurntOut(world, posX, posY, posZ, true)) {
                    world.playSoundEffect((double)((float)posX + 0.5F), (double)((float)posY + 0.5F), (double)((float)posZ + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 5; ++l) {
                        double d0 = (double)posX + random.nextDouble() * 0.6D + 0.2D;
                        double d1 = (double)posY + random.nextDouble() * 0.6D + 0.2D;
                        double d2 = (double)posZ + random.nextDouble() * 0.6D + 0.2D;
                        world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
        else if (!flag && !this.isBurntOut(world, posX, posY, posZ, false)) {
            world.setBlock(posX, posY, posZ, ModBlock.LED_ON, world.getBlockMetadata(posX, posY, posZ), 3);
        }
    }

    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block) {
        if (!this.isValidSupport(world, posX, posY, posZ, block)) {
            boolean flag = this.isBeingPowered(world, posX, posY, posZ);

            if (this.isIlluminated && flag || !this.isIlluminated && !flag) {
                world.scheduleBlockUpdate(posX, posY, posZ, this, this.tickRate(world));
            }
        }
    }

    public int isProvidingStrongPower(IBlockAccess access, int posX, int posY, int posZ, int side) {
        return side == 0 ? this.isProvidingWeakPower(access, posX, posY, posZ, side) : 0;
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
        return Item.getItemFromBlock(ModBlock.LED_ON);
    }

    public boolean canProvidePower()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int posX, int posY, int posZ, Random random)
    {
        if (this.isIlluminated)
        {
            int l = world.getBlockMetadata(posX, posY, posZ);
            double d0 = (double)((float)posX + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.2D;
            double d1 = (double)((float)posY + 0.7F) + (double)(random.nextFloat() - 0.5F) * 0.2D;
            double d2 = (double)((float)posZ + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.2D;
            double d3 = 0.2199999988079071D;
            double d4 = 0.27000001072883606D;

            if (l == 1)
            {
                world.spawnParticle("reddust", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                world.spawnParticle("reddust", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                world.spawnParticle("reddust", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            }
            else if (l == 4)
            {
                world.spawnParticle("reddust", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                world.spawnParticle("reddust", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int posX, int posY, int posZ)
    {
        return Item.getItemFromBlock(ModBlock.LED_ON);
    }

    public boolean isAssociatedBlock(Block block)
    {
        return block == ModBlock.LED || block == ModBlock.LED_ON;
    }

    static class Toggle
    {
        int positionX;
        int positionY;
        int positionZ;
        long toggleTime;

        public Toggle(int posX, int posY, int posZ, long time)
        {
            this.positionX = posX;
            this.positionY = posY;
            this.positionZ = posZ;
            this.toggleTime = time;
        }
    }


}
