package com.playernguyen.asset.gun;

import com.playernguyen.entity.Shooter;
import com.playernguyen.sound.SoundConfiguration;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public abstract class DefaultRifleSingleShoot extends DefaultGun implements SingleShoot {

    public DefaultRifleSingleShoot(GunEnum gunEnum) throws IOException {
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
    public void scope(Shooter shooter, Plugin plugin) {
        if (shooter.isScoping()) {
            // Un scope
            shooter.asPlayer().setWalkSpeed(-0.15f);
            shooter.setScoping(false);
            return;
        }

        shooter.asPlayer().setWalkSpeed(0.2f);
        shooter.setScoping(true);
    }
}
