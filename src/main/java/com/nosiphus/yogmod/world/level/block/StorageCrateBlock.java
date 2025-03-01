package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.StorageCrateBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class StorageCrateBlock extends BaseEntityBlock {
    public static final MapCodec<StorageCrateBlock> CODEC = simpleCodec(StorageCrateBlock::new);
    private static final Component UNKNOWN_CONTENTS = Component.translatable("container.shulkerBox.unknownContents");
    private static final float OPEN_AABB_SIZE = 1.0F;
    public static final ResourceLocation CONTENTS;

    @Override
    protected MapCodec<StorageCrateBlock> codec() {
        return CODEC;
    }

    public StorageCrateBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any());
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StorageCrateBlockEntity(pos, state);
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return InteractionResult.CONSUME;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof StorageCrateBlockEntity storageCrateBlockEntity) {
                player.openMenu(storageCrateBlockEntity);
                //player.awardStat(Stats.OPEN_SHULKER_BOX);
                PiglinAi.angerNearbyPiglins(player, true);
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof StorageCrateBlockEntity storageCrateBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !storageCrateBlockEntity.isEmpty()) {
                ItemStack stack = new ItemStack(ModBlocks.STORAGE_CRATE.get());
                stack.applyComponents(blockEntity.collectComponents());
                ItemEntity itemEntity = new ItemEntity(level, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            } else {
                storageCrateBlockEntity.unpackLootTable(player);
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof StorageCrateBlockEntity storageCrateBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, (consumer) -> {
                for(int value = 0; value < storageCrateBlockEntity.getContainerSize(); ++value) {
                    consumer.accept(storageCrateBlockEntity.getItem(value));
                }

            });
        }
        return super.getDrops(state, builder);
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean status) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            super.onRemove(state, level, pos, newState, status);
            if (blockEntity instanceof StorageCrateBlockEntity) {
                level.updateNeighbourForOutputSignal(pos, state.getBlock());
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (stack.has(DataComponents.CONTAINER_LOOT)) {
            tooltipComponents.add(UNKNOWN_CONTENTS);
        }

        int i = 0;
        int j = 0;

        for (ItemStack itemstack : stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).nonEmptyItems()) {
            j++;
            if (i <= 4) {
                i++;
                tooltipComponents.add(Component.translatable("container.shulkerBox.itemCount", itemstack.getHoverName(), itemstack.getCount()));
            }
        }

        if (j - i > 0) {
            tooltipComponents.add(Component.translatable("container.shulkerBox.more", j - i).withStyle(ChatFormatting.ITALIC));
        }
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container)level.getBlockEntity(pos));
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack itemstack = super.getCloneItemStack(level, pos, state);
        level.getBlockEntity(pos, ModBlockEntityType.STORAGE_CRATE.get()).ifPresent(storageCrateBlockEntity -> storageCrateBlockEntity.saveToItem(itemstack, level.registryAccess()));
        return itemstack;
    }

    static {
        CONTENTS = ResourceLocation.withDefaultNamespace("contents");
    }

}
