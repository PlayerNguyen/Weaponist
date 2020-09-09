package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.Bukkit;

public class ThrowFrag extends DefaultThrow {

    public ThrowFrag() {
        super(ThrowEnum.FRAG);
    }

    @Override
    public void onThrow(Shooter shooter) {
        Bukkit.getScheduler().runTaskAsynchronously(getWeaponist(), () -> {
            // TODO: Add the throw frag,
            // FIXME: 9/9/20 Not energy to do this
        });
    }
}
