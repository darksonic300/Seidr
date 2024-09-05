package com.github.darksonic300.seidr.item;

import com.github.darksonic300.seidr.effect.SeidrEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;

public class KnifeItem extends SwordItem {

    public KnifeItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    public KnifeItem(Tier pTier, Properties pProperties, Tool toolComponentData) {
        super(pTier, pProperties, toolComponentData);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity user) {
        super.postHurtEnemy(itemStack, livingEntity, user);
        livingEntity.addEffect(new MobEffectInstance(SeidrEffects.SACRIFICE, 100));
    }
}
