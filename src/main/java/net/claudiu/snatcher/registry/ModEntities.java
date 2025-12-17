package net.claudiu.snatcher.registry;

import net.claudiu.snatcher.Snatcher;
import net.claudiu.snatcher.entity.SnatcherEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Snatcher.MOD_ID);

    public static final RegistryObject<EntityType<SnatcherEntity>> SNATCHER =
            ENTITIES.register("snatcher", () ->
                    EntityType.Builder.of(SnatcherEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .clientTrackingRange(8)
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    ResourceLocation.fromNamespaceAndPath(Snatcher.MOD_ID, "snatcher")
                            )));

}
