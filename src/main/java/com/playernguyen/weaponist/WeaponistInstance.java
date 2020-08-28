package com.playernguyen.weaponist;

import com.playernguyen.weaponist.asset.ammunition.AmmunitionManager;
import com.playernguyen.weaponist.asset.gun.GunConfigurationFolder;
import com.playernguyen.weaponist.asset.gun.GunManager;
import com.playernguyen.weaponist.debugger.Debugger;
import com.playernguyen.weaponist.entity.ShooterManager;
import com.playernguyen.weaponist.language.LanguageConfiguration;
import com.playernguyen.weaponist.runnable.ActionPerformRunnable;
import com.playernguyen.weaponist.runnable.TaskManager;
import com.playernguyen.weaponist.setting.WeaponistSetting;

public abstract class WeaponistInstance {

    /**
     * Instancing
     *
     * @return the instance of Weaponist
     */
    public Weaponist getWeaponist() {
        return Weaponist.getWeaponist();
    }

    /**
     * Setting of weaponist
     *
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

    public GunConfigurationFolder getWeaponFolder() {
        return getWeaponist().getWeaponFolder();
    }

    public GunManager getGunManager() {
        return getWeaponist().getGunManager();
    }

    public ShooterManager getShooterManager() {
        return getWeaponist().getShooterManager();
    }

    public TaskManager<ActionPerformRunnable> getTaskManager() {
        return getWeaponist().getTaskManager();
    }

}
