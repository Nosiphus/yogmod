package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.core.SinkInteraction;
import com.nosiphus.yogmod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import java.util.Map;

public abstract class AbstractSinkBlock extends Block {
    private static final int SIDE_THICKNESS = 2;
    private static final int LEG_WIDTH = 4;
    private static final int LEG_HEIGHT = 3;
    private static final int LEG_DEPTH = 2;
    protected static final int FLOOR_LEVEL = 4;
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);
    private final Map<Item, SinkInteraction> interactions;

    public AbstractSinkBlock(BlockBehaviour.Properties properties, Map<Item, SinkInteraction> sinkInteractionMap) {
        super(properties);
        this.interactions = sinkInteractionMap;
    }

    protected double getContentHeight(BlockState blockState) {
        return 0.0D;
    }

    protected boolean isEntityInsideContent(BlockState blockState, BlockPos blockPos, Entity entity) {
        return entity.getY() < (double) blockPos.getY() + this.getContentHeight(blockState) && entity.getBoundingBox().maxY > (double) blockPos.getY() + 0.25D;
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        SinkInteraction sinkInteraction = this.interactions.get(itemStack.getItem());
        return sinkInteraction.interact(blockState, level, blockPos, player, interactionHand, itemStack);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public VoxelShape getInteractionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return INSIDE;
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    public abstract boolean isFull(BlockState blockState);

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockPos blockPos1 = PointedDripstoneBlock.findStalactiteTipAboveCauldron(serverLevel, blockPos);
        if (blockPos1 != null) {
            Fluid fluid = PointedDripstoneBlock.getCauldronFillFluidType(serverLevel, blockPos1);
            if (fluid != Fluids.EMPTY && this.canReceiveStalactiteDrip(fluid)) {
                this.receiveStalactiteDrip(blockState, serverLevel, blockPos, fluid);
            }
        }
    }

    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return false;
    }

    protected void receiveStalactiteDrip(BlockState blockState, Level level, BlockPos blockPos, Fluid fluid) {  }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ModBlocks.SINK.get());
    }


}
