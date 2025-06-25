package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class YogBlockCompressed extends Block {

    private final MapColor mapColor;

    public YogBlockCompressed(MapColor mapColor) {
        super(Material.iron);
        this.mapColor = mapColor;
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    public MapColor getMapColor(int index) {
        return this.mapColor;
    }
}
