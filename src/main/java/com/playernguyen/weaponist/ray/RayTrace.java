package com.playernguyen.weaponist.ray;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.runnable.RayCollectRunnable;
import org.bukkit.Particle;

public class RayTrace extends WeaponistInstance {

    private final Shooter shooter;
    private final int distance;
    private final double rate;

    @Deprecated
    public RayTrace(Shooter shooter, int distance) {
        this.shooter = shooter;
        this.distance = distance;
        this.rate = 0;
    }

    public RayTrace(Shooter shooter, int distance, double rate) {
        this.shooter = shooter;
        this.distance = distance;
        this.rate = rate;
    }

    public RayResult ray(Particle particle, int maxPenetrate) {
        RayCollectRunnable collector =
                new RayCollectRunnable(shooter, distance, maxPenetrate, particle, rate / 10);
        // Run the runnable
        collector.run();
        return new RayResult(shooter, collector.getTargets(), collector.getFirstCollideBlock());
    }

    public int getDistance() {
        return distance;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public double getRate() {
        return rate;
    }
}

