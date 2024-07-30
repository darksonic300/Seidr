package com.github.darksonic300.seidr.effect;

import com.github.darksonic300.seidr.entity.goal.FollowOwnerSummonGoal;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FollowBoatGoal;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.material.FluidState;

import java.util.List;

public class AttractionEffect extends MobEffect {
    public AttractionEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        List<? extends Animal> list = pLivingEntity.level().getEntitiesOfClass(Animal.class, pLivingEntity.getBoundingBox().inflate(15, 5, 15));
        for (Animal animal : list) {
            animal.goalSelector.addGoal(0, new FollowOwnerSummonGoal(animal, pLivingEntity, 1.1f, 5.0f, 2.0f));
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
