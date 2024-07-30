package com.github.darksonic300.seidr.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.github.darksonic300.seidr.Seidr;

public class SeidrScrollItems {
    // TODO: Define cooldown constants
    // TODO: Different durabilities
    // TODO: Compatibility Scrolls
    // TODO: Flamethrower Spell
    // TODO: Liquid Walk Spell (Water/Lava)
    // TODO: Healing Spell (low / medium / full)

    // Duration defined constants
    private static final int DUR_SHORT = 35;
    private static final int DUR_MEDIUM_SHORT = 45;
    private static final int DUR_MEDIUM = 60;
    private static final int DUR_MEDIUM_LONG = 75;
    private static final int DUR_LONG = 100;

    // Cooldown defined constants
    private static final int CD_SHORT = 150;
    private static final int CD_MEDIUM_SHORT = 200;
    private static final int CD_MEDIUM = 250;
    private static final int CD_MEDIUM_LONG = 300;
    private static final int CD_LONG = 350;

    //Tablets
    public static final DeferredRegister.Items SCROLL_ITEMS = DeferredRegister.createItems(Seidr.MODID);
    public static final DeferredRegister.Items TABLET_ITEMS = DeferredRegister.createItems(Seidr.MODID);

    public static final DeferredItem<Item> INCOMPLETE_UNDEAD_TABLET = registerTablet("incomplete_undead");
    public static final DeferredItem<Item> DAMAGED_UNDEAD_TABLET = registerTablet("damaged_undead");
    public static final DeferredItem<Item> COMPLETE_UNDEAD_TABLET = registerTablet("complete_undead");

    public static final DeferredItem<Item> EFFECT_REMOVE_TABLET = registerTablet("effect_remove");

    public static final DeferredItem<Item> INCOMPLETE_RESISTANCE_TABLET = registerTablet("incomplete_resistance");
    public static final DeferredItem<Item> DAMAGED_RESISTANCE_TABLET = registerTablet("damaged_resistance");
    public static final DeferredItem<Item> COMPLETE_RESISTANCE_TABLET = registerTablet("complete_resistance");

    public static final DeferredItem<Item> FIREBALL_TABLET = registerTablet("fireball");
    public static final DeferredItem<Item> SOUND_BLAST_TABLET = registerTablet("sound_blast");

    public static final DeferredItem<Item> DAMAGED_WALKING_TABLET = registerTablet("damaged_walking");
    public static final DeferredItem<Item> COMPLETE_WALKING_TABLET = registerTablet("complete_walking");

    public static final DeferredItem<Item> ATTRACTION_TABLET = registerTablet("attraction");

    // Scrolls
    public static final DeferredItem<ScrollItem> INCOMPLETE_UNDEAD_SCROLL = registerScroll("incomplete_undead", CD_SHORT, DUR_SHORT, "| Draugr |");
    public static final DeferredItem<ScrollItem> DAMAGED_UNDEAD_SCROLL = registerScroll("damaged_undead", CD_SHORT, DUR_MEDIUM_SHORT, "| Draugr |");
    public static final DeferredItem<ScrollItem> COMPLETE_UNDEAD_SCROLL = registerScroll("complete_undead", CD_SHORT, DUR_MEDIUM_LONG, "| Draugr |");

    public static final DeferredItem<ScrollItem> EFFECT_REMOVE_SCROLL = registerScroll("effect_remove", CD_SHORT, DUR_LONG, "| Skírr |");

    public static final DeferredItem<ScrollItem> INCOMPLETE_RESISTANCE_SCROLL = registerScroll("incomplete_resistance", CD_SHORT, DUR_SHORT, "| Eljun |");
    public static final DeferredItem<ScrollItem> DAMAGED_RESISTANCE_SCROLL = registerScroll("damaged_resistance", CD_SHORT, DUR_MEDIUM, "| Eljun |");
    public static final DeferredItem<ScrollItem> COMPLETE_RESISTANCE_SCROLL = registerScroll("complete_resistance", CD_SHORT, DUR_MEDIUM, "| Eljun |");

    public static final DeferredItem<ScrollItem> FIREBALL_SCROLL = registerScroll("fireball", CD_MEDIUM, DUR_MEDIUM,"| Logi |");
    public static final DeferredItem<ScrollItem> SOUND_BLAST_SCROLL = registerScroll("sound_blast", CD_LONG, DUR_MEDIUM_LONG, "| Skals |");

    public static final DeferredItem<ScrollItem> DAMAGED_WALKING_SCROLL = registerScroll("damaged_walking", CD_MEDIUM, DUR_MEDIUM_LONG, "| Ganga |");
    public static final DeferredItem<ScrollItem> COMPLETE_WALKING_SCROLL = registerScroll("complete_walking", CD_MEDIUM, DUR_MEDIUM_LONG, "| Ganga |");

    public static final DeferredItem<ScrollItem> ATTRACTION_SCROLL = registerScroll("attraction", CD_MEDIUM, DUR_MEDIUM_LONG, "| Ást |");


    private static DeferredItem<ScrollItem> registerScroll(String name, int cooldown, int duration, String norse){
        return SCROLL_ITEMS.register(name + "_scroll", () -> new ScrollItem(new Item.Properties().stacksTo(1).durability(25).rarity(Rarity.RARE), name, cooldown, duration, norse));
    }

    private static DeferredItem<Item> registerTablet(String name){
        return TABLET_ITEMS.register(name + "_tablet", () -> new Item(new Item.Properties().stacksTo(16)));
    }
}
