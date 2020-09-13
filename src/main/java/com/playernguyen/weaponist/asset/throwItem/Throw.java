package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.Location;

public interface Throw extends Item {

    void onThrow(Shooter shooter);

    void onExplode(Shooter shooter, Location location);

    double getExplodeTime();

    double getPower();

    double getExplodingTime();

}
