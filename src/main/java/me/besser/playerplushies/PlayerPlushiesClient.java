package me.besser.playerplushies;

import me.besser.playerplushies.blocks.PPBlockEntities;
import me.besser.playerplushies.blocks.playerPlushie.PlayerPlushieRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = PlayerPlushies.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = PlayerPlushies.MODID, value = Dist.CLIENT)
public class PlayerPlushiesClient {
    public PlayerPlushiesClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(PPBlockEntities.PLAYER_PLUSHIE_BE.get(), PlayerPlushieRenderer::new);
    }
}
