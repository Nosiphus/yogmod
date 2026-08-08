package com.nosiphus.yogmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;

public class CageRenderer implements BlockEntityRenderer<CageBlockEntity> {

    private final EntityRenderDispatcher entityRenderDispatcher;

    public CageRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderDispatcher = context.getEntityRenderer();
    }

    @Override
    public void render(CageBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        Level level = blockEntity.getLevel();
        if (level != null) {
            BaseSpawner baseSpawner = blockEntity.getSpawner();
            Entity entity = baseSpawner.getOrCreateDisplayEntity(level, blockEntity.getBlockPos());
            if (entity != null) {
                renderEntityInCage(partialTicks, poseStack, multiBufferSource, combinedLight, entity, this.entityRenderDispatcher, baseSpawner.getoSpin(), baseSpawner.getSpin());
            }
        }
    }

    public static void renderEntityInCage(float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, Entity entity, EntityRenderDispatcher entityRenderer, double oSpin, double spin) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.0F, 0.5F);
        float f = 0.53125F;
        float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());
        if ((double) f1 > 1.0) {
            f /= f1;
        }
        poseStack.translate(0.0F, 0.4F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp((double) partialTick, oSpin, spin) * 10.0F));
        poseStack.translate(0.0F, -0.2F, 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(-30.0F));
        poseStack.scale(f, f, f);
        entityRenderer.render(entity, 0.0, 0.0, 0.0, 0.0F, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(CageBlockEntity blockEntity) {
        return true;
    }
}