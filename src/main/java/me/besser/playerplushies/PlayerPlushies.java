package me.besser.playerplushies;

import com.mojang.logging.LogUtils;
import me.besser.playerplushies.blocks.PPBlockEntities;
import me.besser.playerplushies.blocks.PPBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(PlayerPlushies.MODID)
public class PlayerPlushies {
    public static final String MODID = "playerplushies";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PLUSHIE_TAB = CREATIVE_MODE_TABS.register("plushie_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.playerplushies"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> PPBlocks.PLAYER_PLUSHIE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(PPBlocks.PLAYER_PLUSHIE_ITEM.get());
            }).build());


    public PlayerPlushies(IEventBus modEventBus, ModContainer modContainer) {
        PPBlocks.BLOCKS.register(modEventBus);
        PPBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        PPBlocks.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Player Plushies started!");
    }
}
