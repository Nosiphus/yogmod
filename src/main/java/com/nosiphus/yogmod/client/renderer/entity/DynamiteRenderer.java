package com.nosiphus.yogmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DynamiteRenderer extends EntityRenderer<PrimedDynamite> {

    private final BlockRenderDispatcher blockRenderer;

    public DynamiteRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    public void render(PrimedDynamite primedDynamite, float float1, float float2, PoseStack poseStack, MultiBufferSource multiBufferSource, int index) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        int i = primedDynamite.getFuse();
        if ((float)i - float2 + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - float2 + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            poseStack.scale(f1, f1, f1);
        }

        poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5D, -0.5D, 0.5D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, ModBlocks.DYNAMITE.get().defaultBlockState(), poseStack, multiBufferSource, index, i / 5 % 2 == 0);
        poseStack.popPose();
        super.render(primedDynamite, float1, float2, poseStack, multiBufferSource, index);
    }

    public ResourceLocation getTextureLocation(PrimedDynamite primedDynamite) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}
