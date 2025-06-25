package com.nosiphus.yogmod.creativetab;

import com.nosiphus.yogmod.block.ModBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {

    public static CreativeTabs YogTab = new CreativeTabs("YogMod") {
        @Override
        public Item getTabIconItem() {
            return new ItemStack(ModBlock.AIR_VENT).getItem();
        }
    };

}
