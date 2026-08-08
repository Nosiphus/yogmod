package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CageBlock extends BaseEntityBlock {

    public static final MapCodec<CageBlock> CODEC = simpleCodec(CageBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public CageBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CageBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntityType.CAGE.get(), level.isClientSide ? CageBlockEntity::clientTick : CageBlockEntity::serverTick);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        Spawner.appendHoverText(stack, tooltipComponents, "SpawnData");
    }
}