package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.entity.DefaultTarget;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.entity.Target;
import com.playernguyen.weaponist.location.LocationIterator;
import com.playernguyen.weaponist.util.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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
    private Block firstCollideBlock;

    private int blockPenetrate = 0;
    private int entityPenetrate = 0;
    private double rate = 0;

    public RayCollectRunnable(Shooter shooter, int maxDistance, int maxPenetrate) {
        this.shooter = shooter;
        this.maxDistance = maxDistance;
        this.particle = null;
        this.maxPenetrate = maxPenetrate;
    }

    @Deprecated
    public RayCollectRunnable(Shooter shooter, int maxDistance, int maxPenetrate,  Particle particle) {
        this.shooter = shooter;
        this.maxDistance = maxDistance;
        this.particle = particle;
        this.maxPenetrate = maxPenetrate;
    }

    public RayCollectRunnable(Shooter shooter, int maxDistance, int maxPenetrate, Particle particle, double rate) {
        this.shooter = shooter;
        this.maxDistance = maxDistance;
        this.particle = particle;
        this.maxPenetrate = maxPenetrate;
        this.rate = rate;
    }

    @Override
    public void run() {
        // Shooter location
        Location eyeLocation = shooter.asPlayer().getEyeLocation();

        Vector rateCompound = new Vector(
                generateRate(-rate/3, rate/3),
                generateRate(0, rate/2),
                generateRate(-rate/3, rate/3)
        );

        LocationIterator locationIterator = new LocationIterator(
                eyeLocation,
                eyeLocation.getDirection().add(rateCompound),
                maxDistance
        );

        // Iterator
        while (locationIterator.hasNext()) {
            Location currentLocation = locationIterator.next();

            // Play particle
            if (currentLocation.distance(eyeLocation) >= 2 && particle != null) {
//                currentLocation.getWorld().spawnParticle(particle, currentLocation, 1, 0.5f);

                new ParticleBuilder(particle, 1)
                        .extra(0.001).play(currentLocation);
            }

            double detectOffset = 0.1;
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
                    entityPenetrate ++;
                }
            }

            // Block set
            if (!currentLocation.getBlock().getType().isTransparent()) {

                if (this.lastBlock == null || !this.lastBlock.equals(currentLocation.getBlock())) {
                    this.lastBlock = currentLocation.getBlock();
                    if (blockPenetrate == 0) {
                        this.firstCollideBlock = currentLocation.getBlock();
                    }
                    blockPenetrate ++;
                }
            }


            // Break the iterator if the penetrate was full
            if ((blockPenetrate + entityPenetrate) >= maxPenetrate) {
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

    public Block getFirstCollideBlock() {
        return firstCollideBlock;
    }

    private double generateRate(double min, double max) {
        return min + Math.random() * (max - min);
    }

}
