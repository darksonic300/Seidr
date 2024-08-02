package com.github.darksonic300.seidr.datagen;

import com.github.darksonic300.seidr.datagen.tags.SeidrTags;
import com.github.darksonic300.seidr.item.SeidrItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.github.darksonic300.seidr.Seidr;

import java.util.concurrent.CompletableFuture;

public class SeidrRecipeData extends RecipeProvider {
    public SeidrRecipeData(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        for (DeferredHolder<Item, ? extends Item> item : SeidrItems.SCROLL_ITEMS.getEntries()) {
            scrollRecipe(item, consumer);
        }
        for (DeferredHolder<Item, ? extends Item> item : SeidrItems.TABLET_ITEMS.getEntries()) {
            duplicate(item.get(), consumer);
        }
    }

    public void scrollRecipe(DeferredHolder<Item, ? extends Item> scroll, RecipeOutput consumer) {
        Item ingredient = null;
        for (DeferredHolder<Item, ? extends Item> item : SeidrItems.TABLET_ITEMS.getEntries()) {
            if (scroll.getId().toLanguageKey().startsWith(item.getId().toLanguageKey().replaceFirst("_tablet", ""))) {
                ingredient = item.get();
            }
        }
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, scroll.get(), 1)
                .define('D', Items.PAPER)
                .define('T', ingredient)
                .pattern("DDD")
                .pattern("DTD")
                .pattern("DDD")
                .unlockedBy(getHasName(scroll.get()), has(scroll.get()))
                .save(consumer, name(scroll.get().getDescriptionId()));;
    }

    public void duplicate(Item tablet, RecipeOutput consumer) {
        Item item = Items.DIAMOND;
        if(new ItemStack(tablet).is(SeidrTags.Items.DAMAGED_TABLETS)) {
            item = Items.EMERALD;
        } else if(new ItemStack(tablet).is(SeidrTags.Items.INCOMPLETE_TABLETS)) {
            item = Items.GOLD_INGOT;
        }

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, tablet, 2)
                .define('D', item)
                .define('T', tablet)
                .pattern("DTD")
                .pattern("D D")
                .pattern("DDD")
                .unlockedBy(getHasName(tablet), has(tablet))
                .save(consumer, name(tablet.getDescriptionId()));
    }

    protected ResourceLocation name(String name) {
        return ResourceLocation.fromNamespaceAndPath(Seidr.MODID, name);
    }
}
