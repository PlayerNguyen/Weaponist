package com.playernguyen.ray;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.location.LocationIterator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class RayTrace extends WeaponistInstance {

    private Player player;
    private int distance;

    public RayTrace(Player player, int distance) {
        this.player = player;
        this.distance = distance;
    }

    public RayResult ray(Particle particle, int maxPenetrate) {

        RayResult result = new RayResult();
        AtomicInteger penetrate = new AtomicInteger();

        LocationIterator locationIterator = new LocationIterator(
                getPlayer().getEyeLocation(),
                getPlayer().getEyeLocation().getDirection(),
                getDistance()
        );

        Bukkit.getScheduler().runTaskAsynchronously(getWeaponist(), () -> {
            while (locationIterator.hasNext()) {

                Location nextLocation = locationIterator.next();

                // Particle play
                if (particle != null) {
                    nextLocation.getWorld().spawnParticle(particle, nextLocation, 1);
                }

                // Penetrate with block
                if (nextLocation.getBlock().getType() != Material.AIR) {
                    getDebugger().info("New Block Hitting: %s" ,nextLocation
                            .getBlock()
                            .getType()
                            .toString()
                            .toLowerCase()
                    );
                    penetrate.getAndIncrement();
                    result.setHitBlock(nextLocation.getBlock());
                }

                double radius = 0.3f;

                // Hit target
                for (Entity entity : nextLocation.getWorld().getNearbyEntities(nextLocation, radius, radius, radius)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        Bukkit.getScheduler().runTask(getWeaponist(), () -> {
                            getDebugger()
                                    .info("New Entity Target: %s", entity.getType().toString().toLowerCase());
                            penetrate.getAndIncrement();
                        });
                    }
                }

                getDebugger().info("Current penetrate: %s", penetrate.toString());
                if (penetrate.get() >= maxPenetrate) { break; }

                if (locationIterator.outOfLimit()) {
                    getDebugger().info("Ray out of limit !");
                }
            }
        });

        return result;
    }

    public int getDistance() {
        return distance;
    }

    public Player getPlayer() {
        return player;
    }
}

