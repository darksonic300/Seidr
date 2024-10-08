package com.github.darksonic300.seidr.item;


import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.github.darksonic300.seidr.client.SeidrSoundEvents;
import com.github.darksonic300.seidr.particle.SeidrParticleTypes;

import java.util.List;
import java.util.function.Consumer;

public class InstrumentsItem extends Item {
    private int cooldown;
    private int duration;
    private boolean currentlyUsing;
    private String name;

    public InstrumentsItem(Properties pProperties, String name, int cooldown, int duration) {
        super(pProperties);
        this.name = name;
        this.cooldown = cooldown;
        this.currentlyUsing = false;
        this.duration = duration;
    }
    public int getCooldown() {
        return cooldown; }

    public boolean ifInUse() {
        return currentlyUsing;}

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity livingEntity) {
        return duration;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack i = pPlayer.getItemInHand(pUsedHand);
        if(i.is(SeidrItems.REBEC_INSTRUMENTS.get()))
            pLevel.playSound(  pPlayer,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SeidrSoundEvents.ITEM_REBEC_SOUND,
                    SoundSource.VOICE,
                    0.8F,
                    1.0F);


        this.currentlyUsing = true;
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }



    // need to add the music , the effect and the note particle
}

