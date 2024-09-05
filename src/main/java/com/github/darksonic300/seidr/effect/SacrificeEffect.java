package com.github.darksonic300.seidr.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SacrificeEffect extends MobEffect {
    protected SacrificeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    protected SacrificeEffect(MobEffectCategory pCategory, int pColor, ParticleOptions pParticle) {
        super(pCategory, pColor, pParticle);
    }
}
