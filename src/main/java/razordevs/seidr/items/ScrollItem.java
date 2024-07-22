package razordevs.seidr.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ScrollItem extends Item {
    private int cooldown;
    private int duration;

    public ScrollItem(Properties pProperties, int cooldown, int duration) {
        super(pProperties);
        this.cooldown = cooldown;
        this.duration = duration;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pStack.setPopTime(cooldown);
        return pStack;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
