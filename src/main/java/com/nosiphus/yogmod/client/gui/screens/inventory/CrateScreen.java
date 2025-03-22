package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.nosiphus.yogmod.world.inventory.CrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrateScreen extends AbstractContainerScreen<CrateMenu> implements MenuAccess<CrateMenu> {
    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath("yogmod", "textures/gui/container/crate.png");
    private final int containerRows;

    public CrateScreen(CrateMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.containerRows = menu.getRowCount();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(GuiGraphics graphics, int posX, int posY, float index) {
        super.render(graphics, posX, posY, index);
        this.renderTooltip(graphics, posX, posY);
    }

    protected void renderBg(GuiGraphics graphics, float index, int posX, int posY) {
        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        graphics.blit(CONTAINER_BACKGROUND, width, height, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        graphics.blit(CONTAINER_BACKGROUND, width, height + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }

}