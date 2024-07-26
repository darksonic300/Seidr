package razordevs.seidr.client.renderer;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import razordevs.seidr.entity.SeidrEntityTypes;

public class SeidrRenderers {

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SeidrEntityTypes.DRAUGR.get(), DraugrRenderer::new);
    }
}
