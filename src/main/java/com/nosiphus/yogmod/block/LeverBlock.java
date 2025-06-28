package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.BlockLever;
import net.minecraft.block.material.Material;

public class LeverBlock extends BlockLever {

    public LeverBlock(Material material) {
        super();
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
