package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.RecordPlayerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class RecordPlayerBlock extends BaseEntityBlock {

    public static final BooleanProperty HAS_RECORD = BlockStateProperties.HAS_RECORD;

    public RecordPlayerBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_RECORD, Boolean.valueOf(false)));
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(itemStack);
        if (compoundtag != null && compoundtag.contains("RecordItem")) {
            level.setBlock(blockPos, blockState.setValue(HAS_RECORD, Boolean.valueOf(true)), 2);
        }

    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (blockState.getValue(HAS_RECORD)) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof RecordPlayerBlockEntity) {
                RecordPlayerBlockEntity recordPlayerBlockEntity = (RecordPlayerBlockEntity) blockEntity;
                recordPlayerBlockEntity.popOutRecord();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean boolean1) {
        if (!blockState.is(blockState1.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof RecordPlayerBlockEntity) {
                RecordPlayerBlockEntity recordPlayerBlockEntity = (RecordPlayerBlockEntity) blockEntity;
                recordPlayerBlockEntity.popOutRecord();
            }
            super.onRemove(blockState, level, blockPos, blockState1, boolean1);
        }
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RecordPlayerBlockEntity(blockPos, blockState);
    }

    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    public int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        BlockEntity blockEntity = blockGetter.getBlockEntity(blockPos);
        if (blockEntity instanceof RecordPlayerBlockEntity recordPlayerBlockEntity) {
            if (recordPlayerBlockEntity.isRecordPlaying()) {
                return 15;
            }
        }
        return 0;
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof RecordPlayerBlockEntity recordPlayerBlockEntity) {
            Item item = recordPlayerBlockEntity.getFirstItem().getItem();
            if (item instanceof RecordItem) {
                return ((RecordItem)item).getAnalogOutput();
            }
        }

        return 0;
    }

    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_RECORD);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return blockState.getValue(HAS_RECORD) ? createTickerHelper(blockEntityType, ModBlockEntityType.RECORD_PLAYER.get(), RecordPlayerBlockEntity::playRecordTick) : null;
    }

}
