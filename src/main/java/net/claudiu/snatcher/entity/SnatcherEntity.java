package net.claudiu.snatcher.entity;

import net.claudiu.snatcher.ai.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SnatcherEntity extends Monster {

    public SnatcherEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    // ===== ATTRIBUTES =====
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    // ===== AI GOALS =====
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SnatcherAvoidLookGoal(this));
        this.goalSelector.addGoal(2, new SnatcherStalkGoal(this));
        this.goalSelector.addGoal(3, new SnatcherGrabGoal(this));
    }

    // ===== HELPERS =====
    public boolean isPlayerLookingAtMe(Player player) {
        var look = player.getLookAngle().normalize();
        var diff = this.position().subtract(player.position()).normalize();
        return look.dot(diff) > 0.75;
    }

    public boolean isInDarkness() {
        BlockPos pos = this.blockPosition();
        return this.level().getMaxLocalRawBrightness(pos) < 7;
    }
}
