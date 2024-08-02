package com.github.darksonic300.seidr;

import com.github.darksonic300.seidr.client.SeidrSoundEvents;
import com.github.darksonic300.seidr.client.renderer.SeidrRenderers;
import com.github.darksonic300.seidr.datagen.SeidrItemModelData;
import com.github.darksonic300.seidr.datagen.SeidrLanguageData;
import com.github.darksonic300.seidr.datagen.SeidrRecipeData;
import com.github.darksonic300.seidr.datagen.loot.modifiers.SeidrGlobalLootModifiers;
import com.github.darksonic300.seidr.datagen.loot.modifiers.SeidrLootDataProvider;
import com.github.darksonic300.seidr.datagen.tags.SeidrBlockTagData;
import com.github.darksonic300.seidr.datagen.tags.SeidrItemTagData;
import com.github.darksonic300.seidr.effect.SeidrEffects;
import com.github.darksonic300.seidr.entity.SeidrEntityTypes;
import com.github.darksonic300.seidr.item.SeidrItems;
import com.github.darksonic300.seidr.particle.SeidrParticleTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import com.github.darksonic300.seidr.event.SeidrEvents;

import java.util.concurrent.CompletableFuture;

@Mod(Seidr.MODID)
public class Seidr
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "seidr";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Seidr(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::dataSetup);

        SeidrItems.SCROLL_ITEMS.register(modEventBus);
        SeidrItems.TABLET_ITEMS.register(modEventBus);
        SeidrSoundEvents.SOUNDS.register(modEventBus);
        SeidrEntityTypes.ENTITY_TYPES.register(modEventBus);
        SeidrParticleTypes.PARTICLE_TYPES.register(modEventBus);
        SeidrEffects.MOB_EFFECTS.register(modEventBus);
        SeidrGlobalLootModifiers.LOOT_MODIFIERS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        this.eventSetup(modEventBus);
    }

    public void eventSetup(IEventBus neoBus) {
        IEventBus bus = NeoForge.EVENT_BUS;
        SeidrEvents.listen(bus);

        neoBus.addListener(SeidrEntityTypes::registerEntityAttributes);
        neoBus.addListener(SeidrRenderers::registerEntityRenderers);
    }

    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new SeidrItemModelData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new SeidrLanguageData(packOutput));

        // Server Data
        generator.addProvider(event.includeServer(), new SeidrRecipeData(packOutput, lookupProvider));
        SeidrBlockTagData blockTags = new SeidrBlockTagData(packOutput, lookupProvider, fileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new SeidrItemTagData(packOutput, lookupProvider, blockTags.contentsGetter()));
        generator.addProvider(event.includeServer(), new SeidrLootDataProvider(packOutput, lookupProvider));
    }
}
