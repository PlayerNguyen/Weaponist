package com.playernguyen.runnable;

import com.playernguyen.Weaponist;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;

public class RocketProjectileRunnable extends BukkitRunnable {

    private final Weaponist weaponist;

    private Location currentLocation;
    private Location firstLocation;
    private Vector direction;
    private float speed;
    private float damage;
    private int maxDistance;

    public RocketProjectileRunnable(Weaponist weaponist,
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
        );;
        this.direction = direction.multiply(1);
        this.speed = speed;
        this.damage = damage;
        this.maxDistance = maxDistance;
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
        || currentLocation.distance(firstLocation) >= maxDistance) {
            fakeExplosion(currentLocation, damage);
            cancel();
        }

    }

    private void fakeExplosion(Location location, float power) {
        // Play particle
        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, Math.round(power/5), .2 ,.2 ,.2, 0.2);
        location.getWorld().spawnParticle(Particle.SMOKE_LARGE, location, Math.round(power/5), .2, .2, .2, 0.2);
        // Get living entity
        Collection<Entity> entities = location.getWorld().getNearbyEntities(location, power/5, power / 5, power/5);
        for (Entity entity : entities) {
            // Living entity
            if (entity instanceof LivingEntity) {
                Location entityLocation = entity.getLocation();
                Bukkit.getScheduler().runTask(getPlugin(), () -> {
                    double d = power-(entityLocation.distance(location));
                    //System.out
                    // .println(entity.toString() + " -> damage: " + d + " distance: " +
                    // entityLocation.distance(location));

                    ((LivingEntity) entity).damage(d);
                });

                Vector direction = new Vector(
                        entityLocation.getX()-currentLocation.getX(),
                        entityLocation.getY()-currentLocation.getY(),
                        entityLocation.getZ()-currentLocation.getZ()
                );

                entity.setVelocity(direction.normalize());
            }
        }
        // Play sound
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
    }


    public Weaponist getPlugin() {
        return weaponist;
    }
}
