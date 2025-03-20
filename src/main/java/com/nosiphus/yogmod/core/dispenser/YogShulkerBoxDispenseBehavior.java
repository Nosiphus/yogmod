package com.nosiphus.yogmod.core.dispenser;

import com.mojang.logging.LogUtils;
import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import org.slf4j.Logger;

public class YogShulkerBoxDispenseBehavior extends YogOptionalDispenseItemBehavior {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
        this.setSuccess(false);
        Item item = itemStack.getItem();
        if (item instanceof BlockItem) {
            Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
            BlockPos blockpos = yogBlockSource.pos().relative(direction);
            Direction direction1 = yogBlockSource.level().isEmptyBlock(blockpos.below()) ? direction : Direction.UP;

            try {
                this.setSuccess(
                        ((BlockItem)item).place(new DirectionalPlaceContext(yogBlockSource.level(), blockpos, direction, itemStack, direction1)).consumesAction()
                );
            } catch (Exception exception) {
                LOGGER.error("Error trying to place shulker box at {}", blockpos, exception);
            }
        }

        return itemStack;
    }
}
