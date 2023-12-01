package com.nosiphus.yogmod.world.item;

import com.nosiphus.yogmod.client.model.inventory.CrateItemStackRenderer;
import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.entity.*;
import com.nosiphus.yogmod.world.level.block.state.properties.CrateType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CrateBlockItem extends BlockItem {

    protected Supplier<CrateType> type;

    public CrateBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);

        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                Supplier<BlockEntity> modelToUse;
                modelToUse = () -> new CrateBlockEntity(BlockPos.ZERO, ModBlocks.CRATE.get().defaultBlockState());
                return new CrateItemStackRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), modelToUse);
            }

        });
    }

}
