package com.github.darksonic300.seidr.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class NorseParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;


    protected NorseParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, 0.0, 0.0, 0.0);
        this.spriteSet = pSprites;
        this.xd = pX;
        this.yd = pY;
        this.zd = pZ;
        this.hasPhysics = false;
        this.friction = 0.9F;
        this.quadSize = 0.15F * (this.random.nextFloat() * 0.2F + 0.5F);
        this.lifetime = 30;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;

        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public void move(double pX, double pY, double pZ) {
    }

    @Override
    public void tick() {
        this.setSpriteFromAge(spriteSet);
        super.tick();
    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, ClientLevel pLevel, double pX, double pY, double pZ, double centerX, double centerY, double centerZ) {
            return new NorseParticle(pLevel, pX, pY, pZ, this.sprites);
        }
    }
}
