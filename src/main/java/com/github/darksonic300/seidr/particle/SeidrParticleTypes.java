package com.github.darksonic300.seidr.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.github.darksonic300.seidr.Seidr;

public class SeidrParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Seidr.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> NORSE_PARTICLE = PARTICLE_TYPES.register("norse_particle", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WAVE_PARTICLE = PARTICLE_TYPES.register("wave_particle", () -> new SimpleParticleType(false));
}