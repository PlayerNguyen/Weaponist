package com.playernguyen.runnable;

import com.playernguyen.entity.DefaultTarget;
import com.playernguyen.entity.Shooter;
import com.playernguyen.entity.Target;
import com.playernguyen.location.LocationIterator;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RayCollectRunnable extends BukkitRunnable {

    private final Shooter shooter;
    private final int maxDistance;
    private final Particle particle;
    private final int maxPenetrate;

    private final List<Target> targets = new ArrayList<>();
    private Block lastBlock;

    private int penetrate = 0;

    public RayCollectRunnable(Shooter shooter, int maxDistance, int maxPenetrate) {
        this.shooter = shooter;
        this.maxDistance = maxDistance;
        this.particle = null;
        this.maxPenetrate = maxPenetrate;
    }

    public RayCollectRunnable(Shooter shooter, int maxDistance, int maxPenetrate,  Particle particle) {
        this.shooter = shooter;
        this.maxDistance = maxDistance;
        this.particle = particle;
        this.maxPenetrate = maxPenetrate;
    }

    @Override
    public void run() {
        // Shooter location
        Location eyeLocation = shooter.asPlayer().getEyeLocation();
        LocationIterator locationIterator = new LocationIterator(
                eyeLocation,
                eyeLocation.getDirection(),
                maxDistance
        );

        // Iterator
        while (locationIterator.hasNext()) {
            Location currentLocation = locationIterator.next();

            // Play particle
            if (currentLocation.distance(eyeLocation) >= 2)
            currentLocation.getWorld().spawnParticle(particle, currentLocation, 1);

            double detectOffset = 0.5;
            // Detect
            Collection<Entity> entities =
                    currentLocation.getWorld()
                            .getNearbyEntities(
                                    currentLocation,
                                    detectOffset,
                                    detectOffset,
                                    detectOffset);
            for (Entity entity : entities) {
                // Remove shooter and get living entity
                if (entity instanceof LivingEntity
                        && entity != shooter.asPlayer()) {

                    boolean headshot = (currentLocation.distance(((LivingEntity) entity).getEyeLocation()) <= 0.5f);
                    Target target = new DefaultTarget((LivingEntity) entity, shooter, headshot);
                    targets.add(target);
                    penetrate ++;
                }
            }

            // Block set
            if (!currentLocation.getBlock().getType().isTransparent()) {
                this.lastBlock = currentLocation.getBlock();
                penetrate ++;
            }


            // Break the iterator if the penetrate was full
            if (penetrate >= maxPenetrate) {
                break;
            }
        }

    }

    public List<Target> getTargets() {
        return targets;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Block getLastBlock() {
        return lastBlock;
    }

}
