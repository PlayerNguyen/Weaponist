package com.playernguyen;

import com.playernguyen.asset.ammunition.AmmunitionManager;
import com.playernguyen.debugger.Debugger;
import com.playernguyen.language.LanguageConfiguration;
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

    public Debugger getDebugger() {
        return Weaponist.getDebugger();
    }

    public AmmunitionManager getAmmunitionManager() {
        return Weaponist.getWeaponist().getAmmunitionManager();
    }

    public LanguageConfiguration getLanguageConfiguration() {
        return this.getWeaponist().getLanguageConfiguration();
    }
}
