package com.github.darksonic300.seidr.client.renderer;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.github.darksonic300.seidr.Seidr;

@OnlyIn(Dist.CLIENT)
public class DraugrRenderer extends ZombieRenderer {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(Seidr.MODID, "textures/entity/draugr.png");

    public DraugrRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return TEXTURE_LOCATION;
    }
}
