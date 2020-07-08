package com.nosiphus.yogmod.gui;

import com.nosiphus.yogmod.container.ContainerCrate;
import com.nosiphus.yogmod.tileentity.TileEntityCrate;
import com.nosiphus.yogmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrate extends GuiContainer
{
	private static final ResourceLocation GUI_CHEST = new ResourceLocation(Reference.MODID + ":textures/gui/container/crate.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityCrate te;
	
	public GuiCrate(InventoryPlayer playerInventory, TileEntityCrate chestInventory, EntityPlayer player) 
	{
		super(new ContainerCrate(playerInventory, chestInventory, player));
		this.playerInventory = playerInventory;
		this.te = chestInventory;
		
		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(GUI_CHEST);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
