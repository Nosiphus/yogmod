package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockYogBase extends Block {

    public BlockYogBase(int id, Material material) {
        super(id, material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
