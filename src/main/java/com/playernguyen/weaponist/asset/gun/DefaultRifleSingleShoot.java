package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.ray.RayResult;
import com.playernguyen.weaponist.sound.SoundConfiguration;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public abstract class DefaultRifleSingleShoot extends DefaultGun implements SingleShoot {

    public DefaultRifleSingleShoot(GunEnum gunEnum) {
        super(gunEnum);
    }

    @Override
    public SoundConfiguration getCockSound() {
        return new SoundConfiguration(
                Sound.BLOCK_NOTE_SNARE,
                0.5f,
                1f
        );
    }

    @Override
    public void shoot(Shooter shooter, Plugin plugin) {
        if (shooter.isScoping()) {
            generateBullet(shooter, plugin, 0);
        } else {
            generateBullet(shooter, plugin, 5);
        }
    }
}
