package razordevs.seidr;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import razordevs.seidr.item.SeidrScrollItems;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Seidr.MODID)
public class SeidrTabs {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if(tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (DeferredHolder<Item, ? extends Item> item : SeidrScrollItems.SCROLL_ITEMS.getEntries())
                event.accept(item.get());
            for(DeferredHolder<Item, ? extends Item> item : SeidrScrollItems.TABLET_ITEMS.getEntries())
                event.accept(item.get());
        }
    }
}
