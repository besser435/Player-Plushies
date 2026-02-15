package me.besser.playerplushies.blocks.playerPlushie;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class PlayerPlushieBERState extends BlockEntityRenderState {
    public BlockPos lightPosition;
    public Level blockEntityLevel;
    int rotation;
    public boolean onBed;
    //public boolean waterlogged; get other stuff working before adding water loggableness
}
