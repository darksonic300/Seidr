package razordevs.seidr.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class NorseParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;
    private final double xStart;
    private final double yStart;
    private final double zStart;

    protected NorseParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, 0.0, 0.0, 0.0);
        this.xStart = this.x;
        this.yStart = this.y;
        this.zStart = this.z;
        this.spriteSet = pSprites;
        this.hasPhysics = false;
        this.friction = 0.9F;
        this.gravity = 0.1F;
        this.x = pX;
        this.y = pY;
        this.z = pZ;
        this.xd = (Math.random() * 2.0 - 1.0) * 0.05F;
        this.yd = (Math.random() * 2.0 - 1.0) * 0.08F;
        this.zd = (Math.random() * 2.0 - 1.0) * 0.05F;
        this.quadSize = 0.15F * (this.random.nextFloat() * 0.2F + 0.5F);
        this.lifetime = 25;

        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    public void move(double pX, double pY, double pZ) {
        this.setBoundingBox(this.getBoundingBox().move(pX, pY, pZ));
        this.setLocationFromBoundingbox();
    }

    @Override
    public void tick() {
        // Set the sprite for the current particle age, i.e. advance the animation.
        this.setSpriteFromAge(spriteSet);
        super.tick();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, ClientLevel pLevel, double pX, double pY, double pZ, double dX, double dY, double dZ) {
            return new NorseParticle(pLevel, pX, pY, pZ, this.sprites);
        }
    }
}
