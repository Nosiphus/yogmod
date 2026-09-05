package com.nosiphus.yogmod.world.level.block;

import com.nosiphus.yogmod.world.level.block.entity.CageBlockEntity;
import com.nosiphus.yogmod.world.level.block.entity.ModBlockEntityType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class CageBlock extends BaseEntityBlock {

    public CageBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CageBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntityType.CAGE.get(), level.isClientSide ? CageBlockEntity::clientTick : CageBlockEntity::serverTick);
    }

    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean canBreak) {
        super.spawnAfterBreak(state, level, pos, stack, canBreak);
    }

    public int getExpDrop(BlockState state, LevelReader reader, RandomSource source, BlockPos pos, int fortune, int silktouch) {
        return 15 + source.nextInt(15) + source.nextInt(15);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> componentList, TooltipFlag flag) {
        super.appendHoverText(itemStack, blockGetter, componentList, flag);
        Optional<Component> optional = this.getSpawnEntityDisplayName(itemStack);
        if (optional.isPresent()) {
            componentList.add(optional.get());
        } else {
            componentList.add(CommonComponents.EMPTY);
            componentList.add(Component.translatable("block.minecraft.spawner.desc1").withStyle(ChatFormatting.GRAY));
            componentList.add(CommonComponents.space().append(Component.translatable("block.minecraft.spawner.desc2").withStyle(ChatFormatting.BLUE)));
        }

    }

    private Optional<Component> getSpawnEntityDisplayName(ItemStack itemStack) {
        CompoundTag compoundTag = BlockItem.getBlockEntityData(itemStack);
        if (compoundTag != null && compoundTag.contains("SpawnData", 10)) {
            String s = compoundTag.getCompound("SpawnData").getCompound("entity").getString("id");
            ResourceLocation resourcelocation = ResourceLocation.tryParse(s);
            if (resourcelocation != null) {
                return BuiltInRegistries.ENTITY_TYPE.getOptional(resourcelocation).map((entityType) -> {
                    return Component.translatable(entityType.getDescriptionId()).withStyle(ChatFormatting.GRAY);
                });
            }
        }

        return Optional.empty();
    }
}