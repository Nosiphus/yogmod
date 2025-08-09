package com.nosiphus.yogmod.client.renderer.blockentity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nosiphus.yogmod.world.level.block.YogStandingSignBlock;
import com.nosiphus.yogmod.world.level.block.entity.YogSignBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class YogSignRenderer implements BlockEntityRenderer<YogSignBlockEntity> {
    private static final String STICK = "stick";
    private static final int BLACK_TEXT_OUTLINE_COLOR = -988212;
    private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
    private static final float RENDER_SCALE = 0.6666667F;
    private static final Vec3 TEXT_OFFSET = new Vec3(0.0D, (double)0.33333334F, (double)0.046666667F);
    private final Map<WoodType, YogSignRenderer.YogSignModel> signModels;
    private final Font font;

    public YogSignRenderer(BlockEntityRendererProvider.Context context) {
        this.signModels = WoodType.values().collect(ImmutableMap.toImmutableMap((woodType) -> {
            return woodType;
        }, (woodTypes) -> {
            return new YogSignRenderer.YogSignModel(context.bakeLayer(ModelLayers.createSignModelName(woodTypes)));
        }));
        this.font = context.getFont();
    }

    public void render(YogSignBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource source, int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();
        SignBlock signBlock = (SignBlock) state.getBlock();
        WoodType woodType = SignBlock.getWoodType(signBlock);
        YogSignRenderer.YogSignModel yogsignrenderer$yogsignmodel = this.signModels.get(woodType);
        yogsignrenderer$yogsignmodel.stick.visible = state.getBlock() instanceof YogStandingSignBlock;
        this.renderSignWithText(blockEntity, poseStack, source, packedLight, packedOverlay, state, signBlock, woodType, yogsignrenderer$yogsignmodel);
    }

    public float getSignModelRenderScale() {
        return 0.6666667F;
    }

    public float getSignTextRenderScale() {
        return 0.6666667F;
    }

    void renderSignWithText(YogSignBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource source, int packedLight, int packedOverlay, BlockState state, SignBlock signBlock, WoodType woodType, Model model) {
        poseStack.pushPose();
        this.translateSign(poseStack, -signBlock.getYRotationDegrees(state), state);
        this.renderSign(poseStack, source, packedLight, packedOverlay, woodType, model);
        this.renderSignText(blockEntity.getBlockPos(), blockEntity.getFrontText(), poseStack, source, packedLight, blockEntity.getTextLineHeight(), blockEntity.getMaxTextLineWidth(), true);
        this.renderSignText(blockEntity.getBlockPos(), blockEntity.getBackText(), poseStack, source, packedLight, blockEntity.getTextLineHeight(), blockEntity.getMaxTextLineWidth(), false);
        poseStack.popPose();
    }

    void translateSign(PoseStack poseStack, float rotation, BlockState state) {
        poseStack.translate(0.5F, 0.75F * this.getSignModelRenderScale(), 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        if (!(state.getBlock() instanceof YogStandingSignBlock)) {
            poseStack.translate(0.0F, -0.3125F, -0.4375F);
        }
    }

    void renderSign(PoseStack poseStack, MultiBufferSource source, int packedLight, int packedOverlay, WoodType woodType, Model model) {
        poseStack.pushPose();
        float scale = this.getSignModelRenderScale();
        poseStack.scale(scale, -scale, -scale);
        Material material = this.getSignMaterial(woodType);
        VertexConsumer consumer = material.buffer(source, model::renderType);
        this.renderSignModel(poseStack, packedLight, packedOverlay, model, consumer);
        poseStack.popPose();
    }

    void renderSignModel(PoseStack poseStack, int packedLight, int packedOverlay, Model model, VertexConsumer consumer) {
        YogSignRenderer.YogSignModel yogsignrenderer$yogsignmodel = (YogSignRenderer.YogSignModel) model;
        yogsignrenderer$yogsignmodel.root.render(poseStack, consumer, packedLight, packedOverlay);
    }

    Material getSignMaterial(WoodType woodType) {
        return Sheets.getSignMaterial(woodType);
    }

    void renderSignText(BlockPos pos, SignText signText, PoseStack poseStack, MultiBufferSource source, int packedLight, int lineHeight, int maxLineWidth, boolean isFront) {
        poseStack.pushPose();
        this.translateSignText(poseStack, isFront, this.getTextOffset());
        int darkColor = getDarkColor(signText);
        int halfBlockTextureHeight = 4 * lineHeight / 2;
        FormattedCharSequence[] lines = signText.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (component) -> {
            List<FormattedCharSequence> list = this.font.split(component, maxLineWidth);
            return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
        });
        int textColor;
        boolean drawOutline;
        int lightForText;
        if(signText.hasGlowingText()) {
            textColor = signText.getColor().getTextColor();
            drawOutline = isOutlineVisible(pos, textColor);
            lightForText = 15728880;
        } else {
            textColor = darkColor;
            drawOutline = false;
            lightForText = packedLight;
        }

        for (int line = 0; line < 4; ++line) {
            FormattedCharSequence sequence = lines[line];
            float x = -this.font.width(sequence) / 2.0F;
            float y = (float) (line * lineHeight - halfBlockTextureHeight);
            if (drawOutline) {
                this.font.drawInBatch8xOutline(sequence, x, y, textColor, darkColor, poseStack.last().pose(), source, lightForText);
            } else {
                this.font.drawInBatch(sequence, x, y, textColor, false, poseStack.last().pose(), source, Font.DisplayMode.POLYGON_OFFSET, 0, lightForText);
            }
        }

        poseStack.popPose();
    }

    private void translateSignText(PoseStack poseStack, boolean isFront, Vec3 offset) {
        if (!isFront) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        }

        float textScale = 0.015625F * this.getSignTextRenderScale();
        poseStack.translate(offset.x, offset.y, offset.z);
        poseStack.scale(textScale, -textScale, textScale);
    }

    Vec3 getTextOffset() {
        return TEXT_OFFSET;
    }

    static boolean isOutlineVisible(BlockPos pos, int textColor) {
        if (textColor == DyeColor.BLACK.getTextColor()) {
            return true;
        } else {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer localPlayer = minecraft.player;
            if (localPlayer != null && minecraft.options.getCameraType().isFirstPerson() && localPlayer.isScoping()) {
                return true;
            } else {
                Entity entity = minecraft.getCameraEntity();
                return entity != null && entity.distanceToSqr(Vec3.atCenterOf(pos)) < (double) OUTLINE_RENDER_DISTANCE;
            }
        }
    }

    static int getDarkColor(SignText signText) {
        int baseColor = signText.getColor().getTextColor();
        if (baseColor == DyeColor.BLACK.getTextColor() && signText.hasGlowingText()) {
            return -988212;
        } else {
            double shade = 0.4D;
            int r = (int) ((double) FastColor.ARGB32.red(baseColor) * 0.4D);
            int g = (int) ((double) FastColor.ARGB32.green(baseColor) * 0.4D);
            int b = (int) ((double) FastColor.ARGB32.blue(baseColor) * 0.4D);
            return FastColor.ARGB32.color(0, r, g, b);
        }
    }

    public static YogSignRenderer.YogSignModel createSignModel(EntityModelSet modelSet, WoodType woodType) {
        return new YogSignRenderer.YogSignModel(modelSet.bakeLayer(ModelLayers.createSignModelName(woodType)));
    }

    public static LayerDefinition createSignLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("sign", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -14.0F, -1.0F, 24.0F, 12.0F, 2.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 14.0F, 2.0F), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    @OnlyIn(Dist.CLIENT)
    public static final class YogSignModel extends Model {
        public final ModelPart root;
        public final ModelPart stick;

        public YogSignModel(ModelPart root) {
            super(RenderType::entityCutoutNoCull);
            this.root = root;
            this.stick = root.getChild("stick");
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            this.root.render(poseStack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }

}
