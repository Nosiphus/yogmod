package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.inventory.DispenserMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DispenserBlockEntity extends net.minecraft.world.level.block.entity.DispenserBlockEntity {

    public static final int CONTAINER_SIZE = 9;

    protected DispenserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    public DispenserBlockEntity(BlockPos pos, BlockState blockState) {
        this(ModBlockEntityType.DISPENSER.get(), pos, blockState);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new DispenserMenu(id, player, this);
    }
}