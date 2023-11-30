package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FridgeBlockEntity extends BlockEntity implements LidBlockEntity {
    private final CrateLidController crateLidController = new CrateLidController();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
            level.playSound((Player)null, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, SoundEvents.ENDER_CHEST_OPEN, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }

        protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
            level.playSound((Player)null, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, SoundEvents.ENDER_CHEST_CLOSE, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }

        protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int int1, int int2) {
            level.blockEvent(FridgeBlockEntity.this.worldPosition, ModBlocks.FRIDGE.get(), 1, int2);
        }

        protected boolean isOwnContainer(Player player) {
            return player.getFridgeInventory().isActiveFridge(FridgeBlockEntity.this);
        }

    };

    public FridgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityType.FRIDGE.get(), blockPos, blockState);
    }

    public static void lidAnimateTick(Level level, BlockPos blockPos, BlockState blockState, FridgeBlockEntity fridgeBlockEntity) {
        fridgeBlockEntity.crateLidController.tickLid();
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

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
    public float getOpenNess(float open) {
        return this.crateLidController.getOpenness(open);
    }

}
