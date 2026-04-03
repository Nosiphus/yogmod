package com.nosiphus.yogmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class PoweredStripeIntersectionBlockEntity extends BlockEntity {

    private final EnergyStorage energy = new EnergyStorage(29_491_200, 29_491_200, 29_491_200);

    public PoweredStripeIntersectionBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.POWERED_STRIPE_INTERSECTION.get(), pos, state);
    }

    public EnergyStorage getEnergy() {
        return energy;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PoweredStripeIntersectionBlockEntity blockEntity) {
        int stored = blockEntity.energy.getEnergyStored();
        if (stored <= 0) return;

        List<IEnergyStorage> consumers = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            IEnergyStorage cap = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighborPos, dir.getOpposite());

            if (cap != null && cap.canReceive()) {
                consumers.add(cap);
            }
        }

        if (consumers.isEmpty()) return;
        int totalToPush = Math.min(stored, 29_491_200);
        int share = totalToPush / consumers.size();
        int remainder = totalToPush % consumers.size();
        for (IEnergyStorage target : consumers) {
            int toSend = share + (remainder > 0 ? 1 : 0);
            int accepted = target.receiveEnergy(toSend, false);
            blockEntity.energy.extractEnergy(accepted, false);
            if (remainder > 0) remainder--;
        }

        blockEntity.setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        this.energy.serializeNBT(registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.energy.deserializeNBT(registries, tag);
    }

}