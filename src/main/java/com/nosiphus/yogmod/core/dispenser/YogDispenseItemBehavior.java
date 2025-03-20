package com.nosiphus.yogmod.core.dispenser;

import com.mojang.logging.LogUtils;
import com.nosiphus.yogmod.world.level.block.YogDispenserBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface YogDispenseItemBehavior {
    Logger LOGGER = LogUtils.getLogger();
    YogDispenseItemBehavior NOOP = (yogBlockSource, itemStack) -> itemStack;

    ItemStack dispense(YogBlockSource yogBlockSource, ItemStack item);

    static void bootStrap() {
        YogDispenserBlock.registerProjectileBehavior(Items.ARROW);
        YogDispenserBlock.registerProjectileBehavior(Items.TIPPED_ARROW);
        YogDispenserBlock.registerProjectileBehavior(Items.SPECTRAL_ARROW);
        YogDispenserBlock.registerProjectileBehavior(Items.EGG);
        YogDispenserBlock.registerProjectileBehavior(Items.SNOWBALL);
        YogDispenserBlock.registerProjectileBehavior(Items.EXPERIENCE_BOTTLE);
        YogDispenserBlock.registerProjectileBehavior(Items.SPLASH_POTION);
        YogDispenserBlock.registerProjectileBehavior(Items.LINGERING_POTION);
        YogDispenserBlock.registerProjectileBehavior(Items.FIREWORK_ROCKET);
        YogDispenserBlock.registerProjectileBehavior(Items.FIRE_CHARGE);
        YogDispenserBlock.registerProjectileBehavior(Items.WIND_CHARGE);
        YogDefaultDispenseItemBehavior yogdefaultdispenseitembehavior = new YogDefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
                EntityType<?> entitytype = ((SpawnEggItem)itemStack.getItem()).getType(itemStack);

                try {
                    entitytype.spawn(
                        yogBlockSource.level(), itemStack, null, yogBlockSource.pos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false
                    );
                } catch (Exception exception) {
                    LOGGER.error("Error while dispensing spawn egg from dispenser at {}", yogBlockSource.pos(), exception);
                    return ItemStack.EMPTY;
                }

                itemStack.shrink(1);
                yogBlockSource.level().gameEvent(null, GameEvent.ENTITY_PLACE, yogBlockSource.pos());
                return itemStack;
            }
        };

        for (SpawnEggItem spawneggitem : SpawnEggItem.eggs()) {
            YogDispenserBlock.registerBehavior(spawneggitem, yogdefaultdispenseitembehavior);
        }

        YogDispenserBlock.registerBehavior(
            Items.ARMOR_STAND,
            new YogDefaultDispenseItemBehavior() {
                @Override
                public ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                    Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
                    BlockPos blockpos = yogBlockSource.pos().relative(direction);
                    ServerLevel serverlevel = yogBlockSource.level();
                    Consumer<ArmorStand> consumer = EntityType.appendDefaultStackConfig(
                        armorStand -> armorStand.setYRot(direction.toYRot()), serverlevel, itemStack, null
                    );
                    ArmorStand armorstand = EntityType.ARMOR_STAND.spawn(serverlevel, consumer, blockpos, MobSpawnType.DISPENSER, false, false);
                    if (armorstand != null) {
                        itemStack.shrink(1);
                    }

                    return itemStack;
                }
            }
        );
        YogDispenserBlock.registerBehavior(
            Items.SADDLE,
            new YogOptionalDispenseItemBehavior() {
                @Override
                public ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                    BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                    List<LivingEntity> list = yogBlockSource.level()
                        .getEntitiesOfClass(
                            LivingEntity.class,
                            new AABB(blockpos),
                            livingEntity -> !(livingEntity instanceof Saddleable saddleable) ? false : !saddleable.isSaddled() && saddleable.isSaddleable()
                        );
                    if (!list.isEmpty()) {
                        ((Saddleable)list.get(0)).equipSaddle(itemStack.split(1), SoundSource.BLOCKS);
                        this.setSuccess(true);
                        return itemStack;
                    } else {
                        return super.execute(yogBlockSource, itemStack);
                    }
                }
            }
        );
        YogDefaultDispenseItemBehavior defaultdispenseitembehavior1 = new YogOptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));

                for (AbstractHorse abstracthorse : yogBlockSource.level()
                    .getEntitiesOfClass(AbstractHorse.class, new AABB(blockpos), horse -> horse.isAlive() && horse.canUseSlot(EquipmentSlot.BODY))) {
                    if (abstracthorse.isBodyArmorItem(itemStack) && !abstracthorse.isWearingBodyArmor() && abstracthorse.isTamed()) {
                        abstracthorse.setBodyArmorItem(itemStack.split(1));
                        this.setSuccess(true);
                        return itemStack;
                    }
                }

                return super.execute(yogBlockSource, itemStack);
            }
        };
        YogDispenserBlock.registerBehavior(Items.LEATHER_HORSE_ARMOR, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.IRON_HORSE_ARMOR, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.GOLDEN_HORSE_ARMOR, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.DIAMOND_HORSE_ARMOR, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.WHITE_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.ORANGE_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.CYAN_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.BLUE_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.BROWN_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.BLACK_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.GRAY_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.GREEN_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.LIGHT_BLUE_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.LIGHT_GRAY_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.LIME_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.MAGENTA_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.PINK_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.PURPLE_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.RED_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.YELLOW_CARPET, defaultdispenseitembehavior1);
        YogDispenserBlock.registerBehavior(
            Items.CHEST,
            new YogOptionalDispenseItemBehavior() {
                @Override
                public ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                    BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));

                    for (AbstractChestedHorse abstractchestedhorse : yogBlockSource.level()
                        .getEntitiesOfClass(AbstractChestedHorse.class, new AABB(blockpos), chestedHorse -> chestedHorse.isAlive() && !chestedHorse.hasChest())) {
                        if (abstractchestedhorse.isTamed() && abstractchestedhorse.getSlot(499).set(itemStack)) {
                            itemStack.shrink(1);
                            this.setSuccess(true);
                            return itemStack;
                        }
                    }

                    return super.execute(yogBlockSource, itemStack);
                }
            }
        );
        YogDispenserBlock.registerBehavior(Items.OAK_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.OAK));
        YogDispenserBlock.registerBehavior(Items.SPRUCE_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.SPRUCE));
        YogDispenserBlock.registerBehavior(Items.BIRCH_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.BIRCH));
        YogDispenserBlock.registerBehavior(Items.JUNGLE_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.JUNGLE));
        YogDispenserBlock.registerBehavior(Items.DARK_OAK_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.DARK_OAK));
        YogDispenserBlock.registerBehavior(Items.ACACIA_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.ACACIA));
        YogDispenserBlock.registerBehavior(Items.CHERRY_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.CHERRY));
        YogDispenserBlock.registerBehavior(Items.MANGROVE_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.MANGROVE));
        YogDispenserBlock.registerBehavior(Items.BAMBOO_RAFT, new YogBoatDispenseItemBehavior(Boat.Type.BAMBOO));
        YogDispenserBlock.registerBehavior(Items.OAK_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.OAK, true));
        YogDispenserBlock.registerBehavior(Items.SPRUCE_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.SPRUCE, true));
        YogDispenserBlock.registerBehavior(Items.BIRCH_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.BIRCH, true));
        YogDispenserBlock.registerBehavior(Items.JUNGLE_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.JUNGLE, true));
        YogDispenserBlock.registerBehavior(Items.DARK_OAK_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.DARK_OAK, true));
        YogDispenserBlock.registerBehavior(Items.ACACIA_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.ACACIA, true));
        YogDispenserBlock.registerBehavior(Items.CHERRY_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.CHERRY, true));
        YogDispenserBlock.registerBehavior(Items.MANGROVE_CHEST_BOAT, new YogBoatDispenseItemBehavior(Boat.Type.MANGROVE, true));
        YogDispenserBlock.registerBehavior(Items.BAMBOO_CHEST_RAFT, new YogBoatDispenseItemBehavior(Boat.Type.BAMBOO, true));
        YogDispenseItemBehavior dispenseitembehavior1 = new YogDefaultDispenseItemBehavior() {
            private final YogDefaultDispenseItemBehavior defaultDispenseItemBehavior = new YogDefaultDispenseItemBehavior();

            @Override
            public ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)itemStack.getItem();
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                Level level = yogBlockSource.level();
                if (dispensiblecontaineritem.emptyContents(null, level, blockpos, null, itemStack)) {
                    dispensiblecontaineritem.checkExtraContent(null, level, itemStack, blockpos);
                    return this.consumeWithRemainder(yogBlockSource, itemStack, new ItemStack(Items.BUCKET));
                } else {
                    return this.defaultDispenseItemBehavior.dispense(yogBlockSource, itemStack);
                }
            }
        };
        YogDispenserBlock.registerBehavior(Items.LAVA_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.WATER_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.POWDER_SNOW_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.SALMON_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.COD_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.PUFFERFISH_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.TROPICAL_FISH_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.AXOLOTL_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.TADPOLE_BUCKET, dispenseitembehavior1);
        YogDispenserBlock.registerBehavior(Items.BUCKET, new YogDefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                LevelAccessor levelaccessor = yogBlockSource.level();
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                BlockState blockstate = levelaccessor.getBlockState(blockpos);
                if (blockstate.getBlock() instanceof BucketPickup bucketpickup) {
                    ItemStack itemstack = bucketpickup.pickupBlock(null, levelaccessor, blockpos, blockstate);
                    if (itemstack.isEmpty()) {
                        return super.execute(yogBlockSource, itemStack);
                    } else {
                        levelaccessor.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        Item item = itemstack.getItem();
                        return this.consumeWithRemainder(yogBlockSource, itemStack, new ItemStack(item));
                    }
                } else {
                    return super.execute(yogBlockSource, itemStack);
                }
            }
        });
        YogDispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new YogOptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                ServerLevel serverlevel = yogBlockSource.level();
                this.setSuccess(true);
                Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
                BlockPos blockpos = yogBlockSource.pos().relative(direction);
                BlockState blockstate = serverlevel.getBlockState(blockpos);
                if (BaseFireBlock.canBePlacedAt(serverlevel, blockpos, direction)) {
                    serverlevel.setBlockAndUpdate(blockpos, BaseFireBlock.getState(serverlevel, blockpos));
                    serverlevel.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
                } else if (blockstate.getToolModifiedState(new net.minecraft.world.item.context.UseOnContext(yogBlockSource.level(), null, net.minecraft.world.InteractionHand.MAIN_HAND, itemStack, new net.minecraft.world.phys.BlockHitResult(blockpos.getCenter(), direction.getOpposite(), blockpos, false)), net.neoforged.neoforge.common.ItemAbilities.FIRESTARTER_LIGHT, false) instanceof BlockState blockstate2) {
                    serverlevel.setBlockAndUpdate(blockpos, blockstate2);
                    serverlevel.gameEvent(null, GameEvent.BLOCK_CHANGE, blockpos);
                } else if (blockstate.isFlammable(serverlevel, blockpos, yogBlockSource.state().getValue(YogDispenserBlock.FACING).getOpposite())) {
                    blockstate.onCaughtFire(serverlevel, blockpos, yogBlockSource.state().getValue(YogDispenserBlock.FACING).getOpposite(), null);
                    if (blockstate.getBlock() instanceof TntBlock)
                        serverlevel.removeBlock(blockpos, false);
                } else {
                    this.setSuccess(false);
                }

                if (this.isSuccess()) {
                    itemStack.hurtAndBreak(1, serverlevel, null, p_348117_ -> {
                    });
                }

                return itemStack;
            }
        });
        YogDispenserBlock.registerBehavior(Items.BONE_MEAL, new YogOptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                this.setSuccess(true);
                Level level = yogBlockSource.level();
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                if (!BoneMealItem.growCrop(itemStack, level, blockpos) && !BoneMealItem.growWaterPlant(itemStack, level, blockpos, null)) {
                    this.setSuccess(false);
                } else if (!level.isClientSide) {
                    level.levelEvent(1505, blockpos, 15);
                }

                return itemStack;
            }
        });
        YogDispenserBlock.registerBehavior(Blocks.TNT, new YogDefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                Level level = yogBlockSource.level();
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                PrimedTnt primedtnt = new PrimedTnt(level, (double)blockpos.getX() + 0.5, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5, null);
                level.addFreshEntity(primedtnt);
                level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
                itemStack.shrink(1);
                return itemStack;
            }
        });
        YogDispenseItemBehavior dispenseitembehavior = new YogOptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                this.setSuccess(ArmorItem.dispenseArmor(yogBlockSource, itemStack));
                return itemStack;
            }
        };
        YogDispenserBlock.registerBehavior(Items.CREEPER_HEAD, dispenseitembehavior);
        YogDispenserBlock.registerBehavior(Items.ZOMBIE_HEAD, dispenseitembehavior);
        YogDispenserBlock.registerBehavior(Items.DRAGON_HEAD, dispenseitembehavior);
        YogDispenserBlock.registerBehavior(Items.SKELETON_SKULL, dispenseitembehavior);
        YogDispenserBlock.registerBehavior(Items.PIGLIN_HEAD, dispenseitembehavior);
        YogDispenserBlock.registerBehavior(Items.PLAYER_HEAD, dispenseitembehavior);
        YogDispenserBlock.registerBehavior(
            Items.WITHER_SKELETON_SKULL,
            new YogOptionalDispenseItemBehavior() {
                @Override
                protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                    Level level = yogBlockSource.level();
                    Direction direction = yogBlockSource.state().getValue(YogDispenserBlock.FACING);
                    BlockPos blockpos = yogBlockSource.pos().relative(direction);
                    if (level.isEmptyBlock(blockpos) && WitherSkullBlock.canSpawnMob(level, blockpos, itemStack)) {
                        level.setBlock(
                            blockpos,
                            Blocks.WITHER_SKELETON_SKULL
                                .defaultBlockState()
                                .setValue(SkullBlock.ROTATION, Integer.valueOf(RotationSegment.convertToSegment(direction))),
                            3
                        );
                        level.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
                        BlockEntity blockentity = level.getBlockEntity(blockpos);
                        if (blockentity instanceof SkullBlockEntity) {
                            WitherSkullBlock.checkSpawn(level, blockpos, (SkullBlockEntity)blockentity);
                        }

                        itemStack.shrink(1);
                        this.setSuccess(true);
                    } else {
                        this.setSuccess(ArmorItem.dispenseArmor(yogBlockSource, itemStack));
                    }

                    return itemStack;
                }
            }
        );
        YogDispenserBlock.registerBehavior(Blocks.CARVED_PUMPKIN, new YogOptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                Level level = yogBlockSource.level();
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                CarvedPumpkinBlock carvedpumpkinblock = (CarvedPumpkinBlock)Blocks.CARVED_PUMPKIN;
                if (level.isEmptyBlock(blockpos) && carvedpumpkinblock.canSpawnGolem(level, blockpos)) {
                    if (!level.isClientSide) {
                        level.setBlock(blockpos, carvedpumpkinblock.defaultBlockState(), 3);
                        level.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
                    }

                    itemStack.shrink(1);
                    this.setSuccess(true);
                } else {
                    this.setSuccess(ArmorItem.dispenseArmor(yogBlockSource, itemStack));
                }

                return itemStack;
            }
        });
        YogDispenserBlock.registerBehavior(Blocks.SHULKER_BOX.asItem(), new YogShulkerBoxDispenseBehavior());

        for (DyeColor dyecolor : DyeColor.values()) {
            YogDispenserBlock.registerBehavior(ShulkerBoxBlock.getBlockByColor(dyecolor).asItem(), new YogShulkerBoxDispenseBehavior());
        }

        YogDispenserBlock.registerBehavior(
            Items.GLASS_BOTTLE.asItem(),
            new YogOptionalDispenseItemBehavior() {
                private ItemStack takeLiquid(YogBlockSource source, ItemStack emptyItem, ItemStack fullItem) {
                    source.level().gameEvent(null, GameEvent.FLUID_PICKUP, source.pos());
                    return this.consumeWithRemainder(source, emptyItem, fullItem);
                }

                @Override
                public ItemStack execute(YogBlockSource blockSource, ItemStack item) {
                    this.setSuccess(false);
                    ServerLevel serverlevel = blockSource.level();
                    BlockPos blockpos = blockSource.pos().relative(blockSource.state().getValue(YogDispenserBlock.FACING));
                    BlockState blockstate = serverlevel.getBlockState(blockpos);
                    if (blockstate.is(
                            BlockTags.BEEHIVES, p_338544_ -> p_338544_.hasProperty(BeehiveBlock.HONEY_LEVEL) && p_338544_.getBlock() instanceof BeehiveBlock
                        )
                        && blockstate.getValue(BeehiveBlock.HONEY_LEVEL) >= 5) {
                        ((BeehiveBlock)blockstate.getBlock())
                            .releaseBeesAndResetHoneyLevel(serverlevel, blockstate, blockpos, null, BeehiveBlockEntity.BeeReleaseStatus.BEE_RELEASED);
                        this.setSuccess(true);
                        return this.takeLiquid(blockSource, item, new ItemStack(Items.HONEY_BOTTLE));
                    } else if (serverlevel.getFluidState(blockpos).is(FluidTags.WATER)) {
                        this.setSuccess(true);
                        return this.takeLiquid(blockSource, item, PotionContents.createItemStack(Items.POTION, Potions.WATER));
                    } else {
                        return super.execute(blockSource, item);
                    }
                }
            }
        );
        YogDispenserBlock.registerBehavior(Items.GLOWSTONE, new YogOptionalDispenseItemBehavior() {
            @Override
            public ItemStack execute(YogBlockSource p_302423_, ItemStack p_123557_) {
                Direction direction = p_302423_.state().getValue(YogDispenserBlock.FACING);
                BlockPos blockpos = p_302423_.pos().relative(direction);
                Level level = p_302423_.level();
                BlockState blockstate = level.getBlockState(blockpos);
                this.setSuccess(true);
                if (blockstate.is(Blocks.RESPAWN_ANCHOR)) {
                    if (blockstate.getValue(RespawnAnchorBlock.CHARGE) != 4) {
                        RespawnAnchorBlock.charge(null, level, blockpos, blockstate);
                        p_123557_.shrink(1);
                    } else {
                        this.setSuccess(false);
                    }

                    return p_123557_;
                } else {
                    return super.execute(p_302423_, p_123557_);
                }
            }
        });
        YogDispenserBlock.registerBehavior(Items.SHEARS.asItem(), new YogShearsDispenseItemBehavior());
        YogDispenserBlock.registerBehavior(Items.BRUSH.asItem(), new YogOptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(YogBlockSource yogBlockSource, ItemStack itemStack) {
                ServerLevel serverlevel = yogBlockSource.level();
                BlockPos blockpos = yogBlockSource.pos().relative(yogBlockSource.state().getValue(YogDispenserBlock.FACING));
                List<Armadillo> list = serverlevel.getEntitiesOfClass(Armadillo.class, new AABB(blockpos), EntitySelector.NO_SPECTATORS);
                if (list.isEmpty()) {
                    this.setSuccess(false);
                    return itemStack;
                } else {
                    for (Armadillo armadillo : list) {
                        if (armadillo.brushOffScute()) {
                            itemStack.hurtAndBreak(16, serverlevel, null, p_348114_ -> {
                            });
                            return itemStack;
                        }
                    }

                    this.setSuccess(false);
                    return itemStack;
                }
            }
        });
        YogDispenserBlock.registerBehavior(Items.HONEYCOMB, new YogOptionalDispenseItemBehavior() {
            @Override
            public ItemStack execute(YogBlockSource p_302464_, ItemStack p_123567_) {
                BlockPos blockpos = p_302464_.pos().relative(p_302464_.state().getValue(YogDispenserBlock.FACING));
                Level level = p_302464_.level();
                BlockState blockstate = level.getBlockState(blockpos);
                Optional<BlockState> optional = HoneycombItem.getWaxed(blockstate);
                if (optional.isPresent()) {
                    level.setBlockAndUpdate(blockpos, optional.get());
                    level.levelEvent(3003, blockpos, 0);
                    p_123567_.shrink(1);
                    this.setSuccess(true);
                    return p_123567_;
                } else {
                    return super.execute(p_302464_, p_123567_);
                }
            }
        });
        YogDispenserBlock.registerBehavior(
            Items.POTION,
            new YogDefaultDispenseItemBehavior() {
                private final YogDefaultDispenseItemBehavior defaultDispenseItemBehavior = new YogDefaultDispenseItemBehavior();

                @Override
                public ItemStack execute(YogBlockSource p_302453_, ItemStack p_123413_) {
                    PotionContents potioncontents = p_123413_.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                    if (!potioncontents.is(Potions.WATER)) {
                        return this.defaultDispenseItemBehavior.dispense(p_302453_, p_123413_);
                    } else {
                        ServerLevel serverlevel = p_302453_.level();
                        BlockPos blockpos = p_302453_.pos();
                        BlockPos blockpos1 = p_302453_.pos().relative(p_302453_.state().getValue(YogDispenserBlock.FACING));
                        if (!serverlevel.getBlockState(blockpos1).is(BlockTags.CONVERTABLE_TO_MUD)) {
                            return this.defaultDispenseItemBehavior.dispense(p_302453_, p_123413_);
                        } else {
                            if (!serverlevel.isClientSide) {
                                for (int i = 0; i < 5; i++) {
                                    serverlevel.sendParticles(
                                        ParticleTypes.SPLASH,
                                        (double)blockpos.getX() + serverlevel.random.nextDouble(),
                                        (double)(blockpos.getY() + 1),
                                        (double)blockpos.getZ() + serverlevel.random.nextDouble(),
                                        1,
                                        0.0,
                                        0.0,
                                        0.0,
                                        1.0
                                    );
                                }
                            }

                            serverlevel.playSound(null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                            serverlevel.gameEvent(null, GameEvent.FLUID_PLACE, blockpos);
                            serverlevel.setBlockAndUpdate(blockpos1, Blocks.MUD.defaultBlockState());
                            return this.consumeWithRemainder(p_302453_, p_123413_, new ItemStack(Items.GLASS_BOTTLE));
                        }
                    }
                }
            }
        );
    }
}
