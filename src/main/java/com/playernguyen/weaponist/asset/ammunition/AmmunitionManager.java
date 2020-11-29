package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.manager.ManagerSet;

public class AmmunitionManager extends ManagerSet<Ammunition> {

    @Override
    public boolean add(Ammunition e) {
        String globalId = e.getGlobalId();
        Weaponist.getDebugger().warn("Register globalId: " + globalId);
        Weaponist.getWeaponist().getItemManager().put(globalId, e);
        return super.add(e);
    }

    /**
     * Search with O(n) by using enum
     *
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
     *
     * @param id the id of ammo to search
     * @return the ammunition searching
     */
    public Ammunition getRegisteredAmmunition(String id) {
        for (Ammunition ammunition : getContainer()) {
            if (ammunition.getType().getId().toLowerCase().equalsIgnoreCase(id.toLowerCase().trim())) {
                return ammunition;
            }
        }
        return null;
    }

}
