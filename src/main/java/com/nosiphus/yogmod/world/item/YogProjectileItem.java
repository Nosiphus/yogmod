package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.core.dispenser.YogBlockSource;
import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.phys.Vec3;

import java.util.OptionalInt;

public interface YogProjectileItem {

    default YogProjectileItem.YogDispenseConfig createYogDispenseConfig() {
        return YogProjectileItem.YogDispenseConfig.DEFAULT;
    }

    public static record YogDispenseConfig(YogProjectileItem.YogPositionFunction positionFunction, float uncertainty, float power, OptionalInt overrideYogDispenseEvent) {
        public static final YogProjectileItem.YogDispenseConfig DEFAULT = builder().build();

        public static YogProjectileItem.YogDispenseConfig.Builder builder() {
            return new YogProjectileItem.YogDispenseConfig.Builder();
        }

        public static class Builder {
            private YogProjectileItem.YogPositionFunction positionFunction = (yogBlockSource, direction) -> YogDispenserBlock.getDispensePosition(
                    yogBlockSource, 0.7, new Vec3(0.0, 0.1, 0.0)
            );
            private float uncertainty = 6.0F;
            private float power = 1.1F;
            private OptionalInt overrideYogDispenseEvent = OptionalInt.empty();

            public YogProjectileItem.YogDispenseConfig.Builder positionFunction(YogProjectileItem.YogPositionFunction positionFunction) {
                this.positionFunction = positionFunction;
                return this;
            }

            public YogProjectileItem.YogDispenseConfig.Builder uncertainty(float uncertainty) {
                this.uncertainty = uncertainty;
                return this;
            }

            public YogProjectileItem.YogDispenseConfig.Builder power(float power) {
                this.power = power;
                return this;
            }

            public YogProjectileItem.YogDispenseConfig.Builder overrideYogDispenseEvent(int overrideYogDispenseEvent) {
                this.overrideYogDispenseEvent = OptionalInt.of(overrideYogDispenseEvent);
                return this;
            }

            public YogProjectileItem.YogDispenseConfig build() {
                return new YogProjectileItem.YogDispenseConfig(this.positionFunction, this.uncertainty, this.power, this.overrideYogDispenseEvent);
            }
        }
    }

    @FunctionalInterface
    public interface YogPositionFunction {
        Position getYogDispensePosition(YogBlockSource source, Direction direction);
    }

}