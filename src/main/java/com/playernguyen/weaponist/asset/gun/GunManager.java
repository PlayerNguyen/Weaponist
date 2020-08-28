package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.manager.ManagerSet;

public class GunManager extends ManagerSet<Gun> {

    /**
     * Easy searching algo
     * @param id the id to search
     * @return the weapon which has searched or null if found nothing
     */
    public Gun getRegisteredWeapon(String id) {
        return getContainer()
                .stream()
                .filter(e -> e.getId().equalsIgnoreCase(id))
                .findAny()
                .orElse(null);
    }

    public boolean hasWeapon(String id) {
        return getRegisteredWeapon(id) != null;
    }

}
