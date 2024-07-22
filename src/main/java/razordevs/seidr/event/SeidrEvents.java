package razordevs.seidr.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import razordevs.seidr.items.SeidrItems;

public class SeidrEvents {
    public static void listen(IEventBus bus) {
        bus.addListener(SeidrEvents::checkForUndeadScroll);
    }


    public static void checkForUndeadScroll(LivingEntityUseItemEvent event) {
        Level level = event.getEntity().level();
        Zombie zombie = new Zombie(EntityType.ZOMBIE, level);
        zombie.moveTo(event.getEntity().getOnPos(), 0, 0);

        if(event.getItem().is(SeidrItems.INCOMPLETE_UNDEAD_SCROLL)){
            level.addFreshEntity(new Zombie(EntityType.ZOMBIE, level));
        } else if(event.getItem().is(SeidrItems.DAMAGED_UNDEAD_SCROLL)){
            level.addFreshEntity(new Zombie(EntityType.ZOMBIE, level));
            level.addFreshEntity(new Zombie(EntityType.ZOMBIE, level));
        } else if(event.getItem().is(SeidrItems.COMPLETE_UNDEAD_SCROLL)){
            level.addFreshEntity(new Zombie(EntityType.ZOMBIE, level));
            level.addFreshEntity(new Zombie(EntityType.ZOMBIE, level));
            level.addFreshEntity(new Zombie(EntityType.ZOMBIE, level));
        }
    }

}
