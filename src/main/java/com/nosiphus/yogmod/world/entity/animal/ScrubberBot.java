package com.nosiphus.yogmod.world.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ScrubberBot extends AbstractGolem implements RangedAttackMob {
    private static final EntityDataAccessor<Byte> DATA_MONITOR_ID = SynchedEntityData.defineId(ScrubberBot.class, EntityDataSerializers.BYTE);
    private static final byte MONITOR_FLAG = 16;
    private static final float EYE_HEIGHT = 1.7F;

    public ScrubberBot(EntityType<? extends ScrubberBot> entityType, Level level) {
        super(entityType, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (target) -> {
            return target instanceof Enemy;
        }));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.2F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MONITOR_ID, (byte) 16);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("Monitor", this.hasMonitor());
    }

    public void readAdditionalData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("Monitor")) {
            this.setMonitor(compoundTag.getBoolean("Monitor"));
        }
    }

    public boolean isSensitiveToWater() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                return;
            }

            BlockState blockState = Blocks.SNOW.defaultBlockState();

            for (int l = 0; l < 4; ++l) {
                int i = Mth.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                int j = Mth.floor(this.getY());
                int k = Mth.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockPos = new BlockPos(i, j, k);
                if (this.level.isEmptyBlock(blockPos) && blockState.canSurvive(this.level, blockPos)) {
                    this.level.setBlockAndUpdate(blockPos, blockState);
                    this.level.gameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Context.of(this, blockState));
                }
            }
        }
    }

    public void performRangedAttack(LivingEntity livingEntity, float float1) {
        Snowball snowball = new Snowball(this.level, this);
        double d0 = livingEntity.getEyeY() - (double) 1.1F;
        double d1 = livingEntity.getX() - this.getX();
        double d2 = d0 - snowball.getY();
        double d3 = livingEntity.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double) 0.2F;
        snowball.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(snowball);
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 1.7F;
    }

    public boolean hasMonitor() {
        return (this.entityData.get(DATA_MONITOR_ID) & 16) != 0;
    }

    public void setMonitor(boolean hasMonitor) {
        byte b0 = this.entityData.get(DATA_MONITOR_ID);
        if (hasMonitor) {
            this.entityData.set(DATA_MONITOR_ID, (byte) (b0 | 16));
        } else {
            this.entityData.set(DATA_MONITOR_ID, (byte) (b0 & -17));
        }
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SNOW_GOLEM_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double) (0.75F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
    }

}