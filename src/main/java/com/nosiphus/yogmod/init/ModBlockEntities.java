package com.nosiphus.yogmod.init;

import com.nosiphus.yogmod.blockentity.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "yogmod");

    public static final RegistryObject<BlockEntityType<OvenBlockEntity>> OVEN = register("oven", OvenBlockEntity::new, () -> new Block[]{ModBlocks.OVEN.get()});
    public static final RegistryObject<BlockEntityType<PistonMovingBlockEntity>> PISTON = register("piston", PistonMovingBlockEntity::new, () -> new Block[]{ModBlocks.MOVING_PISTON.get()});
    public static final RegistryObject<BlockEntityType<YogSignBlockEntity>> YOG_SIGN = register("yog_sign", YogSignBlockEntity::new, () -> new Block[]{ModBlocks.YOG_SIGN.get(), ModBlocks.YOG_WALL_SIGN.get()});

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block[]> validBlocksSupplier)
    {
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(supplier, validBlocksSupplier.get()).build(null));
    }

}
