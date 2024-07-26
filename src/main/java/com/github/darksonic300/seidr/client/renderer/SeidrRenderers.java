package com.github.darksonic300.seidr.client.renderer;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import com.github.darksonic300.seidr.entity.SeidrEntityTypes;

public class SeidrRenderers {

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SeidrEntityTypes.DRAUGR.get(), DraugrRenderer::new);
        event.registerEntityRenderer(SeidrEntityTypes.SONIC_BOOM_PROJECTILE.get(), SoundBoomProjectileRenderer::new);
    }
}
