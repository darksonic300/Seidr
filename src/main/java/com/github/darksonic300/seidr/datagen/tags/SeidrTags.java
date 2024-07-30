package com.github.darksonic300.seidr.datagen.tags;

import com.github.darksonic300.seidr.Seidr;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SeidrTags {
    public static class Items {
        public static final TagKey<Item> SCROLLS = tag("scrolls");
        public static final TagKey<Item> TABLETS = tag("tablets");

        public static final TagKey<Item> INCOMPLETE_TABLETS = tag("incomplete_scrolls");
        public static final TagKey<Item> DAMAGED_TABLETS = tag("damaged_scrolls");
        public static final TagKey<Item> COMPLETE_TABLETS = tag("complete_scrolls");

        public static final TagKey<Item> INCOMPLETE_SCROLLS = tag("incomplete_scrolls");
        public static final TagKey<Item> DAMAGED_SCROLLS = tag("damaged_scrolls");
        public static final TagKey<Item> COMPLETE_SCROLLS = tag("complete_scrolls");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Seidr.MODID, name));
        }
    }
}
