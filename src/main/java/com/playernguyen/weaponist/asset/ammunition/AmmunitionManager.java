package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.manager.ManagerSet;

public class AmmunitionManager extends ManagerSet<Ammunition> {

    /**
     * Search with O(n) by using enum
     * @param ammunitionEnum type to search
     * @return the ammunition searching
     */
    public Ammunition getRegisteredAmmunition(AmmunitionEnum ammunitionEnum) {
        for (Ammunition e : getContainer()) {
            if (ammunitionEnum.getId().equalsIgnoreCase(e.getType().getId())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Search with O(n) by using id
     * @param id the id of ammo to search
     * @return the ammunition searching
     */
    public Ammunition getRegisteredAmmunition(String id) {
        for (Ammunition ammunition : getContainer()) {
            if (ammunition.getType().getId().equalsIgnoreCase(id)) {
                return ammunition;
            }
        }
        return null;
    }

}
