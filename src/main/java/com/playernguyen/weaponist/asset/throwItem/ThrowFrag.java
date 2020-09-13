package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.WeaponistUtil;
import org.bukkit.Location;

public class ThrowFrag extends DefaultThrow {

    public ThrowFrag() {
        super(ThrowEnum.FRAG);
    }

    @Override
    public void onExplode(Shooter shooter, Location location) {
        WeaponistUtil.fakeExplosion(location, (float) getPower(), shooter);
    }
}
