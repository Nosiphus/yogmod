package com.nosiphus.yogmod.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public class WireBlock extends Block {

    public static final MapCodec<WireBlock> CODEC = simpleCodec(WireBlock::new);
    public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.NORTH_REDSTONE;
    public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.EAST_REDSTONE;
    public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.SOUTH_REDSTONE;
    public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.WEST_REDSTONE;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public static final Map<Direction, EnumProperty<RedstoneSide>> PROPERTY_BY_DIRECTION = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, NORTH,
            Direction.EAST, EAST,
            Direction.SOUTH, SOUTH,
            Direction.WEST, WEST
    ));

    private static final VoxelShape SHAPE_DOT = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);

    private static final Map<Direction, VoxelShape> SHAPES_FLOOR = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(3.0D, 0.0D, 0.0D, 13.0D, 1.0D, 13.0D),
            Direction.SOUTH, Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 16.0D),
            Direction.EAST, Block.box(3.0D, 0.0D, 3.0D, 16.0D, 1.0D, 13.0D),
            Direction.WEST, Block.box(0.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D)
    ));

    private static final Map<Direction, VoxelShape> SHAPES_UP = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Shapes.or(SHAPES_FLOOR.get(Direction.NORTH), Block.box(3.0D, 0.0D, 0.0D, 13.0D, 16.0D, 1.0D)),
            Direction.SOUTH, Shapes.or(SHAPES_FLOOR.get(Direction.SOUTH), Block.box(3.0D, 0.0D, 15.0D, 13.0D, 16.0D, 16.0D)),
            Direction.EAST, Shapes.or(SHAPES_FLOOR.get(Direction.EAST), Block.box(15.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D)),
            Direction.WEST, Shapes.or(SHAPES_FLOOR.get(Direction.WEST), Block.box(0.0D, 0.0D, 3.0D, 1.0D, 16.0D, 13.0D))
    ));

    private static final Map<BlockState, VoxelShape> SHAPES_CACHE = Maps.newHashMap();

    private static final Vec3[] COLORS = Util.make(new Vec3[16], colors -> {
        for (int i = 0; i <= 15; ++i) {
            float f = (float) i / 15.0F;
            float r = f * 0.6F + (f > 0.0F ? 0.4F : 0.3F);
            float g = Mth.clamp(f * f * 0.7F - 0.5F, 0.0F, 1.0F);
            float b = Mth.clamp(f * f * 0.6F - 0.7F, 0.0F, 1.0F);
            colors[i] = new Vec3(r, g, b);
        }
    });

    private final BlockState crossState;
    private boolean shouldSignal = true;

    @Override
    public MapCodec<WireBlock> codec() {
        return CODEC;
    }

    public WireBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, RedstoneSide.NONE)
                .setValue(EAST, RedstoneSide.NONE)
                .setValue(SOUTH, RedstoneSide.NONE)
                .setValue(WEST, RedstoneSide.NONE)
                .setValue(POWER, 0));

        this.crossState = this.defaultBlockState()
                .setValue(NORTH, RedstoneSide.SIDE)
                .setValue(EAST, RedstoneSide.SIDE)
                .setValue(SOUTH, RedstoneSide.SIDE)
                .setValue(WEST, RedstoneSide.SIDE);

        for (BlockState blockState : this.getStateDefinition().getPossibleStates()) {
            if (blockState.getValue(POWER) == 0) {
                SHAPES_CACHE.put(blockState, this.calculateShape(blockState));
            }
        }
    }

    private VoxelShape calculateShape(BlockState state) {
        VoxelShape shape = SHAPE_DOT;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide side = state.getValue(PROPERTY_BY_DIRECTION.get(direction));
            if (side == RedstoneSide.SIDE) {
                shape = Shapes.or(shape, SHAPES_FLOOR.get(direction));
            } else if (side == RedstoneSide.UP) {
                shape = Shapes.or(shape, SHAPES_UP.get(direction));
            }
        }
        return shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPES_CACHE.get(state.setValue(POWER, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getConnectionState(context.getLevel(), this.crossState, context.getClickedPos());
    }

    private BlockState getConnectionState(BlockGetter getter, BlockState state, BlockPos pos) {
        boolean isDotBefore = isDot(state);
        state = this.getMissingConnections(getter, this.defaultBlockState().setValue(POWER, state.getValue(POWER)), pos);
        if (isDotBefore && isDot(state)) {
            return state;
        } else {
            boolean northConnected = state.getValue(NORTH).isConnected();
            boolean southConnected = state.getValue(SOUTH).isConnected();
            boolean eastConnected = state.getValue(EAST).isConnected();
            boolean westConnected = state.getValue(WEST).isConnected();

            boolean noNorthSouth = !northConnected && !southConnected;
            boolean noEastWest = !eastConnected && !westConnected;

            if (!westConnected && noNorthSouth) {
                state = state.setValue(WEST, RedstoneSide.SIDE);
            }
            if (!eastConnected && noNorthSouth) {
                state = state.setValue(EAST, RedstoneSide.SIDE);
            }
            if (!northConnected && noEastWest) {
                state = state.setValue(NORTH, RedstoneSide.SIDE);
            }
            if (!southConnected && noEastWest) {
                state = state.setValue(SOUTH, RedstoneSide.SIDE);
            }
            return state;
        }
    }

    private BlockState getMissingConnections(BlockGetter getter, BlockState state, BlockPos pos) {
        boolean canConnectAbove = !getter.getBlockState(pos.above()).isRedstoneConductor(getter, pos);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!state.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected()) {
                RedstoneSide side = this.getConnectingSide(getter, pos, direction, canConnectAbove);
                state = state.setValue(PROPERTY_BY_DIRECTION.get(direction), side);
            }
        }
        return state;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN) {
            return state;
        } else if (direction == Direction.UP) {
            return this.getConnectionState(levelAccessor, state, pos);
        } else {
            RedstoneSide side = this.getConnectingSide(levelAccessor, pos, direction);
            return side.isConnected() == state.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected() && !isCross(state)
                    ? state.setValue(PROPERTY_BY_DIRECTION.get(direction), side)
                    : this.getConnectionState(levelAccessor, this.crossState.setValue(POWER, state.getValue(POWER)).setValue(PROPERTY_BY_DIRECTION.get(direction), side), pos);
        }
    }

    private static boolean isCross(BlockState state) {
        return state.getValue(NORTH).isConnected() && state.getValue(SOUTH).isConnected() && state.getValue(EAST).isConnected() && state.getValue(WEST).isConnected();
    }

    private static boolean isDot(BlockState state) {
        return !state.getValue(NORTH).isConnected() && !state.getValue(SOUTH).isConnected() && !state.getValue(EAST).isConnected() && !state.getValue(WEST).isConnected();
    }

    @Override
    protected void updateIndirectNeighbourShapes(BlockState state, LevelAccessor levelAccessor, BlockPos pos, int flags, int recursionLeft) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide side = state.getValue(PROPERTY_BY_DIRECTION.get(direction));
            if (side != RedstoneSide.NONE && !levelAccessor.getBlockState(mutablePos.setWithOffset(pos, direction)).is(this)) {
                mutablePos.move(Direction.DOWN);
                BlockState stateBelow = levelAccessor.getBlockState(mutablePos);
                if (stateBelow.is(this)) {
                    BlockPos relativePos = mutablePos.relative(direction.getOpposite());
                    levelAccessor.neighborShapeChanged(direction.getOpposite(), levelAccessor.getBlockState(relativePos), mutablePos, relativePos, flags, recursionLeft);
                }

                mutablePos.setWithOffset(pos, direction).move(Direction.UP);
                BlockState stateAbove = levelAccessor.getBlockState(mutablePos);
                if (stateAbove.is(this)) {
                    BlockPos relativePos = mutablePos.relative(direction.getOpposite());
                    levelAccessor.neighborShapeChanged(direction.getOpposite(), levelAccessor.getBlockState(relativePos), mutablePos, relativePos, flags, recursionLeft);
                }
            }
        }
    }

    private RedstoneSide getConnectingSide(BlockGetter getter, BlockPos pos, Direction direction) {
        return this.getConnectingSide(getter, pos, direction, !getter.getBlockState(pos.above()).isRedstoneConductor(getter, pos));
    }

    private RedstoneSide getConnectingSide(BlockGetter getter, BlockPos pos, Direction direction, boolean canConnectAbove) {
        BlockPos relativePos = pos.relative(direction);
        BlockState relativeState = getter.getBlockState(relativePos);

        if (canConnectAbove) {
            boolean canSurvive = this.canSurviveOn(getter, relativePos, relativeState);
            if (canSurvive && shouldConnectTo(getter.getBlockState(relativePos.above()), null)) {
                if (relativeState.isFaceSturdy(getter, relativePos, direction.getOpposite())) {
                    return RedstoneSide.UP;
                }
                return RedstoneSide.SIDE;
            }
        }

        if (shouldConnectTo(relativeState, direction)) {
            return RedstoneSide.SIDE;
        } else if (relativeState.isRedstoneConductor(getter, relativePos)) {
            return RedstoneSide.NONE;
        } else {
            BlockPos belowPos = relativePos.below();
            return shouldConnectTo(getter.getBlockState(belowPos), null) ? RedstoneSide.SIDE : RedstoneSide.NONE;
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = levelReader.getBlockState(belowPos);
        return this.canSurviveOn(levelReader, belowPos, belowState);
    }

    private boolean canSurviveOn(BlockGetter getter, BlockPos pos, BlockState state) {
        return state.isFaceSturdy(getter, pos, Direction.UP) || state.is(Blocks.HOPPER);
    }

    private void updatePowerStrength(Level level, BlockPos pos, BlockState state) {
        int targetPower = this.calculateTargetStrength(level, pos);
        if (state.getValue(POWER) != targetPower) {
            if (level.getBlockState(pos) == state) {
                level.setBlock(pos, state.setValue(POWER, targetPower), 2);
            }

            Set<BlockPos> affectedPositions = Sets.newHashSet();
            affectedPositions.add(pos);

            for (Direction direction : Direction.values()) {
                affectedPositions.add(pos.relative(direction));
            }

            for (BlockPos affectedPos : affectedPositions) {
                level.updateNeighborsAt(affectedPos, this);
            }
        }
    }

    private int calculateTargetStrength(Level level, BlockPos pos) {
        this.shouldSignal = false;
        int maxNeighborSignal = level.getBestNeighborSignal(pos);
        this.shouldSignal = true;

        int maxWireSignal = 0;
        if (maxNeighborSignal < 15) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos relativePos = pos.relative(direction);
                BlockState relativeState = level.getBlockState(relativePos);
                maxWireSignal = Math.max(maxWireSignal, this.getWireSignal(relativeState));

                BlockPos abovePos = pos.above();
                if (relativeState.isRedstoneConductor(level, relativePos) && !level.getBlockState(abovePos).isRedstoneConductor(level, abovePos)) {
                    maxWireSignal = Math.max(maxWireSignal, this.getWireSignal(level.getBlockState(relativePos.above())));
                } else if (!relativeState.isRedstoneConductor(level, relativePos)) {
                    maxWireSignal = Math.max(maxWireSignal, this.getWireSignal(level.getBlockState(relativePos.below())));
                }
            }
        }

        return Math.max(maxNeighborSignal, maxWireSignal - 1);
    }

    private int getWireSignal(BlockState state) {
        return state.is(this) ? state.getValue(POWER) : 0;
    }

    private void checkCornerChangeAt(Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(this)) {
            level.updateNeighborsAt(pos, this);
            for (Direction direction : Direction.values()) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock()) && !level.isClientSide) {
            this.updatePowerStrength(level, pos, state);
            for (Direction direction : Direction.Plane.VERTICAL) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }
            this.updateNeighborsOfNeighboringWires(level, pos);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!this.shouldSignal && !state.is(newState.getBlock())) {
            super.onRemove(state, level, pos, newState, isMoving);
            if (!level.isClientSide) {
                for (Direction direction : Direction.values()) {
                    level.updateNeighborsAt(pos.relative(direction), this);
                }
                this.updatePowerStrength(level, pos, state);
                this.updateNeighborsOfNeighboringWires(level, pos);
            }
        }
    }

    private void updateNeighborsOfNeighboringWires(Level level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            this.checkCornerChangeAt(level, pos.relative(direction));
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos relativePos = pos.relative(direction);
            if (level.getBlockState(relativePos).isRedstoneConductor(level, relativePos)) {
                this.checkCornerChangeAt(level, relativePos.above());
            } else {
                this.checkCornerChangeAt(level, relativePos.below());
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            if (state.canSurvive(level, pos)) {
                this.updatePowerStrength(level, pos, state);
            } else {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        return !this.shouldSignal ? 0 : state.getSignal(getter, pos, direction);
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        if (this.shouldSignal && direction != Direction.DOWN) {
            int power = state.getValue(POWER);
            if (power == 0) {
                return 0;
            } else {
                return direction != Direction.UP && !this.getConnectionState(getter, state, pos).getValue(PROPERTY_BY_DIRECTION.get(direction.getOpposite())).isConnected() ? 0 : power;
            }
        } else {
            return 0;
        }
    }

    protected static boolean shouldConnectTo(BlockState state) {
        return shouldConnectTo(state, null);
    }

    protected static boolean shouldConnectTo(BlockState state, @Nullable Direction direction) {
        if (state.is(Blocks.REDSTONE_WIRE) || state.is(ModBlocks.WIRE.get())) {
            return true;
        } else if (state.is(Blocks.REPEATER)) {
            Direction facing = state.getValue(RepeaterBlock.FACING);
            return facing == direction || facing.getOpposite() == direction;
        } else if (state.is(Blocks.OBSERVER)) {
            return direction == state.getValue(ObserverBlock.FACING);
        } else {
            return state.isSignalSource() && direction != null;
        }
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return this.shouldSignal;
    }

    public static int colorMultiplier(int power) {
        Vec3 vec3 = COLORS[power];
        return Mth.color((float) vec3.x(), (float) vec3.y(), (float) vec3.z());
    }

    private void spawnParticlesAlongLine(Level level, RandomSource randomSource, BlockPos pos, Vec3 vec3, Direction direction, Direction sideDirection, float minOffset, float maxOffset) {
        float span = maxOffset - minOffset;
        if (randomSource.nextFloat() < 0.2F * span) {
            float offset = minOffset + span * randomSource.nextFloat();
            double x = 0.5D + (double) (0.4375F * direction.getStepX()) + (double) (offset * sideDirection.getStepX());
            double y = 0.5D + (double) (0.4375F * direction.getStepY()) + (double) (offset * sideDirection.getStepY());
            double z = 0.5D + (double) (0.4375F * direction.getStepZ()) + (double) (offset * sideDirection.getStepZ());
            level.addParticle(new DustParticleOptions(vec3.toVector3f(), 1.0F), (double) pos.getX() + x, (double) pos.getY() + y, (double) pos.getZ() + z, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        int power = state.getValue(POWER);
        if (power != 0) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                RedstoneSide side = state.getValue(PROPERTY_BY_DIRECTION.get(direction));
                switch (side) {
                    case UP -> this.spawnParticlesAlongLine(level, randomSource, pos, COLORS[power], direction, Direction.UP, -0.5F, 0.5F);
                    case SIDE -> this.spawnParticlesAlongLine(level, randomSource, pos, COLORS[power], Direction.DOWN, direction, 0.0F, 0.5F);
                    default -> this.spawnParticlesAlongLine(level, randomSource, pos, COLORS[power], Direction.DOWN, direction, 0.0F, 0.3F);
                }
            }
        }
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case CLOCKWISE_180 -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(EAST, state.getValue(WEST)).setValue(SOUTH, state.getValue(NORTH)).setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90 -> state.setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
            case CLOCKWISE_90 -> state.setValue(NORTH, state.getValue(WEST)).setValue(EAST, state.getValue(NORTH)).setValue(SOUTH, state.getValue(EAST)).setValue(WEST, state.getValue(SOUTH));
            default -> state;
        };
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case LEFT_RIGHT -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK -> state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default -> super.mirror(state, mirror);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, POWER);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            if (isCross(state) || isDot(state)) {
                BlockState newState = isCross(state) ? this.defaultBlockState() : this.crossState;
                newState = newState.setValue(POWER, state.getValue(POWER));
                newState = this.getConnectionState(level, newState, pos);
                if (newState != state) {
                    level.setBlock(pos, newState, 3);
                    this.updatesOnShapeChange(level, pos, state, newState);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }
    }

    private void updatesOnShapeChange(Level level, BlockPos pos, BlockState oldState, BlockState newState) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos relativePos = pos.relative(direction);
            if (oldState.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected() != newState.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected() && level.getBlockState(relativePos).isRedstoneConductor(level, relativePos)) {
                level.updateNeighborsAtExceptFromFacing(relativePos, newState.getBlock(), direction.getOpposite());
            }
        }
    }
}