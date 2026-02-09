package me.besser.playerplushies.blocks.playerPlushie;

import me.besser.playerplushies.blocks.PPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlayerPlushieBlockEntity extends BlockEntity {
    public PlayerPlushieBlockEntity(BlockPos pos, BlockState state) {
        super(PPBlockEntities.PLAYER_PLUSHIE_BE.get(), pos, state);
    }
}
