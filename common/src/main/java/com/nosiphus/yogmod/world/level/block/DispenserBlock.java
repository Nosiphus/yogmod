package com.nosiphus.yogmod.world.level.block;

import com.mojang.logging.LogUtils;
import com.nosiphus.yogmod.world.level.block.entity.DispenserBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.slf4j.Logger;

public class DispenserBlock extends net.minecraft.world.level.block.DispenserBlock {

    private static final Logger LOGGER = LogUtils.getLogger();

    public DispenserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, false));
    }

    @Override
    protected void dispenseFrom(ServerLevel level, BlockState state, BlockPos pos) {
        DispenserBlockEntity dispenserBlockEntity = level.getBlockEntity(pos, ModBlockEntityType.DISPENSER.get()).orElse(null);
        if (dispenserBlockEntity == null) {
            LOGGER.warn("Ignoring dispensing attempt for Dispenser without matching block entity at {}", pos);
        } else {
            BlockSource blockSource = new BlockSource(level, pos, state, dispenserBlockEntity);
            int slot = dispenserBlockEntity.getRandomSlot(level.random);
            if (slot < 0) {
                level.levelEvent(1001, pos, 0);
                level.gameEvent(GameEvent.BLOCK_ACTIVATE, pos, GameEvent.Context.of(dispenserBlockEntity.getBlockState()));
            } else {
                ItemStack itemStack = dispenserBlockEntity.getItem(slot);
                DispenseItemBehavior dispenseBehavior = this.getDispenseMethod(level, itemStack);
                if (dispenseBehavior != DispenseItemBehavior.NOOP) {
                    dispenserBlockEntity.setItem(slot, dispenseBehavior.dispense(blockSource, itemStack));
                }
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DispenserBlockEntity(pos, state);
    }
}