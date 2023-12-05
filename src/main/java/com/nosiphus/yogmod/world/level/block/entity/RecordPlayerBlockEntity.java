package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.level.block.RecordPlayerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.VisibleForTesting;

import javax.annotation.Nullable;
import java.util.Objects;

public class RecordPlayerBlockEntity extends BlockEntity implements Clearable, ContainerSingleItem {

    private static final int SONG_END_PADDING = 20;
    private final NonNullList<ItemStack> items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    private int ticksSinceLastEvent;
    private long tickCount;
    private long recordStartedTick;
    private boolean isPlaying;

    public RecordPlayerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityType.RECORD_PLAYER.get(), blockPos, blockState);
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        if (compoundTag.contains("RecordItem", 10)) {
            this.items.set(0, ItemStack.of(compoundTag.getCompound("RecordItem")));
        }

        this.isPlaying = compoundTag.getBoolean("IsPlaying");
        this.recordStartedTick = compoundTag.getLong("RecordStartTick");
        this.tickCount = compoundTag.getLong("TickCount");
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.getFirstItem().isEmpty()) {
            compoundTag.put("RecordItem", this.getFirstItem().save(new CompoundTag()));
        }

        compoundTag.putBoolean("IsPlaying", this.isPlaying);
        compoundTag.putLong("RecordStartTick", this.recordStartedTick);
        compoundTag.putLong("TickCount", this.tickCount);
    }

    public boolean isRecordPlaying() {
        return !this.getFirstItem().isEmpty() && this.isPlaying;
    }

    private void setHasRecordBlockState(@Nullable Entity entity, boolean isPlaying) {
        if (this.level.getBlockState(this.getBlockPos()) == this.getBlockState()) {
            this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(RecordPlayerBlock.HAS_RECORD, Boolean.valueOf(isPlaying)), 2);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
        }
    }

    @VisibleForTesting
    public void startPlaying() {
        this.recordStartedTick = this.tickCount;
        this.isPlaying = true;
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.level.levelEvent((Player) null, 1010, this.getBlockPos(), Item.getId(this.getFirstItem().getItem()));
        this.setChanged();
    }

    private void stopPlaying() {
        this.isPlaying = false;
        this.level.gameEvent(GameEvent.JUKEBOX_STOP_PLAY, this.getBlockPos(), GameEvent.Context.of(this.getBlockState()));
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.level.levelEvent(1011, this.getBlockPos(), 0);
        this.setChanged();
    }

    private void tick(Level level, BlockPos blockPos, BlockState blockState) {
        ++this.ticksSinceLastEvent;
        if (this.isRecordPlaying()) {
            Item item = this.getFirstItem().getItem();
            if (item instanceof RecordItem) {
                RecordItem recordItem = (RecordItem) item;
                if (this.shouldRecordStopPlaying(recordItem)) {
                    this.stopPlaying();
                } else if (this.shouldSendRecordPlayerPlayingEvent()) {
                    this.ticksSinceLastEvent = 0;
                    level.gameEvent(GameEvent.JUKEBOX_PLAY, blockPos, GameEvent.Context.of(blockState));
                    this.spawnMusicParticles(level, blockPos);
                }
            }
        }
        ++this.tickCount;
    }

    private boolean shouldRecordStopPlaying(RecordItem recordItem) {
        return this.tickCount >= this.recordStartedTick + (long) recordItem.getLengthInTicks() + 20L;
    }

    private boolean shouldSendRecordPlayerPlayingEvent() {
        return this.ticksSinceLastEvent >= 20;
    }

    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    public ItemStack removeItem(int int1, int int2) {
        ItemStack itemStack = Objects.requireNonNullElse(this.items.get(int1), ItemStack.EMPTY);
        this.items.set(int1, ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            this.setHasRecordBlockState((Entity) null, false);
            this.stopPlaying();
        }
        return itemStack;
    }

    public void setItem(int int1, ItemStack itemStack) {
        if (itemStack.is(ItemTags.MUSIC_DISCS) && this.level != null) {
            this.items.set(int1, itemStack);
            this.setHasRecordBlockState((Entity) null, true);
            this.startPlaying();
        }
    }

    public int getMaxStackSize() {
        return 1;
    }

    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    public boolean canTakeItem(Container container, int slot, ItemStack itemStack) {
        return container.hasAnyMatching(ItemStack::isEmpty);
    }

    private void spawnMusicParticles(Level level, BlockPos blockPos) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 vec3 = Vec3.atBottomCenterOf(blockPos).add(0.0D, (double)1.2F, 0.0D);
            float f = (float)level.getRandom().nextInt(4) / 24.0F;
            serverLevel.sendParticles(ParticleTypes.NOTE, vec3.x(), vec3.y(), vec3.z(), 0, (double)f, 0.0D, 0.0D, 1.0D);
        }

    }

    public void popOutRecord() {
        if (this.level != null && !this.level.isClientSide) {
            BlockPos blockPos = this.getBlockPos();
            ItemStack itemStack = this.getFirstItem();
            if (!itemStack.isEmpty()) {
                this.removeFirstItem();
                Vec3 vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5D, 1.01D, 0.5D).offsetRandom(this.level.random, 0.7F);
                ItemStack itemStack1 = itemStack.copy();
                ItemEntity itemEntity = new ItemEntity(this.level, vec3.x(), vec3.y(), vec3.z(), itemStack1);
                itemEntity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itemEntity);
            }
        }
    }

    public static void playRecordTick(Level level, BlockPos blockPos, BlockState blockState, RecordPlayerBlockEntity recordPlayerBlockEntity) {
        recordPlayerBlockEntity.tick(level, blockPos, blockState);
    }

    @VisibleForTesting
    public void setRecordWithoutPlaying(ItemStack itemStack) {
        this.items.set(0, itemStack);
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }

}
