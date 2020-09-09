package com.playernguyen.weaponist;

import com.playernguyen.weaponist.asset.ammunition.AmmunitionManager;
import com.playernguyen.weaponist.asset.gun.GunConfigurationFolder;
import com.playernguyen.weaponist.asset.gun.GunManager;
import com.playernguyen.weaponist.asset.throwItem.ThrowManager;
import com.playernguyen.weaponist.debugger.Debugger;
import com.playernguyen.weaponist.entity.ShooterManager;
import com.playernguyen.weaponist.item.ItemManager;
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
    protected Weaponist getWeaponist() {
        return Weaponist.getWeaponist();
    }

    /**
     * Setting of weaponist
     *
     * @return the {@link WeaponistSetting} class of weaponist
     */
    protected WeaponistSetting getWeaponistSetting() {
        return getWeaponist().getWeaponistSetting();
    }

    protected Debugger getDebugger() {
        return Weaponist.getDebugger();
    }

    protected AmmunitionManager getAmmunitionManager() {
        return Weaponist.getWeaponist().getAmmunitionManager();
    }

    protected LanguageConfiguration getLanguageConfiguration() {
        return this.getWeaponist().getLanguageConfiguration();
    }

    protected GunConfigurationFolder getWeaponFolder() {
        return getWeaponist().getWeaponFolder();
    }

    protected GunManager getGunManager() {
        return getWeaponist().getGunManager();
    }

    protected ShooterManager getShooterManager() {
        return getWeaponist().getShooterManager();
    }

    protected TaskManager<ActionPerformRunnable> getTaskManager() {
        return getWeaponist().getTaskManager();
    }

    protected void reloadWeaponist() {
        getWeaponist().reloadPlugin();
    }

    protected ItemManager getItemManager() {
        return this.getWeaponist().getItemManager();
    }

    protected ThrowManager getThrowManager() {
        return this.getWeaponist().getThrowManager();
    }

}
