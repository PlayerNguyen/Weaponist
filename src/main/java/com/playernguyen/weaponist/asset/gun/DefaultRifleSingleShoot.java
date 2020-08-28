package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.sound.SoundConfiguration;
import org.bukkit.Sound;

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

}
