package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.inventory.CrateMenu;
import com.nosiphus.yogmod.world.level.block.entity.CrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import com.nosiphus.yogmod.world.level.block.state.properties.ModBlockStateProperties;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class CrateBlock extends AbstractCrateBlock<CrateBlockEntity> implements SimpleWaterloggedBlock {

    public static final MapCodec<CrateBlock> CODEC = simpleCodec(props -> new CrateBlock(props, () -> ModBlockEntityType.CRATE.get()));
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<CrateType> TYPE = ModBlockStateProperties.CRATE_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape NORTH_AABB = Block.box(1.0, 0.0, 0.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape EAST_AABB = Block.box(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    protected static final VoxelShape AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);

    private static final DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<Container>> CRATE_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        @Override
        public Optional<Container> acceptDouble(CrateBlockEntity first, CrateBlockEntity second) {
            return Optional.of(new CompoundContainer(first, second));
        }

        @Override
        public Optional<Container> acceptSingle(CrateBlockEntity single) {
            return Optional.of(single);
        }

        @Override
        public Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        @Override
        public Optional<MenuProvider> acceptDouble(final CrateBlockEntity first, final CrateBlockEntity second) {
            final Container container = new CompoundContainer(first, second);
            return Optional.of(new MenuProvider() {
                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
                    if (first.canOpen(player) && second.canOpen(player)) {
                        first.unpackLootTable(inventory.player);
                        second.unpackLootTable(inventory.player);
                        return CrateMenu.sixRows(containerId, inventory, container);
                    } else {
                        return null;
                    }
                }

                @Override
                public Component getDisplayName() {
                    if (first.hasCustomName()) {
                        return first.getDisplayName();
                    } else {
                        return second.hasCustomName() ? second.getDisplayName() : Component.translatable("container.chestDouble");
                    }
                }
            });
        }

        @Override
        public Optional<MenuProvider> acceptSingle(CrateBlockEntity single) {
            return Optional.of(single);
        }

        @Override
        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    @Override
    public MapCodec<? extends CrateBlock> codec() {
        return CODEC;
    }

    public CrateBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends CrateBlockEntity>> blockEntityType) {
        super(properties, blockEntityType);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, CrateType.SINGLE).setValue(WATERLOGGED, false)
        );
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
        CrateType crateType = state.getValue(TYPE);
        if (crateType == CrateType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return crateType == CrateType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (facingState.is(this) && facing.getAxis().isHorizontal()) {
            CrateType crateType = facingState.getValue(TYPE);
            if (state.getValue(TYPE) == CrateType.SINGLE
                    && crateType != CrateType.SINGLE
                    && state.getValue(FACING) == facingState.getValue(FACING)
                    && getConnectedDirection(facingState) == facing.getOpposite()) {
                return state.setValue(TYPE, crateType.getOpposite());
            }
        } else if (getConnectedDirection(state) == facing) {
            return state.setValue(TYPE, CrateType.SINGLE);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(TYPE) == CrateType.SINGLE) {
            return AABB;
        } else {
            return switch (getConnectedDirection(state)) {
                case SOUTH -> SOUTH_AABB;
                case WEST -> WEST_AABB;
                case EAST -> EAST_AABB;
                default -> NORTH_AABB;
            };
        }
    }

    public static Direction getConnectedDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == CrateType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        CrateType crateType = CrateType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        boolean secondaryUse = context.isSecondaryUseActive();
        Direction clickedFace = context.getClickedFace();

        if (clickedFace.getAxis().isHorizontal() && secondaryUse) {
            Direction partnerFacing = this.candidatePartnerFacing(context, clickedFace.getOpposite());
            if (partnerFacing != null && partnerFacing.getAxis() != clickedFace.getAxis()) {
                direction = partnerFacing;
                crateType = partnerFacing.getCounterClockWise() == clickedFace.getOpposite() ? CrateType.RIGHT : CrateType.LEFT;
            }
        }

        if (crateType == CrateType.SINGLE && !secondaryUse) {
            if (direction == this.candidatePartnerFacing(context, direction.getClockWise())) {
                crateType = CrateType.LEFT;
            } else if (direction == this.candidatePartnerFacing(context, direction.getCounterClockWise())) {
                crateType = CrateType.RIGHT;
            }
        }

        return this.defaultBlockState()
                .setValue(FACING, direction)
                .setValue(TYPE, crateType)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direction) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockState.is(this) && blockState.getValue(TYPE) == CrateType.SINGLE ? blockState.getValue(FACING) : null;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuProvider = this.getMenuProvider(state, level, pos);
            if (menuProvider != null) {
                player.openMenu(menuProvider);
                player.awardStat(this.getOpenChestStat());
                PiglinAi.angerNearbyPiglins(player, true);
            }
            return InteractionResult.CONSUME;
        }
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    public BlockEntityType<? extends CrateBlockEntity> blockEntityType() {
        return this.blockEntityType.get();
    }

    @Nullable
    public static Container getContainer(CrateBlock crate, BlockState state, Level level, BlockPos pos, boolean override) {
        return crate.combine(state, level, pos, override).apply(CRATE_COMBINER).orElse(null);
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends CrateBlockEntity> combine(
            BlockState state, Level level, BlockPos pos, boolean override
    ) {
        BiPredicate<LevelAccessor, BlockPos> isBlocked = override ? (lvl, blockPos) -> false : CrateBlock::isCrateBlockedAt;
        return DoubleBlockCombiner.combineWithNeigbour(
                this.blockEntityType.get(), CrateBlock::getBlockType, CrateBlock::getConnectedDirection, FACING, state, level, pos, isBlocked
        );
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<CrateBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<>() {
            @Override
            public Float2FloatFunction acceptDouble(CrateBlockEntity crate, CrateBlockEntity otherCrate) {
                return partialTicks -> Math.max(crate.getOpenNess(partialTicks), otherCrate.getOpenNess(partialTicks));
            }

            @Override
            public Float2FloatFunction acceptSingle(CrateBlockEntity crate) {
                return crate::getOpenNess;
            }

            @Override
            public Float2FloatFunction acceptNone() {
                return lid::getOpenNess;
            }
        };
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CrateBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, this.blockEntityType(), CrateBlockEntity::lidAnimateTick) : null;
    }

    public static boolean isCrateBlockedAt(LevelAccessor level, BlockPos pos) {
        return isBlockedCrateByBlock(level, pos);
    }

    private static boolean isBlockedCrateByBlock(BlockGetter level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        return level.getBlockState(abovePos).isRedstoneConductor(level, abovePos);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, blockState, level, pos, false));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        BlockState rotated = state.rotate(mirror.getRotation(state.getValue(FACING)));
        return mirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CrateBlockEntity crateBlockEntity) {
            crateBlockEntity.recheckOpen();
        }
    }
}