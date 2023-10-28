package com.nosiphus.yogmod.block.piston;

import com.nosiphus.yogmod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    private static VoxelShape[] makeShapes(boolean boolean1) {
        return Arrays.stream(Direction.values()).map((map) -> {
            return calculateShape(map, boolean1);
        }).toArray((array) -> {
            return new VoxelShape[array];
        });
    }

    private static VoxelShape calculateShape(Direction direction, boolean boolean1) {
        switch (direction) {
            case DOWN:
            default:
                return Shapes.or(DOWN_AABB, boolean1 ? SHORT_DOWN_ARM_AABB : DOWN_ARM_AABB);
            case UP:
                return Shapes.or(UP_AABB, boolean1 ? SHORT_UP_ARM_AABB : UP_ARM_AABB);
            case NORTH:
                return Shapes.or(NORTH_AABB, boolean1 ? SHORT_NORTH_ARM_AABB : NORTH_ARM_AABB);
            case SOUTH:
                return Shapes.or(SOUTH_AABB, boolean1 ? SHORT_SOUTH_ARM_AABB : SOUTH_ARM_AABB);
            case WEST:
                return Shapes.or(WEST_AABB, boolean1 ? SHORT_WEST_ARM_AABB : WEST_ARM_AABB);
            case EAST:
                return Shapes.or(EAST_AABB, boolean1 ? SHORT_EAST_ARM_AABB : EAST_ARM_AABB);
        }
    }

    public PistonHeadBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, PistonType.DEFAULT).setValue(SHORT, Boolean.valueOf(false)));
    }

    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return true;
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return (blockState.getValue(SHORT) ? SHAPES_SHORT : SHAPES_LONG)[blockState.getValue(FACING).ordinal()];
    }

    private boolean isFittingBase(BlockState blockState, BlockState blockState1) {
        Block block = blockState.getValue(TYPE) == PistonType.DEFAULT ? ModBlocks.PISTON.get() : ModBlocks.STICKY_PISTON.get();
        return blockState1.is(block) && blockState1.getValue(PistonBaseBlock.EXTENDED) && blockState1.getValue(FACING) == blockState.getValue(FACING);
    }

    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if (!level.isClientSide && player.getAbilities().instabuild) {
            BlockPos blockpos = blockPos.relative(blockState.getValue(FACING).getOpposite());
            if (this.isFittingBase(blockState, level.getBlockState(blockpos))) {
                level.destroyBlock(blockpos, false);
            }
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean boolean1) {
        if (!blockState.is(blockState1.getBlock())) {
            super.onRemove(blockState, level, blockPos, blockState1, boolean1);
            BlockPos blockpos = blockPos.relative(blockState.getValue(FACING).getOpposite());
            if (this.isFittingBase(blockState, level.getBlockState(blockpos))) {
                level.destroyBlock(blockpos, true);
            }

        }
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        return direction.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState blockstate = levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING).getOpposite()));
        return this.isFittingBase(blockState, blockstate) || blockstate.is(ModBlocks.MOVING_PISTON.get()) && blockstate.getValue(FACING) == blockState.getValue(FACING);
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean boolean1) {
        if (blockState.canSurvive(level, blockPos)) {
            level.neighborChanged(blockPos.relative(blockState.getValue(FACING).getOpposite()), block, blockPos1);
        }

    }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(blockState.getValue(TYPE) == PistonType.STICKY ? ModBlocks.STICKY_PISTON.get() : ModBlocks.PISTON.get());
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(FACING, TYPE, SHORT);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }
}