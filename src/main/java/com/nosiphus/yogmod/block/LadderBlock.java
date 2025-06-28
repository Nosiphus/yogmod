package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;

public class LadderBlock extends BlockLadder {

    public LadderBlock(Material material) {
        super();
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
