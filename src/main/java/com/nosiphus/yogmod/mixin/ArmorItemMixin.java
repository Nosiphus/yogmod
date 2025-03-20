package com.nosiphus.yogmod.mixin;

import com.nosiphus.yogmod.core.dispenser.YogBlockSource;
import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {

    @Inject(method = "dispenseArmor", at = @At(value = "TAIL", shift = At.Shift.AFTER))
    private static void yogDispenseArmor(YogBlockSource yogBlockSource, ItemStack armorItem, CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockpos = yogBlockSource.pos().relative((Direction)yogBlockSource.state().getValue(YogDispenserBlock.FACING));
        List<LivingEntity> list = yogBlockSource.level().getEntitiesOfClass(LivingEntity.class, new AABB(blockpos), EntitySelector.NO_SPECTATORS.and(new EntitySelector.MobCanWearArmorEntitySelector(armorItem)));
        if (list.isEmpty()) {
            return false;
        } else {
            LivingEntity livingentity = (LivingEntity)list.get(0);
            EquipmentSlot equipmentslot = livingentity.getEquipmentSlotForItem(armorItem);
            if (!armorItem.canEquip(equipmentslot, livingentity)) {
                return false;
            } else {
                ItemStack itemstack = armorItem.split(1);
                livingentity.setItemSlot(equipmentslot, itemstack);
                if (livingentity instanceof Mob) {
                    ((Mob)livingentity).setDropChance(equipmentslot, 2.0F);
                    ((Mob)livingentity).setPersistenceRequired();
                }

                return true;
            }
        }
    }

}
