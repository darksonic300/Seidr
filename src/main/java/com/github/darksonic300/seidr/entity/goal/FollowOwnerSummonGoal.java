package com.github.darksonic300.seidr.entity.goal;

import com.github.darksonic300.seidr.entity.Draugr;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.PathType;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FollowOwnerSummonGoal extends Goal {
    private final PathfinderMob entity;
    @Nullable
    private LivingEntity owner;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;

    public FollowOwnerSummonGoal(PathfinderMob draugr, LivingEntity owner, double speed, float start, float stop) {
        this.entity = draugr;
        this.speedModifier = speed;
        this.navigation = draugr.getNavigation();
        this.startDistance = start;
        this.stopDistance = stop;
        this.owner = owner;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(draugr.getNavigation() instanceof GroundPathNavigation) && !(draugr.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerSummonGoal");
        }
    }

    @Override
    public boolean canUse() {
        if (owner == null) {
            return false;
        } else if (this.entity.distanceToSqr(owner) < (double)(this.startDistance * this.startDistance)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        } else {
            return !(this.entity.distanceToSqr(this.owner) <= (double)(this.stopDistance * this.stopDistance));
        }
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.entity.getPathfindingMalus(PathType.WATER);
        this.entity.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.entity.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        this.entity.getLookControl().setLookAt(this.owner, 10.0F, (float)this.entity.getMaxHeadXRot());

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.navigation.moveTo(this.owner, this.speedModifier);
        }
    }
}