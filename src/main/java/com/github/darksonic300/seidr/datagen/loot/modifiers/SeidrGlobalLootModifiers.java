package com.github.darksonic300.seidr.datagen.loot.modifiers;

import com.github.darksonic300.seidr.Seidr;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class SeidrGlobalLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Seidr.MODID);

    public static final Supplier<MapCodec<SeidrAddLootModifier>> NETHER_LOOT_CODEC = LOOT_MODIFIERS.register("nether_loot", () -> SeidrAddLootModifier.CODEC);
    public static final Supplier<MapCodec<SeidrAddLootModifier>> MINESHAFT_LOOT_CODEC = LOOT_MODIFIERS.register("mineshaft_loot", () -> SeidrAddLootModifier.CODEC);
    public static final Supplier<MapCodec<SeidrAddLootModifier>> SEA_LOOT_CODEC = LOOT_MODIFIERS.register("sea_loot", () -> SeidrAddLootModifier.CODEC);
    public static final Supplier<MapCodec<SeidrAddLootModifier>> BURIED_TREASURE_LOOT_CODEC = LOOT_MODIFIERS.register("buried_treasure_loot", () -> SeidrAddLootModifier.CODEC);
    public static final Supplier<MapCodec<SeidrAddLootModifier>> END_CITY_LOOT_CODEC = LOOT_MODIFIERS.register("end_city_loot", () -> SeidrAddLootModifier.CODEC);
    public static final Supplier<MapCodec<SeidrAddLootModifier>> ANCIENT_CITY_LOOT_CODEC = LOOT_MODIFIERS.register("ancient_city_loot", () -> SeidrAddLootModifier.CODEC);
}
