package com.github.darksonic300.seidr.datagen.tags;

import com.github.darksonic300.seidr.item.SeidrItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class SeidrItemTagData extends ItemTagsProvider {
    public SeidrItemTagData(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Seidr Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        IntrinsicTagAppender<Item> scrollTag = this.tag(SeidrTags.Items.SCROLLS);
        IntrinsicTagAppender<Item> tabletTag = this.tag(SeidrTags.Items.TABLETS);
        Collection<DeferredHolder<Item, ? extends Item>> scrollEntries = SeidrItems.SCROLL_ITEMS.getEntries();
        Collection<DeferredHolder<Item, ? extends Item>> tabletEntries = SeidrItems.TABLET_ITEMS.getEntries();

        for (DeferredHolder<Item, ? extends Item> item : scrollEntries)
            scrollTag.add(item.get());

        for (DeferredHolder<Item, ? extends Item> item : tabletEntries)
            tabletTag.add(item.get());

        this.tag(SeidrTags.Items.INCOMPLETE_TABLETS).add(
                SeidrItems.INCOMPLETE_RESISTANCE_TABLET.get(),
                SeidrItems.INCOMPLETE_UNDEAD_TABLET.get(),
                SeidrItems.FIREBALL_TABLET.get(),
                SeidrItems.ATTRACTION_TABLET.get()
        );

        this.tag(SeidrTags.Items.DAMAGED_TABLETS).add(
                SeidrItems.DAMAGED_RESISTANCE_TABLET.get(),
                SeidrItems.DAMAGED_UNDEAD_TABLET.get(),
                SeidrItems.DAMAGED_WALKING_TABLET.get(),
                SeidrItems.EFFECT_REMOVE_TABLET.get()
                );

        this.tag(SeidrTags.Items.COMPLETE_TABLETS).add(
                SeidrItems.COMPLETE_RESISTANCE_TABLET.get(),
                SeidrItems.COMPLETE_UNDEAD_TABLET.get(),
                SeidrItems.COMPLETE_WALKING_TABLET.get(),
                SeidrItems.SOUND_BLAST_TABLET.get()
        );

        this.tag(SeidrTags.Items.INCOMPLETE_SCROLLS).add(
                SeidrItems.INCOMPLETE_RESISTANCE_SCROLL.get(),
                SeidrItems.INCOMPLETE_UNDEAD_SCROLL.get(),
                SeidrItems.FIREBALL_SCROLL.get(),
                SeidrItems.ATTRACTION_SCROLL.get()
        );

        this.tag(SeidrTags.Items.DAMAGED_SCROLLS).add(
                SeidrItems.DAMAGED_RESISTANCE_SCROLL.get(),
                SeidrItems.DAMAGED_UNDEAD_SCROLL.get(),
                SeidrItems.DAMAGED_WALKING_SCROLL.get(),
                SeidrItems.EFFECT_REMOVE_SCROLL.get()
        );

        this.tag(SeidrTags.Items.COMPLETE_SCROLLS).add(
                SeidrItems.COMPLETE_RESISTANCE_SCROLL.get(),
                SeidrItems.COMPLETE_UNDEAD_SCROLL.get(),
                SeidrItems.COMPLETE_WALKING_SCROLL.get(),
                SeidrItems.SOUND_BLAST_SCROLL.get()
        );

    }
}
