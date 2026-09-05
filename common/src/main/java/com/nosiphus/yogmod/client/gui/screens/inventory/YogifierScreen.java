package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.world.inventory.YogifierMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class YogifierScreen extends ItemCombinerScreen<YogifierMenu> {

    private static final ResourceLocation YOGIFIER_LOCATION = new ResourceLocation(YogMod.MOD_ID, "textures/gui/container/yogifier.png");

    public YogifierScreen(YogifierMenu yogifierMenu, Inventory inventory, Component component) {
        super(yogifierMenu, inventory, component, YOGIFIER_LOCATION);
        this.titleLabelX = 70;
        this.titleLabelY = 18;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void renderErrorIcon(GuiGraphics guiGraphics, int x, int y) {
        if (this.hasRecipeError()) {
            guiGraphics.blit(YOGIFIER_LOCATION, x + 74, y + 45, this.imageWidth, 0, 28, 21);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    private boolean hasRecipeError() {
        return this.menu.getSlot(0).hasItem() && this.menu.getSlot(1).hasItem() && !this.menu.getSlot(this.menu.getResultSlot()).hasItem();
    }
}