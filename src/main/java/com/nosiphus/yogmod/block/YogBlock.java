package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class YogBlock extends Block {

    public YogBlock(Material material) {
        super(material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
