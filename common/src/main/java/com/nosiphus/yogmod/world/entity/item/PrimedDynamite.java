package com.nosiphus.yogmod.world.entity.item;

import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.portal.DimensionTransition;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PrimedDynamite extends Entity implements TraceableEntity {

    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedDynamite.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE_ID = SynchedEntityData.defineId(PrimedDynamite.class, EntityDataSerializers.BLOCK_STATE);

    private static final int DEFAULT_FUSE_TIME = 80;
    public static final String TAG_FUSE = "fuse";
    private static final String TAG_BLOCK_STATE = "block_state";

    private static final ExplosionDamageCalculator USED_PORTAL_DAMAGE_CALCULATOR = new ExplosionDamageCalculator() {
        @Override
        public boolean shouldBlockExplode(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, float power) {
            return !state.is(Blocks.NETHER_PORTAL) && super.shouldBlockExplode(explosion, reader, pos, state, power);
        }

        @Override
        public Optional<Float> getBlockExplosionResistance(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, FluidState fluidState) {
            return state.is(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlockExplosionResistance(explosion, reader, pos, state, fluidState);
        }
    };

    @Nullable
    private LivingEntity owner;
    private boolean usedPortal;

    public PrimedDynamite(EntityType<? extends PrimedDynamite> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public PrimedDynamite(Level level, double posX, double posY, double posZ, @Nullable LivingEntity owner) {
        this(ModEntityType.DYNAMITE.get(), level);
        this.setPos(posX, posY, posZ);
        double angle = level.random.nextDouble() * (Math.PI * 2.0);
        this.setDeltaMovement(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);
        this.setFuse(DEFAULT_FUSE_TIME);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = owner;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_FUSE_ID, DEFAULT_FUSE_TIME);
        builder.define(DATA_BLOCK_STATE_ID, ModBlocks.DYNAMITE.get().defaultBlockState());
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.04;
    }

    @Override
    public void tick() {
        this.handlePortal();
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));

        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }

        int fuse = this.getFuse() - 1;
        this.setFuse(fuse);

        if (fuse <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    protected void explode() {
        this.level().explode(
                this,
                Explosion.getDefaultDamageSource(this.level(), this),
                this.usedPortal ? USED_PORTAL_DAMAGE_CALCULATOR : null,
                this.getX(),
                this.getY(0.0625),
                this.getZ(),
                4.0F,
                false,
                Level.ExplosionInteraction.TNT
        );
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putShort(TAG_FUSE, (short) this.getFuse());
        compound.put(TAG_BLOCK_STATE, NbtUtils.writeBlockState(this.getBlockState()));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setFuse(compound.getShort(TAG_FUSE));
        if (compound.contains(TAG_BLOCK_STATE, 10)) {
            this.setBlockState(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound(TAG_BLOCK_STATE)));
        }
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    @Override
    public void restoreFrom(Entity entity) {
        super.restoreFrom(entity);
        if (entity instanceof PrimedDynamite primedDynamite) {
            this.owner = primedDynamite.owner;
        }
    }

    public void setFuse(int life) {
        this.entityData.set(DATA_FUSE_ID, life);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public void setBlockState(BlockState blockState) {
        this.entityData.set(DATA_BLOCK_STATE_ID, blockState);
    }

    public BlockState getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE_ID);
    }

    private void setUsedPortal(boolean usedPortal) {
        this.usedPortal = usedPortal;
    }

    @Override
    @Nullable
    public Entity changeDimension(DimensionTransition transition) {
        Entity entity = super.changeDimension(transition);
        if (entity instanceof PrimedDynamite primedDynamite) {
            primedDynamite.setUsedPortal(true);
        }
        return entity;
    }
}