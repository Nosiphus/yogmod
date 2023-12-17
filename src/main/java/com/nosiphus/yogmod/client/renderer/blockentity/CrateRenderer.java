package com.nosiphus.yogmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nosiphus.yogmod.world.level.block.AbstractCrateBlock;
import com.nosiphus.yogmod.world.level.block.CrateBlock;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.CrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrateRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {
    public static final ModelLayerLocation CRATE = new ModelLayerLocation(new ResourceLocation("yogmod", "entity/crate/normal"), "main");
    public static final ModelLayerLocation DOUBLE_CRATE_LEFT = new ModelLayerLocation(new ResourceLocation("yogmod", "entity/crate/normal_left"), "main");
    public static final ModelLayerLocation DOUBLE_CRATE_RIGHT = new ModelLayerLocation(new ResourceLocation("yogmod", "entity/crate/normal_right"), "main");
    public static final ResourceLocation CRATE_LOCATION = new ResourceLocation("yogmod", "entity/crate/normal");
    public static final ResourceLocation CRATE_LOCATION_LEFT = new ResourceLocation("yogmod", "entity/crate/normal_left");
    public static final ResourceLocation CRATE_LOCATION_RIGHT = new ResourceLocation("yogmod", "entity/crate/normal_right");
    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private final CrateBlockEntity crate = new CrateBlockEntity(BlockPos.ZERO, ModBlocks.CRATE.get().defaultBlockState());
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart doubleLeftLid;
    private final ModelPart doubleLeftBottom;
    private final ModelPart doubleRightLid;
    private final ModelPart doubleRightBottom;

    public CrateRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart singlePart = context.bakeLayer(CRATE);
        this.bottom = singlePart.getChild("bottom");
        this.lid = singlePart.getChild("lid");
        ModelPart doubleLeftPart = context.bakeLayer(DOUBLE_CRATE_LEFT);
        this.doubleLeftBottom = doubleLeftPart.getChild("bottom");
        this.doubleLeftLid = doubleLeftPart.getChild("lid");
        ModelPart doubleRightPart = context.bakeLayer(DOUBLE_CRATE_RIGHT);
        this.doubleRightBottom = doubleRightPart.getChild("bottom");
        this.doubleRightLid = doubleRightPart.getChild("lid");
    }

    public static LayerDefinition createSingleBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createDoubleBodyLeftLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createDoubleBodyRightLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public void render(T blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        Level level = blockEntity.getLevel();
        boolean flag = level != null;
        BlockState blockState = flag ? blockEntity.getBlockState() : ModBlocks.CRATE.get().defaultBlockState().setValue(CrateBlock.FACING, Direction.SOUTH);
        CrateType crateType = blockState.hasProperty(CrateBlock.TYPE) ? blockState.getValue(CrateBlock.TYPE) : CrateType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractCrateBlock<?> abstractCrateBlock) {
            boolean flag1 = crateType != CrateType.SINGLE;
            poseStack.pushPose();
            float f = blockState.getValue(CrateBlock.FACING).toYRot();
            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            DoubleBlockCombiner.NeighborCombineResult<? extends CrateBlockEntity> neighborCombineResult;
            if (flag) {
                neighborCombineResult = abstractCrateBlock.combine(blockState, level, blockEntity.getBlockPos(), true);
            } else {
                neighborCombineResult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborCombineResult.<Float2FloatFunction>apply(CrateBlock.opennessCombiner(blockEntity)).get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborCombineResult.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(combinedLight);
            Material material = new Material(Sheets.CHEST_SHEET, CrateRenderer.chooseCrateTexture(crateType));
            VertexConsumer vertexConsumer = material.buffer(multiBufferSource, RenderType::entityCutout);
            if (flag1) {
                if (crateType == CrateType.LEFT) {
                    this.render(poseStack, vertexConsumer, this.doubleLeftLid, this.doubleLeftBottom, f1, i, combinedOverlay);
                } else {
                    this.render(poseStack, vertexConsumer,this.doubleRightLid, this.doubleRightBottom, f1, i, combinedOverlay);
                }
            } else {
                this.render(poseStack, vertexConsumer, this.lid, this.bottom, f1, i , combinedOverlay);
            }
            poseStack.popPose();
        }
    }

    private void render(PoseStack poseStack, VertexConsumer vertexConsumer, ModelPart lid, ModelPart bottom, float partialTicks, int combinedLight, int combinedOverlay) {
        lid.xRot = -(partialTicks * ((float)Math.PI / 2F));
        lid.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);
        bottom.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);
    }

    public static ResourceLocation chooseCrateTexture(CrateType crateType) {
        return switch (crateType) {
            case LEFT -> CRATE_LOCATION_LEFT;
            case RIGHT -> CRATE_LOCATION_RIGHT;
            case SINGLE -> CRATE_LOCATION;
            default -> CRATE_LOCATION;
        };
    }

}
