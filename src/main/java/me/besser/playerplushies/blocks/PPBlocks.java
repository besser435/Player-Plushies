package me.besser.playerplushies.blocks;

import me.besser.playerplushies.PlayerPlushies;
import me.besser.playerplushies.blocks.playerPlushie.PlayerPlushieBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PPBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlayerPlushies.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PlayerPlushies.MODID);

    public static final DeferredBlock<Block> PLAYER_PLUSHIE = BLOCKS.register("player_plushie",
            () -> new PlayerPlushieBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f, 0.5f)
                    .sound(SoundType.WOOL)
                    .requiresCorrectToolForDrops()
            ));

    // TODO: Use datagen or post build script to add the following:
    // `"render_type": "minecraft:cutout",`     to fix outer skin layers
    // "ambientocclusion": false,               to fix lighting
    // loot table

    public static final DeferredItem<BlockItem> PLAYER_PLUSHIE_ITEM = ITEMS.registerSimpleBlockItem("player_plushie", PLAYER_PLUSHIE);
}
