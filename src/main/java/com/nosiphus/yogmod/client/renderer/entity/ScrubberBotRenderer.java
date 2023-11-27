package com.nosiphus.yogmod.client.renderer.entity;

import com.nosiphus.yogmod.client.model.ScrubberBotModel;
import com.nosiphus.yogmod.client.renderer.entity.layers.ScrubberBotHeadLayer;
import com.nosiphus.yogmod.world.entity.animal.ScrubberBot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScrubberBotRenderer extends MobRenderer<ScrubberBot, ScrubberBotModel<ScrubberBot>> {
    private static final ResourceLocation SCRUBBER_BOT_LOCATION = new ResourceLocation("yogmod", "textures/entity/scrubber_bot.png");

    public ScrubberBotRenderer(EntityRendererProvider.Context context) {
        super(context, new ScrubberBotModel(context.bakeLayer(ScrubberBotModel.SCRUBBER_BOT)), 0.5F);
        this.addLayer(new ScrubberBotHeadLayer(this, context.getBlockRenderDispatcher(), context.getItemRenderer()));
    }

    public ResourceLocation getTextureLocation(ScrubberBot scrubberBot) {
        return SCRUBBER_BOT_LOCATION;
    }

}
