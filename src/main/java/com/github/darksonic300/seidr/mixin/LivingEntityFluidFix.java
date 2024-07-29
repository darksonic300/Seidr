package com.github.darksonic300.seidr.mixin;

import com.github.darksonic300.seidr.effect.SeidrEffects;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityFluidFix {
    @Inject(at = @At(value = "HEAD"), method = "canStandOnFluid", cancellable = true)
    public void canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if(livingEntity.hasEffect(SeidrEffects.LIQUID_WALK)){
            if(state.is(FluidTags.WATER))
                cir.setReturnValue(true);
        }
    }
}
