package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.inventory.CrateMenu;
import com.nosiphus.yogmod.world.level.block.CrateBlock;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

public class CrateBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            CrateBlockEntity.playSound(level, pos, state, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            CrateBlockEntity.playSound(level, pos, state, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int int1, int int2) {
            CrateBlockEntity.this.signalOpenCount(level, pos, state, int1, int2);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof CrateMenu)) {
                return false;
            } else {
                Container container = ((CrateMenu)player.containerMenu).getContainer();
                return container == CrateBlockEntity.this
                    || container instanceof CompoundContainer && ((CompoundContainer)container).contains(CrateBlockEntity.this);
            }
        }
    };
    private final CrateLidController crateLidController = new CrateLidController();

    protected CrateBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public CrateBlockEntity(BlockPos pos, BlockState blockState) {
        this(ModBlockEntityType.CRATE.get(), pos, blockState);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.yogmod.crate");
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, registries);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, registries);
        }
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, CrateBlockEntity blockEntity) {
        blockEntity.crateLidController.tickLid();
    }

    static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        CrateType cratetype = state.getValue(CrateBlock.TYPE);
        if (cratetype != CrateType.LEFT) {
            double d0 = (double)pos.getX() + 0.5;
            double d1 = (double)pos.getY() + 0.5;
            double d2 = (double)pos.getZ() + 0.5;
            if (cratetype == CrateType.RIGHT) {
                Direction direction = CrateBlock.getConnectedDirection(state);
                d0 += (double)direction.getStepX() * 0.5;
                d2 += (double)direction.getStepZ() * 0.5;
            }

            level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.crateLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.crateLidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof CrateBlockEntity) {
                return ((CrateBlockEntity)blockentity).openersCounter.getOpenerCount();
            }
        }

        return 0;
    }

    public static void swapContents(CrateBlockEntity crate, CrateBlockEntity otherCrate) {
        NonNullList<ItemStack> nonnulllist = crate.getItems();
        crate.setItems(otherCrate.getItems());
        otherCrate.setItems(nonnulllist);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return CrateMenu.threeRows(id, player, this);
    }

    @Override
    public void setBlockState(BlockState state) {
        var oldState = getBlockState();
        super.setBlockState(state);
        if ((oldState.getValue(CrateBlock.FACING) != state.getValue(CrateBlock.FACING))
                || (oldState.getValue(CrateBlock.TYPE) != state.getValue(CrateBlock.TYPE))) {
            this.invalidateCapabilities();
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        Block block = state.getBlock();
        level.blockEvent(pos, block, 1, eventParam);
    }
}
