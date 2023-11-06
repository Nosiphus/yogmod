package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.DispenserBlockEntity;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;

public class DispenserBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
    private static final Map<Item, DispenseItemBehavior> DISPENSER_REGISTRY = Util.make(new Object2ObjectOpenHashMap<>(), (itemBehavior) -> {
        itemBehavior.defaultReturnValue(new DefaultDispenseItemBehavior());
    });
    private static final int TRIGGER_DURATION = 4;

    public static void registerBehavior(ItemLike itemLike, DispenseItemBehavior itemBehavior) {
        DISPENSER_REGISTRY.put(itemLike.asItem(), itemBehavior);
    }

    public DispenserBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.valueOf(false)));
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof DispenserBlockEntity) {
                player.openMenu((DispenserBlockEntity) blockentity);
                player.awardStat(Stats.INSPECT_DISPENSER);
            }
            return InteractionResult.CONSUME;
        }
    }

    protected void dispenseFrom(ServerLevel serverLevel, BlockPos blockPos) {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(serverLevel, blockPos);
        DispenserBlockEntity dispenserblockentity = blocksourceimpl.getEntity();
        int i = dispenserblockentity.getRandomSlot(serverLevel.random);
        if (i < 0) {
            serverLevel.levelEvent(1001, blockPos, 0);
            serverLevel.gameEvent((Entity)null, GameEvent.DISPENSE_FAIL, blockPos);
        } else {
            ItemStack itemstack = dispenserblockentity.getItem(i);
            DispenseItemBehavior dispenseitembehavior = this.getDispenseMethod(itemstack);
            if (dispenseitembehavior != DispenseItemBehavior.NOOP) {
                dispenserblockentity.setItem(i, dispenseitembehavior.dispense(blocksourceimpl, itemstack));
            }

        }
    }

    protected DispenseItemBehavior getDispenseMethod(ItemStack p_52667_) {
        return DISPENSER_REGISTRY.get(p_52667_.getItem());
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean boolean1) {
        boolean flag = level.hasNeighborSignal(blockPos) || level.hasNeighborSignal(blockPos.above());
        boolean flag1 = blockState.getValue(TRIGGERED);
        if (flag && !flag1) {
            level.scheduleTick(blockPos, this, 4);
            level.setBlock(blockPos, blockState.setValue(TRIGGERED, Boolean.valueOf(true)), 4);
        } else if (!flag && flag1) {
            level.setBlock(blockPos, blockState.setValue(TRIGGERED, Boolean.valueOf(false)), 4);
        }

    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        this.dispenseFrom(serverLevel, blockPos);
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DispenserBlockEntity(blockPos, blockState);
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getNearestLookingDirection().getOpposite());
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof DispenserBlockEntity) {
                ((DispenserBlockEntity)blockentity).setCustomName(itemStack.getHoverName());
            }
        }

    }

    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean boolean1) {
        if (!blockState.is(blockState1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof DispenserBlockEntity) {
                Containers.dropContents(level, blockPos, (DispenserBlockEntity)blockentity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(blockState, level, blockPos, blockState1, boolean1);
        }
    }

    public static Position getDispensePosition(BlockSource blockSource) {
        Direction direction = blockSource.getBlockState().getValue(FACING);
        double d0 = blockSource.x() + 0.7D * (double)direction.getStepX();
        double d1 = blockSource.y() + 0.7D * (double)direction.getStepY();
        double d2 = blockSource.z() + 0.7D * (double)direction.getStepZ();
        return new PositionImpl(d0, d1, d2);
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState p_52716_, Rotation rotation) {
        return p_52716_.setValue(FACING, rotation.rotate(p_52716_.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinitionBuilder) {
        stateDefinitionBuilder.add(FACING, TRIGGERED);
    }

}
