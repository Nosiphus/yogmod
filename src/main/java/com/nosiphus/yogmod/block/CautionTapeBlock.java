package com.nosiphus.yogmod.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class CautionTapeBlock extends YogBlock {

    public CautionTapeBlock(Material material) {
        super(material);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ) {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)posX, (double)posY, (double)posZ, (double)(posX + 1), (double)((float)(posY + 1) - f), (double)(posZ + 1));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity) {
        entity.motionX *= 0.4D;
        entity.motionZ *= 0.4D;
    }
}
