package razordevs.seidr.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import razordevs.seidr.Seidr;

public class SeidrItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Seidr.MODID);

    public static final DeferredItem<ScrollItem> INCOMPLETE_UNDEAD_SCROLL = ITEMS.register("incomplete_undead_scroll", () -> new ScrollItem(new Item.Properties(), 20, 3));
    public static final DeferredItem<ScrollItem> DAMAGED_UNDEAD_SCROLL = ITEMS.register("damaged_undead_scroll", () -> new ScrollItem(new Item.Properties(), 30, 5));
    public static final DeferredItem<ScrollItem> COMPLETE_UNDEAD_SCROLL = ITEMS.register("complete_undead_scroll", () -> new ScrollItem(new Item.Properties(), 50, 6));

}
