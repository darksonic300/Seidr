package com.github.darksonic300.seidr.event;

import com.github.darksonic300.seidr.effect.SeidrEffects;
import com.github.darksonic300.seidr.entity.Draugr;
import com.github.darksonic300.seidr.entity.SeidrEntityTypes;
import com.github.darksonic300.seidr.entity.goal.FollowOwnerSummonGoal;
import com.github.darksonic300.seidr.entity.projectile.SoundBoomProjectile;
import com.github.darksonic300.seidr.item.SeidrScrollItems;
import com.github.darksonic300.seidr.particle.SeidrParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
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
    }


    public static void checkForClearEffectScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            if (event.getItem().is(SeidrScrollItems.EFFECT_REMOVE_SCROLL.get()))
                event.getEntity().removeAllEffects();
        }
    }

    public static void checkForAttractionScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrScrollItems.ATTRACTION_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(SeidrEffects.ATTRACTION, 300, 0));
            }
        }
    }

    public static void checkForSoundBlastScroll(LivingEntityUseItemEvent event) {
        if (event.getItem().is(SeidrScrollItems.SOUND_BLAST_SCROLL.get())) {
            if (event.getDuration() == SeidrScrollItems.SOUND_BLAST_SCROLL.get().getUseDuration(event.getItem(), event.getEntity()) / 3) {
                event.getEntity().playSound(SoundEvents.WARDEN_SONIC_CHARGE, 0.5F, 1.0F);
            }

            if (event.getDuration() == 0) {
                SoundBoomProjectile projectile = new SoundBoomProjectile(event.getEntity().level(), event.getEntity(), 10f,10f,10f);
                projectile.moveTo(event.getEntity().getX(), event.getEntity().getEyeHeight(), event.getEntity().getZ(), 0, 0);

                projectile.shootFromRotation(event.getEntity(), event.getEntity().getXRot(), event.getEntity().getYRot(), 0.0F, 10.0F, 0.0F);

                event.getEntity().level().addFreshEntity(projectile);

                // TODO: Relay sound effect to projectile
                event.getEntity().playSound(SoundEvents.WARDEN_SONIC_BOOM, 0.5F, 1.0F);
            }
        }
    }

    public static void checkForResistanceScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrScrollItems.INCOMPLETE_RESISTANCE_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
            } else if (event.getItem().is(SeidrScrollItems.DAMAGED_RESISTANCE_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 2));
            } else if (event.getItem().is(SeidrScrollItems.COMPLETE_RESISTANCE_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3));
            }
        }
    }

    public static void checkForFireballScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrScrollItems.FIREBALL_SCROLL.get())) {
                SmallFireball projectile = new SmallFireball(event.getEntity().level(), 5f,5f,5f, entity.getDeltaMovement());

                projectile.moveTo(event.getEntity().getX(), event.getEntity().getEyeHeight(), event.getEntity().getZ(), 0, 0);
                projectile.shootFromRotation(event.getEntity(), event.getEntity().getXRot(), event.getEntity().getYRot(), 0.0F, 5.0F, 0.0F);
                event.getEntity().level().addFreshEntity(projectile);
            }
        }
    }

    public static void checkForUndeadScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            Level level = event.getEntity().level();
            if (event.getItem().is(SeidrScrollItems.INCOMPLETE_UNDEAD_SCROLL.get())) {
                summonZombie(level, event.getEntity(), 1);
            } else if (event.getItem().is(SeidrScrollItems.DAMAGED_UNDEAD_SCROLL.get())) {
                summonZombie(level, event.getEntity(), 2);
            } else if (event.getItem().is(SeidrScrollItems.COMPLETE_UNDEAD_SCROLL.get())) {
                summonZombie(level, event.getEntity(), 3);
            }
        }
    }

    public static void checkForWalkingScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            LivingEntity entity = event.getEntity();
            if (event.getItem().is(SeidrScrollItems.DAMAGED_WALKING_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(SeidrEffects.LIQUID_WALK, 300, 0));
            } else if (event.getItem().is(SeidrScrollItems.COMPLETE_WALKING_SCROLL.get())) {
                entity.addEffect(new MobEffectInstance(SeidrEffects.LIQUID_WALK, 600, 0));
            }
        }
    }

    public static void removeAttraction(MobEffectEvent.Expired event){
        List<? extends Animal> list = event.getEntity().level().getEntitiesOfClass(Animal.class, event.getEntity().getBoundingBox().inflate(15, 5, 15));
        for (Animal animal : list) {
            animal.goalSelector.removeGoal(new FollowOwnerSummonGoal(animal, event.getEntity(), 1.1f, 5.0f, 2.0f));
            event.getEntity().sendSystemMessage(Component.literal(animal.goalSelector.getAvailableGoals().toString()));
        }
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

}
