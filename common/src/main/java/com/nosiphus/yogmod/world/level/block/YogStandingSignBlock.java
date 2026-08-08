package com.nosiphus.yogmod.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nosiphus.yogmod.world.level.block.entity.YogSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class YogStandingSignBlock extends StandingSignBlock {

    public static final MapCodec<YogStandingSignBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(StandingSignBlock::type),
                    propertiesCodec()
            ).apply(instance, YogStandingSignBlock::new)
    );

    public YogStandingSignBlock(WoodType woodType, Properties properties) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new YogSignBlockEntity(blockPos, blockState);
    }
}