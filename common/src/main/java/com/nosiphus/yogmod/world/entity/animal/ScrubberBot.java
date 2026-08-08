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
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ScrubberBot extends AbstractGolem implements RangedAttackMob {

    private static final EntityDataAccessor<Byte> DATA_MONITOR_ID = SynchedEntityData.defineId(ScrubberBot.class, EntityDataSerializers.BYTE);
    private static final byte MONITOR_FLAG = 16;

    public ScrubberBot(EntityType<? extends ScrubberBot> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (target) -> target instanceof Enemy));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_MONITOR_ID, MONITOR_FLAG);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("Monitor", this.hasMonitor());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("Monitor")) {
            this.setMonitor(compoundTag.getBoolean("Monitor"));
        }
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            if (!this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                return;
            }

            BlockState blockState = Blocks.SNOW.defaultBlockState();

            for (int l = 0; l < 4; ++l) {
                int i = Mth.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                int j = Mth.floor(this.getY());
                int k = Mth.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockPos = new BlockPos(i, j, k);
                if (this.level().isEmptyBlock(blockPos) && blockState.canSurvive(this.level(), blockPos)) {
                    this.level().setBlockAndUpdate(blockPos, blockState);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Context.of(this, blockState));
                }
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float distanceFactor) {
        Snowball snowball = new Snowball(this.level(), this);
        double eyeY = livingEntity.getEyeY() - 1.1F;
        double dx = livingEntity.getX() - this.getX();
        double dy = eyeY - snowball.getY();
        double dz = livingEntity.getZ() - this.getZ();
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz) * 0.2F;
        snowball.shoot(dx, dy + horizontalDistance, dz, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(snowball);
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        return super.getDefaultDimensions(pose).withEyeHeight(1.7F);
    }

    public boolean hasMonitor() {
        return (this.entityData.get(DATA_MONITOR_ID) & MONITOR_FLAG) != 0;
    }

    public void setMonitor(boolean hasMonitor) {
        byte flags = this.entityData.get(DATA_MONITOR_ID);
        if (hasMonitor) {
            this.entityData.set(DATA_MONITOR_ID, (byte) (flags | MONITOR_FLAG));
        } else {
            this.entityData.set(DATA_MONITOR_ID, (byte) (flags & ~MONITOR_FLAG));
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SNOW_GOLEM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, 0.75F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }
}