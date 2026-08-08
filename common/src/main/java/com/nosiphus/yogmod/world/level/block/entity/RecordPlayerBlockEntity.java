package com.nosiphus.yogmod.world.level.block.entity;

import com.google.common.annotations.VisibleForTesting;
import com.nosiphus.yogmod.world.level.block.RecordPlayerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;

import java.util.Optional;

public class RecordPlayerBlockEntity extends BlockEntity implements Clearable, ContainerSingleItem.BlockContainerSingleItem {

    public static final String SONG_ITEM_TAG_ID = "RecordItem";
    public static final String TICKS_SINCE_SONG_STARTED_TAG_ID = "ticks_since_song_started";

    private ItemStack item = ItemStack.EMPTY;
    private final JukeboxSongPlayer jukeboxSongPlayer = new JukeboxSongPlayer(this::onSongChanged, this.getBlockPos());

    public RecordPlayerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.RECORD_PLAYER.get(), pos, blockState);
    }

    public JukeboxSongPlayer getSongPlayer() {
        return this.jukeboxSongPlayer;
    }

    public void onSongChanged() {
        if (this.level != null) {
            this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        }
        this.setChanged();
    }

    private void notifyItemChangedInRecordPlayer(boolean hasRecord) {
        if (this.level != null && this.level.getBlockState(this.getBlockPos()) == this.getBlockState()) {
            this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(RecordPlayerBlock.HAS_RECORD, hasRecord), 2);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(this.getBlockState()));
        }
    }

    public void popOutTheItem() {
        if (this.level != null && !this.level.isClientSide) {
            BlockPos blockPos = this.getBlockPos();
            ItemStack itemStack = this.getTheItem();
            if (!itemStack.isEmpty()) {
                this.removeTheItem();
                Vec3 vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5, 1.01, 0.5).offsetRandom(this.level.random, 0.7F);
                ItemStack copyStack = itemStack.copy();
                ItemEntity itemEntity = new ItemEntity(this.level, vec3.x(), vec3.y(), vec3.z(), copyStack);
                itemEntity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itemEntity);
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RecordPlayerBlockEntity recordPlayer) {
        recordPlayer.jukeboxSongPlayer.tick(level, state);
    }

    public int getComparatorOutput() {
        return JukeboxSong.fromStack(this.level.registryAccess(), this.item)
                .map(Holder::value)
                .map(JukeboxSong::comparatorOutput)
                .orElse(0);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains(SONG_ITEM_TAG_ID, 10)) {
            this.item = ItemStack.parse(registries, tag.getCompound(SONG_ITEM_TAG_ID)).orElse(ItemStack.EMPTY);
        } else {
            this.item = ItemStack.EMPTY;
        }

        if (tag.contains(TICKS_SINCE_SONG_STARTED_TAG_ID, 4)) {
            JukeboxSong.fromStack(registries, this.item)
                    .ifPresent(song -> this.jukeboxSongPlayer.setSongWithoutPlaying(song, tag.getLong(TICKS_SINCE_SONG_STARTED_TAG_ID)));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.getTheItem().isEmpty()) {
            tag.put(SONG_ITEM_TAG_ID, this.getTheItem().save(registries));
        }

        if (this.jukeboxSongPlayer.getSong() != null) {
            tag.putLong(TICKS_SINCE_SONG_STARTED_TAG_ID, this.jukeboxSongPlayer.getTicksSinceSongStarted());
        }
    }

    @Override
    public ItemStack getTheItem() {
        return this.item;
    }

    @Override
    public ItemStack splitTheItem(int amount) {
        ItemStack itemStack = this.item;
        this.setTheItem(ItemStack.EMPTY);
        return itemStack;
    }

    @Override
    public void setTheItem(ItemStack item) {
        this.item = item;
        boolean hasRecord = !this.item.isEmpty();
        Optional<Holder<JukeboxSong>> song = JukeboxSong.fromStack(this.level.registryAccess(), this.item);
        this.notifyItemChangedInRecordPlayer(hasRecord);
        if (hasRecord && song.isPresent()) {
            this.jukeboxSongPlayer.play(this.level, song.get());
        } else {
            this.jukeboxSongPlayer.stop(this.level, this.getBlockState());
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return stack.has(DataComponents.JUKEBOX_PLAYABLE) && this.getItem(slot).isEmpty();
    }

    @Override
    public boolean canTakeItem(Container target, int slot, ItemStack stack) {
        return target.hasAnyMatching(ItemStack::isEmpty);
    }

    @VisibleForTesting
    public void setSongItemWithoutPlaying(ItemStack stack) {
        this.item = stack;
        JukeboxSong.fromStack(this.level.registryAccess(), stack)
                .ifPresent(song -> this.jukeboxSongPlayer.setSongWithoutPlaying(song, 0L));
        if (this.level != null) {
            this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        }
        this.setChanged();
    }

    @VisibleForTesting
    public void tryForcePlaySong() {
        JukeboxSong.fromStack(this.level.registryAccess(), this.getTheItem())
                .ifPresent(song -> this.jukeboxSongPlayer.play(this.level, song));
    }
}