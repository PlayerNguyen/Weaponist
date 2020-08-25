package com.playernguyen.asset.gun;

import com.playernguyen.Weaponist;
import com.playernguyen.entity.Shooter;
import com.playernguyen.ray.RayResult;
import com.playernguyen.runnable.RocketProjectileRunnable;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public abstract class RocketGun extends DefaultGun {

    public RocketGun(GunEnum gunEnum) throws IOException {
        super(gunEnum);
    }

    @Override
    public RayResult shoot(Shooter shooter, Plugin plugin) {
        Player player = shooter.asPlayer();

        BukkitRunnable r = new RocketProjectileRunnable(
                Weaponist.getWeaponist(),
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                2,
                getMaxDistance(),
                (float) getDamage()
        );
        r.runTaskTimerAsynchronously(Weaponist.getWeaponist(), 0, 0);


        return null;
    }
}
