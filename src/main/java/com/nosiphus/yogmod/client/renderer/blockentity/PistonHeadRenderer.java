package com.nosiphus.yogmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nosiphus.yogmod.world.level.block.piston.PistonBaseBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonHeadBlock;
import com.nosiphus.yogmod.world.level.block.piston.PistonMovingBlockEntity;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PistonHeadRenderer implements BlockEntityRenderer<PistonMovingBlockEntity> {
    private BlockRenderDispatcher blockRenderer;

    public PistonHeadRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    public void render(PistonMovingBlockEntity pistonMovingBlockEntity, float float1, PoseStack poseStack, MultiBufferSource multiBufferSource, int int1, int int2) {
        Level level = pistonMovingBlockEntity.getLevel();
        if (level != null) {
            BlockPos blockpos = pistonMovingBlockEntity.getBlockPos().relative(pistonMovingBlockEntity.getMovementDirection().getOpposite());
            BlockState blockstate = pistonMovingBlockEntity.getMovedState();
            if (!blockstate.isAir()) {
                ModelBlockRenderer.enableCaching();
                poseStack.pushPose();
                poseStack.translate((double)pistonMovingBlockEntity.getXOff(float1), (double)pistonMovingBlockEntity.getYOff(float1), (double)pistonMovingBlockEntity.getZOff(float1));
                if (blockstate.is(ModBlocks.PISTON_HEAD.get()) && pistonMovingBlockEntity.getProgress(float1) <= 4.0F) {
                    blockstate = blockstate.setValue(PistonHeadBlock.SHORT, Boolean.valueOf(pistonMovingBlockEntity.getProgress(float1) <= 0.5F));
                    this.renderBlock(blockpos, blockstate, poseStack, multiBufferSource, level, false, int2);
                } else if (pistonMovingBlockEntity.isSourcePiston() && !pistonMovingBlockEntity.isExtending()) {
                    PistonType pistontype = blockstate.is(ModBlocks.STICKY_PISTON.get()) ? PistonType.STICKY : PistonType.DEFAULT;
                    BlockState blockstate1 = ModBlocks.PISTON_HEAD.get().defaultBlockState().setValue(PistonHeadBlock.TYPE, pistontype).setValue(PistonHeadBlock.FACING, blockstate.getValue(PistonBaseBlock.FACING));
                    blockstate1 = blockstate1.setValue(PistonHeadBlock.SHORT, Boolean.valueOf(pistonMovingBlockEntity.getProgress(float1) >= 0.5F));
                    this.renderBlock(blockpos, blockstate1, poseStack, multiBufferSource, level, false, int2);
                    BlockPos blockpos1 = blockpos.relative(pistonMovingBlockEntity.getMovementDirection());
                    poseStack.popPose();
                    poseStack.pushPose();
                    blockstate = blockstate.setValue(PistonBaseBlock.EXTENDED, Boolean.valueOf(true));
                    this.renderBlock(blockpos1, blockstate, poseStack, multiBufferSource, level, true, int2);
                } else {
                    this.renderBlock(blockpos, blockstate, poseStack, multiBufferSource, level, false, int2);
                }

                poseStack.popPose();
                ModelBlockRenderer.clearCache();
            }
        }
    }

    private void renderBlock(BlockPos blockPos, BlockState blockState, PoseStack poseStack, MultiBufferSource multiBufferSource, Level level, boolean boolean1, int boolean2) {
        net.minecraftforge.client.ForgeHooksClient.renderPistonMovedBlocks(blockPos, blockState, poseStack, multiBufferSource, level, boolean1, boolean2, blockRenderer == null ? blockRenderer = net.minecraft.client.Minecraft.getInstance().getBlockRenderer() : blockRenderer);
        if(false) {
            RenderType rendertype = ItemBlockRenderTypes.getMovingBlockRenderType(blockState);
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(rendertype);
            this.blockRenderer.getModelRenderer().tesselateBlock(level, this.blockRenderer.getBlockModel(blockState), blockState, blockPos, poseStack, vertexconsumer, boolean1, RandomSource.create(), blockState.getSeed(blockPos), boolean2);
        }
    }

    public int getViewDistance() {
        return 68;
    }
}
