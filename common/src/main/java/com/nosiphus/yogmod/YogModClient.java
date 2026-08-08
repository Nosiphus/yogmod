package com.nosiphus.yogmod;

import com.nosiphus.yogmod.client.renderer.blockentity.CageRenderer;
import com.nosiphus.yogmod.client.renderer.blockentity.CrateRenderer;
import com.nosiphus.yogmod.client.renderer.blockentity.PistonHeadRenderer;
import com.nosiphus.yogmod.client.renderer.entity.DynamiteRenderer;
import com.nosiphus.yogmod.client.renderer.entity.ScrubberBotRenderer;
import com.nosiphus.yogmod.world.entity.ModEntityType;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import com.nosiphus.yogmod.world.level.block.state.properties.ModWoodType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.state.properties.WoodType;

public class YogModClient {

    public static void initClient() {
        WoodType.register(ModWoodType.YOG);

        Sheets.SIGN_MATERIALS.put(ModWoodType.YOG, Sheets.createSignMaterial(ModWoodType.YOG));
        Sheets.HANGING_SIGN_MATERIALS.put(ModWoodType.YOG, Sheets.createHangingSignMaterial(ModWoodType.YOG));

        registerBlockEntityRenderers();
        registerEntityRenderers();
    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(ModBlockEntityType.CAGE.get(), CageRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.CRATE.get(), CrateRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.PISTON.get(), PistonHeadRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.YOG_SIGN.get(), SignRenderer::new);
    }

    private static void registerEntityRenderers() {
        EntityRenderers.register(ModEntityType.DYNAMITE.get(), DynamiteRenderer::new);
        EntityRenderers.register(ModEntityType.SCRUBBER_BOT.get(), ScrubberBotRenderer::new);
    }

}
