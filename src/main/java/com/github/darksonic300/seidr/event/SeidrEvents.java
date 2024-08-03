package com.github.darksonic300.seidr.event;

import com.github.darksonic300.seidr.effect.SeidrEffects;
import com.github.darksonic300.seidr.entity.Draugr;
import com.github.darksonic300.seidr.entity.SeidrEntityTypes;
import com.github.darksonic300.seidr.entity.goal.FollowOwnerSummonGoal;
import com.github.darksonic300.seidr.entity.projectile.SoundBoomProjectile;
import com.github.darksonic300.seidr.item.SeidrItems;
import com.github.darksonic300.seidr.particle.SeidrParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.List;

public class SeidrEvents {
    public static void listen(IEventBus bus) {
        bus.addListener(SeidrEvents::checkForUndeadScroll);
        bus.addListener(SeidrEvents::checkForClearEffectScroll);
        bus.addListener(SeidrEvents::checkForResistanceScroll);
        bus.addListener(SeidrEvents::checkForSoundBlastScroll);
        bus.addListener(SeidrEvents::checkForWalkingScroll);
        bus.addListener(SeidrEvents::checkForFireballScroll);
        bus.addListener(SeidrEvents::checkForAttractionScroll);

        bus.addListener(SeidrEvents::removeAttraction);
        bus.addListener(SeidrEvents::expireAttraction);
    }


    public static void checkForClearEffectScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            if (event.getItem().is(SeidrItems.EFFECT_REMOVE_SCROLL.get()))
                event.getEntity().removeAllEffects();
        }
    }

    public static void checkForAttractionScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrItems.ATTRACTION_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(SeidrEffects.ATTRACTION, 300, 0));
            }
        }
    }

    public static void checkForSoundBlastScroll(LivingEntityUseItemEvent event) {
        if (event.getItem().is(SeidrItems.SOUND_BLAST_SCROLL.get())) {
            if (event.getDuration() == SeidrItems.SOUND_BLAST_SCROLL.get().getUseDuration(event.getItem(), event.getEntity()) / 3) {
                event.getEntity().playSound(SoundEvents.WARDEN_SONIC_CHARGE, 0.5F, 1.0F);
            }

            if (event.getDuration() == 0) {
                LivingEntity entity = event.getEntity();
                SoundBoomProjectile projectile = new SoundBoomProjectile(entity.level(), entity, 0f, 0f, 0f);
                projectile.setPos(entity.getX(), entity.getEyeY(), entity.getZ());
                projectile.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 7.0F, 0.0F);
                entity.level().addFreshEntity(projectile);

                // TODO: Relay sound effect to projectile
                entity.playSound(SoundEvents.WARDEN_SONIC_BOOM, 0.5F, 1.0F);
            }
        }
    }

    public static void checkForFireballScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrItems.FIREBALL_SCROLL.get())) {
                LargeFireball projectile = new LargeFireball(entity.level(), entity, new Vec3(0f, 0f, 0f), 1);
                projectile.setPos(entity.getX(), entity.getEyeY(), entity.getZ());
                projectile.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 1.0F, 0.0F);
                entity.level().addFreshEntity(projectile);
            }
        }
    }


    public static void checkForResistanceScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrItems.INCOMPLETE_RESISTANCE_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
            } else if (event.getItem().is(SeidrItems.DAMAGED_RESISTANCE_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 2));
            } else if (event.getItem().is(SeidrItems.COMPLETE_RESISTANCE_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3));
            }
        }
    }

    public static void checkForUndeadScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            Level level = event.getEntity().level();
            if (event.getItem().is(SeidrItems.INCOMPLETE_UNDEAD_SCROLL.get())) {
                summonZombie(level, event.getEntity(), 1);
            } else if (event.getItem().is(SeidrItems.DAMAGED_UNDEAD_SCROLL.get())) {
                summonZombie(level, event.getEntity(), 2);
            } else if (event.getItem().is(SeidrItems.COMPLETE_UNDEAD_SCROLL.get())) {
                summonZombie(level, event.getEntity(), 3);
            }
        }
    }

    public static void checkForWalkingScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrItems.DAMAGED_WALKING_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(SeidrEffects.LIQUID_WALK, 300, 0));
            } else if (event.getItem().is(SeidrItems.COMPLETE_WALKING_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(SeidrEffects.LIQUID_WALK, 600, 0));
            }
        }
    }

    public static void expireAttraction(MobEffectEvent.Expired event){
        removeGoal(event.getEntity(), event);
    }

    public static void removeAttraction(MobEffectEvent.Remove event){
        removeGoal(event.getEntity(), event);
    }

    // Utility Methods
    private static void summonZombie(Level level, LivingEntity entity, int number){
        for(int i = 0; i < number; i++){
            Draugr draugr = new Draugr(SeidrEntityTypes.DRAUGR.get(), level);
            BlockPos pos = switch(i) {
                case 1 -> entity.getOnPos().above().relative(entity.getDirection().getClockWise(), 1);
                case 2 -> entity.getOnPos().above().relative(entity.getDirection().getCounterClockWise(), 1);
                default -> entity.getOnPos().above().relative(entity.getDirection(), 1);
            };
            draugr.moveTo(pos, entity.getYRot(), entity.getXRot());
            draugr.setOwnerUUID(entity.getUUID());
            level.addFreshEntity(draugr);
            level.addParticle(SeidrParticleTypes.WAVE_PARTICLE.get(), draugr.getX(), draugr.getY() + 0.05, draugr.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    private static void removeGoal(LivingEntity entity, MobEffectEvent event) {
        List<? extends Animal> list = entity.level().getEntitiesOfClass(Animal.class, entity.getBoundingBox().inflate(15, 5, 15));
        for (Animal animal : list) {
            for (WrappedGoal wrappedgoal : animal.goalSelector.getAvailableGoals())
                if (wrappedgoal.getGoal() instanceof FollowOwnerSummonGoal)
                    wrappedgoal.stop();

            animal.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof FollowOwnerSummonGoal);
        }
    }

}
