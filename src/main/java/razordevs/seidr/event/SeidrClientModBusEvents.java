package razordevs.seidr.event;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import razordevs.seidr.Seidr;
import razordevs.seidr.item.ScrollItem;
import razordevs.seidr.item.SeidrScrollItems;
import razordevs.seidr.particle.SeidrParticleTypes;
import razordevs.seidr.particle.custom.NorseParticle;
import razordevs.seidr.particle.custom.WaveParticle;

@EventBusSubscriber(modid = Seidr.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class SeidrClientModBusEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> { // ItemProperties#register is not threadsafe, so we need to call it on the main thread
            for (DeferredHolder<Item, ? extends Item> item : SeidrScrollItems.SCROLL_ITEMS.getEntries())
                ItemProperties.register(
                        item.get(),
                        // The id of the property.
                        ResourceLocation.fromNamespaceAndPath(Seidr.MODID, "using"),
                        // A reference to a method that calculates the override value.
                        (stack, level, player, seed) -> ((ScrollItem) stack.getItem()).isInUse() ? 1 : 0
                );
        });
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SeidrParticleTypes.NORSE_PARTICLE.get(), NorseParticle.Provider::new);
        event.registerSpriteSet(SeidrParticleTypes.WAVE_PARTICLE.get(), WaveParticle.Factory::new);
    }
}
