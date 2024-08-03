package com.github.darksonic300.seidr.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.github.darksonic300.seidr.client.SeidrSoundEvents;
import com.github.darksonic300.seidr.particle.SeidrParticleTypes;

import java.util.List;

public class ScrollItem extends Item {
    private int cooldown;
    private int duration;
    private boolean currentlyUsing;
    private String name;
    private String norse;

    public ScrollItem(Properties pProperties, String name, int cooldown, int duration, String norse) {
        super(pProperties);
        this.cooldown = cooldown;
        this.duration = duration;
        this.currentlyUsing = false;
        this.name = name;
        this.norse = norse;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.literal(norse).withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("tooltip.item.seidr." + name + "_scroll.description").withStyle(ChatFormatting.GRAY));
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isInUse() {
        return currentlyUsing;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity livingEntity) {
        return duration;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.NONE;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player)
            player.getCooldowns().addCooldown(this, cooldown);
        // Decrease durability here
        pLevel.addParticle(SeidrParticleTypes.WAVE_PARTICLE.get(), pLivingEntity.getX(), pLivingEntity.getY() + 0.05, pLivingEntity.getZ(), 0.0D, 0.0D, 0.0D);
        return pStack;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        this.currentlyUsing = false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        this.currentlyUsing = true;
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        RandomSource random = RandomSource.create();

        // Random particles for Client
        if(pLevel.isClientSide) {
            if (pRemainingUseDuration % 2 == 0) {
                particleCircle(pLevel, pLivingEntity, SeidrParticleTypes.NORSE_PARTICLE.get(), random, pRemainingUseDuration * 15);
            }

            if (pRemainingUseDuration % 5 == 0) {
                pLevel.addParticle(ParticleTypes.NOTE, pLivingEntity.getX() + (0.5 - random.nextFloat()), pLivingEntity.getY() + 2.1, pLivingEntity.getZ() + (0.5 - random.nextFloat()), 0, 0, 0);
            }
        }

        // Plays random sound for melody simulation
        if (pRemainingUseDuration % 13 == 0) {
            if(pLivingEntity instanceof Mob mob)
                mob.playAmbientSound();
            else {
                DeferredHolder<SoundEvent, SoundEvent> soundEvent = switch (random.nextInt(3)) {
                    case 0 -> SeidrSoundEvents.ITEM_SCROLL_LOW;
                    case 1 -> SeidrSoundEvents.ITEM_SCROLL_MEDIUM;
                    case 2 -> SeidrSoundEvents.ITEM_SCROLL_HIGH;
                    default -> SeidrSoundEvents.ITEM_SCROLL_LOW; // This line should theoretically never be reached
                };
                pLevel.playSound(
                        null,
                        pLivingEntity.getX(),
                        pLivingEntity.getY(),
                        pLivingEntity.getZ(),
                        soundEvent,
                        SoundSource.VOICE,
                        0.8F,
                        1.0F
                );
            }
        }
    }

    private static void particleCircle(Level pLevel, LivingEntity pLivingEntity, ParticleOptions particle, RandomSource randomSource, int pRemainingUseDuration){
        double actualY = pLivingEntity.getY() + 1.2 + (randomSource.nextBoolean() ? 0.05 : -0.05);

        double angle = Math.toRadians(pRemainingUseDuration);
        double radius = 1.0D;
        double vx = pLivingEntity.getX() + radius * Math.cos(angle);
        double vz = pLivingEntity.getZ() + radius * Math.sin(angle);
        pLevel.addParticle(particle, vx, actualY, vz, 0f, 0f, 0f);

//        pLevel.addParticle(particle, pLivingEntity.getX() + 1, pLivingEntity.getY() + 1, pLivingEntity.getZ(), 0f, 0f, 0f);
//        pLevel.addParticle(particle, pLivingEntity.getX() - 1, pLivingEntity.getY() + 1, pLivingEntity.getZ(), 0f, 0f, 0f);
//        pLevel.addParticle(particle, pLivingEntity.getX(), pLivingEntity.getY() + 1, pLivingEntity.getZ() + 1, 0f, 0f, 0f);
//        pLevel.addParticle(particle, pLivingEntity.getX(), pLivingEntity.getY() + 1, pLivingEntity.getZ() - 1, 0f, 0f, 0f);
    }
}
