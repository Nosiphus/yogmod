package com.nosiphus.yogmod.client.gui.screens.inventory;

import com.nosiphus.yogmod.YogMod;
import com.nosiphus.yogmod.world.inventory.OvenMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class OvenScreen extends AbstractFurnaceScreen<OvenMenu> {

    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(YogMod.MOD_ID, "container/oven/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(YogMod.MOD_ID, "container/oven/burn_progress");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(YogMod.MOD_ID, "textures/gui/container/oven.png");

    public OvenScreen(OvenMenu menu, Inventory inventory, Component component) {
        super(menu, new SmeltingRecipeBookComponent(), inventory, component, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = this.topPos;
        graphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            int l = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
            graphics.blitSprite(LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - l, i + 56, j + 36 + 14 - l, 14, l);
        }

        int j1 = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
        graphics.blitSprite(BURN_PROGRESS_SPRITE, 24, 16, 0, 0, i + 79, j + 34, j1, 16);
    }
}