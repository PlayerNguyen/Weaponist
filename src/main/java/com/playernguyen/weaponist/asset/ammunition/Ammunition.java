package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.asset.Item;

public interface Ammunition extends Item {

    int getMaxPenetrate();

    AmmunitionEnum getType();

    default String getId() {
        return getType().getId();
    }



}
