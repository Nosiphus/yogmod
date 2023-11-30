package com.nosiphus.yogmod.client.renderer;

import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModSheets extends Sheets {
    public static final ResourceLocation CRATE_SHEET = new ResourceLocation("yogmod", "textures/atlas/crate.png");
    private static final RenderType CRATE_SHEET_TYPE = RenderType.entityCutout(CRATE_SHEET);
    public static final Material CRATE_LOCATION = crateMaterial("normal");
    public static final Material CRATE_LOCATION_LEFT = crateMaterial("normal_left");
    public static final Material CRATE_LOCATION_RIGHT = crateMaterial("normal_right");

    public static RenderType crateSheet() {
        return CRATE_SHEET_TYPE;
    }

    private static Material crateMaterial(String name) {
        return new Material(CRATE_SHEET, new ResourceLocation("yogmod", "entity/crate/" + name));
    }

    public static Material chooseCrateMaterial(BlockEntity blockEntity, CrateType crateType) {
        return chooseCrateMaterial(crateType, CRATE_LOCATION, CRATE_LOCATION_LEFT, CRATE_LOCATION_RIGHT);
    }

    private static Material chooseCrateMaterial(CrateType crateType, Material material, Material material1, Material material2) {
        switch (crateType) {
            case LEFT:
                return material1;
            case RIGHT:
                return material2;
            case SINGLE:
            default:
                return material;
        }
    }

}
