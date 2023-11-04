package com.nosiphus.yogmod.core;

import com.nosiphus.yogmod.world.level.block.LayeredSinkBlock;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;
import java.util.function.Predicate;

public interface SinkInteraction {
    Map<Item, SinkInteraction> EMPTY = newInteractionMap();
    Map<Item, SinkInteraction> WATER = newInteractionMap();
    Map<Item, SinkInteraction> LAVA = newInteractionMap();
    Map<Item, SinkInteraction> POWDER_SNOW = newInteractionMap();

    SinkInteraction FILL_WATER = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        return emptyBucket(level, blockPos, player, interactionHand, itemStack, ModBlocks.WATER_SINK.get().defaultBlockState().setValue(LayeredSinkBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY);
    };
    SinkInteraction FILL_LAVA = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        return emptyBucket(level, blockPos, player, interactionHand, itemStack, ModBlocks.LAVA_SINK.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    };
    SinkInteraction FILL_POWDER_SNOW = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        return emptyBucket(level, blockPos, player, interactionHand, itemStack, ModBlocks.POWDER_SNOW_SINK.get().defaultBlockState().setValue(LayeredSinkBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY);
    };
    SinkInteraction SHULKER_BOX = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        Block block = Block.byItem(itemStack.getItem());
        if (!(block instanceof ShulkerBoxBlock)) {
            return InteractionResult.PASS;
        } else {
            if (!level.isClientSide) {
                ItemStack itemstack = new ItemStack(Blocks.SHULKER_BOX);
                if (itemStack.hasTag()) {
                    itemstack.setTag(itemStack.getTag().copy());
                }

                player.setItemInHand(interactionHand, itemstack);
                player.awardStat(Stats.CLEAN_SHULKER_BOX);
                LayeredSinkBlock.lowerFillLevel(blockState, level, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    };
    SinkInteraction BANNER = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if (BannerBlockEntity.getPatternCount(itemStack) <= 0) {
            return InteractionResult.PASS;
        } else {
            if (!level.isClientSide) {
                ItemStack itemstack = itemStack.copy();
                itemstack.setCount(1);
                BannerBlockEntity.removeLastPattern(itemstack);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                if (itemStack.isEmpty()) {
                    player.setItemInHand(interactionHand, itemstack);
                } else if (player.getInventory().add(itemstack)) {
                    player.inventoryMenu.sendAllDataToRemote();
                } else {
                    player.drop(itemstack, false);
                }

                player.awardStat(Stats.CLEAN_BANNER);
                LayeredSinkBlock.lowerFillLevel(blockState, level, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    };
    SinkInteraction DYED_ITEM = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        Item item = itemStack.getItem();
        if (!(item instanceof DyeableLeatherItem dyeableleatheritem)) {
            return InteractionResult.PASS;
        } else if (!dyeableleatheritem.hasCustomColor(itemStack)) {
            return InteractionResult.PASS;
        } else {
            if (!level.isClientSide) {
                dyeableleatheritem.clearColor(itemStack);
                player.awardStat(Stats.CLEAN_ARMOR);
                LayeredSinkBlock.lowerFillLevel(blockState, level, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    };

    static Object2ObjectOpenHashMap<Item, SinkInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (sinkInteractionMap) -> {
            sinkInteractionMap.defaultReturnValue((blockState, level, blockPos, player, interactionHand, itemStack) -> {
                return InteractionResult.PASS;
            });
        });
    }

    InteractionResult interact(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack);

    static void bootStrap() {
        addDefaultInteractions(EMPTY);
        EMPTY.put(Items.POTION, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if (PotionUtils.getPotion(itemStack) != Potions.WATER) {
                return InteractionResult.PASS;
            } else {
                if (!level.isClientSide) {
                    Item item = itemStack.getItem();
                    player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    level.setBlockAndUpdate(blockPos, ModBlocks.WATER_SINK.get().defaultBlockState());
                    level.playSound((Player)null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockPos);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        });
        addDefaultInteractions(WATER);
        WATER.put(Items.BUCKET, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillBucket(blockState, level, blockPos, player, interactionHand, itemStack, new ItemStack(Items.WATER_BUCKET), (blockState1) -> {
                return blockState1.getValue(LayeredSinkBlock.LEVEL) == 3;
            }, SoundEvents.BUCKET_FILL);
        });
        WATER.put(Items.GLASS_BOTTLE, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if (!level.isClientSide) {
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredSinkBlock.lowerFillLevel(blockState, level, blockPos);
                level.playSound((Player)null, blockPos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        });
        WATER.put(Items.POTION, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if (blockState.getValue(LayeredSinkBlock.LEVEL) != 3 && PotionUtils.getPotion(itemStack) == Potions.WATER) {
                if (!level.isClientSide) {
                    player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                    level.setBlockAndUpdate(blockPos, blockState.cycle(LayeredSinkBlock.LEVEL));
                    level.playSound((Player)null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockPos);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });
        WATER.put(Items.LEATHER_BOOTS, DYED_ITEM);
        WATER.put(Items.LEATHER_LEGGINGS, DYED_ITEM);
        WATER.put(Items.LEATHER_CHESTPLATE, DYED_ITEM);
        WATER.put(Items.LEATHER_HELMET, DYED_ITEM);
        WATER.put(Items.LEATHER_HORSE_ARMOR, DYED_ITEM);
        WATER.put(Items.WHITE_BANNER, BANNER);
        WATER.put(Items.GRAY_BANNER, BANNER);
        WATER.put(Items.BLACK_BANNER, BANNER);
        WATER.put(Items.BLUE_BANNER, BANNER);
        WATER.put(Items.BROWN_BANNER, BANNER);
        WATER.put(Items.CYAN_BANNER, BANNER);
        WATER.put(Items.GREEN_BANNER, BANNER);
        WATER.put(Items.LIGHT_BLUE_BANNER, BANNER);
        WATER.put(Items.LIGHT_GRAY_BANNER, BANNER);
        WATER.put(Items.LIME_BANNER, BANNER);
        WATER.put(Items.MAGENTA_BANNER, BANNER);
        WATER.put(Items.ORANGE_BANNER, BANNER);
        WATER.put(Items.PINK_BANNER, BANNER);
        WATER.put(Items.PURPLE_BANNER, BANNER);
        WATER.put(Items.RED_BANNER, BANNER);
        WATER.put(Items.YELLOW_BANNER, BANNER);
        WATER.put(Items.WHITE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.GRAY_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.BLACK_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.BLUE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.BROWN_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.CYAN_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.GREEN_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.LIGHT_BLUE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.LIGHT_GRAY_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.LIME_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.MAGENTA_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.ORANGE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.PINK_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.PURPLE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.RED_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.YELLOW_SHULKER_BOX, SHULKER_BOX);
        LAVA.put(Items.BUCKET, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillBucket(blockState, level, blockPos, player, interactionHand, itemStack, new ItemStack(Items.LAVA_BUCKET), (blockState1) -> {
                return true;
            }, SoundEvents.BUCKET_FILL_LAVA);
        });
        addDefaultInteractions(LAVA);
        POWDER_SNOW.put(Items.BUCKET, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillBucket(blockState, level, blockPos, player, interactionHand, itemStack, new ItemStack(Items.POWDER_SNOW_BUCKET), (blockState1) -> {
                return blockState1.getValue(LayeredSinkBlock.LEVEL) == 3;
            }, SoundEvents.BUCKET_FILL_POWDER_SNOW);
        });
        addDefaultInteractions(POWDER_SNOW);
    }

    static void addDefaultInteractions(Map<Item, SinkInteraction> sinkInteractionMap) {
        sinkInteractionMap.put(Items.LAVA_BUCKET, FILL_LAVA);
        sinkInteractionMap.put(Items.WATER_BUCKET, FILL_WATER);
        sinkInteractionMap.put(Items.POWDER_SNOW_BUCKET, FILL_POWDER_SNOW);
    }

    static InteractionResult fillBucket(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack, ItemStack itemStack1, Predicate<BlockState> blockStatePredicate, SoundEvent soundEvent) {
        if (!blockStatePredicate.test(blockState)) {
            return InteractionResult.PASS;
        } else {
            if (!level.isClientSide) {
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, itemStack1));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                level.setBlockAndUpdate(blockPos, ModBlocks.SINK.get().defaultBlockState());
                level.playSound((Player)null, blockPos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    static InteractionResult emptyBucket(Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack, BlockState blockState, SoundEvent soundEvent) {
        if (!level.isClientSide) {
            Item item = itemStack.getItem();
            player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.BUCKET)));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            level.setBlockAndUpdate(blockPos, blockState);
            level.playSound((Player)null, blockPos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockPos);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
