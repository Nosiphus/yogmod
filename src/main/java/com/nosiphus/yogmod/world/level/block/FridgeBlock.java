package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.inventory.PlayerFridgeContainer;
import com.nosiphus.yogmod.world.level.block.entity.CrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.FridgeBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class FridgeBlock extends AbstractCrateBlock<FridgeBlockEntity> implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    private static final Component CONTAINER_TITLE = Component.translatable("container.yogmod.fridge");
    public FridgeBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends FridgeBlockEntity>> blockEntityTypeSupplier) {
        super(properties, blockEntityTypeSupplier);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }
    public DoubleBlockCombiner.NeighborCombineResult<? extends CrateBlockEntity> combine(BlockState blockState, Level level, BlockPos blockPos, boolean isDouble) {
        return DoubleBlockCombiner.Combiner::acceptNone;
    }

    public VoxelShape getShape(BlockState blockState) {
        return SHAPE;
    }

    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        PlayerFridgeContainer playerFridgeContainer = player.getFridgeInventory();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (playerFridgeContainer != null && blockEntity instanceof FridgeBlockEntity) {
            BlockPos blockPos1 = blockPos.above();
            if (level.getBlockState(blockPos1).isRedstoneConductor(level, blockPos1)) {
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                FridgeBlockEntity fridgeBlockEntity = (FridgeBlockEntity) blockEntity;
                playerFridgeContainer.setActiveFridge(fridgeBlockEntity);
                player.openMenu(new SimpleMenuProvider((ID, inventory, player1) -> {
                    return ChestMenu.threeRows(ID, inventory, playerFridgeContainer);
                }, CONTAINER_TITLE));
                player.awardStat(Stats.OPEN_ENDERCHEST);
                PiglinAi.angerNearbyPiglins(player, true);
                return InteractionResult.CONSUME;
            }
        } else {
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FridgeBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, ModBlockEntityType.FRIDGE.get(), FridgeBlockEntity::lidAnimateTick) : null;
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        for (int i = 0; i < 3; ++i) {
            int j = randomSource.nextInt(2) * 2 - 1;
            int k = randomSource.nextInt(2) * 2 - 1;
            double d0 = (double) blockPos.getX() + 0.5D + 0.25D * (double) j;
            double d1 = (double) ((float) blockPos.getY() + randomSource.nextFloat());
            double d2 = (double)blockPos.getZ() + 0.5D + 0.25D * (double)k;
            double d3 = (double)(randomSource.nextFloat() * (float)j);
            double d4 = ((double)randomSource.nextFloat() - 0.5D) * 0.125D;
            double d5 = (double)(randomSource.nextFloat() * (float)k);
            level.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
        }
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof FridgeBlockEntity) {
            ((FridgeBlockEntity) blockEntity).recheckOpen();
        }
    }


}
