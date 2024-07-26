package com.github.darksonic300.seidr.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.github.darksonic300.seidr.Seidr;

public class SeidrScrollItems {

    // Duration defined constants
    private static final int SHORT = 35;
    private static final int MEDIUM_SHORT = 45;
    private static final int MEDIUM = 60;
    private static final int MEDIUM_LONG = 75;
    private static final int LONG = 100;

    public static final DeferredRegister.Items SCROLL_ITEMS = DeferredRegister.createItems(Seidr.MODID);
    public static final DeferredRegister.Items TABLET_ITEMS = DeferredRegister.createItems(Seidr.MODID);

    public static final DeferredItem<ScrollItem> INCOMPLETE_UNDEAD_SCROLL = registerScroll("incomplete_undead", 60, SHORT);
    public static final DeferredItem<ScrollItem> DAMAGED_UNDEAD_SCROLL = registerScroll("damaged_undead", 70, MEDIUM_SHORT);
    public static final DeferredItem<ScrollItem> COMPLETE_UNDEAD_SCROLL = registerScroll("complete_undead", 90, MEDIUM_LONG);

    public static final DeferredItem<ScrollItem> EFFECT_REMOVE_SCROLL = registerScroll("effect_remove", 70, LONG);

    public static final DeferredItem<ScrollItem> INCOMPLETE_RESISTANCE_SCROLL = registerScroll("incomplete_resistance", 150, SHORT);
    public static final DeferredItem<ScrollItem> DAMAGED_RESISTANCE_SCROLL = registerScroll("damaged_resistance", 170, MEDIUM);
    public static final DeferredItem<ScrollItem> COMPLETE_RESISTANCE_SCROLL = registerScroll("complete_resistance", 180, MEDIUM);

    public static final DeferredItem<ScrollItem> SOUND_BLAST_SCROLL = registerScroll("sound_blast", 170, MEDIUM_LONG);

    private static DeferredItem<ScrollItem> registerScroll(String name, int cooldown, int duration){
        TABLET_ITEMS.register(name + "_tablet", () -> new Item(new Item.Properties()));
        return SCROLL_ITEMS.register(name + "_scroll", () -> new ScrollItem(new Item.Properties().stacksTo(1).durability(25), cooldown, duration));
    }
}
