package com.playernguyen.asset.gun;

import com.playernguyen.Weaponist;
import com.playernguyen.entity.Shooter;
import com.playernguyen.ray.RayResult;
import com.playernguyen.runnable.RocketProjectileRunnable;
import com.playernguyen.sound.SoundConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public abstract class DefaultRocketGun extends DefaultGun {

    public DefaultRocketGun(GunEnum gunEnum) throws IOException {
        super(gunEnum);
    }

    @Override
    public RayResult shoot(Shooter shooter, Plugin plugin) {
        Player player = shooter.asPlayer();

        BukkitRunnable r = new RocketProjectileRunnable(
                Weaponist.getWeaponist(),
                shooter,
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                2,
                getMaxDistance(),
                (float) getDamage()
        );
        r.runTaskTimerAsynchronously(Weaponist.getWeaponist(), 0, 0);

        // Take ammo
        player
                .getInventory().getItemInMainHand()
                .setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

        // Play sound
        for (SoundConfiguration soundConfiguration : getShootSoundList()) {
            soundConfiguration.play(shooter.asPlayer().getLocation());
        }
        return null;
    }
}
