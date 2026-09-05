package com.nosiphus.yogmod;

import com.nosiphus.yogmod.client.renderer.blockentity.CageRenderer;
import com.nosiphus.yogmod.client.renderer.blockentity.CrateRenderer;
import com.nosiphus.yogmod.client.renderer.blockentity.PistonHeadRenderer;
import com.nosiphus.yogmod.client.renderer.entity.DynamiteRenderer;
import com.nosiphus.yogmod.client.renderer.entity.ScrubberBotRenderer;
import com.nosiphus.yogmod.mixin.BlockEntityRenderersInvoker;
import com.nosiphus.yogmod.mixin.EntityRenderersInvoker;
import com.nosiphus.yogmod.mixin.SheetsAccessor;
import com.nosiphus.yogmod.mixin.WoodTypeInvoker;
import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.state.properties.ModWoodType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;

public class YogModClient {

    public static void initClient() {
        WoodTypeInvoker.invokeRegister(ModWoodType.YOG);

        Material signMaterial = new Material(
                Sheets.SIGN_SHEET,
                new ResourceLocation(YogMod.MOD_ID, "entity/signs/" + ModWoodType.YOG.name())
        );

        SheetsAccessor.getSignMaterials().put(ModWoodType.YOG, signMaterial);

        registerBlockEntityRenderers();
        registerEntityRenderers();
    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRenderersInvoker.invokeRegister(ModBlockEntityType.CAGE.get(), CageRenderer::new);
        BlockEntityRenderersInvoker.invokeRegister(ModBlockEntityType.CRATE.get(), CrateRenderer::new);
        BlockEntityRenderersInvoker.invokeRegister(ModBlockEntityType.PISTON.get(), PistonHeadRenderer::new);
        BlockEntityRenderersInvoker.invokeRegister(ModBlockEntityType.YOG_SIGN.get(), SignRenderer::new);
    }

    private static void registerEntityRenderers() {
        EntityRenderersInvoker.invokeRegister(ModEntityType.DYNAMITE.get(), DynamiteRenderer::new);
        EntityRenderersInvoker.invokeRegister(ModEntityType.SCRUBBER_BOT.get(), ScrubberBotRenderer::new);
    }

}
