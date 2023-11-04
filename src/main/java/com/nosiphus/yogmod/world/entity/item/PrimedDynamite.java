package com.nosiphus.yogmod.world.entity.item;

import com.nosiphus.yogmod.world.entity.ModEntityType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PrimedDynamite extends Entity {

    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedDynamite.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE_TIME = 80;
    @Nullable
    private LivingEntity owner;

    public PrimedDynamite(EntityType<? extends PrimedDynamite> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public PrimedDynamite(Level level, double posX, double posY, double posZ, @Nullable LivingEntity livingEntity) {
        this(ModEntityType.DYNAMITE.get(), level);
        this.setPos(posX, posY, posZ);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = livingEntity;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void explode() {
        float f = 4.0F;
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
    }

    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putShort("Fuse", (short)this.getFuse());
    }

    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setFuse(compoundTag.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.15F;
    }

    public void setFuse(int index) {
        this.entityData.set(DATA_FUSE_ID, index);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

}
