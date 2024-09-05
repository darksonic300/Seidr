package com.github.darksonic300.seidr.datagen;

import com.github.darksonic300.seidr.Seidr;
import com.github.darksonic300.seidr.item.ScrollItem;
import com.github.darksonic300.seidr.item.SeidrItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class SeidrLanguageData extends LanguageProvider {
    public SeidrLanguageData(PackOutput output) {
        super(output, Seidr.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addScroll(SeidrItems.ATTRACTION_SCROLL, "Attraction", "- All nearby animals will follow you");
        addScroll(SeidrItems.EFFECT_REMOVE_SCROLL, "Purity", "- Clears all active effects");
        addScroll(SeidrItems.FIREBALL_SCROLL, "Fireball", "- Shoots a powerful fireball");
        addScroll(SeidrItems.SOUND_BLAST_SCROLL, "Echo" , "- Harnesses the power of the Warden");

        addIncompleteScroll(SeidrItems.INCOMPLETE_UNDEAD_SCROLL, "Undead", "- Summons undead mobs to fight for you");
        addIncompleteScroll(SeidrItems.INCOMPLETE_RESISTANCE_SCROLL, "Endurance", "- Decreases incoming damage");
        addDamagedScroll(SeidrItems.DAMAGED_UNDEAD_SCROLL, "Undead", "- Summons undead mobs to fight for you");
        addDamagedScroll(SeidrItems.DAMAGED_RESISTANCE_SCROLL, "Resilience", "- Decreases incoming damage");
        addDamagedScroll(SeidrItems.DAMAGED_WALKING_SCROLL, "Light Walk", "- Makes you walk on water");
        addCompleteScroll(SeidrItems.COMPLETE_UNDEAD_SCROLL, "Undead", "- Summons undead mobs to fight for you");
        addCompleteScroll(SeidrItems.COMPLETE_RESISTANCE_SCROLL, "Resilience", "- Decreases incoming damage");
        addCompleteScroll(SeidrItems.COMPLETE_WALKING_SCROLL, "Light Walk", "- Makes you walk on water");

        this.add(SeidrItems.LLAMA_FUR.get().getDescriptionId(), "Llama Fur");
        this.add(SeidrItems.SACRIFICIAL_KNIFE.get().getDescriptionId(), "Sacrificial Knife");
        this.add(SeidrItems.FOX_TAIL.get().getDescriptionId(), "Fox Tail");
        this.add(SeidrItems.FOX_HAT.get().getDescriptionId(), "Fox Hat");
    }

    public void addScroll(Supplier<? extends ScrollItem> key, String name, String description) {
        this.add(key.get().getDescriptionId(), "Norse Scroll");
        this.add("tooltip." + key.get().getDescriptionId() + ".description" , description);
        this.add(key.get().getDescriptionId().replaceFirst("scroll", "tablet"), "Ruined Tablet");
    }

    public void addScroll(String prefix, Supplier<? extends ScrollItem> key, String name, String description) {
        this.add(key.get().getDescriptionId(), prefix + " Norse Scroll");
        this.add("tooltip." + key.get().getDescriptionId() + ".description" , description);
        this.add(key.get().getDescriptionId().replaceFirst("scroll", "tablet"), prefix + " Ruined Tablet");
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
