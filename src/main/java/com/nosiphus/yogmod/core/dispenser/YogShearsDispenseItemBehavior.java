package com.nosiphus.yogmod.core.dispenser;

import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

public class YogShearsDispenseItemBehavior extends YogOptionalDispenseItemBehavior {
    @Override
    protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack item) {
        ServerLevel serverlevel = yogBlockSource.level();
        if (!serverlevel.isClientSide()) {
            BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
            this.setSuccess(net.neoforged.neoforge.common.CommonHooks.tryDispenseShearsHarvestBlock(yogBlockSource, item, serverlevel, blockpos) || tryShearBeehive(serverlevel, blockpos) || tryShearLivingEntity(serverlevel, blockpos, item));
            if (this.isSuccess()) {
                item.hurtAndBreak(1, serverlevel, null, p_348118_ -> {
                });
            }
        }

        return item;
    }

    private static boolean tryShearBeehive(ServerLevel level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.is(BlockTags.BEEHIVES, p_202454_ -> p_202454_.hasProperty(BeehiveBlock.HONEY_LEVEL) && p_202454_.getBlock() instanceof BeehiveBlock)) {
            int i = blockstate.getValue(BeehiveBlock.HONEY_LEVEL);
            if (i >= 5) {
                level.playSound(null, pos, SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
                BeehiveBlock.dropHoneycomb(level, pos);
                ((BeehiveBlock)blockstate.getBlock())
                    .releaseBeesAndResetHoneyLevel(level, blockstate, pos, null, BeehiveBlockEntity.BeeReleaseStatus.BEE_RELEASED);
                level.gameEvent(null, GameEvent.SHEAR, pos);
                return true;
            }
        }

        return false;
    }

    private static boolean tryShearLivingEntity(ServerLevel level, BlockPos pos, ItemStack stack) {
        for (LivingEntity livingentity : level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {
            if (livingentity instanceof net.neoforged.neoforge.common.IShearable shearable && shearable.isShearable(null, stack, level, pos)) {
                shearable.onSheared(null, stack, level, pos)
                        .forEach(drop -> shearable.spawnShearedDrop(level, pos, drop));
                level.gameEvent(null, GameEvent.SHEAR, pos);
                return true;
            }
        }

        return false;
    }
}
