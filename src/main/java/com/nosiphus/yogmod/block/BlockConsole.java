package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.BlockNote;
import net.minecraft.block.material.Material;

public class BlockConsole extends BlockNote {

    public BlockConsole(Material material) {
        super();
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

}
