package com.github.darksonic300.seidr.datagen.loot.modifiers;

import com.github.darksonic300.seidr.Seidr;
import com.github.darksonic300.seidr.item.SeidrItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SeidrLootDataProvider extends GlobalLootModifierProvider {
    public SeidrLootDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Seidr.MODID);
    }

    @Override
    protected void start() {
        add("nether_loot_modifiers", new SeidrAddLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ruined_portal"))
                        .or(LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge")))
                        .build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.ATTRACTION_TABLET.get(), 1), 20),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.DAMAGED_UNDEAD_TABLET.get(), 1), 30),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.FIREBALL_TABLET.get(), 1), 60)
                        ),
                110,
                0.8f
        ));

        add("mineshaft_loot_modifiers", new SeidrAddLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"))
                        .or(LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple")))
                        .build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.INCOMPLETE_UNDEAD_TABLET.get(), 1), 70),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.EFFECT_REMOVE_TABLET.get(), 1), 30)
                ),
                100,
                0.8f
        ));
    }
}
