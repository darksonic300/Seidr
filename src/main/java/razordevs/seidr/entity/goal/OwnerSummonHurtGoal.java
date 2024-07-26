package razordevs.seidr.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import razordevs.seidr.entity.Draugr;

import java.util.EnumSet;

public class OwnerSummonHurtGoal extends TargetGoal {

    private final Draugr entity;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public OwnerSummonHurtGoal(Draugr entity) {
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
                this.ownerLastHurt = livingentity.getLastHurtMob();
                int i = livingentity.getLastHurtMobTimestamp();
                return i != this.timestamp
                        && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT);
            }
    }

    @Override
    public void start() {
        this.mob.setTarget(this.ownerLastHurt);
        LivingEntity livingentity = this.entity.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
