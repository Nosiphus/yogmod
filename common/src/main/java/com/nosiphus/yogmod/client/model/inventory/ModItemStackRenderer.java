package com.nosiphus.yogmod.client.model.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nosiphus.yogmod.world.item.CrateBlockItem;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.CrateBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Supplier;

public class ModItemStackRenderer extends BlockEntityWithoutLevelRenderer {

    public static final ModItemStackRenderer INSTANCE = new ModItemStackRenderer();

    private CrateBlockEntity crateBlockEntity;

    public ModItemStackRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
    }

    public ModItemStackRenderer() {
        this(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Item item = stack.getItem();
        if (item instanceof CrateBlockItem) {
            if (this.crateBlockEntity == null) {
                this.crateBlockEntity = new CrateBlockEntity(BlockPos.ZERO, ModBlocks.CRATE.get().defaultBlockState());
            }

            BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
            if (dispatcher != null) {
                dispatcher.renderItem(this.crateBlockEntity, poseStack, buffer, packedLight, packedOverlay);
            }
        } else {
            super.renderByItem(stack, displayContext, poseStack, buffer, packedLight, packedOverlay);
        }
    }
}