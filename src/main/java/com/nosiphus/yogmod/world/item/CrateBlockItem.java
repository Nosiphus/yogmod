package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.client.model.inventory.ModItemStackRenderer;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.CrateBlockEntity;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Supplier;

public class CrateBlockItem extends BlockItem {

    protected Supplier<CrateType> type;

    public CrateBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    public static final class CrateRender implements IClientItemExtensions {

        public static final CrateRender INSTANCE = new CrateRender();

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            Supplier<BlockEntity> modelToUse;
            modelToUse = () -> new CrateBlockEntity(BlockPos.ZERO, ModBlocks.CRATE.get().defaultBlockState());
            return new ModItemStackRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }

    }

}