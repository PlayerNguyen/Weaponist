package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ThrowSmoke extends DefaultThrow {

    public ThrowSmoke() {
        super(ThrowEnum.SMOKE);
    }

    @Override
    public void onExplode(Shooter shooter, Location location) {
        BukkitRunnable runnable = new BukkitRunnable() {
            double duration = getExplodingTime();
            @Override
            public void run() {
                duration = duration - (1.0/20.0);

                // Create smoke
                new ParticleBuilder(Particle.CLOUD, 20 * 6)
                        .offset(new Vector(2, 3, 2))
                        .extra(0.1)
                        .play(location);

                if (duration <= 0) {
                    cancel();
                }
            }
        };

        runnable.runTaskTimerAsynchronously(getWeaponist(), 0, 0);
    }
}
