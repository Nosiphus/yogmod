package com.nosiphus.yogmod.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScrubberBotHeadLayer extends RenderLayer<ScrubberBot, ScrubberBotModel<ScrubberBot>> {
    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public ScrubberBotHeadLayer(RenderLayerParent<ScrubberBot, ScrubberBotModel<ScrubberBot>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        super(renderLayerParent);
        this.blockRenderDispatcher = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int index, ScrubberBot scrubberBot, float p_117498_, float p_117499_, float p_117500_, float p_117501_, float p_117502_, float p_117503_) {
        if (scrubberBot.hasMonitor()) {
            boolean flag = Minecraft.getInstance().shouldEntityAppearGlowing(scrubberBot) && scrubberBot.isInvisible();
            if (!scrubberBot.isInvisible() || flag) {
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                float f = 0.625F;
                poseStack.translate(0.0D, -0.34375D, 0.0D);
                poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
                poseStack.scale(0.625F, -0.625F, -0.625F);
                ItemStack itemStack = new ItemStack(ModBlocks.LIT_MONITOR.get());
                if (flag) {
                    BlockState blockState = ModBlocks.LIT_MONITOR.get().defaultBlockState();
                    BakedModel bakedModel = this.blockRenderDispatcher.getBlockModel(blockState);
                    int i = LivingEntityRenderer.getOverlayCoords(scrubberBot, 0.0F);
                    poseStack.translate(-0.5D, -0.5D, -0.5D);
                    this.blockRenderDispatcher.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, index, i);
                } else {
                    this.itemRenderer.renderStatic(scrubberBot, itemStack, ItemTransforms.TransformType.HEAD, false, poseStack, multiBufferSource, scrubberBot.level, index, LivingEntityRenderer.getOverlayCoords(scrubberBot, 0.0F), scrubberBot.getId());
                }
                poseStack.popPose();
            }
        }
    }

}