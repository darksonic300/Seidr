package com.github.darksonic300.seidr.client.renderer;

import com.github.darksonic300.seidr.Seidr;
import com.github.darksonic300.seidr.entity.projectile.SoundBoomProjectile;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SoundBoomProjectileRenderer extends EntityRenderer<SoundBoomProjectile> {
    private static final RenderType RENDER_TYPE = RenderType.translucent();

    protected SoundBoomProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public boolean shouldRender(SoundBoomProjectile pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(SoundBoomProjectile pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Seidr.MODID, "textures/item/scroll_old.png");
    }
}
