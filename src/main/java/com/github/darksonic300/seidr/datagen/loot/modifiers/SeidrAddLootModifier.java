package com.github.darksonic300.seidr.datagen.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class SeidrAddLootModifier extends LootModifier {

    public static final MapCodec<SeidrAddLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(inst.group(
                    WeightedEntry.Wrapper.codec(ItemStack.CODEC).listOf().fieldOf("items").forGetter(m -> m.items),
                    Codec.INT.fieldOf("totalWeight").forGetter(m -> m.totalWeight),
                    Codec.FLOAT.fieldOf("chanceToSpawn").forGetter(m -> m.chance)
            )).apply(inst, SeidrAddLootModifier::new)
    );

    public final List<WeightedEntry.Wrapper<ItemStack>> items;
    public final int totalWeight;
    public final float chance;

    public SeidrAddLootModifier(final LootItemCondition[] conditionsIn, List<WeightedEntry.Wrapper<ItemStack>> items, int totalWeight, float chance) {
        super(conditionsIn);
        this.items = items.stream().map(wrapper -> WeightedEntry.wrap(wrapper.data().copy(), wrapper.getWeight().asInt())).toList();
        this.totalWeight = totalWeight;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        // size of the loots
        int size = generatedLoot.size();

        // is the loot full?
        boolean isFull = size == 27;

        // size diff for when it's not full
        int sizeDiff = 27-size;

        // if the loot is not full, for each slot remaining, have x chance to add one of our item in the empty slots
        if(!isFull) {
            for(int i = 0; i<= sizeDiff; i++) {
                if(context.getRandom().nextFloat() > chance) {
                    WeightedRandom.getRandomItem(context.getRandom(), this.items, totalWeight).ifPresent(e -> generatedLoot.add(e.data()));
                }
            }
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
