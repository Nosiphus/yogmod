package com.nosiphus.yogmod.world.level.block.piston;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;

public class PistonHeadBlock extends DirectionalBlock {

    public static final MapCodec<PistonHeadBlock> CODEC = simpleCodec(PistonHeadBlock::new);
    public static final EnumProperty<PistonType> TYPE = BlockStateProperties.PISTON_TYPE;
    public static final BooleanProperty SHORT = BlockStateProperties.SHORT;
    public static final float PLATFORM = 4.0F;

    protected static final VoxelShape EAST_AABB = Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    protected static final float AABB_OFFSET = 2.0F;
    protected static final float EDGE_MIN = 6.0F;
    protected static final float EDGE_MAX = 10.0F;

    protected static final VoxelShape UP_ARM_AABB = Block.box(6.0D, -4.0D, 6.0D, 10.0D, 12.0D, 10.0D);
    protected static final VoxelShape DOWN_ARM_AABB = Block.box(6.0D, 4.0D, 6.0D, 10.0D, 20.0D, 10.0D);
    protected static final VoxelShape SOUTH_ARM_AABB = Block.box(6.0D, 6.0D, -4.0D, 10.0D, 10.0D, 12.0D);
    protected static final VoxelShape NORTH_ARM_AABB = Block.box(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 20.0D);
    protected static final VoxelShape EAST_ARM_AABB = Block.box(-4.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
    protected static final VoxelShape WEST_ARM_AABB = Block.box(4.0D, 6.0D, 6.0D, 20.0D, 10.0D, 10.0D);

    protected static final VoxelShape SHORT_UP_ARM_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);
    protected static final VoxelShape SHORT_DOWN_ARM_AABB = Block.box(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape SHORT_SOUTH_ARM_AABB = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 12.0D);
    protected static final VoxelShape SHORT_NORTH_ARM_AABB = Block.box(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape SHORT_EAST_ARM_AABB = Block.box(0.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
    protected static final VoxelShape SHORT_WEST_ARM_AABB = Block.box(4.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

    private static final VoxelShape[] SHAPES_SHORT = makeShapes(true);
    private static final VoxelShape[] SHAPES_LONG = makeShapes(false);

    @Override
    protected MapCodec<PistonHeadBlock> codec() {
        return CODEC;
    }

    private static VoxelShape[] makeShapes(boolean isShort) {
        return Arrays.stream(Direction.values()).map(dir -> calculateShape(dir, isShort)).toArray(VoxelShape[]::new);
    }

    private static VoxelShape calculateShape(Direction direction, boolean isShort) {
        return switch (direction) {
            case DOWN -> Shapes.or(DOWN_AABB, isShort ? SHORT_DOWN_ARM_AABB : DOWN_ARM_AABB);
            case UP -> Shapes.or(UP_AABB, isShort ? SHORT_UP_ARM_AABB : UP_ARM_AABB);
            case NORTH -> Shapes.or(NORTH_AABB, isShort ? SHORT_NORTH_ARM_AABB : NORTH_ARM_AABB);
            case SOUTH -> Shapes.or(SOUTH_AABB, isShort ? SHORT_SOUTH_ARM_AABB : SOUTH_ARM_AABB);
            case WEST -> Shapes.or(WEST_AABB, isShort ? SHORT_WEST_ARM_AABB : WEST_ARM_AABB);
            case EAST -> Shapes.or(EAST_AABB, isShort ? SHORT_EAST_ARM_AABB : EAST_ARM_AABB);
        };
    }

    public PistonHeadBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, PistonType.DEFAULT).setValue(SHORT, false));
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState blockState) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return (blockState.getValue(SHORT) ? SHAPES_SHORT : SHAPES_LONG)[blockState.getValue(FACING).ordinal()];
    }

    private boolean isFittingBase(BlockState headState, BlockState baseState) {
        Block baseBlock = headState.getValue(TYPE) == PistonType.DEFAULT ? ModBlocks.PISTON.get() : ModBlocks.STICKY_PISTON.get();
        return baseState.is(baseBlock) && baseState.getValue(PistonBaseBlock.EXTENDED) && baseState.getValue(FACING) == headState.getValue(FACING);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if (!level.isClientSide && player.getAbilities().instabuild) {
            BlockPos basePos = blockPos.relative(blockState.getValue(FACING).getOpposite());
            if (this.isFittingBase(blockState, level.getBlockState(basePos))) {
                level.destroyBlock(basePos, false);
            }
        }
        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    protected void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (!blockState.is(newState.getBlock())) {
            super.onRemove(blockState, level, blockPos, newState, isMoving);
            BlockPos basePos = blockPos.relative(blockState.getValue(FACING).getOpposite());
            if (this.isFittingBase(blockState, level.getBlockState(basePos))) {
                level.destroyBlock(basePos, true);
            }
        }
    }

    @Override
    protected BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos neighborPos) {
        return direction.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(levelAccessor, blockPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState baseState = levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING).getOpposite()));
        return this.isFittingBase(blockState, baseState) || (baseState.is(ModBlocks.MOVING_PISTON.get()) && baseState.getValue(FACING) == blockState.getValue(FACING));
    }

    @Override
    protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos fromPos, boolean isMoving) {
        if (blockState.canSurvive(level, blockPos)) {
            level.neighborChanged(blockPos.relative(blockState.getValue(FACING).getOpposite()), block, fromPos);
        }
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(state.getValue(TYPE) == PistonType.STICKY ? ModBlocks.STICKY_PISTON.get() : ModBlocks.PISTON.get());
    }

    @Override
    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(FACING, TYPE, SHORT);
    }

    @Override
    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }
}