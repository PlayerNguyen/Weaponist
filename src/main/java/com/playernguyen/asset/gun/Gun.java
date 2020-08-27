package com.playernguyen.asset.gun;

import com.playernguyen.Weaponist;
import com.playernguyen.asset.Weapon;
import com.playernguyen.entity.Shooter;
import com.playernguyen.ray.RayResult;
import com.playernguyen.sound.SoundConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public interface Gun extends Weapon {

    String getAmmunitionType();

    RayResult shoot(Shooter shooter, Plugin plugin);

    void reload(Shooter shooter, Weaponist plugin);

    double getReloadTime();

    List<SoundConfiguration> getReloadSoundList();

    List<SoundConfiguration> getShootSoundList();

    double getDelayPerShootTime();

    double getFireAccuracy();

    int getMaxDistance();

    int getMaxPenetrate();

}
