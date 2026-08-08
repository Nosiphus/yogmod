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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CrateBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

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
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldOpeners, int newOpeners) {
            CrateBlockEntity.this.signalOpenCount(level, pos, state, oldOpeners, newOpeners);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof CrateMenu crateMenu)) {
                return false;
            } else {
                Container container = crateMenu.getContainer();
                return container == CrateBlockEntity.this
                        || (container instanceof CompoundContainer compoundContainer && compoundContainer.contains(CrateBlockEntity.this));
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
        CrateType crateType = state.getValue(CrateBlock.TYPE);
        if (crateType != CrateType.LEFT) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.5;
            if (crateType == CrateType.RIGHT) {
                Direction direction = CrateBlock.getConnectedDirection(state);
                x += direction.getStepX() * 0.5;
                z += direction.getStepZ() * 0.5;
            }

            level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
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
        BlockState blockState = level.getBlockState(pos);
        if (blockState.hasBlockEntity()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CrateBlockEntity crateBlockEntity) {
                return crateBlockEntity.openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    public static void swapContents(CrateBlockEntity crate, CrateBlockEntity otherCrate) {
        NonNullList<ItemStack> items = crate.getItems();
        crate.setItems(otherCrate.getItems());
        otherCrate.setItems(items);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return CrateMenu.threeRows(id, player, this);
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