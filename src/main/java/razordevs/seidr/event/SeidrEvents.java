package razordevs.seidr.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import razordevs.seidr.entity.Draugr;
import razordevs.seidr.entity.SeidrEntityTypes;
import razordevs.seidr.item.SeidrScrollItems;

public class SeidrEvents {
    public static void listen(IEventBus bus) {
        bus.addListener(SeidrEvents::checkForUndeadScroll);
        bus.addListener(SeidrEvents::checkForClearEffectScroll);
        bus.addListener(SeidrEvents::checkForResistanceScroll);
        bus.addListener(SeidrEvents::checkForSoundBlastScroll);
    }


    public static void checkForClearEffectScroll(LivingEntityUseItemEvent event) {
        if(event.getDuration() == 0) {
            if (event.getItem().is(SeidrScrollItems.EFFECT_REMOVE_SCROLL.get()))
                event.getEntity().removeAllEffects();
        }
    }

    public static void checkForSoundBlastScroll(LivingEntityUseItemEvent event) {
        if (event.getItem().is(SeidrScrollItems.SOUND_BLAST_SCROLL.get())) {
            if (event.getDuration() == SeidrScrollItems.SOUND_BLAST_SCROLL.get().getUseDuration(event.getItem(), event.getEntity()) / 3) {
                event.getEntity().playSound(SoundEvents.WARDEN_SONIC_CHARGE, 0.5F, 1.0F);
            }

            if (event.getDuration() == 0) {
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
            level.addParticle(ParticleTypes.ENCHANT, draugr.getX(), draugr.getY() + 2, draugr.getZ(), 0.0f, 0.5f, 0.0f);
        }
    }

}
