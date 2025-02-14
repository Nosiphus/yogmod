package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.nosiphus.yogmod.world.inventory.StorageCrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StorageCrateScreen extends AbstractContainerScreen<StorageCrateMenu> {

    private static final ResourceLocation CONTAINER_TEXTURE = ResourceLocation.fromNamespaceAndPath("yogmod","textures/gui/container/storage_crate.png");

    public StorageCrateScreen(StorageCrateMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        ++this.imageHeight;
    }

    public void render(GuiGraphics graphics, int posX, int posY, float index) {
        super.render(graphics, posX, posY, index);
        this.renderTooltip(graphics, posX, posY);
    }

    protected void renderBg(GuiGraphics graphics, float index, int posX, int posY) {
        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        graphics.blit(CONTAINER_TEXTURE, width, height, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected void renderLabels(GuiGraphics graphics, int posX, int posY) {
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 2, 4210752, false);
    }

}
