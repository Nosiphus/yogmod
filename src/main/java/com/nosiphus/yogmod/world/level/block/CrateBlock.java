package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.CrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import com.nosiphus.yogmod.world.level.block.state.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class CrateBlock extends AbstractCrateBlock<CrateBlockEntity> implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<CrateType> TYPE = ModBlockStateProperties.CRATE_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    protected static final int AABB_OFFSET = 1;
    protected static final int AABB_HEIGHT = 14;
    protected static final VoxelShape NORTH_AABB = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape EAST_AABB = Block.box(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    private static final DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<Container>> CRATE_COMBINER = new DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<Container>>() {
        public Optional<Container> acceptDouble(CrateBlockEntity crateBlockEntity, CrateBlockEntity crateBlockEntity1) {
            return Optional.of(new CompoundContainer(crateBlockEntity, crateBlockEntity1));
        }

        public Optional<Container> acceptSingle(CrateBlockEntity crateBlockEntity) {
            return Optional.of(crateBlockEntity);
        }

        public Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<CrateBlockEntity, Optional<MenuProvider>>() {
        public Optional<MenuProvider> acceptDouble(final CrateBlockEntity crateBlockEntity, final CrateBlockEntity crateBlockEntity1) {
            final Container container = new CompoundContainer(crateBlockEntity, crateBlockEntity1);
            return Optional.of(new MenuProvider() {
                @Nullable
                public AbstractContainerMenu createMenu(int ID, Inventory inventory, Player player) {
                    if (crateBlockEntity.canOpen(player) && crateBlockEntity1.canOpen(player)) {
                        crateBlockEntity.unpackLootTable(inventory.player);
                        crateBlockEntity1.unpackLootTable(inventory.player);
                        return CrateMenu.sixRows(ID, inventory, container);
                    } else {
                        return null;
                    }
                }
                public Component getDisplayName() {
                    if (crateBlockEntity.hasCustomName()) {
                        return crateBlockEntity.getDisplayName();
                    } else {
                        return (Component) (crateBlockEntity1.hasCustomName() ? crateBlockEntity1.getDisplayName() : Component.translatable("container.nfm.double_crate"));
                    }
                }
            });
        }

        public Optional<MenuProvider> acceptSingle(CrateBlockEntity crateBlockEntity) {
            return Optional.of(crateBlockEntity);
        }

        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }

    };

    public CrateBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends CrateBlockEntity>> blockEntityTypeSupplier) {
        super(properties, blockEntityTypeSupplier);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, CrateType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState blockState) {
        CrateType crateType = blockState.getValue(TYPE);
        if (crateType == CrateType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return crateType == CrateType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if (blockState1.is(this) && direction.getAxis().isHorizontal()) {
            CrateType crateType = blockState1.getValue(TYPE);
            if (blockState.getValue(TYPE) == CrateType.SINGLE && crateType != CrateType.SINGLE && blockState.getValue(FACING) == blockState1.getValue(FACING) && getConnectedDirection(blockState1) == direction.getOpposite()) {
                return blockState.setValue(TYPE, crateType.getOpposite());
            }
        } else if (getConnectedDirection(blockState) == direction) {
            return blockState.setValue(TYPE, CrateType.SINGLE);
        }

        return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);

    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.getValue(TYPE) == CrateType.SINGLE) {
            return AABB;
        } else {
            switch (getConnectedDirection(blockState)) {
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

    public static Direction getConnectedDirection(BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        return blockState.getValue(TYPE) == CrateType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        CrateType crateType = CrateType.SINGLE;
        Direction direction = blockPlaceContext.getHorizontalDirection().getOpposite();
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        boolean flag = blockPlaceContext.isSecondaryUseActive();
        Direction direction1 = blockPlaceContext.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(blockPlaceContext, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                crateType = direction2.getCounterClockWise() == direction1.getOpposite() ? CrateType.RIGHT : CrateType.LEFT;
            }
        }

        if (crateType == CrateType.SINGLE && !flag) {
            if (direction == this.candidatePartnerFacing(blockPlaceContext, direction.getClockWise())) {
                crateType = CrateType.LEFT;
            } else if (direction == this.candidatePartnerFacing(blockPlaceContext, direction.getCounterClockWise())) {
                crateType = CrateType.RIGHT;
            }
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, crateType).setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Nullable
    private Direction candidatePartnerFacing(BlockPlaceContext blockPlaceContext, Direction direction) {
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(direction));
        return blockState.is(this) && blockState.getValue(TYPE) == CrateType.SINGLE ? blockState.getValue(FACING) : null;
    }





    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof CrateBlockEntity) {
            ((CrateBlockEntity) blockEntity).recheckOpen();
        }
    }

}
