package com.nosiphus.yogmod.world.level.block;

import com.mojang.logging.LogUtils;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.YogDispenserBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.slf4j.Logger;

public class YogDispenserBlock extends DispenserBlock {
    private static final Logger LOGGER = LogUtils.getLogger();

    public YogDispenserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.valueOf(false)));
    }

    @Override
    protected void dispenseFrom(ServerLevel level, BlockState state, BlockPos pos) {
        DispenserBlockEntity dispenserblockentity = level.getBlockEntity(pos, ModBlockEntityType.YOG_DISPENSER.get()).orElse(null);
        if (dispenserblockentity == null) {
            LOGGER.warn("Ignoring dispensing attempt for Dispenser without matching block entity at {}", pos);
        } else {
            BlockSource blocksource = new BlockSource(level, pos, state, dispenserblockentity);
            int i = dispenserblockentity.getRandomSlot(level.random);
            if (i < 0) {
                level.levelEvent(1001, pos, 0);
                level.gameEvent(GameEvent.BLOCK_ACTIVATE, pos, GameEvent.Context.of(dispenserblockentity.getBlockState()));
            } else {
                ItemStack itemstack = dispenserblockentity.getItem(i);
                DispenseItemBehavior dispenseitembehavior = this.getDispenseMethod(level, itemstack);
                if (dispenseitembehavior != DispenseItemBehavior.NOOP) {
                    dispenserblockentity.setItem(i, dispenseitembehavior.dispense(blocksource, itemstack));
                }
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new YogDispenserBlockEntity(pos, state);
    }

}
