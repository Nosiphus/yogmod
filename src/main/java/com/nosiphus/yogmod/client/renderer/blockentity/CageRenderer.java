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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CageRenderer implements BlockEntityRenderer<CageBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public CageRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderDispatcher = context.getEntityRenderer();
    }

    public void render(CageBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.0F, 0.5F);
        BaseSpawner baseSpawner = blockEntity.getSpawner();
        Entity entity = baseSpawner.getOrCreateDisplayEntity(blockEntity.getLevel(), blockEntity.getLevel().getRandom(), blockEntity.getBlockPos());
        if (entity != null) {
            float f = 0.53125F;
            float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());
            if ((double) f1 > 1.0D) {
                f /= f1;
            }

            poseStack.translate(0.0F, 0.4F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp((double)partialTicks, baseSpawner.getoSpin(), baseSpawner.getSpin()) * 10.0F));
            poseStack.translate(0.0F, -0.2F, 0.0F);
            poseStack.mulPose(Axis.XP.rotationDegrees(-30.0F));
            poseStack.scale(f, f, f);
            this.entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, poseStack, multiBufferSource, combinedLight);
        }

        poseStack.popPose();
    }
}
