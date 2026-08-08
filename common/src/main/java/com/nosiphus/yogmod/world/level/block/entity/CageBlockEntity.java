package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CageBlockEntity extends BlockEntity {

    private final BaseSpawner spawner = new BaseSpawner() {
        @Override
        public void broadcastEvent(Level level, BlockPos pos, int event) {
            level.blockEvent(pos, ModBlocks.CAGE.get(), event, 0);
        }

        @Override
        public void setNextSpawnData(@Nullable Level level, BlockPos blockPos, SpawnData spawnData) {
            super.setNextSpawnData(level, blockPos, spawnData);
            if (level != null) {
                BlockState blockState = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, blockState, blockState, 4);
            }
        }

        @Nullable
        public BlockEntity getSpawnerBlockEntity() {
            return CageBlockEntity.this;
        }
    };

    public CageBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityType.CAGE.get(), blockPos, blockState);
    }

    @Override
    public void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider registries) {
        super.loadAdditional(compoundTag, registries);
        this.spawner.load(this.level, this.worldPosition, compoundTag);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider registries) {
        super.saveAdditional(compoundTag, registries);
        this.spawner.save(compoundTag);
    }

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, CageBlockEntity cageBlockEntity) {
        cageBlockEntity.spawner.clientTick(level, blockPos);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, CageBlockEntity cageBlockEntity) {
        cageBlockEntity.spawner.serverTick((ServerLevel) level, blockPos);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag compoundTag = this.saveWithoutMetadata(registries);
        compoundTag.remove("SpawnPotentials");
        return compoundTag;
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return this.spawner.onEventTriggered(this.level, id) || super.triggerEvent(id, type);
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public void setEntityId(EntityType<?> entityType, RandomSource randomSource) {
        this.spawner.setEntityId(entityType, this.level, randomSource, this.worldPosition);
        this.setChanged();
    }

    public BaseSpawner getSpawner() {
        return this.spawner;
    }
}