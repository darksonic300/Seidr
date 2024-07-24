package razordevs.seidr;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import razordevs.seidr.client.SeidrSoundEvents;
import razordevs.seidr.event.SeidrEvents;
import razordevs.seidr.items.SeidrItems;

@Mod(Seidr.MODID)
public class Seidr
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "seidr";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Seidr(IEventBus modEventBus, ModContainer modContainer)
    {
        SeidrItems.ITEMS.register(modEventBus);
        SeidrSoundEvents.SOUNDS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        this.eventSetup(modEventBus);
    }

    public void eventSetup(IEventBus neoBus) {
        IEventBus bus = NeoForge.EVENT_BUS;
        SeidrEvents.listen(bus);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
