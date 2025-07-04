package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;

public class BlockYogLadder extends BlockLadder {

    public BlockYogLadder(Material material) {
        super();
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
