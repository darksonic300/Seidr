package com.github.darksonic300.seidr.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.github.darksonic300.seidr.Seidr;

public class SeidrSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Seidr.MODID);

    // Items
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_SCROLL_LOW = register("item.scroll.low");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_SCROLL_MEDIUM = register("item.scroll.medium");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_SCROLL_HIGH = register("item.scroll.high");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String location) {
        return SOUNDS.register(location, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Seidr.MODID, location)));
    }
}
