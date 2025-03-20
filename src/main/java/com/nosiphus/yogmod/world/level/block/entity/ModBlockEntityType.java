package com.nosiphus.yogmod.world.level.block.entity;

import com.nosiphus.yogmod.world.level.block.ModBlocks;
import com.nosiphus.yogmod.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntityType {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "yogmod");

    //public static final java.util.function.Supplier<BlockEntityType<CrateBlockEntity>> CRATE = register("crate", CrateBlockEntity::new, () -> new Block[]{ModBlocks.CRATE.get()});
    public static final Supplier<BlockEntityType<OvenBlockEntity>> OVEN = register("oven", OvenBlockEntity::new, () -> new Block[]{ModBlocks.OVEN.get()});
    public static final Supplier<BlockEntityType<PistonMovingBlockEntity>> PISTON = register("piston", PistonMovingBlockEntity::new, () -> new Block[]{ModBlocks.MOVING_PISTON.get()});
    //public static final Supplier<BlockEntityType<RecordPlayerBlockEntity>> RECORD_PLAYER = register("record_player", RecordPlayerBlockEntity::new, () -> new Block[]{ModBlocks.RECORD_PLAYER.get()});
    public static final Supplier<BlockEntityType<StorageCrateBlockEntity>> STORAGE_CRATE = register("storage_crate", StorageCrateBlockEntity::new, () -> new Block[]{ModBlocks.STORAGE_CRATE.get()});
    public static final Supplier<BlockEntityType<YogDispenserBlockEntity>> YOG_DISPENSER = register("dispenser", YogDispenserBlockEntity::new, () -> new Block[]{ModBlocks.YOG_DISPENSER.get()});
    public static final Supplier<BlockEntityType<YogSignBlockEntity>> YOG_SIGN = register("yog_sign", YogSignBlockEntity::new, () -> new Block[]{ModBlocks.YOG_SIGN.get(), ModBlocks.YOG_WALL_SIGN.get()});

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block[]> validBlocksSupplier)
    {
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(supplier, validBlocksSupplier.get()).build(null));
    }

}
