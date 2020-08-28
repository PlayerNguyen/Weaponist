package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.asset.Weapon;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.ray.RayResult;
import com.playernguyen.weaponist.sound.SoundConfiguration;
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
