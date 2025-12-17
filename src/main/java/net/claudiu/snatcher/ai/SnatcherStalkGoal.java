package net.claudiu.snatcher.ai;


import net.claudiu.snatcher.entity.SnatcherEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;


public class SnatcherStalkGoal extends Goal {


    private final SnatcherEntity snatcher;
    private Player target;


    public SnatcherStalkGoal(SnatcherEntity snatcher) {
        this.snatcher = snatcher;
    }


    @Override
    public boolean canUse() {
        target = snatcher.level().getNearestPlayer(snatcher, 35);
        return target != null && !snatcher.isPlayerLookingAtMe(target) && snatcher.isInDarkness();
    }


    @Override
    public void tick() {
        snatcher.getNavigation().moveTo(target, 1.0);
    }
}