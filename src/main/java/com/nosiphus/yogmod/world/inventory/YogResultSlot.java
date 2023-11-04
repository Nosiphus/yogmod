package com.nosiphus.yogmod.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class YogResultSlot extends Slot {
    private final YogifierContainer yogifierContainer;
    private final Player player;
    private int removeCount;

    public YogResultSlot(Player player, YogifierContainer yogifierContainer, Container container, int int1, int int2, int int3) {
        super(container, int1, int2, int3);
        this.player = player;
        this.yogifierContainer = yogifierContainer;
    }

    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }

    public ItemStack remove(int index) {
        if (this.hasItem()) {
            this.removeCount += Math.min(index, this.getItem().getCount());
        }
        return super.remove(index);
    }

    protected void onQuickCraft(ItemStack p_40180_, int p_40181_) {
        this.removeCount += p_40181_;
        this.checkTakeAchievements(p_40180_);
    }

    protected void onSwapCraft(int p_40183_) {
        this.removeCount += p_40183_;
    }

    protected void checkTakeAchievements(ItemStack p_40185_) {
        if (this.removeCount > 0) {
            p_40185_.onCraftedBy(this.player.level, this.player, this.removeCount);
            net.minecraftforge.event.ForgeEventFactory.firePlayerCraftingEvent(this.player, p_40185_, this.yogifierContainer);
        }

        if (this.container instanceof RecipeHolder) {
            ((RecipeHolder)this.container).awardUsedRecipes(this.player);
        }

        this.removeCount = 0;
    }

    public void onTake(Player p_150638_, ItemStack p_150639_) {
        this.checkTakeAchievements(p_150639_);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(p_150638_);
        NonNullList<ItemStack> nonnulllist = p_150638_.level.getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, this.yogifierContainer, p_150638_.level);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.yogifierContainer.getItem(i);
            ItemStack itemstack1 = nonnulllist.get(i);
            if (!itemstack.isEmpty()) {
                this.yogifierContainer.removeItem(i, 1);
                itemstack = this.yogifierContainer.getItem(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.yogifierContainer.setItem(i, itemstack1);
                } else if (ItemStack.isSame(itemstack, itemstack1) && ItemStack.tagMatches(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.yogifierContainer.setItem(i, itemstack1);
                } else if (!this.player.getInventory().add(itemstack1)) {
                    this.player.drop(itemstack1, false);
                }
            }
        }

    }

}
