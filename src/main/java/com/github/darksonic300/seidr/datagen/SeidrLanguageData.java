package com.github.darksonic300.seidr.datagen;

import com.github.darksonic300.seidr.Seidr;
import com.github.darksonic300.seidr.item.ScrollItem;
import com.github.darksonic300.seidr.item.SeidrScrollItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class SeidrLanguageData extends LanguageProvider {
    public SeidrLanguageData(PackOutput output) {
        super(output, Seidr.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addScroll(SeidrScrollItems.ATTRACTION_SCROLL, "Attraction", "- All nearby animals will follow you");
        addScroll(SeidrScrollItems.EFFECT_REMOVE_SCROLL, "Purity", "- Clears all active effects");
        addScroll(SeidrScrollItems.FIREBALL_SCROLL, "Fireball", "- Shoots a powerful fireball");
        addScroll(SeidrScrollItems.SOUND_BLAST_SCROLL, "Echo" , "- Harnesses the power of the Warden");

        addIncompleteScroll(SeidrScrollItems.INCOMPLETE_UNDEAD_SCROLL, "the Undead", "- Summons undead mobs to fight for you");
        addIncompleteScroll(SeidrScrollItems.INCOMPLETE_RESISTANCE_SCROLL, "Endurance", "- Decreases incoming damage");
        addDamagedScroll(SeidrScrollItems.DAMAGED_UNDEAD_SCROLL, "the Undead", "- Summons undead mobs to fight for you");
        addDamagedScroll(SeidrScrollItems.DAMAGED_RESISTANCE_SCROLL, "Resilience", "- Decreases incoming damage");
        addDamagedScroll(SeidrScrollItems.DAMAGED_WALKING_SCROLL, "Light Walk", "- Makes you walk on water");
        addCompleteScroll(SeidrScrollItems.COMPLETE_UNDEAD_SCROLL, "the Undead", "- Summons undead mobs to fight for you");
        addCompleteScroll(SeidrScrollItems.COMPLETE_RESISTANCE_SCROLL, "Resilience", "- Decreases incoming damage");
        addCompleteScroll(SeidrScrollItems.COMPLETE_WALKING_SCROLL, "Light Walk", "- Makes you walk on water");
    }

    public void addScroll(Supplier<? extends ScrollItem> key, String name, String description) {
        this.add(key.get().getDescriptionId(), "Scroll of " + name);
        this.add("tooltip." + key.get().getDescriptionId() + ".description" , description);
        this.add(key.get().getDescriptionId().replaceFirst("scroll", "tablet"), "Tablet of " + name);
    }

    public void addScroll(String prefix, Supplier<? extends ScrollItem> key, String name, String description) {
        this.add(key.get().getDescriptionId(), prefix + " Scroll of " + name);
        this.add("tooltip." + key.get().getDescriptionId() + ".description" , description);
        this.add(key.get().getDescriptionId().replaceFirst("scroll", "tablet"), prefix +" Tablet of " + name);
    }

    public void addIncompleteScroll(Supplier<? extends ScrollItem> key, String name, String description) {
        this.addScroll("Incomplete", key, name, description);
    }

    public void addDamagedScroll(Supplier<? extends ScrollItem> key, String name, String description) {
        this.addScroll("Damaged", key, name, description);
    }

    public void addCompleteScroll(Supplier<? extends ScrollItem> key, String name, String description) {
        this.addScroll("Complete", key, name, description);
    }
}
