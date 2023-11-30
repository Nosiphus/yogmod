package com.nosiphus.yogmod.client.model.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Supplier;

public class CrateItemStackRenderer<T extends BlockEntity> extends BlockEntityWithoutLevelRenderer {

    private final Supplier<T> blockEntity;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public CrateItemStackRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet, Supplier<T> blockEntity) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.blockEntity = blockEntity;
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        this.blockEntityRenderDispatcher.renderItem(this.blockEntity.get(), poseStack, multiBufferSource, combinedLight, combinedOverlay);
    }

}
