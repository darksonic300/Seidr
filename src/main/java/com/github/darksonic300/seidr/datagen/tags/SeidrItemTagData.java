package com.github.darksonic300.seidr.datagen.tags;

import com.github.darksonic300.seidr.Seidr;
import com.github.darksonic300.seidr.item.SeidrItems;
import com.github.darksonic300.seidr.item.SeidrScrollItems;
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
        Collection<DeferredHolder<Item, ? extends Item>> scrollEntries = SeidrScrollItems.SCROLL_ITEMS.getEntries();
        Collection<DeferredHolder<Item, ? extends Item>> tabletEntries = SeidrScrollItems.TABLET_ITEMS.getEntries();

        for (DeferredHolder<Item, ? extends Item> item : scrollEntries)
            scrollTag.add(item.get());

        for (DeferredHolder<Item, ? extends Item> item : tabletEntries)
            tabletTag.add(item.get());

    }
}
