package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BlockHatch extends BlockTrapDoor {

    protected BlockHatch(Material material) {
        super(material);
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
