package com.playernguyen.runnable;

import com.playernguyen.Weaponist;
import com.playernguyen.entity.Shooter;
import com.playernguyen.event.WeaponistPlayerShootEntityEvent;
import com.playernguyen.event.WeaponistPlayerShootEvent;
import com.playernguyen.util.LocationUtil;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collection;
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

                    ((LivingEntity) entity).damage(d, shooter.asPlayer());

                    WeaponistPlayerShootEntityEvent event =
                            new WeaponistPlayerShootEntityEvent(shooter, (LivingEntity)entity, d);
                    Bukkit.getPluginManager().callEvent(event);
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
