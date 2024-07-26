package razordevs.seidr.item;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import razordevs.seidr.client.SeidrSoundEvents;
import razordevs.seidr.particle.SeidrParticleTypes;

public class ScrollItem extends Item {
    private int cooldown;
    private int duration;
    private boolean currentlyUsing;

    public ScrollItem(Properties pProperties, int cooldown, int duration) {
        super(pProperties);
        this.cooldown = cooldown;
        this.duration = duration;
        this.currentlyUsing = false;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if(pLivingEntity instanceof Player player)
            player.getCooldowns().addCooldown(this, cooldown);

        pLevel.addParticle(SeidrParticleTypes.WAVE_PARTICLE.get(), pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), 0.0D, 0.0D, 0.0D);
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
            if (pRemainingUseDuration % 3 == 0) {
                if(random.nextBoolean())
                    pLevel.addParticle(SeidrParticleTypes.NORSE_PARTICLE.get(), pLivingEntity.getX() + random.nextFloat(), pLivingEntity.getY() + 1.3 + random.nextFloat(), pLivingEntity.getZ() + random.nextFloat(), 0, 0, 0);
                else
                    pLevel.addParticle(SeidrParticleTypes.NORSE_PARTICLE.get(), pLivingEntity.getX() + random.nextFloat(), pLivingEntity.getY() + 1.3 + random.nextFloat(), pLivingEntity.getZ() - random.nextFloat(), 0, 0, 0);
            }

            if (pRemainingUseDuration % 5 == 0) {
                if(random.nextBoolean())
                    pLevel.addParticle(SeidrParticleTypes.NORSE_PARTICLE.get(), pLivingEntity.getX() - random.nextFloat(), pLivingEntity.getY() + 1.3 + random.nextFloat(), pLivingEntity.getZ() + random.nextFloat(), 0, 0, 0);
                else
                    pLevel.addParticle(SeidrParticleTypes.NORSE_PARTICLE.get(), pLivingEntity.getX() - random.nextFloat(), pLivingEntity.getY() + 1.3 + random.nextFloat(), pLivingEntity.getZ() - random.nextFloat(), 0, 0, 0);

                pLevel.addParticle(ParticleTypes.NOTE, pLivingEntity.getX() + (0.5 - random.nextFloat()), pLivingEntity.getY() + 2.1, pLivingEntity.getZ() + (0.5 - random.nextFloat()), 0, 0, 0);
            }
        }

        // Plays random sound for melody simulation
        if (pRemainingUseDuration % 13 == 0) {
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

    private static ParticleOptions randomParticle(RandomSource random){
        return random.nextInt(3) == 0 ? ParticleTypes.NOTE : SeidrParticleTypes.NORSE_PARTICLE.get();
    }
}
