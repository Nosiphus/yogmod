package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.StorageCrateBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class StorageCrateBlock extends BaseEntityBlock {
    private static final float OPEN_AABB_SIZE = 1.0F;
    public static final ResourceLocation CONTENTS;
    
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

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return InteractionResult.CONSUME;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof StorageCrateBlockEntity) {
                StorageCrateBlockEntity storageCrateBlockEntity = (StorageCrateBlockEntity)blockEntity;
                player.openMenu(storageCrateBlockEntity);
                //player.awardStat(Stats.OPEN_SHULKER_BOX);
                PiglinAi.angerNearbyPiglins(player, true);
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof StorageCrateBlockEntity storageCrateBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !storageCrateBlockEntity.isEmpty()) {
                ItemStack stack = new ItemStack(ModBlocks.STORAGE_CRATE.get());
                blockEntity.saveToItem(stack);
                if (storageCrateBlockEntity.hasCustomName()) {
                    stack.setHoverName(storageCrateBlockEntity.getCustomName());
                }

                ItemEntity itemEntity = new ItemEntity(level, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            } else {
                storageCrateBlockEntity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity blockEntity = (BlockEntity)builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof StorageCrateBlockEntity storageCrateBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, (consumer) -> {
                for(int value = 0; value < storageCrateBlockEntity.getContainerSize(); ++value) {
                    consumer.accept(storageCrateBlockEntity.getItem(value));
                }

            });
        }
        return super.getDrops(state, builder);
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity livingEntity, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof StorageCrateBlockEntity) {
                ((StorageCrateBlockEntity)blockEntity).setCustomName(stack.getHoverName());
            }
        }
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean status) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof StorageCrateBlockEntity) {
                level.updateNeighbourForOutputSignal(pos, state.getBlock());
            }
            super.onRemove(state, level, pos, newState, status);
        }
    }

    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, getter, list, flag);
        CompoundTag tag = BlockItem.getBlockEntityData(stack);
        if (tag != null) {
            if (tag.contains("LootTable", 8)) {
                list.add(Component.literal("???????"));
            }

            if (tag.contains("Items", 9)) {
                NonNullList<ItemStack> itemStackList = NonNullList.withSize(27, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(tag, itemStackList);
                int $$6 = 0;
                int $$7 = 0;
                Iterator iterator = itemStackList.iterator();

                while(iterator.hasNext()) {
                    ItemStack itemStack = (ItemStack)iterator.next();
                    if (!itemStack.isEmpty()) {
                        ++$$7;
                        if ($$6 <= 4) {
                            ++$$6;
                            MutableComponent mutableComponent = itemStack.getHoverName().copy();
                            mutableComponent.append(" x").append(String.valueOf(itemStack.getCount()));
                            list.add(mutableComponent);
                        }
                    }
                }

                if ($$7 - $$6 > 0) {
                    list.add(Component.translatable("container.shulkerBox.more", new Object[]{$$7 - $$6}).withStyle(ChatFormatting.ITALIC));
                }
            }
        }

    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container)level.getBlockEntity(pos));
    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        ItemStack stack = super.getCloneItemStack(getter, pos, state);
        getter.getBlockEntity(pos, ModBlockEntityType.STORAGE_CRATE.get()).ifPresent((blockEntity) -> {
            blockEntity.saveToItem(stack);
        });
        return stack;
    }

    static {
        CONTENTS = new ResourceLocation("contents");
    }

}
