package com.github.darksonic300.seidr.entity.projectile;

import com.github.darksonic300.seidr.entity.SeidrEntityTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public class SoundBoomProjectile extends AbstractHurtingProjectile {
    private int ticksInAir;

    public SoundBoomProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public SoundBoomProjectile(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(SeidrEntityTypes.SONIC_BOOM_PROJECTILE.get(), shooter, new Vec3(accelX, accelY, accelZ), level);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        if (!this.onGround()) {
            ++this.ticksInAir;
        }
        if (this.ticksInAir > 5) {
            if (!this.level().isClientSide()) {
                this.discard();
            }
        }
        if (this.level().isClientSide() || (this.getOwner() == null || this.getOwner().isAlive()) && this.level().hasChunkAt(this.blockPosition())) {
            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitResult)) {
                this.onHit(hitResult);
            }

            this.checkInsideBlocks();
            Vec3 vec3 = this.getDeltaMovement();
            double d0 = this.getX() + vec3.x();
            double d1 = this.getY() + vec3.y();
            double d2 = this.getZ() + vec3.z();
            double d4 = vec3.horizontalDistance();
            this.setYRot((float)(Mth.atan2(-vec3.x, -vec3.z) * 180.0F / (float)Math.PI));

            this.setXRot((float)(Mth.atan2(vec3.y, d4) * 180.0F / (float)Math.PI));
            this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
            this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
            float f = this.getInertia();

            this.setDeltaMovement(vec3.scale(f));
            ParticleOptions particleoptions = this.getTrailParticle();
            if (particleoptions != null) {
                this.level().addParticle(particleoptions, d0, d1, d2, 0.0, 0.0, 0.0);
            }

            this.setPos(d0, d1, d2);
        } else {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (this.getOwner() instanceof LivingEntity owner)
            pResult.getEntity().hurt(damageSources().indirectMagic(owner, this), 20.0f);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("TicksInAir", this.ticksInAir);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("TicksInAir")) {
            this.ticksInAir = tag.getInt("TicksInAir");
        }
    }

    /**
     * Prevents this projectile from being on fire.
     */
    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SONIC_BOOM;
    }
}
