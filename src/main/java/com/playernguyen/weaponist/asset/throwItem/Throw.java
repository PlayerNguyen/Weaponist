package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.entity.Shooter;

public interface Throw extends Item {

    void onThrow(Shooter shooter);

    double getExplodeTime();

    double getPower();

}
