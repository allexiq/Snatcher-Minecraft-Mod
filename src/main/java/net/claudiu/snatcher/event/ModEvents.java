package net.claudiu.snatcher.event;

import net.claudiu.snatcher.Snatcher;
import net.claudiu.snatcher.entity.SnatcherEntity;
import net.claudiu.snatcher.registry.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Snatcher.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(
                ModEntities.SNATCHER.get(),
                SnatcherEntity.createAttributes().build()
        );
    }
}