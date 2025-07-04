package com.nosiphus.yogmod.item;

import com.nosiphus.yogmod.block.BlockColoredYogBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemYogColored extends ItemBlock {

    public ItemYogColored(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = stack.getItemDamage();
        if (i < 0 || i >= 16) {
            i = 0;
        }
        return "tile." + BlockColoredYogBase.colorNames[i];
    }
}
