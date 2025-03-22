package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.nosiphus.yogmod.world.inventory.DispenserMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DispenserScreen extends AbstractContainerScreen<DispenserMenu> {
    private static final ResourceLocation CONTAINER_LOCATION = ResourceLocation.fromNamespaceAndPath("yogmod", "textures/gui/container/dispenser.png");

    public DispenserScreen(DispenserMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void render(GuiGraphics graphics, int posX, int posY, float index) {
        super.render(graphics, posX, posY, index);
        this.renderTooltip(graphics, posX, posY);
    }

    protected void renderBg(GuiGraphics graphics, float index, int posX, int posY) {
        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        graphics.blit(CONTAINER_LOCATION, width, height, 0, 0, this.imageWidth, this.imageHeight);
    }

}