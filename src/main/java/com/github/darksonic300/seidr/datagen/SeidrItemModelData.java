package com.github.darksonic300.seidr.datagen;

import com.github.darksonic300.seidr.datagen.tags.SeidrItemTagData;
import com.github.darksonic300.seidr.datagen.tags.SeidrTags;
import com.github.darksonic300.seidr.item.SeidrScrollItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.github.darksonic300.seidr.Seidr;

public class SeidrItemModelData extends ItemModelProvider {
    public SeidrItemModelData(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Seidr.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(DeferredHolder<Item, ? extends Item> item : SeidrScrollItems.SCROLL_ITEMS.getEntries())
            this.scrollItem(item.get());

        for(DeferredHolder<Item, ? extends Item> item : SeidrScrollItems.TABLET_ITEMS.getEntries())
            this.tabletItem(item.get());
    }

    public void scrollItem(Item item){
        this.withExistingParent(BuiltInRegistries.ITEM.getKey(item).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/scroll"))
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Seidr.MODID, "using"), 1f)
                .model(this.getExistingFile(this.modLoc("item/scroll_using"))).end();
    }

    public void tabletItem(Item item){
        this.withExistingParent(BuiltInRegistries.ITEM.getKey(item).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/tablet"));
    }
}
