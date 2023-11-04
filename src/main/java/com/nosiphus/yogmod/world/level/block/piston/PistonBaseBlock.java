package com.nosiphus.yogmod.world.level.block.piston;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Map;

public class PistonBaseBlock extends DirectionalBlock {
    public static final BooleanProperty EXTENDED = BlockStateProperties.EXTENDED;
    public static final int TRIGGER_EXTEND = 0;
    public static final int TRIGGER_CONTRACT = 1;
    public static final int TRIGGER_DROP = 2;
    public static final float PLATFORM_THICKNESS = 4.0F;
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private final boolean isSticky;

    public PistonBaseBlock(boolean isSticky, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(EXTENDED, Boolean.valueOf(false)));
        this.isSticky = isSticky;
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.getValue(EXTENDED)) {
            switch ((Direction)blockState.getValue(FACING)) {
                case DOWN:
                    return DOWN_AABB;
                case UP:
                default:
                    return UP_AABB;
                case NORTH:
                    return NORTH_AABB;
                case SOUTH:
                    return SOUTH_AABB;
                case WEST:
                    return WEST_AABB;
                case EAST:
                    return EAST_AABB;
            }
        } else {
            return Shapes.block();
        }
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (!level.isClientSide) {
            this.checkIfExtend(level, blockPos, blockState);
        }

    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean boolean1) {
        if (!level.isClientSide) {
            this.checkIfExtend(level, blockPos, blockState);
        }

    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean boolean1) {
        if (!blockState1.is(blockState.getBlock())) {
            if (!level.isClientSide && level.getBlockEntity(blockPos) == null) {
                this.checkIfExtend(level, blockPos, blockState);
            }

        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getNearestLookingDirection().getOpposite()).setValue(EXTENDED, Boolean.valueOf(false));
    }

    private void checkIfExtend(Level level, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        boolean flag = this.getNeighborSignal(level, blockPos, direction);
        if (flag && !blockState.getValue(EXTENDED)) {
            if ((new PistonStructureResolver(level, blockPos, direction, true)).resolve()) {
                level.blockEvent(blockPos, this, 0, direction.get3DDataValue());
            }
        } else if (!flag && blockState.getValue(EXTENDED)) {
            BlockPos blockpos = blockPos.relative(direction, 2);
            BlockState blockstate = level.getBlockState(blockpos);
            int i = 1;
            if (blockstate.is(ModBlocks.MOVING_PISTON.get()) && blockstate.getValue(FACING) == direction) {
                BlockEntity blockEntity = level.getBlockEntity(blockpos);
                if (blockEntity instanceof PistonMovingBlockEntity) {
                    PistonMovingBlockEntity pistonMovingBlockEntity = (PistonMovingBlockEntity) blockEntity;
                    if (pistonMovingBlockEntity.isExtending() && (pistonMovingBlockEntity.getProgress(0.0F) < 0.5F || level.getGameTime() == pistonMovingBlockEntity.getLastTicked() || ((ServerLevel)level).isHandlingTick())) {
                        i = 2;
                    }
                }
            }

            level.blockEvent(blockPos, this, i, direction.get3DDataValue());
        }

    }

    private boolean getNeighborSignal(Level level, BlockPos blockPos, Direction direction) {
        for(Direction direction1 : Direction.values()) {
            if (direction1 != direction && level.hasSignal(blockPos.relative(direction1), direction1)) {
                return true;
            }
        }

        if (level.hasSignal(blockPos, Direction.DOWN)) {
            return true;
        } else {
            BlockPos blockpos = blockPos.above();

            for(Direction direction2 : Direction.values()) {
                if (direction2 != Direction.DOWN && level.hasSignal(blockpos.relative(direction2), direction2)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean triggerEvent(BlockState blockState, Level level, BlockPos blockPos, int int1, int int2) {
        Direction direction = blockState.getValue(FACING);
        if (!level.isClientSide) {
            boolean flag = this.getNeighborSignal(level, blockPos, direction);
            if (flag && (int1 == 1 || int1 == 2)) {
                level.setBlock(blockPos, blockState.setValue(EXTENDED, Boolean.valueOf(true)), 2);
                return false;
            }

            if (!flag && int1 == 0) {
                return false;
            }
        }

        if (int1 == 0) {
            if (net.minecraftforge.event.ForgeEventFactory.onPistonMovePre(level, blockPos, direction, true)) return false;
            if (!this.moveBlocks(level, blockPos, direction, true)) {
                return false;
            }

            level.setBlock(blockPos, blockState.setValue(EXTENDED, Boolean.valueOf(true)), 67);
            level.playSound((Player)null, blockPos, SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.25F + 0.6F);
            level.gameEvent((Entity)null, GameEvent.PISTON_EXTEND, blockPos);
        } else if (int1 == 1 || int1 == 2) {
            if (net.minecraftforge.event.ForgeEventFactory.onPistonMovePre(level, blockPos, direction, false)) return false;
            BlockEntity blockentity1 = level.getBlockEntity(blockPos.relative(direction));
            if (blockentity1 instanceof PistonMovingBlockEntity) {
                ((PistonMovingBlockEntity)blockentity1).finalTick();
            }

            BlockState blockstate = ModBlocks.MOVING_PISTON.get().defaultBlockState().setValue(MovingPistonBlock.FACING, direction).setValue(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);
            level.setBlock(blockPos, blockstate, 20);
            level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(blockPos, blockstate, this.defaultBlockState().setValue(FACING, Direction.from3DDataValue(int2 & 7)), direction, false, true));
            level.blockUpdated(blockPos, blockstate.getBlock());
            blockstate.updateNeighbourShapes(level, blockPos, 2);
            if (this.isSticky) {
                BlockPos blockpos = blockPos.offset(direction.getStepX() * 2, direction.getStepY() * 2, direction.getStepZ() * 2);
                BlockState blockstate1 = level.getBlockState(blockpos);
                boolean flag1 = false;
                if (blockstate1.is(ModBlocks.MOVING_PISTON.get())) {
                    BlockEntity blockentity = level.getBlockEntity(blockpos);
                    if (blockentity instanceof PistonMovingBlockEntity) {
                        PistonMovingBlockEntity pistonmovingblockentity = (PistonMovingBlockEntity)blockentity;
                        if (pistonmovingblockentity.getDirection() == direction && pistonmovingblockentity.isExtending()) {
                            pistonmovingblockentity.finalTick();
                            flag1 = true;
                        }
                    }
                }

                if (!flag1) {
                    if (int1 != 1 || blockstate1.isAir() || !isPushable(blockstate1, level, blockpos, direction.getOpposite(), false, direction) || blockstate1.getPistonPushReaction() != PushReaction.NORMAL && !blockstate1.is(ModBlocks.PISTON.get()) && !blockstate1.is(ModBlocks.STICKY_PISTON.get())) {
                        level.removeBlock(blockPos.relative(direction), false);
                    } else {
                        this.moveBlocks(level, blockPos, direction, false);
                    }
                }
            } else {
                level.removeBlock(blockPos.relative(direction), false);
            }

            level.playSound((Player)null, blockPos, SoundEvents.PISTON_CONTRACT, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.6F);
            level.gameEvent((Entity)null, GameEvent.PISTON_CONTRACT, blockPos);
        }

        net.minecraftforge.event.ForgeEventFactory.onPistonMovePost(level, blockPos, direction, (int1 == 0));
        return true;
    }

    public static boolean isPushable(BlockState blockState, Level level, BlockPos blockPos, Direction direction, boolean boolean1, Direction direction1) {
        if (blockPos.getY() >= level.getMinBuildHeight() && blockPos.getY() <= level.getMaxBuildHeight() - 1 && level.getWorldBorder().isWithinBounds(blockPos)) {
            if (blockState.isAir()) {
                return true;
            } else if (!blockState.is(Blocks.OBSIDIAN) && !blockState.is(Blocks.CRYING_OBSIDIAN) && !blockState.is(Blocks.RESPAWN_ANCHOR) && !blockState.is(Blocks.REINFORCED_DEEPSLATE)) {
                if (direction == Direction.DOWN && blockPos.getY() == level.getMinBuildHeight()) {
                    return false;
                } else if (direction == Direction.UP && blockPos.getY() == level.getMaxBuildHeight() - 1) {
                    return false;
                } else {
                    if (!blockState.is(ModBlocks.PISTON.get()) && !blockState.is(ModBlocks.STICKY_PISTON.get())) {
                        if (blockState.getDestroySpeed(level, blockPos) == -1.0F) {
                            return false;
                        }

                        switch (blockState.getPistonPushReaction()) {
                            case BLOCK:
                                return false;
                            case DESTROY:
                                return boolean1;
                            case PUSH_ONLY:
                                return direction == direction1;
                        }
                    } else if (blockState.getValue(EXTENDED)) {
                        return false;
                    }

                    return !blockState.hasBlockEntity();
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean moveBlocks(Level level, BlockPos blockPos, Direction direction, boolean boolean1) {
        BlockPos blockpos = blockPos.relative(direction);
        if (!boolean1 && level.getBlockState(blockpos).is(ModBlocks.PISTON_HEAD.get())) {
            level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 20);
        }

        PistonStructureResolver pistonstructureresolver = new PistonStructureResolver(level, blockPos, direction, boolean1);
        if (!pistonstructureresolver.resolve()) {
            return false;
        } else {
            Map<BlockPos, BlockState> map = Maps.newHashMap();
            List<BlockPos> list = pistonstructureresolver.getToPush();
            List<BlockState> list1 = Lists.newArrayList();

            for(int i = 0; i < list.size(); ++i) {
                BlockPos blockpos1 = list.get(i);
                BlockState blockstate = level.getBlockState(blockpos1);
                list1.add(blockstate);
                map.put(blockpos1, blockstate);
            }

            List<BlockPos> list2 = pistonstructureresolver.getToDestroy();
            BlockState[] ablockstate = new BlockState[list.size() + list2.size()];
            Direction direction1 = boolean1 ? direction : direction.getOpposite();
            int j = 0;

            for(int k = list2.size() - 1; k >= 0; --k) {
                BlockPos blockpos2 = list2.get(k);
                BlockState blockstate1 = level.getBlockState(blockpos2);
                BlockEntity blockentity = blockstate1.hasBlockEntity() ? level.getBlockEntity(blockpos2) : null;
                dropResources(blockstate1, level, blockpos2, blockentity);
                level.setBlock(blockpos2, Blocks.AIR.defaultBlockState(), 18);
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockpos2, GameEvent.Context.of(blockstate1));
                if (!blockstate1.is(BlockTags.FIRE)) {
                    level.addDestroyBlockEffect(blockpos2, blockstate1);
                }

                ablockstate[j++] = blockstate1;
            }

            for(int l = list.size() - 1; l >= 0; --l) {
                BlockPos blockpos3 = list.get(l);
                BlockState blockstate5 = level.getBlockState(blockpos3);
                blockpos3 = blockpos3.relative(direction1);
                map.remove(blockpos3);
                BlockState blockstate8 = ModBlocks.MOVING_PISTON.get().defaultBlockState().setValue(FACING, direction);
                level.setBlock(blockpos3, blockstate8, 68);
                level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(blockpos3, blockstate8, list1.get(l), direction, boolean1, false));
                ablockstate[j++] = blockstate5;
            }

            if (boolean1) {
                PistonType pistontype = this.isSticky ? PistonType.STICKY : PistonType.DEFAULT;
                BlockState blockstate4 = ModBlocks.PISTON_HEAD.get().defaultBlockState().setValue(PistonHeadBlock.FACING, direction).setValue(PistonHeadBlock.TYPE, pistontype);
                BlockState blockstate6 = ModBlocks.MOVING_PISTON.get().defaultBlockState().setValue(MovingPistonBlock.FACING, direction).setValue(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);
                map.remove(blockpos);
                level.setBlock(blockpos, blockstate6, 68);
                level.setBlockEntity(MovingPistonBlock.newMovingBlockEntity(blockpos, blockstate6, blockstate4, direction, true, true));
            }

            BlockState blockstate3 = Blocks.AIR.defaultBlockState();

            for(BlockPos blockpos4 : map.keySet()) {
                level.setBlock(blockpos4, blockstate3, 82);
            }

            for(Map.Entry<BlockPos, BlockState> entry : map.entrySet()) {
                BlockPos blockpos5 = entry.getKey();
                BlockState blockstate2 = entry.getValue();
                blockstate2.updateIndirectNeighbourShapes(level, blockpos5, 2);
                blockstate3.updateNeighbourShapes(level, blockpos5, 2);
                blockstate3.updateIndirectNeighbourShapes(level, blockpos5, 2);
            }

            j = 0;

            for(int i1 = list2.size() - 1; i1 >= 0; --i1) {
                BlockState blockstate7 = ablockstate[j++];
                BlockPos blockpos6 = list2.get(i1);
                blockstate7.updateIndirectNeighbourShapes(level, blockpos6, 2);
                level.updateNeighborsAt(blockpos6, blockstate7.getBlock());
            }

            for(int j1 = list.size() - 1; j1 >= 0; --j1) {
                level.updateNeighborsAt(list.get(j1), ablockstate[j++].getBlock());
            }

            if (boolean1) {
                level.updateNeighborsAt(blockpos, ModBlocks.PISTON_HEAD.get());
            }

            return true;
        }
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState rotate(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos, Rotation rotation) {
        return blockState.getValue(EXTENDED) ? blockState : super.rotate(blockState, levelAccessor, blockPos, rotation);
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(FACING, EXTENDED);
    }

    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return blockState.getValue(EXTENDED);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }
}

