package com.nosiphus.yogmod.core.dispenser;

import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class YogProjectileDispenseBehavior extends YogDefaultDispenseItemBehavior {
    private final ProjectileItem projectileItem;
    private final ProjectileItem.DispenseConfig dispenseConfig;

    public YogProjectileDispenseBehavior(Item projectile) {
        if (projectile instanceof ProjectileItem projectileitem) {
            this.projectileItem = projectileitem;
            this.dispenseConfig = projectileitem.createDispenseConfig();
        } else {
            throw new IllegalArgumentException(projectile + " not instance of " + ProjectileItem.class.getSimpleName());
        }
    }

    @Override
    public ItemStack execute(YogBlockSource yogBlockSource, ItemStack item) {
        Level level = yogBlockSource.level();
        Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
        Position position = this.dispenseConfig.positionFunction().getYogDispensePosition(yogBlockSource, direction);
        Projectile projectile = this.projectileItem.asProjectile(level, position, item, direction);
        this.projectileItem
            .shoot(
                projectile,
                (double)direction.getStepX(),
                (double)direction.getStepY(),
                (double)direction.getStepZ(),
                this.dispenseConfig.power(),
                this.dispenseConfig.uncertainty()
            );
        level.addFreshEntity(projectile);
        item.shrink(1);
        return item;
    }

    @Override
    protected void playSound(YogBlockSource yogBlockSource) {
        yogBlockSource.level().levelEvent(this.dispenseConfig.overrideDispenseEvent().orElse(1002), yogBlockSource.pos(), 0);
    }
}