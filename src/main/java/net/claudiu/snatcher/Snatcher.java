package net.claudiu.snatcher;

import com.mojang.logging.LogUtils;
import net.claudiu.snatcher.client.model.SnatcherModel;
import net.claudiu.snatcher.client.renderer.SnatcherRenderer;
import net.claudiu.snatcher.registry.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import org.slf4j.Logger;

@Mod(Snatcher.MOD_ID)
public final class Snatcher {
    public static final String MOD_ID = "snatcher";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Snatcher(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        ModEntities.ENTITIES.register(modBusGroup);
        // Lifecycle events (ModBus)
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);
        BuildCreativeModeTabContentsEvent.getBus(modBusGroup).addListener(Snatcher::addCreative);
        FMLClientSetupEvent.getBus(modBusGroup).addListener(ClientModEvents::onClientSetup);

        // Config
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // ForgeBus (server/game events)
        //MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // setup logic
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        // creative tab logic
    }

    private void onServerStarting(ServerStartingEvent event) {
        // server starting logic
    }

    @Mod.EventBusSubscriber(modid = Snatcher.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.SNATCHER.get(), SnatcherRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(SnatcherModel.LAYER_LOCATION, SnatcherModel::createBodyLayer);
        }
    }

}
