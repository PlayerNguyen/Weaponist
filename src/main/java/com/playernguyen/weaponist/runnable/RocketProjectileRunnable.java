package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.LocationUtil;
import com.playernguyen.weaponist.util.WeaponistUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collections;

public class RocketProjectileRunnable extends BukkitRunnable {

    private final Weaponist weaponist;

    private Location currentLocation;
    private final Location firstLocation;
    private final Vector direction;
    private final float damage;
    private final int maxDistance;
    private final Shooter shooter;

    public RocketProjectileRunnable(Weaponist weaponist,
                                    Shooter shooter,
                                    Location location,
                                    Vector direction,
                                    float speed,
                                    int maxDistance,
                                    float damage) {
        this.weaponist = weaponist;
        this.firstLocation = new Location(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
        this.currentLocation = new Location(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
        this.direction = direction.multiply(speed);
        this.damage = damage;
        this.maxDistance = maxDistance;
        this.shooter = shooter;
    }

    @Override
    public void run() {

        // Put direction
        this.currentLocation = currentLocation.add(direction);

        // Spawn particle
        //System.out.println(currentLocation.distance(firstLocation) );
        if (currentLocation.distance(firstLocation) >= 1) {
            this.currentLocation.getWorld()
                    .spawnParticle(Particle.SMOKE_NORMAL, currentLocation, 15, 0.1, .1, .1, 0.2);
            this.currentLocation.getWorld()
                    .spawnParticle(Particle.CRIT_MAGIC, currentLocation, 1);
        }

        // Cancel
        if (currentLocation.getBlock().getType() != Material.AIR
        || currentLocation.distance(firstLocation) >= maxDistance
        || LocationUtil.hasLivingEntity(
                currentLocation,
                        0.3,
                        Collections.singletonList(shooter.asPlayer()))
        ) {
            // Create explosion
            WeaponistUtil.fakeExplosion(currentLocation, damage, shooter);
            cancel();
        }

    }


    public Weaponist getPlugin() {
        return weaponist;
    }
}
