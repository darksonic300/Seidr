package com.github.darksonic300.seidr.particle.custom;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

public class WaveParticle extends TextureSheetParticle {
    private int delay;

    protected WaveParticle(ClientLevel pLevel, double pX, double pY, double pZ, int pDelay) {
        super(pLevel, pX, pY, pZ, 0.0, 0.0, 0.0);
        this.quadSize = 0.85F;
        this.delay = pDelay;
        this.lifetime = 30;
        this.gravity = 0.0F;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }

    @Override
    public float getQuadSize(float pScaleFactor) {
        return this.quadSize * Mth.clamp(((float)this.age + pScaleFactor) / (float)this.lifetime * 0.75F, 0.0F, 1.0F);
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        if (this.delay <= 0) {
            this.alpha = 1.0F - Mth.clamp(((float)this.age + pPartialTicks) / (float)this.lifetime, 0.0F, 1.0F);
            Quaternionf quaternionf = new Quaternionf();
            quaternionf.rotationX(1.5708F);
            this.renderRotatedQuad(pBuffer, pRenderInfo, quaternionf, pPartialTicks);
            quaternionf.rotationYXZ((float) -Math.PI, 1.5708F, 0.0F);
            this.renderRotatedQuad(pBuffer, pRenderInfo, quaternionf, pPartialTicks);
        }
    }

    @Override
    public int getLightColor(float pPartialTick) {
        return 240;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        if (this.delay > 0) {
            this.delay--;
        } else {
            this.setSize((float) age / 10, (float) age / 10);
            super.tick();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            WaveParticle particle = new WaveParticle(worldIn, x, y, z, 0);
            particle.pickSprite(spriteSet);
            return particle;
        }
    }
}
