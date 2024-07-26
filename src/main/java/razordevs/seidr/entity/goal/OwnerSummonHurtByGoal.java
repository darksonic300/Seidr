package razordevs.seidr.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import razordevs.seidr.entity.Draugr;

import java.util.EnumSet;

public class OwnerSummonHurtByGoal extends TargetGoal {
    private final Draugr entity;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public OwnerSummonHurtByGoal(Draugr entity) {
        super(entity, false);
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.entity.getOwner();
        if (livingentity == null) {
            return false;
        } else {
            this.ownerLastHurtBy = livingentity.getLastHurtByMob();
            int i = livingentity.getLastHurtByMobTimestamp();
            return i != this.timestamp
                    && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT);
        }
    }

    @Override
    public void start() {
        this.mob.setTarget(this.ownerLastHurtBy);
        LivingEntity livingentity = this.entity.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getLastHurtByMobTimestamp();
        }

        super.start();
    }
}
