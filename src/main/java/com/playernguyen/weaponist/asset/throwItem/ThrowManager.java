package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.manager.ManagerSet;

public class ThrowManager extends ManagerSet<Throw> {

    @Override
    public boolean add(Throw e) {
        String globalId = e.getGlobalId();
        Weaponist.getDebugger().warn("Register globalId: " + globalId);
        Weaponist.getWeaponist().getItemManager().put(globalId, e);
        return super.add(e);
    }

    public Throw getRegisteredWeapon(String id) {
        return getContainer()
                .stream()
                .filter(e -> e.getId().equalsIgnoreCase(id) || e.getGlobalId().equalsIgnoreCase(id))
                .findAny()
                .orElse(null);
    }

}
