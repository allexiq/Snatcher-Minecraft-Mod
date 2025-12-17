package net.claudiu.snatcher.ai;


import net.claudiu.snatcher.entity.SnatcherEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;


import java.util.EnumSet;


public class SnatcherAvoidLookGoal extends Goal {


    private final SnatcherEntity snatcher;
    private Player target;


    public SnatcherAvoidLookGoal(SnatcherEntity snatcher) {
        this.snatcher = snatcher;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }


    @Override
    public boolean canUse() {
        target = snatcher.level().getNearestPlayer(snatcher, 30);
        return target != null && snatcher.isPlayerLookingAtMe(target);
    }


    @Override
    public void tick() {
        double distance = snatcher.distanceTo(target);


        if (distance > 12) {
            snatcher.getNavigation().stop();
        } else if (distance > 4) {
            snatcher.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.45);
            snatcher.getNavigation().moveTo(snatcher.getRandomX(10), snatcher.getY(), snatcher.getRandomZ(10), 1.5);
        }
    }
}