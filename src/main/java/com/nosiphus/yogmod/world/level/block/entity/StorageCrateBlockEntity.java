package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.inventory.StorageCrateMenu;
import com.nosiphus.yogmod.world.level.block.StorageCrateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class StorageCrateBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    public static final int COLUMNS = 9;
    public static final int ROWS = 3;
    public static final int CONTAINER_SIZE = 27;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    public static final int OPENING_TICK_LENGTH = 10;
    private static final int[] SLOTS = IntStream.range(0, 27).toArray();
    private NonNullList<ItemStack> itemStacks;
    private int openCount;

    public StorageCrateBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.STORAGE_CRATE.get(), pos, state);
        this.itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    public int getContainerSize() {
        return this.itemStacks.size();
    }

    public boolean triggerEvent(int status, int openCount) {
        if (status == 1) {
            this.openCount = openCount;
            if (openCount == 0) {
                doNeighborUpdates(this.getLevel(), this.worldPosition, this.getBlockState());
            }

            if (openCount == 1) {
                doNeighborUpdates(this.getLevel(), this.worldPosition, this.getBlockState());
            }

            return true;
        } else {
            return super.triggerEvent(status, openCount);
        }
    }

    private static void doNeighborUpdates(Level level, BlockPos pos, BlockState state) {
        state.updateNeighbourShapes(level, pos, 3);
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount == 1) {
                this.level.gameEvent(player, GameEvent.CONTAINER_OPEN, this.worldPosition);
                this.level.playSound(null, this.worldPosition, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            --this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount <= 0) {
                this.level.gameEvent(player, GameEvent.CONTAINER_CLOSE, this.worldPosition);
                this.level.playSound(null, this.worldPosition, SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    protected Component getDefaultName() {
        return Component.translatable("container.yogmod.storage_crate");
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.loadFromTag(tag, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.itemStacks, false, registries);
        }
    }

    public void loadFromTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag) && tag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(tag, this.itemStacks, levelRegistry);
        }
    }

    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    protected void setItems(NonNullList<ItemStack> itemStackList) {
        this.itemStacks = itemStackList;
    }

    public int[] getSlotsForFace(Direction direction) {
        return SLOTS;
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return !(Block.byItem(stack.getItem()) instanceof StorageCrateBlock) && stack.getItem().canFitInsideContainerItems();
    }

    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    protected AbstractContainerMenu createMenu(int ID, Inventory inventory) {
        return new StorageCrateMenu(ID, inventory, this);
    }

    protected IItemHandler createUnSidedHandler() {
        return new SidedInvWrapper(this, Direction.UP);
    }

}
