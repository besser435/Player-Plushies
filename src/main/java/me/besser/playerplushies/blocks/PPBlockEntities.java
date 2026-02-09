package me.besser.playerplushies.blocks;

import me.besser.playerplushies.PlayerPlushies;
import me.besser.playerplushies.blocks.playerPlushie.PlayerPlushieBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PPBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, PlayerPlushies.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlayerPlushieBlockEntity>> PLAYER_PLUSHIE_BE =
            BLOCK_ENTITIES.register("player_plushie_be",
                    () -> BlockEntityType.Builder.of(PlayerPlushieBlockEntity::new, PPBlocks.PLAYER_PLUSHIE.get()).build(null));
}
