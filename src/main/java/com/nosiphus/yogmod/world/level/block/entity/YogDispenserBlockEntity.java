package com.nosiphus.yogmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class YogDispenserBlockEntity extends DispenserBlockEntity {

    public static final int CONTAINER_SIZE = 9;
    private NonNullList<ItemStack> items;

    protected YogDispenserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.items = NonNullList.withSize(9, ItemStack.EMPTY);
    }

    public YogDispenserBlockEntity(BlockPos pos, BlockState blockState) {
        this(ModBlockEntityType.YOG_DISPENSER.get(), pos, blockState);
    }

}
