package com.nosiphus.yogmod.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ScrubberBotHeadLayer extends RenderLayer<ScrubberBot, ScrubberBotModel<ScrubberBot>> {

    private final BlockRenderDispatcher blockRenderDispatcher;
    private final ItemRenderer itemRenderer;

    public ScrubberBotHeadLayer(RenderLayerParent<ScrubberBot, ScrubberBotModel<ScrubberBot>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        super(renderLayerParent);
        this.blockRenderDispatcher = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ScrubberBot scrubberBot, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (scrubberBot.hasMonitor()) {
            boolean flag = Minecraft.getInstance().shouldEntityAppearGlowing(scrubberBot) && scrubberBot.isInvisible();
            if (!scrubberBot.isInvisible() || flag) {
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0D, -0.34375D, 0.0D);
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                poseStack.scale(0.625F, -0.625F, -0.625F);
                ItemStack itemStack = new ItemStack(ModBlocks.LIT_MONITOR.get());
                if (flag) {
                    BlockState blockState = ModBlocks.LIT_MONITOR.get().defaultBlockState();
                    BakedModel bakedModel = this.blockRenderDispatcher.getBlockModel(blockState);
                    int overlay = LivingEntityRenderer.getOverlayCoords(scrubberBot, 0.0F);
                    poseStack.translate(-0.5D, -0.5D, -0.5D);
                    this.blockRenderDispatcher.getModelRenderer().renderModel(
                            poseStack.last(),
                            buffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)),
                            blockState,
                            bakedModel,
                            0.0F, 0.0F, 0.0F,
                            packedLight,
                            overlay
                    );
                } else {
                    this.itemRenderer.renderStatic(
                            scrubberBot,
                            itemStack,
                            ItemDisplayContext.HEAD,
                            false,
                            poseStack,
                            buffer,
                            scrubberBot.level(),
                            packedLight,
                            LivingEntityRenderer.getOverlayCoords(scrubberBot, 0.0F),
                            scrubberBot.getId()
                    );
                }
                poseStack.popPose();
            }
        }
    }
}