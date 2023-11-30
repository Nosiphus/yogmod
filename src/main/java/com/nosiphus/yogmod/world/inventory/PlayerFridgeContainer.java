package com.nosiphus.yogmod.world.inventory;

import com.nosiphus.yogmod.world.level.block.entity.FridgeBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PlayerFridgeContainer extends SimpleContainer {

    @Nullable
    private FridgeBlockEntity activeFridge;

    public PlayerFridgeContainer() {
        super(27);
    }

    public void setActiveFridge(FridgeBlockEntity fridgeBlockEntity) {
        this.activeFridge = fridgeBlockEntity;
    }

    public boolean isActiveFridge(FridgeBlockEntity fridgeBlockEntity) {
        return this.activeFridge == fridgeBlockEntity;
    }

    public void fromTag(ListTag listTag) {
        for(int i = 0; i < this.getContainerSize(); ++i) {
            this.setItem(i, ItemStack.EMPTY);
        }
        for(int k = 0; k < listTag.size(); ++k) {
            CompoundTag compoundTag = listTag.getCompound(k);
            int j = compoundTag.getByte("Slot") & 255;
            if (j >= 0 && j < this.getContainerSize()) {
                this.setItem(j, ItemStack.of(compoundTag));
            }
        }
    }

    public ListTag createTag() {
        ListTag listTag = new ListTag();
        for (int i = 0; i < this.getContainerSize(); ++i) {
            ItemStack itemStack = this.getItem(i);
            if (!itemStack.isEmpty()) {
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putByte("Slot", (byte) i);
                itemStack.save(compoundTag);
                listTag.add(compoundTag);
            }
        }
        return listTag;
    }

    public boolean stillValid(Player player) {
        return this.activeFridge != null && !this.activeFridge.stillValid(player) ? false : super.stillValid(player);
    }

    public void startOpen(Player player) {
        if (this.activeFridge != null) {
            this.activeFridge.startOpen(player);
        }
        super.startOpen(player);
    }

    public void stopOpen(Player player) {
        if (this.activeFridge != null) {
            this.activeFridge.stopOpen(player);
        }
        super.stopOpen(player);
        this.activeFridge = null;
    }



}
