package com.nosiphus.yogmod.item;

import com.nosiphus.yogmod.block.BlockYogWood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemYogWoodSlab extends ItemBlock {

    private final BlockSlab singleSlab;
    private final BlockSlab doubleSlab;

    public static BlockSlab singleSlabRef;
    public static BlockSlab doubleSlabRef;


    public ItemYogWoodSlab(Block block) {
        super(block);
        this.singleSlab = singleSlabRef;
        this.doubleSlab = doubleSlabRef;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = stack.getItemDamage();
        if (i < 0 || i >= BlockYogWood.woodTypes.length) {
            i = 0;
        }
        return "tile." + BlockYogWood.woodTypes[i] + "_brick_slab";
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
                             float hitX, float hitY, float hitZ) {

        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        int slabMeta = stack.getItemDamage() & 7;
        boolean isTop = (meta & 8) != 0;

        if (doubleSlab == null) {
            return false;
        }

        if (block == singleSlab && (meta & 7) == slabMeta) {
            if ((side == 1 && !isTop) || (side == 0 && isTop)) {
                if (!world.isRemote) {
                    world.setBlock(x, y, z, doubleSlab, slabMeta, 3);
                    stack.stackSize--;
                }
                return true;
            }
        }

        return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

}
