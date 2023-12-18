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
    private static final int TITLE_LABEL_X = 60;
    private static final int TITLE_LABEL_Y = 18;
    private static final int ERROR_ICON_WIDTH = 28;
    private static final int ERROR_ICON_HEIGHT = 21;
    private static final int ERROR_ICON_X = 99;
    private static final int ERROR_ICON_Y = 45;

    public YogifierScreen(YogifierMenu yogifierMenu, Inventory inventory, Component component) {
        super(yogifierMenu, inventory, component, YOGIFIER_LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    public void render(GuiGraphics guiGraphics, int posX, int posY, float index) {
        super.render(guiGraphics, posX, posY, index);
    }

    protected void renderErrorIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (this.hasRecipeError()) {
            guiGraphics.blit(YOGIFIER_LOCATION, posX + 99, posY + 45, this.imageWidth, 0, 28, 21);
        }
    }

    protected void renderLabels(GuiGraphics guiGraphics, int int1, int int2) {
        RenderSystem.disableBlend();
        super.renderLabels(guiGraphics, int1, int2);
    }

    private boolean hasRecipeError() {
        return this.menu.getSlot(0).hasItem() && this.menu.getSlot(1).hasItem() && !this.menu.getSlot(this.menu.getResultSlot()).hasItem();
    }

}
