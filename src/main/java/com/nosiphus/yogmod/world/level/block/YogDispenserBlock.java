package com.nosiphus.yogmod.world.level.block;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.core.dispenser.YogBlockSource;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.entity.YogDispenserBlockEntity;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import java.util.Map;

public class YogDispenserBlock extends BaseEntityBlock {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MapCodec<YogDispenserBlock> CODEC = simpleCodec(YogDispenserBlock::new);
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
    private static final DefaultDispenseItemBehavior DEFAULT_BEHAVIOR = new DefaultDispenseItemBehavior();
    public static final Map<Item, DispenseItemBehavior> DISPENSER_REGISTRY = Util.make(
            new Object2ObjectOpenHashMap<>(), p_340795_ -> p_340795_.defaultReturnValue(DEFAULT_BEHAVIOR)
    );
    private static final int TRIGGER_DURATION = 4;

    @Override
    public MapCodec<? extends YogDispenserBlock> codec() {
        return CODEC;
    }

    public static void registerBehavior(ItemLike item, DispenseItemBehavior behavior) {
        DISPENSER_REGISTRY.put(item.asItem(), behavior);
    }

    public static void registerProjectileBehavior(ItemLike item) {
        DISPENSER_REGISTRY.put(item.asItem(), new ProjectileDispenseBehavior(item.asItem()));
    }

    public YogDispenserBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.valueOf(false)));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof YogDispenserBlockEntity) {
                player.openMenu((YogDispenserBlockEntity)blockentity);
                //player.awardStat(Stats.INSPECT_DISPENSER);
            }

            return InteractionResult.CONSUME;
        }
    }

    protected void dispenseFrom(ServerLevel level, BlockState state, BlockPos pos) {
        YogDispenserBlockEntity dispenserblockentity = level.getBlockEntity(pos, ModBlockEntityType.YOG_DISPENSER.get()).orElse(null);
        if (dispenserblockentity == null) {
            LOGGER.warn("Ignoring dispensing attempt for Dispenser without matching block entity at {}", pos);
        } else {
            YogBlockSource blocksource = new YogBlockSource(level, pos, state, dispenserblockentity);
            int i = dispenserblockentity.getRandomSlot(level.random);
            if (i < 0) {
                level.levelEvent(1001, pos, 0);
                level.gameEvent(GameEvent.BLOCK_ACTIVATE, pos, GameEvent.Context.of(dispenserblockentity.getBlockState()));
            } else {
                ItemStack itemstack = dispenserblockentity.getItem(i);
                DispenseItemBehavior dispenseitembehavior = this.getDispenseMethod(level, itemstack);
                if (dispenseitembehavior != DispenseItemBehavior.NOOP) {
                    dispenserblockentity.setItem(i, dispenseitembehavior.dispense(blocksource, itemstack));
                }
            }
        }
    }

    protected DispenseItemBehavior getDispenseMethod(Level level, ItemStack item) {
        return (DispenseItemBehavior)(!item.isItemEnabled(level.enabledFeatures()) ? DEFAULT_BEHAVIOR : DISPENSER_REGISTRY.get(item.getItem()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        boolean flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        boolean flag1 = state.getValue(TRIGGERED);
        if (flag && !flag1) {
            level.scheduleTick(pos, this, 4);
            level.setBlock(pos, state.setValue(TRIGGERED, Boolean.valueOf(true)), 2);
        } else if (!flag && flag1) {
            level.setBlock(pos, state.setValue(TRIGGERED, Boolean.valueOf(false)), 2);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.dispenseFrom(level, state, pos);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new YogDispenserBlockEntity(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    public static Position getDispensePosition(BlockSource blockSource) {
        return getDispensePosition(blockSource, 0.7, Vec3.ZERO);
    }

    public static Position getDispensePosition(BlockSource blockSource, double multiplier, Vec3 offset) {
        Direction direction = blockSource.state().getValue(FACING);
        return blockSource.center()
                .add(
                        multiplier * (double)direction.getStepX() + offset.x(),
                        multiplier * (double)direction.getStepY() + offset.y(),
                        multiplier * (double)direction.getStepZ() + offset.z()
                );
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TRIGGERED);
    }
}