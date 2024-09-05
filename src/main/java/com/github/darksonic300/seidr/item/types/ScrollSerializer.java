package com.github.darksonic300.seidr.item.types;

import com.github.darksonic300.seidr.item.ScrollItem;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;

public interface ScrollSerializer<T extends ScrollItem> {

    MapCodec<T> codec();

    StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
}
