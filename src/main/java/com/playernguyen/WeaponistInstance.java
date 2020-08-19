package com.playernguyen;

import com.playernguyen.setting.WeaponistSetting;

public abstract class WeaponistInstance {

    /**
     * Instancing
     * @return the instance of Weaponist
     */
    public Weaponist getWeaponist() {
        return Weaponist.getWeaponist();
    }

    /**
     * Setting of weaponist
     * @return the {@link WeaponistSetting} class of weaponist
     */
    public WeaponistSetting getWeaponistSetting() {
        return getWeaponist().getWeaponistSetting();
    }
}
