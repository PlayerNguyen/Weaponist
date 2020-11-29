package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.LocationUtil;
import com.playernguyen.weaponist.util.ParticleBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ThrowSmoke extends DefaultThrow {

    public ThrowSmoke() {
        super(ThrowEnum.SMOKE);
    }

    @Override
    public void onExplode(Shooter shooter, Location location) {
        BukkitRunnable runnable = new BukkitRunnable() {
            double duration = getExplodingTime() * 20;
            @Override
            public void run() {
//                duration = 0;
                duration--;

                // Create smoke
                this.createSmoke(location);

                if (duration <= 0) {
                    cancel();
                }
            }

            private void createSmoke(Location location) {
                new ParticleBuilder(Particle.CLOUD, 20 * 6)
                        .offset(new Vector(2, 3, 2))
                        .extra(0.1)
                        .play(location);
                // Replace airs with blocks
//                List<Location> circle = LocationUtil.circle(location, 5, 5, false, true, 0);
//                for (Location location1 : circle) {
//                    Bukkit.getScheduler().runTask(Weaponist.getWeaponist(), () -> {
//                        Block b = location1.getBlock();
//                        if (b.getType() == Material.AIR) {
//                            b.setType(Material.RED_ROSE);
//                            b.setData((byte) 2);
//                            Bukkit.getScheduler().runTaskLater(Weaponist.getWeaponist(), () -> {
//                                for (Location location2 : circle) {
//                                    Block b2 = location2.getBlock();
//                                    if (b2.getType() == Material.RED_ROSE && b2.getData() == 3) {
//                                        b2.setType(Material.AIR);
//                                    }
//                                }
//                            }, 100);
//                        }
//                    });
//                }
            }
        };

        runnable.runTaskTimerAsynchronously(getWeaponist(), 0, 0);
    }


}
