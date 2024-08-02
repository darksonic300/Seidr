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
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/bastion_other"))
                        .or(LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge")))
                        .build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.ATTRACTION_TABLET.get(), 1), 20),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.DAMAGED_UNDEAD_TABLET.get(), 1), 30),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.FIREBALL_TABLET.get(), 1), 60)
                        ),
                110,
                0.9f
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
                0.9f
        ));

        add("sea_loot_modifiers", new SeidrAddLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_supply"))
                        .or(LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/underwater_ruin_big")))
                        .build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.DAMAGED_WALKING_TABLET.get(), 1), 70),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.EFFECT_REMOVE_TABLET.get(), 1), 30)
                ),
                100,
                0.9f
        ));

        add("buried_treasure_loot_modifiers", new SeidrAddLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/buried_treasure")).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.COMPLETE_WALKING_TABLET.get(), 1), 100)),
                100,
                0.9f
        ));

        add("end_city_loot_modifiers", new SeidrAddLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure")).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.INCOMPLETE_RESISTANCE_TABLET.get(), 1), 70),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.COMPLETE_UNDEAD_TABLET.get(), 1), 30)
                ),
                100,
                0.9f
        ));

        add("ancient_city_loot_modifiers", new SeidrAddLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city")).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(SeidrItems.SOUND_BLAST_TABLET.get(), 1), 30),
                        WeightedEntry.wrap(new ItemStack(SeidrItems.COMPLETE_RESISTANCE_TABLET.get(), 1), 70)
                ),
                100,
                0.9f
        ));
    }
}
