package com.nosiphus.yogmod.block.piston;

import com.nosiphus.yogmod.blockentity.PistonMovingBlockEntity;
import com.nosiphus.yogmod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class MovingPistonBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = net.minecraft.world.level.block.piston.PistonHeadBlock.FACING;
    public static final EnumProperty<PistonType> TYPE = PistonHeadBlock.TYPE;

    public MovingPistonBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, PistonType.DEFAULT));
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    public static BlockEntity newMovingBlockEntity(BlockPos blockPos, BlockState blockState, BlockState blockState1, Direction direction, boolean boolean1, boolean boolean2) {
        return new PistonMovingBlockEntity(blockPos, blockState, blockState1, direction, boolean1, boolean2);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.PISTON.get(), PistonMovingBlockEntity::tick);
    }

    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean boolean1) {
        if (!blockState.is(blockState1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof PistonMovingBlockEntity) {
                ((PistonMovingBlockEntity)blockentity).finalTick();
            }

        }
    }

    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        BlockPos blockpos = blockPos.relative(blockState.getValue(FACING).getOpposite());
        BlockState blockstate = levelAccessor.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof PistonBaseBlock && blockstate.getValue(PistonBaseBlock.EXTENDED)) {
            levelAccessor.removeBlock(blockpos, false);
        }

    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide && level.getBlockEntity(blockPos) == null) {
            level.removeBlock(blockPos, false);
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.PASS;
        }
    }

    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder lootContextBuilder) {
        PistonMovingBlockEntity pistonmovingblockentity = this.getBlockEntity(lootContextBuilder.getLevel(), new BlockPos(lootContextBuilder.getParameter(LootContextParams.ORIGIN)));
        return pistonmovingblockentity == null ? Collections.emptyList() : pistonmovingblockentity.getMovedState().getDrops(lootContextBuilder);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.empty();
    }

    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        PistonMovingBlockEntity pistonmovingblockentity = this.getBlockEntity(blockGetter, blockPos);
        return pistonmovingblockentity != null ? pistonmovingblockentity.getCollisionShape(blockGetter, blockPos) : Shapes.empty();
    }

    @Nullable
    private PistonMovingBlockEntity getBlockEntity(BlockGetter blockGetter, BlockPos blockPos) {
        BlockEntity blockentity = blockGetter.getBlockEntity(blockPos);
        return blockentity instanceof PistonMovingBlockEntity ? (PistonMovingBlockEntity)blockentity : null;
    }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return ItemStack.EMPTY;
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(FACING, TYPE);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }
}