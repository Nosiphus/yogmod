package com.nosiphus.yogmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.piston.PistonBaseBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonHeadBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.PistonType;

public class PistonHeadRenderer implements BlockEntityRenderer<PistonMovingBlockEntity> {

    private final BlockRenderDispatcher blockRenderer;

    public PistonHeadRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PistonMovingBlockEntity pistonMovingBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        Level level = pistonMovingBlockEntity.getLevel();
        if (level != null) {
            BlockPos blockpos = pistonMovingBlockEntity.getBlockPos().relative(pistonMovingBlockEntity.getMovementDirection().getOpposite());
            BlockState blockstate = pistonMovingBlockEntity.getMovedState();
            if (!blockstate.isAir()) {
                ModelBlockRenderer.enableCaching();
                poseStack.pushPose();
                poseStack.translate(pistonMovingBlockEntity.getXOff(partialTick), pistonMovingBlockEntity.getYOff(partialTick), (double) pistonMovingBlockEntity.getZOff(partialTick));

                if (blockstate.is(ModBlocks.PISTON_HEAD.get()) && pistonMovingBlockEntity.getProgress(partialTick) <= 4.0F) {
                    blockstate = blockstate.setValue(PistonHeadBlock.SHORT, pistonMovingBlockEntity.getProgress(partialTick) <= 0.5F);
                    this.renderBlock(blockpos, blockstate, poseStack, multiBufferSource, level, false, combinedOverlay);
                } else if (pistonMovingBlockEntity.isSourcePiston() && !pistonMovingBlockEntity.isExtending()) {
                    PistonType pistontype = blockstate.is(ModBlocks.STICKY_PISTON.get()) ? PistonType.STICKY : PistonType.DEFAULT;
                    BlockState blockstate1 = ModBlocks.PISTON_HEAD.get().defaultBlockState()
                            .setValue(PistonHeadBlock.TYPE, pistontype)
                            .setValue(PistonHeadBlock.FACING, blockstate.getValue(PistonBaseBlock.FACING))
                            .setValue(PistonHeadBlock.SHORT, pistonMovingBlockEntity.getProgress(partialTick) >= 0.5F);

                    this.renderBlock(blockpos, blockstate1, poseStack, multiBufferSource, level, false, combinedOverlay);
                    BlockPos blockpos1 = blockpos.relative(pistonMovingBlockEntity.getMovementDirection());
                    poseStack.popPose();
                    poseStack.pushPose();
                    blockstate = blockstate.setValue(PistonBaseBlock.EXTENDED, true);
                    this.renderBlock(blockpos1, blockstate, poseStack, multiBufferSource, level, true, combinedOverlay);
                } else {
                    this.renderBlock(blockpos, blockstate, poseStack, multiBufferSource, level, false, combinedOverlay);
                }

                poseStack.popPose();
                ModelBlockRenderer.clearCache();
            }
        }
    }

    private void renderBlock(BlockPos blockPos, BlockState blockState, PoseStack poseStack, MultiBufferSource multiBufferSource, Level level, boolean checkSides, int packedOverlay) {
        RenderType rendertype = ItemBlockRenderTypes.getMovingBlockRenderType(blockState);
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(rendertype);
        this.blockRenderer.getModelRenderer().tesselateBlock(
                level,
                this.blockRenderer.getBlockModel(blockState),
                blockState,
                blockPos,
                poseStack,
                vertexconsumer,
                checkSides,
                RandomSource.create(),
                blockState.getSeed(blockPos),
                packedOverlay
        );
    }

    @Override
    public int getViewDistance() {
        return 68;
    }
}