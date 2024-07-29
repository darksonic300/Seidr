package com.github.darksonic300.seidr.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class LiquidWalkEffect extends MobEffect {
    public LiquidWalkEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        FluidState state = pLivingEntity.level().getFluidState(pLivingEntity.getOnPos());
        if((state.is(FluidTags.WATER) && pAmplifier == 0) || (state.is(FluidTags.WATER) || state.is(FluidTags.LAVA) && pAmplifier == 1)){
            pLivingEntity.canStandOnFluid(state);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }
}
