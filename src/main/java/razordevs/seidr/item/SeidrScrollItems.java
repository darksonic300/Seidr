package razordevs.seidr.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import razordevs.seidr.Seidr;

public class SeidrScrollItems {
    public static final DeferredRegister.Items SCROLL_ITEMS = DeferredRegister.createItems(Seidr.MODID);
    public static final DeferredRegister.Items TABLET_ITEMS = DeferredRegister.createItems(Seidr.MODID);

    public static final DeferredItem<ScrollItem> INCOMPLETE_UNDEAD_SCROLL = registerScroll("incomplete_undead", 50, 35);
    public static final DeferredItem<ScrollItem> DAMAGED_UNDEAD_SCROLL = registerScroll("damaged_undead", 70, 50);
    public static final DeferredItem<ScrollItem> COMPLETE_UNDEAD_SCROLL = registerScroll("complete_undead", 80, 65);

    public static final DeferredItem<ScrollItem> EFFECT_REMOVE_SCROLL = registerScroll("effect_remove", 70, 100);

    public static final DeferredItem<ScrollItem> INCOMPLETE_RESISTANCE_SCROLL = registerScroll("incomplete_resistance", 150, 35);
    public static final DeferredItem<ScrollItem> DAMAGED_RESISTANCE_SCROLL = registerScroll("damaged_resistance", 170, 40);
    public static final DeferredItem<ScrollItem> COMPLETE_RESISTANCE_SCROLL = registerScroll("complete_resistance", 180, 50);

    private static DeferredItem<ScrollItem> registerScroll(String name, int cooldown, int duration){
        TABLET_ITEMS.register(name + "_tablet", () -> new Item(new Item.Properties()));
        return SCROLL_ITEMS.register(name + "_scroll", () -> new ScrollItem(new Item.Properties().stacksTo(1).durability(25), cooldown, duration));
    }
}
