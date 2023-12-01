package com.nosiphus.yogmod.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum CrateType implements StringRepresentable {
    SINGLE("single", 0),
    LEFT("left", 2),
    RIGHT("right", 1);

    public static final CrateType[] BY_ID = values();
    private final String name;
    private final int opposite;

    private CrateType(String name, int opposite) {
        this.name = name;
        this.opposite = opposite;
    }

    public String getSerializedName() {
        return this.name;
    }

    public CrateType getOpposite() {
        return BY_ID[this.opposite];
    }

}
