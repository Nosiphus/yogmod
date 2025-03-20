package com.nosiphus.yogmod.core.dispenser;

import com.nosiphus.yogmod.world.level.block.entity.YogDispenserBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public record YogBlockSource(ServerLevel level, BlockPos pos, BlockState state, YogDispenserBlockEntity blockEntity) {
    public YogBlockSource(ServerLevel level, BlockPos pos, BlockState state, YogDispenserBlockEntity blockEntity) {
        this.level = level;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
    }

    public Vec3 center() {
        return this.pos.getCenter();
    }

    public ServerLevel level() {
        return this.level;
    }

    public BlockPos pos() {
        return this.pos;
    }

    public BlockState state() {
        return this.state;
    }

    public YogDispenserBlockEntity blockEntity() { return this.blockEntity; }

}
