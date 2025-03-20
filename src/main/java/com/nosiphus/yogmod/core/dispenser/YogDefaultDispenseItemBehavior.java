package com.nosiphus.yogmod.core.dispenser;

import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class YogDefaultDispenseItemBehavior implements YogDispenseItemBehavior {
    private static final int DEFAULT_ACCURACY = 6;

    @Override
    public final ItemStack dispense(YogBlockSource yogBlockSource, ItemStack item) {
        ItemStack itemstack = this.execute(yogBlockSource, item);
        this.playSound(yogBlockSource);
        this.playAnimation(yogBlockSource, yogBlockSource.state().getValue(YogDispenserBlock.FACING));
        return itemstack;
    }

    protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack item) {
        Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
        Position position = YogDispenserBlock.getDispensePosition(yogBlockSource);
        ItemStack itemstack = item.split(1);
        spawnItem(yogBlockSource.level(), itemstack, 6, direction, position);
        return item;
    }

    public static void spawnItem(Level level, ItemStack stack, int speed, Direction facing, Position position) {
        double d0 = position.x();
        double d1 = position.y();
        double d2 = position.z();
        if (facing.getAxis() == Direction.Axis.Y) {
            d1 -= 0.125;
        } else {
            d1 -= 0.15625;
        }

        ItemEntity itementity = new ItemEntity(level, d0, d1, d2, stack);
        double d3 = level.random.nextDouble() * 0.1 + 0.2;
        itementity.setDeltaMovement(
            level.random.triangle((double)facing.getStepX() * d3, 0.0172275 * (double)speed),
            level.random.triangle(0.2, 0.0172275 * (double)speed),
            level.random.triangle((double)facing.getStepZ() * d3, 0.0172275 * (double)speed)
        );
        level.addFreshEntity(itementity);
    }

    protected void playSound(YogBlockSource yogBlockSource) {
        playDefaultSound(yogBlockSource);
    }

    protected void playAnimation(YogBlockSource yogBlockSource, Direction direction) {
        playDefaultAnimation(yogBlockSource, direction);
    }

    private static void playDefaultSound(YogBlockSource yogBlockSource) {
        yogBlockSource.level().levelEvent(1000, yogBlockSource.pos(), 0);
    }

    private static void playDefaultAnimation(YogBlockSource yogBlockSource, Direction direction) {
        yogBlockSource.level().levelEvent(2000, yogBlockSource.pos(), direction.get3DDataValue());
    }

    protected ItemStack consumeWithRemainder(YogBlockSource yogBlockSource, ItemStack stack, ItemStack remainder) {
        stack.shrink(1);
        if (stack.isEmpty()) {
            return remainder;
        } else {
            this.addToInventoryOrDispense(yogBlockSource, remainder);
            return stack;
        }
    }

    private void addToInventoryOrDispense(YogBlockSource yogBlockSource, ItemStack remainder) {
        ItemStack itemstack = yogBlockSource.blockEntity().insertItem(remainder);
        if (!itemstack.isEmpty()) {
            Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
            spawnItem(yogBlockSource.level(), itemstack, 6, direction, YogDispenserBlock.getDispensePosition(yogBlockSource));
            playDefaultSound(yogBlockSource);
            playDefaultAnimation(yogBlockSource, direction);
        }
    }
}