package com.nosiphus.yogmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DispenserBlockEntity extends RandomizableContainerBlockEntity {
    public static final int CONTAINER_SIZE = 9;
    private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

    protected DispenserBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public DispenserBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntityType.DISPENSER.get(), blockPos, blockState);
    }

    public int getContainerSize() {
        return 9;
    }

    public int getRandomSlot(RandomSource randomSource) {
        this.unpackLootTable((Player) null);
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.items.size(); ++k) {
            if (!this.items.get(k).isEmpty() && randomSource.nextInt(j++) == 0) {
                i = k;
            }
        }
        return i;
    }

    public int addItem(ItemStack itemStack) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).isEmpty()) {
                this.setItem(i, itemStack);
                return i;
            }
        }
        return -1;
    }

    protected Component getDefaultName() {
        return Component.translatable("container.yogmod.dispenser");
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }

    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }

    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> itemStack) {
        this.items = itemStack;
    }

    protected AbstractContainerMenu createMenu(int int1, Inventory inventory) {
        return new DispenserMenu(int1, inventory, this);
    }

}
