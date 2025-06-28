package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class HatchBlock extends BlockTrapDoor {

    protected HatchBlock(Material material) {
        super(material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
