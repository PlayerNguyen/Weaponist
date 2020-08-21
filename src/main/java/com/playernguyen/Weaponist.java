package com.playernguyen;

import com.playernguyen.asset.ammunition.AmmunitionConfigurationFolder;
import com.playernguyen.asset.ammunition.AmmunitionManager;
import com.playernguyen.asset.ammunition.AmmunitionPistol;
import com.playernguyen.config.ConfigurationFolder;
import com.playernguyen.debugger.Debugger;
import com.playernguyen.listener.ListenerManager;
import com.playernguyen.listener.PlayerInteractListener;
import com.playernguyen.setting.SettingFlag;
import com.playernguyen.setting.WeaponistSetting;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Weaponist extends JavaPlugin {

    private static Weaponist weaponist;
    private static Debugger debugger;

    private WeaponistSetting weaponistSetting;
    private ListenerManager listenerManager;
    private ConfigurationFolder ammunitionFolder;
    private AmmunitionManager ammunitionManager;

    @Override
    public void onEnable() {
        // Instance setup
        weaponist = this;
        // Setting setup
        setupSetting();
        // Listener setup
        setupListener();
    }

    private void setupSetting() {
        this.getLogger().info("Initial settings file...");
        try {
            this.weaponistSetting = new WeaponistSetting();
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
        // Debugger setting
        debugger = new Debugger(getWeaponistSetting());
        // Load ammunition
        this.ammunitionFolder = new AmmunitionConfigurationFolder(getWeaponistSetting()
                .getString(SettingFlag.AMMUNITION_FOLDER));

        this.ammunitionManager = new AmmunitionManager();
        try {
            this.ammunitionManager.add(new AmmunitionPistol());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setupListener() {
        this.getLogger().info("Initial listeners...");
        // Initial the manager
        this.listenerManager = new ListenerManager();
        // Register the listener
        listenerManager.add(new PlayerInteractListener());

        // Register the manager
        getListenerManager().register(this);
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    /**
     * Get instance of weaponist
     * @return The current weaponist instance
     */
    public static Weaponist getWeaponist() {
        return weaponist;
    }

    /**
     * Setting of weaponist
     * @return Setting of weaponist
     */
    public WeaponistSetting getWeaponistSetting() {
        return weaponistSetting;
    }

    public static Debugger getDebugger() {
        return debugger;
    }

    public ConfigurationFolder getAmmunitionFolder() {
        return ammunitionFolder;
    }

    public AmmunitionManager getAmmunitionManager() {
        return ammunitionManager;
    }
}
