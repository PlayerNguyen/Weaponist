package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.manager.ManagerSet;

import java.util.TreeSet;

public class GunManager extends ManagerSet<Gun> {

    public GunManager() {
        super(new TreeSet<>());
    }

    @Override
    public boolean add(Gun e) {
        String globalId = e.getGlobalId();
        Weaponist.getDebugger().warn("Register globalId: " + globalId);
        Weaponist.getWeaponist().getItemManager().put(globalId, e);
        return super.add(e);
    }

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
