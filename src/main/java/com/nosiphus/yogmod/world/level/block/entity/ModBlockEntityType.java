package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntityType {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "yogmod");

    public static final RegistryObject<BlockEntityType<DispenserBlockEntity>> DISPENSER = register("dispenser", DispenserBlockEntity::new, () -> new Block[]{ModBlocks.DISPENSER.get()});
    public static final RegistryObject<BlockEntityType<OvenBlockEntity>> OVEN = register("oven", OvenBlockEntity::new, () -> new Block[]{ModBlocks.OVEN.get()});
    public static final RegistryObject<BlockEntityType<PistonMovingBlockEntity>> PISTON = register("piston", PistonMovingBlockEntity::new, () -> new Block[]{ModBlocks.MOVING_PISTON.get()});
    public static final RegistryObject<BlockEntityType<RecordPlayerBlockEntity>> RECORD_PLAYER = register("record_player", RecordPlayerBlockEntity::new, () -> new Block[]{ModBlocks.RECORD_PLAYER.get()});
    public static final RegistryObject<BlockEntityType<YogSignBlockEntity>> YOG_SIGN = register("yog_sign", YogSignBlockEntity::new, () -> new Block[]{ModBlocks.YOG_SIGN.get(), ModBlocks.YOG_WALL_SIGN.get()});

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block[]> validBlocksSupplier)
    {
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(supplier, validBlocksSupplier.get()).build(null));
    }

}
