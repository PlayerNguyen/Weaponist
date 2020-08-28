package com.playernguyen.weaponist.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class DefaultTarget implements Target {

    private final LivingEntity entity;
    private final Shooter shooter;
    private final boolean headshot;

    public DefaultTarget(LivingEntity entity, Shooter shooter, boolean headshot) {
        this.entity = entity;
        this.shooter = shooter;
        this.headshot = headshot;
    }

    @Override
    public boolean isHeadshot() {
        return headshot;
    }

    @Override
    public LivingEntity asEntity() {
        return entity;
    }

    @Override
    public Shooter getShooter() {
        return shooter;
    }

    @Override
    public Location getLocation() {
        return asEntity().getLocation();
    }
}
