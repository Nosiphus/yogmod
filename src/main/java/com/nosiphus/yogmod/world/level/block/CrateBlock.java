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

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class CrateBlock extends AbstractCrateBlock<CrateBlockEntity> implements SimpleWaterloggedBlock {
    public static final MapCodec<CrateBlock> CODEC = simpleCodec(p_304364_ -> new CrateBlock(p_304364_, () -> ModBlockEntityType.CRATE.get()));
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<CrateType> TYPE = ModBlockStateProperties.CRATE_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    protected static final int AABB_OFFSET = 1;
    protected static final int AABB_HEIGHT = 14;
    protected static final VoxelShape NORTH_AABB = Block.box(1.0, 0.0, 0.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape EAST_AABB = Block.box(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    protected static final VoxelShape AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<Container>> CRATE_COMBINER = new DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<Container>>() {
        public Optional<Container> acceptDouble(CrateBlockEntity p_51591_, CrateBlockEntity p_51592_) {
            return Optional.of(new CompoundContainer(p_51591_, p_51592_));
        }

        public Optional<Container> acceptSingle(CrateBlockEntity p_51589_) {
            return Optional.of(p_51589_);
        }

        public Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };
    private static final DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<MenuProvider>>() {
        public Optional<MenuProvider> acceptDouble(final CrateBlockEntity p_51604_, final CrateBlockEntity p_51605_) {
            final Container container = new CompoundContainer(p_51604_, p_51605_);
            return Optional.of(new MenuProvider() {
                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int p_51622_, Inventory p_51623_, Player p_51624_) {
                    if (p_51604_.canOpen(p_51624_) && p_51605_.canOpen(p_51624_)) {
                        p_51604_.unpackLootTable(p_51623_.player);
                        p_51605_.unpackLootTable(p_51623_.player);
                        return CrateMenu.sixRows(p_51622_, p_51623_, container);
                    } else {
                        return null;
                    }
                }

                @Override
                public Component getDisplayName() {
                    if (p_51604_.hasCustomName()) {
                        return p_51604_.getDisplayName();
                    } else {
                        return (Component)(p_51605_.hasCustomName() ? p_51605_.getDisplayName() : Component.translatable("container.chestDouble"));
                    }
                }
            });
        }

        public Optional<MenuProvider> acceptSingle(CrateBlockEntity p_51602_) {
            return Optional.of(p_51602_);
        }

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
            this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, CrateType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false))
        );
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
        CrateType cratetype = state.getValue(TYPE);
        if (cratetype == CrateType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return cratetype == CrateType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
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
            CrateType cratetype = facingState.getValue(TYPE);
            if (state.getValue(TYPE) == CrateType.SINGLE
                && cratetype != CrateType.SINGLE
                && state.getValue(FACING) == facingState.getValue(FACING)
                && getConnectedDirection(facingState) == facing.getOpposite()) {
                return state.setValue(TYPE, cratetype.getOpposite());
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
            switch (getConnectedDirection(state)) {
                case NORTH:
                default:
                    return NORTH_AABB;
                case SOUTH:
                    return SOUTH_AABB;
                case WEST:
                    return WEST_AABB;
                case EAST:
                    return EAST_AABB;
            }
        }
    }

    public static Direction getConnectedDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == CrateType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        CrateType cratetype = CrateType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                cratetype = direction2.getCounterClockWise() == direction1.getOpposite() ? CrateType.RIGHT : CrateType.LEFT;
            }
        }

        if (cratetype == CrateType.SINGLE && !flag) {
            if (direction == this.candidatePartnerFacing(context, direction.getClockWise())) {
                cratetype = CrateType.LEFT;
            } else if (direction == this.candidatePartnerFacing(context, direction.getCounterClockWise())) {
                cratetype = CrateType.RIGHT;
            }
        }

        return this.defaultBlockState()
            .setValue(FACING, direction)
            .setValue(TYPE, cratetype)
            .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) && blockstate.getValue(TYPE) == CrateType.SINGLE ? blockstate.getValue(FACING) : null;
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
            MenuProvider menuprovider = this.getMenuProvider(state, level, pos);
            if (menuprovider != null) {
                player.openMenu(menuprovider);
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
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (override) {
            bipredicate = (p_51578_, p_51579_) -> false;
        } else {
            bipredicate = CrateBlock::isCrateBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(
            this.blockEntityType.get(), CrateBlock::getBlockType, CrateBlock::getConnectedDirection, FACING, state, level, pos, bipredicate
        );
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<CrateBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<CrateBlockEntity, Float2FloatFunction>() {
            public Float2FloatFunction acceptDouble(CrateBlockEntity crate, CrateBlockEntity otherCrate) {
                return p_51638_ -> Math.max(crate.getOpenNess(p_51638_), otherCrate.getOpenNess(p_51638_));
            }

            public Float2FloatFunction acceptSingle(CrateBlockEntity p_51631_) {
                return p_51631_::getOpenNess;
            }

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
        //return isBlockedCrateByBlock(level, pos) || isCatSittingOnCrate(level, pos);
    }

    private static boolean isBlockedCrateByBlock(BlockGetter level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return level.getBlockState(blockpos).isRedstoneConductor(level, blockpos);
    }

    /*
    private static boolean isCatSittingOnCrate(LevelAccessor level, BlockPos pos) {
        List<Cat> list = level.getEntitiesOfClass(
            Cat.class,
            new AABB(
                (double)pos.getX(),
                (double)(pos.getY() + 1),
                (double)pos.getZ(),
                (double)(pos.getX() + 1),
                (double)(pos.getY() + 2),
                (double)(pos.getZ() + 1)
            )
        );
        if (!list.isEmpty()) {
            for (Cat cat : list) {
                if (cat.isInSittingPose()) {
                    return true;
                }
            }
        }

        return false;
    }

     */

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
        return mirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());  // Forge: Fixed MC-134110 Structure mirroring breaking apart double chests
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
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof CrateBlockEntity) {
            ((CrateBlockEntity)blockentity).recheckOpen();
        }
    }
}
