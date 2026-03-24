package me.besser.playerplushies.blocks.playerPlushie;

import me.besser.playerplushies.blocks.PPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class PlayerPlushieBlockEntity extends BlockEntity {
    public PlayerPlushieBlockEntity(BlockPos pos, BlockState state) {
        super(PPBlockEntities.PLAYER_PLUSHIE_BE.get(), pos, state);
    }

    public UUID getPlayerUUID() {
        String uuidString = "75418e9c-34ef-4926-af64-96d98d10954c";

        return UUID.fromString(uuidString);
    }
}
