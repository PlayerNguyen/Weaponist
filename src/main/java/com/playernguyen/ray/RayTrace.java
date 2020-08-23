package com.playernguyen.ray;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.entity.Shooter;
import com.playernguyen.location.LocationIterator;
import com.playernguyen.runnable.RayCollectRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class RayTrace extends WeaponistInstance {

    private final Shooter shooter;
    private final int distance;

    public RayTrace(Shooter shooter, int distance) {
        this.shooter = shooter;
        this.distance = distance;
    }

    public RayResult ray(Particle particle, int maxPenetrate) {
        RayCollectRunnable collector =
                new RayCollectRunnable(shooter, distance, maxPenetrate, particle);
        // Run the runnable
        collector.run();
        return new RayResult(shooter, collector.getTargets(), collector.getLastBlock());
    }

    public int getDistance() {
        return distance;
    }

    public Shooter getShooter() {
        return shooter;
    }
}

