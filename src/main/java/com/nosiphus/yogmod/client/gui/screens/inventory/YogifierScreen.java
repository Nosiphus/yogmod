package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.nosiphus.yogmod.world.inventory.YogifierMenu;
import net.minecraft.client.gui.GuiGraphics;
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

    protected void renderErrorIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (this.hasRecipeError()) {
            guiGraphics.blit(YOGIFIER_LOCATION, posX + 65, posY + 46, this.imageWidth, 0, 28, 21);
        }
    }

    protected void renderLabels(GuiGraphics guiGraphics, int int1, int int2) {
        RenderSystem.disableBlend();
        super.renderLabels(guiGraphics, int1, int2);
    }

    private boolean hasRecipeError() {
        return this.menu.getSlot(0).hasItem() && this.menu.getSlot(1).hasItem() && this.menu.getSlot(2).hasItem() && !this.menu.getSlot(this.menu.getResultSlot()).hasItem();
    }

}
