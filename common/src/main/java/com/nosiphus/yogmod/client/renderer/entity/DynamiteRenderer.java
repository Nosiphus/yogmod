package com.nosiphus.yogmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosiphus.yogmod.world.entity.item.PrimedDynamite;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DynamiteRenderer extends EntityRenderer<PrimedDynamite> {

    private final BlockRenderDispatcher blockRenderer;

    public DynamiteRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PrimedDynamite primedDynamite, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        int fuse = primedDynamite.getFuse();
        if ((float) fuse - partialTick + 1.0F < 10.0F) {
            float f = 1.0F - ((float) fuse - partialTick + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float scale = 1.0F + f * 0.3F;
            poseStack.scale(scale, scale, scale);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5D, -0.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));

        TntMinecartRenderer.renderWhiteSolidBlock(
                this.blockRenderer,
                ModBlocks.DYNAMITE.get().defaultBlockState(),
                poseStack,
                multiBufferSource,
                packedLight,
                fuse / 5 % 2 == 0
        );
        poseStack.popPose();
        super.render(primedDynamite, entityYaw, partialTick, poseStack, multiBufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PrimedDynamite primedDynamite) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}