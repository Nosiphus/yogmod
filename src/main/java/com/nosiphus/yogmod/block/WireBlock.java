package com.nosiphus.yogmod.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.math.Vector3f;
import com.nosiphus.yogmod.init.ModBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public class WireBlock extends Block {
    public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.NORTH_REDSTONE;
    public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.EAST_REDSTONE;
    public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.SOUTH_REDSTONE;
    public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.WEST_REDSTONE;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    public static final Map<Direction, EnumProperty<RedstoneSide>> PROPERTY_BY_DIRECTION = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST));
    protected static final int H = 1;
    protected static final int W = 3;
    protected static final int E = 13;
    protected static final int N = 3;
    protected static final int S = 13;
    private static final VoxelShape SHAPE_DOT = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);
    private static final Map<Direction, VoxelShape> SHAPES_FLOOR = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(3.0D, 0.0D, 0.0D, 13.0D, 1.0D, 13.0D), Direction.SOUTH, Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 16.0D), Direction.EAST, Block.box(3.0D, 0.0D, 3.0D, 16.0D, 1.0D, 13.0D), Direction.WEST, Block.box(0.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D)));
    private static final Map<Direction, VoxelShape> SHAPES_UP = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Shapes.or(SHAPES_FLOOR.get(Direction.NORTH), Block.box(3.0D, 0.0D, 0.0D, 13.0D, 16.0D, 1.0D)), Direction.SOUTH, Shapes.or(SHAPES_FLOOR.get(Direction.SOUTH), Block.box(3.0D, 0.0D, 15.0D, 13.0D, 16.0D, 16.0D)), Direction.EAST, Shapes.or(SHAPES_FLOOR.get(Direction.EAST), Block.box(15.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D)), Direction.WEST, Shapes.or(SHAPES_FLOOR.get(Direction.WEST), Block.box(0.0D, 0.0D, 3.0D, 1.0D, 16.0D, 13.0D))));
    private static final Map<BlockState, VoxelShape> SHAPES_CACHE = Maps.newHashMap();
    private static final Vector3f[] COLORS = Util.make(new Vector3f[16], (colors) -> {
        for(int i = 0; i <= 15; ++i) {
            float f = (float)i / 15.0F;
            float f1 = f * 0.6F + (f > 0.0F ? 0.4F : 0.3F);
            float f2 = Mth.clamp(f * f * 0.7F - 0.5F, 0.0F, 1.0F);
            float f3 = Mth.clamp(f * f * 0.6F - 0.7F, 0.0F, 1.0F);
            colors[i] = new Vector3f(f1, f2, f3);
        }
    });
    private static final float PARTICLE_DENSITY = 0.2F;
    private final BlockState crossState;
    private boolean shouldSignal = true;

    public WireBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, RedstoneSide.NONE).setValue(EAST, RedstoneSide.NONE).setValue(SOUTH, RedstoneSide.NONE).setValue(WEST, RedstoneSide.NONE).setValue(POWER, Integer.valueOf(0)));
        this.crossState = this.defaultBlockState().setValue(NORTH, RedstoneSide.SIDE).setValue(EAST, RedstoneSide.SIDE).setValue(SOUTH, RedstoneSide.SIDE).setValue(WEST, RedstoneSide.SIDE);

        for(BlockState blockstate : this.getStateDefinition().getPossibleStates()) {
            if (blockstate.getValue(POWER) == 0) {
                SHAPES_CACHE.put(blockstate, this.calculateShape(blockstate));
            }
        }
    }

    private VoxelShape calculateShape(BlockState state) {
        VoxelShape voxelshape = SHAPE_DOT;

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide RedstoneSide = state.getValue(PROPERTY_BY_DIRECTION.get(direction));
            if (RedstoneSide == RedstoneSide.SIDE) {
                voxelshape = Shapes.or(voxelshape, SHAPES_FLOOR.get(direction));
            } else if (RedstoneSide == RedstoneSide.UP) {
                voxelshape = Shapes.or(voxelshape, SHAPES_UP.get(direction));
            }
        }

        return voxelshape;

    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPES_CACHE.get(state.setValue(POWER, Integer.valueOf(0)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getConnectionState(context.getLevel(), this.crossState, context.getClickedPos());
    }

    private BlockState getConnectionState(BlockGetter getter, BlockState state, BlockPos pos) {
        boolean flag = isDot(state);
        state = this.getMissingConnections(getter, this.defaultBlockState().setValue(POWER, state.getValue(POWER)), pos);
        if (flag && isDot(state)) {
            return state;
        } else {
            boolean flag1 = state.getValue(NORTH).isConnected();
            boolean flag2 = state.getValue(SOUTH).isConnected();
            boolean flag3 = state.getValue(EAST).isConnected();
            boolean flag4 = state.getValue(WEST).isConnected();
            boolean flag5 = !flag1 && !flag2;
            boolean flag6 = !flag3 && !flag4;
            if (!flag4 && flag5) {
                state = state.setValue(WEST, RedstoneSide.SIDE);
            }

            if (!flag3 && flag5) {
                state = state.setValue(EAST, RedstoneSide.SIDE);
            }

            if (!flag1 && flag6) {
                state = state.setValue(NORTH, RedstoneSide.SIDE);
            }

            if (!flag2 && flag6) {
                state = state.setValue(SOUTH, RedstoneSide.SIDE);
            }
            return state;
        }
    }

    private BlockState getMissingConnections(BlockGetter getter, BlockState state, BlockPos pos) {
        boolean flag = !getter.getBlockState(pos.above()).isRedstoneConductor(getter, pos);

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if (!state.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected()) {
                RedstoneSide RedstoneSide = this.getConnectingSide(getter, pos, direction, flag);
                state = state.setValue(PROPERTY_BY_DIRECTION.get(direction), RedstoneSide);
            }
        }

        return state;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.DOWN) {
            return state;
        } else if (direction == Direction.UP) {
            return this.getConnectionState(levelAccessor, state, pos);
        } else {
            RedstoneSide RedstoneSide = this.getConnectingSide(levelAccessor, pos, direction);
            return RedstoneSide.isConnected() == state.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected() && !isCross(state) ? state.setValue(PROPERTY_BY_DIRECTION.get(direction), RedstoneSide) : this.getConnectionState(levelAccessor, this.crossState.setValue(POWER, state.getValue(POWER)).setValue(PROPERTY_BY_DIRECTION.get(direction), RedstoneSide), pos);
        }
    }

    private static boolean isCross(BlockState state) {
        return state.getValue(NORTH).isConnected() && state.getValue(SOUTH).isConnected() && state.getValue(EAST).isConnected() && state.getValue(WEST).isConnected();
    }

    private static boolean isDot(BlockState state) {
        return !state.getValue(NORTH).isConnected() && !state.getValue(SOUTH).isConnected() && !state.getValue(EAST).isConnected() && !state.getValue(WEST).isConnected();
    }

    public void updateIndirectNeighbourShapes(BlockState state, LevelAccessor levelAccessor, BlockPos pos, int int1, int int2) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide RedstoneSide = state.getValue(PROPERTY_BY_DIRECTION.get(direction));
            if (RedstoneSide != RedstoneSide.NONE && !levelAccessor.getBlockState(blockpos$mutableblockpos.setWithOffset(pos, direction)).is(this)) {
                blockpos$mutableblockpos.move(Direction.DOWN);
                BlockState blockstate = levelAccessor.getBlockState(blockpos$mutableblockpos);
                if (blockstate.is(this)) {
                    BlockPos blockpos = blockpos$mutableblockpos.relative(direction.getOpposite());
                    levelAccessor.neighborShapeChanged(direction.getOpposite(), levelAccessor.getBlockState(blockpos), blockpos$mutableblockpos, blockpos, int1, int2);
                }

                blockpos$mutableblockpos.setWithOffset(pos, direction).move(Direction.UP);
                BlockState blockstate1 = levelAccessor.getBlockState(blockpos$mutableblockpos);
                if (blockstate1.is(this)) {
                    BlockPos blockpos1 = blockpos$mutableblockpos.relative(direction.getOpposite());
                    levelAccessor.neighborShapeChanged(direction.getOpposite(), levelAccessor.getBlockState(blockpos1), blockpos$mutableblockpos, blockpos1, int1, int2);
                }
            }
        }

    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        if (state.is(Blocks.REDSTONE_WIRE))
        {
            return true;
        }
        else if (state.is(ModBlocks.WIRE.get()))
        {
            return true;
        }
        else if (state.is(Blocks.REPEATER))
        {
            Direction facing = state.getValue(RepeaterBlock.FACING);
            return facing == direction || facing.getOpposite() == direction;
        }
        else if (state.is(Blocks.OBSERVER))
        {
            return direction == state.getValue(ObserverBlock.FACING);
        }
        else
        {
            return state.isSignalSource() && direction != null;
        }
    }

    private RedstoneSide getConnectingSide(BlockGetter getter, BlockPos pos, Direction direction) {
        return this.getConnectingSide(getter, pos, direction, !getter.getBlockState(pos.above()).isRedstoneConductor(getter, pos));
    }

    private RedstoneSide getConnectingSide(BlockGetter getter, BlockPos pos, Direction direction, boolean shouldSignal) {
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = getter.getBlockState(blockpos);
        if (shouldSignal) {
            boolean flag = this.canSurviveOn(getter, blockpos, blockstate);
            if (flag && getter.getBlockState(blockpos.above()).canRedstoneConnectTo(getter, blockpos.above(), null)) {
                if (blockstate.isFaceSturdy(getter, blockpos, direction.getOpposite())) {
                    return RedstoneSide.UP;
                }

                return RedstoneSide.SIDE;
            }
        }

        if (blockstate.canRedstoneConnectTo(getter, blockpos, direction)) {
            return RedstoneSide.SIDE;
        } else if (blockstate.isRedstoneConductor(getter, blockpos)) {
            return RedstoneSide.NONE;
        } else {
            BlockPos blockPosBelow = blockpos.below();
            return getter.getBlockState(blockPosBelow).canRedstoneConnectTo(getter, blockPosBelow, null) ? RedstoneSide.SIDE : RedstoneSide.NONE;
        }
    }

    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = levelReader.getBlockState(blockpos);
        return this.canSurviveOn(levelReader, blockpos, blockstate);
    }

    private boolean canSurviveOn(BlockGetter getter, BlockPos pos, BlockState state) {
        return state.isFaceSturdy(getter, pos, Direction.UP) || state.is(Blocks.HOPPER);
    }

    private void updatePowerStrength(Level level, BlockPos pos, BlockState state) {
        int i = this.calculateTargetStrength(level, pos);
        if (state.getValue(POWER) != i) {
            if (level.getBlockState(pos) == state) {
                level.setBlock(pos, state.setValue(POWER, Integer.valueOf(i)), 2);
            }

            Set<BlockPos> set = Sets.newHashSet();
            set.add(pos);

            for(Direction direction : Direction.values()) {
                set.add(pos.relative(direction));
            }

            for(BlockPos blockpos : set) {
                level.updateNeighborsAt(blockpos, this);
            }
        }

    }

    private int calculateTargetStrength(Level level, BlockPos pos) {
        this.shouldSignal = false;
        int i = level.getBestNeighborSignal(pos);
        this.shouldSignal = true;
        int j = 0;
        if (i < 100) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = level.getBlockState(blockpos);
                j = Math.max(j, this.getWireSignal(blockstate));
                BlockPos blockpos1 = pos.above();
                if (blockstate.isRedstoneConductor(level, blockpos) && !level.getBlockState(blockpos1).isRedstoneConductor(level, blockpos1)) {
                    j = Math.max(j, this.getWireSignal(level.getBlockState(blockpos.above())));
                } else if (!blockstate.isRedstoneConductor(level, blockpos)) {
                    j = Math.max(j, this.getWireSignal(level.getBlockState(blockpos.below())));
                }
            }
        }

        return Math.max(i, j - 1);
    }

    private int getWireSignal(BlockState state) {
        return state.is(this) ? state.getValue(POWER) : 0;
    }

    private void checkCornerChangeAt(Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(this)) {
            level.updateNeighborsAt(pos, this);

            for(Direction direction : Direction.values()) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }

        }
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state1, boolean shouldSignal) {
        if (!state1.is(state.getBlock()) && !level.isClientSide) {
            this.updatePowerStrength(level, pos, state);

            for(Direction direction : Direction.Plane.VERTICAL) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }

            this.updateNeighborsOfNeighboringWires(level, pos);
        }
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean shouldSignal) {
        if (!WireBlock.this.shouldSignal && !state.is(state1.getBlock())) {
            super.onRemove(state, level, pos, state1, WireBlock.this.shouldSignal);
            if (!level.isClientSide) {
                for(Direction direction : Direction.values()) {
                    level.updateNeighborsAt(pos.relative(direction), this);
                }

                this.updatePowerStrength(level, pos, state);
                this.updateNeighborsOfNeighboringWires(level, pos);
            }
        }
    }

    private void updateNeighborsOfNeighboringWires(Level level, BlockPos pos) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            this.checkCornerChangeAt(level, pos.relative(direction));
        }

        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos.relative(direction1);
            if (level.getBlockState(blockpos).isRedstoneConductor(level, blockpos)) {
                this.checkCornerChangeAt(level, blockpos.above());
            } else {
                this.checkCornerChangeAt(level, blockpos.below());
            }
        }

    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean shouldSignal) {
        if (!level.isClientSide) {
            if (state.canSurvive(level, pos)) {
                this.updatePowerStrength(level, pos, state);
            } else {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
            }

        }
    }

    public int getDirectSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        return !this.shouldSignal ? 0 : state.getSignal(getter, pos, direction);
    }

    public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        if (this.shouldSignal && direction != Direction.DOWN) {
            int i = state.getValue(POWER);
            if (i == 0) {
                return 0;
            } else {
                return direction != Direction.UP && !this.getConnectionState(getter, state, pos).getValue(PROPERTY_BY_DIRECTION.get(direction.getOpposite())).isConnected() ? 0 : i;
            }
        } else {
            return 0;
        }
    }

    protected static boolean shouldConnectTo(BlockState state) {
        return shouldConnectTo(state, (Direction)null);
    }

    protected static boolean shouldConnectTo(BlockState state, @Nullable Direction direction1) {
        if (state.is(Blocks.REDSTONE_WIRE)) {
            return true;
        } else if (state.is(ModBlocks.WIRE.get())) {
            return true;
        } else if (state.is(Blocks.REPEATER)) {
            Direction direction = state.getValue(RepeaterBlock.FACING);
            return direction == direction1 || direction.getOpposite() == direction1;
        } else if (state.is(Blocks.OBSERVER)) {
            return direction1 == state.getValue(ObserverBlock.FACING);
        } else {
            return state.isSignalSource() && direction1 != null;
        }
    }

    public boolean isSignalSource(BlockState state) {
        return this.shouldSignal;
    }

    public static int colorMultiplier(int power) {
        Vector3f vector3f = COLORS[power];
        return Mth.color(vector3f.x(), vector3f.y(), vector3f.z());
    }

    private void spawnParticlesAlongLine(Level level, RandomSource randomSource, BlockPos pos, Vector3f vec3, Direction direction, Direction direction1, float float1, float float2) {
        float f = float2 - float1;
        if (!(randomSource.nextFloat() >= 0.2F * f)) {
            float f1 = 0.4375F;
            float f2 = float1 + f * randomSource.nextFloat();
            double d0 = 0.5D + (double)(0.4375F * (float)direction.getStepX()) + (double)(f2 * (float)direction1.getStepX());
            double d1 = 0.5D + (double)(0.4375F * (float)direction.getStepY()) + (double)(f2 * (float)direction1.getStepY());
            double d2 = 0.5D + (double)(0.4375F * (float)direction.getStepZ()) + (double)(f2 * (float)direction1.getStepZ());
            level.addParticle(new DustParticleOptions(vec3, 1.0F), (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        int i = state.getValue(POWER);
        if (i != 0) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                RedstoneSide RedstoneSide = state.getValue(PROPERTY_BY_DIRECTION.get(direction));
                switch (RedstoneSide) {
                    case UP:
                        this.spawnParticlesAlongLine(level, randomSource, pos, COLORS[i], direction, Direction.UP, -0.5F, 0.5F);
                    case SIDE:
                        this.spawnParticlesAlongLine(level, randomSource, pos, COLORS[i], Direction.DOWN, direction, 0.0F, 0.5F);
                        break;
                    case NONE:
                    default:
                        this.spawnParticlesAlongLine(level, randomSource, pos, COLORS[i], Direction.DOWN, direction, 0.0F, 0.3F);
                }
            }

        }
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                return state.setValue(NORTH, state.getValue(SOUTH)).setValue(EAST, state.getValue(WEST)).setValue(SOUTH, state.getValue(NORTH)).setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return state.setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
            case CLOCKWISE_90:
                return state.setValue(NORTH, state.getValue(WEST)).setValue(EAST, state.getValue(NORTH)).setValue(SOUTH, state.getValue(EAST)).setValue(WEST, state.getValue(SOUTH));
            default:
                return state;
        }
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT:
                return state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK:
                return state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default:
                return super.mirror(state, mirror);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(NORTH, EAST, SOUTH, WEST, POWER);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            if (isCross(state) || isDot(state)) {
                BlockState blockstate = isCross(state) ? this.defaultBlockState() : this.crossState;
                blockstate = blockstate.setValue(POWER, state.getValue(POWER));
                blockstate = this.getConnectionState(level, blockstate, pos);
                if (blockstate != state) {
                    level.setBlock(pos, blockstate, 3);
                    this.updatesOnShapeChange(level, pos, state, blockstate);
                    return InteractionResult.SUCCESS;
                }
            }

            return InteractionResult.PASS;
        }
    }

    private void updatesOnShapeChange(Level level, BlockPos pos, BlockState state, BlockState state1) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos.relative(direction);
            if (state.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected() != state1.getValue(PROPERTY_BY_DIRECTION.get(direction)).isConnected() && level.getBlockState(blockpos).isRedstoneConductor(level, blockpos)) {
                level.updateNeighborsAtExceptFromFacing(blockpos, state1.getBlock(), direction.getOpposite());
            }
        }

    }

}
