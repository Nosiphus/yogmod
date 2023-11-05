package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.level.block.RecordPlayerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class RecordPlayerBlockEntity extends BlockEntity implements Clearable {

    private ItemStack record = ItemStack.EMPTY;
    private int f_238796_;
    private long tickCount;
    private long recordStartTick;
    private boolean isPlaying;

    public RecordPlayerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityType.RECORD_PLAYER.get(), blockPos, blockState);
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        if (compoundTag.contains("RecordItem", 10)) {
            this.setRecord(ItemStack.of(compoundTag.getCompound("RecordItem")));
        }

        this.isPlaying = compoundTag.getBoolean("IsPlaying");
        this.recordStartTick = compoundTag.getLong("RecordStartTick");
        this.tickCount = compoundTag.getLong("TickCount");
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.getRecord().isEmpty()) {
            compoundTag.put("RecordItem", this.getRecord().save(new CompoundTag()));
        }

        compoundTag.putBoolean("IsPlaying", this.isPlaying);
        compoundTag.putLong("RecordStartTick", this.recordStartTick);
        compoundTag.putLong("TickCount", this.tickCount);
    }

    public ItemStack getRecord() {
        return this.record;
    }

    public void setRecord(ItemStack itemStack) {
        this.record = itemStack;
        this.setChanged();
    }

    public void m_239936_() {
        this.recordStartTick = this.tickCount;
        this.isPlaying = true;
    }

    public void clearContent() {
        this.setRecord(ItemStack.EMPTY);
        this.isPlaying = false;
    }

    public static void m_239937_(Level level, BlockPos blockPos, BlockState blockState, RecordPlayerBlockEntity recordPlayerBlockEntity) {
        ++recordPlayerBlockEntity.f_238796_;
        if (m_240053_(blockState, recordPlayerBlockEntity)) {
            Item item = recordPlayerBlockEntity.getRecord().getItem();
            if (item instanceof RecordItem) {
                RecordItem recorditem = (RecordItem)item;
                if (m_239766_(recordPlayerBlockEntity, recorditem)) {
                    level.gameEvent(GameEvent.f_238649_, blockPos, GameEvent.Context.of(blockState));
                    recordPlayerBlockEntity.isPlaying = false;
                } else if (m_239365_(recordPlayerBlockEntity)) {
                    recordPlayerBlockEntity.f_238796_ = 0;
                    level.gameEvent(GameEvent.f_238690_, blockPos, GameEvent.Context.of(blockState));
                }
            }
        }

        ++recordPlayerBlockEntity.tickCount;
    }

    private static boolean m_240053_(BlockState blockState, RecordPlayerBlockEntity recordPlayerBlockEntity) {
        return blockState.getValue(RecordPlayerBlock.HAS_RECORD) && recordPlayerBlockEntity.isPlaying;
    }

    private static boolean m_239766_(RecordPlayerBlockEntity recordPlayerBlockEntity, RecordItem recordItem) {
        return recordPlayerBlockEntity.tickCount >= recordPlayerBlockEntity.recordStartTick + (long)recordItem.m_43036_();
    }

    private static boolean m_239365_(RecordPlayerBlockEntity recordPlayerBlockEntity) {
        return recordPlayerBlockEntity.f_238796_ >= 20;
    }

}
