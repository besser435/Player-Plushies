package me.besser.playerplushies.blocks;

import me.besser.playerplushies.PlayerPlushies;
import me.besser.playerplushies.blocks.playerPlushie.PlayerPlushieBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

    public static final String PLAYER_PLUSHIE_ID = "player_plushie";
    public static final DeferredBlock<Block> PLAYER_PLUSHIE = BLOCKS.register(PLAYER_PLUSHIE_ID,
            () -> new PlayerPlushieBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f, 0.1f)
                    .sound(SoundType.WOOL)
                    .requiresCorrectToolForDrops()

                    // TODO: Do something else, this is stupid. Tutorial from one mod guy does it differently, but I
                    // Dont have a register method like him :c
                    // https://youtu.be/tz0WOnXZ2pw?si=VJuxaH4pk99KezQp&t=566
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(PlayerPlushies.MODID, PLAYER_PLUSHIE_ID)))
            ));

    // TODO: Use datagen or post build script to add the following:
    // `"render_type": "minecraft:cutout",`     to fix outer skin layers
    // "ambientocclusion": false,               to fix lighting
    // loot table

    public static final DeferredItem<BlockItem> PLAYER_PLUSHIE_ITEM = ITEMS.registerSimpleBlockItem(PLAYER_PLUSHIE_ID, PLAYER_PLUSHIE);
}
