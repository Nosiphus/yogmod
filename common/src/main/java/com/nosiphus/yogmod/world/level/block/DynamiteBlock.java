package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.entity.item.PrimedDynamite;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class DynamiteBlock extends Block {

    public static final MapCodec<DynamiteBlock> CODEC = simpleCodec(DynamiteBlock::new);
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    @Override
    public MapCodec<DynamiteBlock> codec() {
        return CODEC;
    }

    public DynamiteBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, false));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock()) && level.hasNeighborSignal(pos)) {
            explode(level, pos, null);
            level.removeBlock(pos, false);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.hasNeighborSignal(pos)) {
            explode(level, pos, null);
            level.removeBlock(pos, false);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && !player.isCreative() && state.getValue(UNSTABLE)) {
            explode(level, pos, null);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            PrimedDynamite primedDynamite = new PrimedDynamite(
                    level, (double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5, explosion.getIndirectSourceEntity()
            );
            int fuse = primedDynamite.getFuse();
            primedDynamite.setFuse(level.random.nextInt(fuse / 4) + fuse / 8);
            level.addFreshEntity(primedDynamite);
        }
    }

    public static void explode(Level level, BlockPos pos) {
        explode(level, pos, null);
    }

    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            PrimedDynamite primedDynamite = new PrimedDynamite(level, (double) blockPos.getX() + 0.5D, blockPos.getY(), (double) blockPos.getZ() + 0.5D, igniter);
            level.addFreshEntity(primedDynamite);
            level.playSound(null, primedDynamite.getX(), primedDynamite.getY(), primedDynamite.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(igniter, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            explode(level, pos, player);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            Item item = stack.getItem();
            if (stack.is(Items.FLINT_AND_STEEL)) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            } else {
                stack.consume(1, player);
            }

            player.awardStat(Stats.ITEM_USED.get(item));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos pos = hit.getBlockPos();
            Entity owner = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(level, pos)) {
                explode(level, pos, owner instanceof LivingEntity livingEntity ? livingEntity : null);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }
}