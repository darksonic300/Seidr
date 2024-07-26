package com.github.darksonic300.seidr.entity;

import com.github.darksonic300.seidr.entity.projectile.SoundBoomProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.github.darksonic300.seidr.Seidr;

public class SeidrEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Seidr.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Draugr>> DRAUGR = ENTITY_TYPES.register("draugr",
            () -> EntityType.Builder.<Draugr>of(Draugr::new, MobCategory.MONSTER).sized(1.0F, 2.0F).clientTrackingRange(10).build("draugr"));

    public static final DeferredHolder<EntityType<?>, EntityType<SoundBoomProjectile>> SONIC_BOOM_PROJECTILE = ENTITY_TYPES.register("sonic_boom_projectile",
            () -> EntityType.Builder.<SoundBoomProjectile>of(SoundBoomProjectile::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).build("sonic_boom_projectile"));


    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(SeidrEntityTypes.DRAUGR.get(), Draugr.createAttributes().build());
    }
}
