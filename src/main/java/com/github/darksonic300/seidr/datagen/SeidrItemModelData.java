package com.github.darksonic300.seidr.datagen;

import com.github.darksonic300.seidr.item.SeidrItems;
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
        for(DeferredHolder<Item, ? extends Item> item : SeidrItems.SCROLL_ITEMS.getEntries())
            this.scrollItem(item.get());

        for(DeferredHolder<Item, ? extends Item> item : SeidrItems.TABLET_ITEMS.getEntries())
            this.tabletItem(item.get());

        this.item(SeidrItems.SACRIFICIAL_KNIFE.get());
        this.item(SeidrItems.LLAMA_FUR.get());
        this.item(SeidrItems.FOX_TAIL.get());
        this.item(SeidrItems.FOX_HAT.get());
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

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }


    public String itemName(Item item) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }
}
