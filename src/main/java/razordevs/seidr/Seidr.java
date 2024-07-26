package razordevs.seidr;

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
import razordevs.seidr.client.SeidrSoundEvents;
import razordevs.seidr.client.renderer.SeidrRenderers;
import razordevs.seidr.datagen.SeidrItemModelData;
import razordevs.seidr.datagen.SeidrRecipeData;
import razordevs.seidr.entity.SeidrEntityTypes;
import razordevs.seidr.event.SeidrEvents;
import razordevs.seidr.item.SeidrScrollItems;
import razordevs.seidr.particle.SeidrParticleTypes;

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

        SeidrScrollItems.SCROLL_ITEMS.register(modEventBus);
        SeidrScrollItems.TABLET_ITEMS.register(modEventBus);
        SeidrSoundEvents.SOUNDS.register(modEventBus);
        SeidrEntityTypes.ENTITY_TYPES.register(modEventBus);
        SeidrParticleTypes.PARTICLE_TYPES.register(modEventBus);

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

        // Server Data
        generator.addProvider(event.includeServer(), new SeidrRecipeData(packOutput, lookupProvider));

    }
}
