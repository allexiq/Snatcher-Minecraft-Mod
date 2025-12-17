package net.claudiu.snatcher.ai;


import net.claudiu.snatcher.entity.SnatcherEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import java.util.List;

public class SnatcherGrabGoal extends Goal {


    private final SnatcherEntity snatcher;
    private Player target;


    public SnatcherGrabGoal(SnatcherEntity snatcher) {
        this.snatcher = snatcher;
    }


    @Override
    public boolean canUse() {
        target = snatcher.level().getNearestPlayer(snatcher, 3);
        return target != null;
    }


    @Override
    public void start() {
        target.startRiding(snatcher);
    }


    @Override
    public void tick() {
        // Drag target if riding
        if (target != null && target.isPassenger()) {
            target.startRiding(snatcher, true);
        }

        // Find nearest Creeper in 30-block radius
        List<Creeper> creepers = snatcher.level().getEntitiesOfClass(
                Creeper.class,
                snatcher.getBoundingBox().inflate(30)
        );

        if (!creepers.isEmpty()) {
            // Find the closest creeper manually
            Creeper closest = creepers.get(0);
            double minDistance = snatcher.distanceToSqr(closest);

            for (Creeper c : creepers) {
                double d = snatcher.distanceToSqr(c);
                if (d < minDistance) {
                    closest = c;
                    minDistance = d;
                }
            }

            // Navigate toward the closest creeper
            snatcher.getNavigation().moveTo(closest, 1.4);
        }
    }
}