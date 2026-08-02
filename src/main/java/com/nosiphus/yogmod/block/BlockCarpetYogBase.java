package com.nosiphus.yogmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCarpetYogBase extends BlockYogBase {

    public BlockCarpetYogBase(Material material) {
        super(material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.setTickRandomly(true);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void setBlockBoundsForItemRender() {
        this.renderFunction(0);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        this.renderFunction(blockAccess.getBlockMetadata(posX, posY, posZ));
    }

    protected void renderFunction(int variable) {
        byte b0 = 0;
        float f = (float) (1 * (1 + b0)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ) {
        return super.canPlaceBlockAt(world, posX, posY, posZ) && this.canBlockStay(world, posX, posY, posZ);
    }

    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block) {
        this.neighborChangeFunction(world, posX, posY, posZ);
    }

    private boolean neighborChangeFunction(World world, int posX, int posY, int posZ) {
        if(!this.canBlockStay(world, posX, posY, posZ)) {
            this.dropBlockAsItem(world, posX, posY, posZ, world.getBlockMetadata(posX, posY, posZ), 0);
            world.setBlockToAir(posX, posY, posZ);
            return false;
        }
        else {
            return true;
        }
    }

    public boolean canBlockStay(World world, int posX, int posY, int posZ) {
        return !world.isAirBlock(posX, posY - 1, posZ);
    }

}
