package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.inventory.CrateMenu;
import com.nosiphus.yogmod.world.level.block.CrateBlock;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CrateBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
            CrateBlockEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_OPEN);
        }

        protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
            CrateBlockEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_CLOSE);
        }

        protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int int1, int int2) {
            CrateBlockEntity.this.signalOpenCount(level, blockPos, blockState, int1, int2);
        }

        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof CrateMenu)) {
                return false;
            } else {
                Container container = ((CrateMenu) player.containerMenu).getContainer();
                return container == CrateBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer) container).contains(CrateBlockEntity.this);
            }
        }
    };
    private final CrateLidController crateLidController = new CrateLidController();

    protected CrateBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public CrateBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntityType.CRATE.get(), blockPos, blockState);
    }

    public int getContainerSize() {
        return 27;
    }

    protected Component getDefaultName() {
        return Component.translatable("container.yogmod.crate");
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

    public static void lidAnimateTick(Level level, BlockPos blockPos, BlockState blockState, CrateBlockEntity crateBlockEntity) {
        crateBlockEntity.crateLidController.tickLid();
    }

    static void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        CrateType crateType = blockState.getValue(CrateBlock.TYPE);
        if (crateType != CrateType.LEFT) {
            double d0 = (double) blockPos.getX() + 0.5D;
            double d1 = (double) blockPos.getY() + 0.5D;
            double d2 = (double) blockPos.getZ() + 0.5D;
            if (crateType == CrateType.RIGHT) {
                Direction direction = CrateBlock.getConnectedDirection(blockState);
                d0 += (double) direction.getStepX() * 0.5D;
                d2 += (double) direction.getStepZ() * 0.5D;
            }
            level.playSound((Player) null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    public boolean triggerEvent(int int1, int int2) {
        if (int1 == 1) {
            this.crateLidController.shouldBeOpen(int2 > 0);
            return true;
        } else {
            return super.triggerEvent(int1, int2);
        }
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public float getOpenNess(float open) {
        return this.crateLidController.getOpenness(open);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockState = blockGetter.getBlockState(blockPos);
        if (blockState.hasBlockEntity()) {
            BlockEntity blockEntity = blockGetter.getBlockEntity(blockPos);
            if (blockEntity instanceof CrateBlockEntity) {
                return ((CrateBlockEntity) blockEntity).openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    public static void swapContents(CrateBlockEntity crateBlockEntity, CrateBlockEntity crateBlockEntity1) {
        NonNullList<ItemStack> nonNullList = crateBlockEntity.getItems();
        crateBlockEntity.setItems(crateBlockEntity1.getItems());
        crateBlockEntity1.setItems(nonNullList);
    }

    protected AbstractContainerMenu createMenu(int ID, Inventory inventory) {
        return CrateMenu.threeRows(ID, inventory, this);
    }

    private LazyOptional<IItemHandlerModifiable> crateHandler;

    @Override
    public void setBlockState(BlockState blockState) {
        super.setBlockState(blockState);
        if (this.crateHandler != null) {
            LazyOptional<?> oldHandler = this.crateHandler;
            this.crateHandler = null;
            oldHandler.invalidate();
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction direction) {
        if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER) {
            if (this.crateHandler == null)
                this.crateHandler = LazyOptional.of(this::createHandler);
            return this.crateHandler.cast();
        }
        return super.getCapability(cap, direction);
    }

    private IItemHandlerModifiable createHandler() {
        BlockState blockState = this.getBlockState();
        if (!(blockState.getBlock() instanceof CrateBlock)) {
            return new InvWrapper(this);
        }
        Container container = CrateBlock.getContainer((CrateBlock) blockState.getBlock(), blockState, getLevel(), getBlockPos(), true);
        return new InvWrapper(container == null ? this : container);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (crateHandler != null) {
            crateHandler.invalidate();
            crateHandler = null;
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int int1, int int2) {
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, int2);
    }

}
