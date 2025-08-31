package com.nosiphus.yogmod.item;

import com.nosiphus.yogmod.block.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemYogDoor extends ItemDoor {
    private Material doorMaterial;

    public ItemYogDoor(Material material) {
        super(material);
        this.doorMaterial = material;
        this.maxStackSize = 1;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_onItemUse_7_, float p_onItemUse_8_, float p_onItemUse_9_, float p_onItemUse_10_) {
        if (p_onItemUse_7_ != 1) {
            return false;
        } else {
            ++y;
            Block var11;
            if (this.doorMaterial == Material.wood) {
                var11 = ModBlock.WOODEN_DOOR;
            } else {
                var11 = ModBlock.IRON_DOOR;
            }

            if (player.canPlayerEdit(x, y, z, p_onItemUse_7_, stack) && player.canPlayerEdit(x, y + 1, z, p_onItemUse_7_, stack)) {
                if (!var11.canPlaceBlockAt(world, x, y, z)) {
                    return false;
                } else {
                    int var12 = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - (double)0.5F) & 3;
                    placeDoorBlock(world, x, y, z, var12, var11);
                    --stack.stackSize;
                    return true;
                }
            } else {
                return false;
            }
        }
    }



}
