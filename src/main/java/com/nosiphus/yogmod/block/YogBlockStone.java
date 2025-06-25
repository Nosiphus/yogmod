package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class YogBlockStone extends Block {

    public YogBlockStone() {
        super(Material.rock);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
