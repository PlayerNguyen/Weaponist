package com.playernguyen.asset.ammunition;

import com.playernguyen.manager.ManagerSet;

public class AmmunitionManager extends ManagerSet<Ammunition> {

    public Ammunition getRegisteredAmmunition(AmmunitionEnum ammunitionEnum) {
        for (Ammunition e : getContainer()) {
            if (ammunitionEnum.getId().equalsIgnoreCase(e.getType().getId())) {
                return e;
            }
        }
        return null;
    }

}
