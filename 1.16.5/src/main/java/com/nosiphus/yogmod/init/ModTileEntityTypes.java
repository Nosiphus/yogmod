package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.init.ModBlocks;
import com.nosiphus.yogmod.tileentity.*;
import com.nosiphus.yogmod.util.Reference;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final TileEntityType<?> CRATE = TILE_ENTITY_TYPES.register("crate", () -> TileEntityType.Builder.create(CrateTileEntity::new, ModBlocks.CRATE));

}
