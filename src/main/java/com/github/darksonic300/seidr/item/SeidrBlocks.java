package com.github.darksonic300.seidr.item;

import com.github.darksonic300.seidr.Seidr;
import com.github.darksonic300.seidr.block.NorseAltar;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SeidrBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Seidr.MODID);

    public static final DeferredBlock<Block> NORTH_STONE = registerBlock("north_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> NORSE_ALTAR = registerBlock("norse_altar", () -> new NorseAltar(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return SeidrItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
