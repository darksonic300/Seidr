package com.github.darksonic300.seidr.effect;

import com.github.darksonic300.seidr.Seidr;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SeidrEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Seidr.MODID);

    public static final DeferredHolder<MobEffect, ? extends MobEffect> LIQUID_WALK = MOB_EFFECTS.register("liquid_walk", () -> new LiquidWalkEffect(MobEffectCategory.BENEFICIAL, 3402751));
    public static final DeferredHolder<MobEffect, ? extends MobEffect> ATTRACTION = MOB_EFFECTS.register("attraction", () -> new AttractionEffect(MobEffectCategory.NEUTRAL, 16262179));
}
