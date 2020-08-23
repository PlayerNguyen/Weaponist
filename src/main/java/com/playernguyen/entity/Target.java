package com.playernguyen.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public interface Target {

    LivingEntity asEntity();

    Shooter getShooter();

    boolean isHeadshot();

    Location getLocation();

}
