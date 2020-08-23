package com.playernguyen.asset.weapon;

import com.playernguyen.asset.Weapon;
import com.playernguyen.ray.RayResult;
import com.playernguyen.sound.SoundConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public interface Gun extends Weapon {

    String getAmmunitionType();

    RayResult shoot(Player player);

    void reload(Player player);

    double getReloadTime();

    List<SoundConfiguration> getReloadSoundList();

    List<SoundConfiguration> getShootSoundList();


}
