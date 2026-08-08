package com.nosiphus.yogmod.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum CrateType implements StringRepresentable {
    SINGLE("single"),
    LEFT("left"),
    RIGHT("right");

    private final String name;

    CrateType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public CrateType getOpposite() {
        return switch (this) {
            case SINGLE -> SINGLE;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}