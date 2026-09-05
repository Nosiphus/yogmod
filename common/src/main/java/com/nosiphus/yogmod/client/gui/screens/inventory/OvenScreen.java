package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.world.inventory.OvenMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;

public class OvenScreen extends AbstractFurnaceScreen<OvenMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(YogMod.MOD_ID, "textures/gui/container/oven.png");

    public OvenScreen(OvenMenu menu, Inventory inventory, Component component) {
        super(menu, new SmeltingRecipeBookComponent(), inventory, component, TEXTURE);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float index, int posX, int posY) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
        int progress;
        if (((AbstractFurnaceMenu)this.menu).isLit()) {
            progress = ((AbstractFurnaceMenu)this.menu).getLitProgress();
            graphics.blit(TEXTURE, leftPos + 56, topPos + 36 + 12 - progress, 176, 12 - progress, 14, progress + 2);
        }

        progress = ((AbstractFurnaceMenu)this.menu).getBurnProgress();
        graphics.blit(TEXTURE, leftPos + 79, topPos + 34, 176, 14, progress + 1, 16);
    }

    @Override
    public void init() {
        super.init();
    }
}