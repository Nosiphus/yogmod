package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.nosiphus.yogmod.world.inventory.YogifierMenu;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class YogifierScreen extends ItemCombinerScreen<YogifierMenu> {

    private static final ResourceLocation YOGIFIER_LOCATION = new ResourceLocation("yogmod", "textures/gui/container/yogifier.png");

    public YogifierScreen(YogifierMenu yogifierMenu, Inventory inventory, Component component) {
        super(yogifierMenu, inventory, component, YOGIFIER_LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void renderLabels(PoseStack poseStack, int int1, int int2) {
        RenderSystem.disableBlend();
        super.renderLabels(poseStack, int1, int2);
    }

}
