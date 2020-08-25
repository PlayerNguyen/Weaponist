package com.playernguyen;

import com.playernguyen.asset.ammunition.*;
import com.playernguyen.asset.gun.GunBeretta;
import com.playernguyen.asset.gun.GunConfigurationFolder;
import com.playernguyen.asset.gun.GunManager;
import com.playernguyen.asset.gun.GunUzi;
import com.playernguyen.command.Command;
import com.playernguyen.command.CommandManager;
import com.playernguyen.command.ammunition.CommandAmmunition;
import com.playernguyen.command.weapon.CommandWeapon;
import com.playernguyen.config.ConfigurationFolder;
import com.playernguyen.debugger.Debugger;
import com.playernguyen.entity.DefaultShooter;
import com.playernguyen.entity.Shooter;
import com.playernguyen.entity.ShooterManager;
import com.playernguyen.language.LanguageConfiguration;
import com.playernguyen.listener.*;
import com.playernguyen.setting.SettingFlag;
import com.playernguyen.setting.WeaponistSetting;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Weaponist extends JavaPlugin {

    private static Weaponist weaponist;
    private static Debugger debugger;

    private WeaponistSetting weaponistSetting;
    private LanguageConfiguration languageConfiguration;
    private ListenerManager listenerManager;
    private ConfigurationFolder ammunitionFolder;
    private AmmunitionManager ammunitionManager;
    private CommandManager commandManager;
    private GunConfigurationFolder weaponFolder;
    private GunManager gunManager;
    private ShooterManager shooterManager;

    @Override
    public void onEnable() {
        // Instance setup
        weaponist = this;
        // Setting setup
        setupSetting();
        // Language setup
        setupLanguage();
        // Listener setup
        setupListener();
        // Command setup
        setupCommand();
        // Shooter set up
        setupShooter();

    }

    private void setupShooter() {
        this.shooterManager = new ShooterManager();
        Bukkit.getOnlinePlayers().forEach(player -> {
            Shooter shooter = new DefaultShooter(player);
            getShooterManager().add(shooter);
        });
    }

    private void setupCommand() {
        this.commandManager = new CommandManager();
        // Append data
        getCommandManager().add(new CommandAmmunition());
        getCommandManager().add(new CommandWeapon());
        // Register
        for (Command command : commandManager.getContainer()) {
            PluginCommand pluginCommand = Bukkit.getPluginCommand(command.getCommand());

            // Check exception if not register on plugin.yml
            if (pluginCommand == null) {
                throw new NullPointerException("The command wasn't register on /plugin.yml");
            }

            // Set executor
            pluginCommand.setExecutor(command);
        }
    }

    private void setupLanguage() {
        try {
            this.languageConfiguration = new LanguageConfiguration();
        } catch (IOException e) {
            getDebugger().err("Cannot save language file...");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void setupSetting() {
        this.getLogger().info("Initial settings file...");
        try {
            this.weaponistSetting = new WeaponistSetting();
        } catch (IOException e) {
            getDebugger().err("Cannot save weaponist setting file...");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
        // Debugger setting
        debugger = new Debugger(getWeaponistSetting());
        // Ammunition folder
        this.ammunitionFolder = new AmmunitionConfigurationFolder(getWeaponistSetting()
                .getString(SettingFlag.AMMUNITION_FOLDER));
        // Ammunition setting
        this.ammunitionManager = new AmmunitionManager();
        try {
            getAmmunitionManager().add(new AmmunitionPistol());
            getAmmunitionManager().add(new AmmunitionCommunistRifle());
            getAmmunitionManager().add(new AmmunitionRocket());
        } catch (IOException e) {
            debugger.err("Cannot save ammunition...");
            e.printStackTrace();
        }
        // Weapon setting
        this.weaponFolder = new GunConfigurationFolder(getWeaponistSetting().getString(SettingFlag.WEAPON_FOLDER));
        this.gunManager = new GunManager();

        try {
            getGunManager().add(new GunBeretta());
            getGunManager().add(new GunUzi());
        } catch (IOException e) {
            debugger.err("Cannot save weapon...");
            e.printStackTrace();
        }

    }

    private void setupListener() {
        this.getLogger().info("Initial listeners...");
        // Initial the manager
        this.listenerManager = new ListenerManager();
        // Register the listener
        listenerManager.add(new PlayerInteractListener());
        listenerManager.add(new PlayerSwapHandListener());
        listenerManager.add(new PlayerJoinListener());
        listenerManager.add(new PlayerItemHeldListener());

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

    public LanguageConfiguration getLanguageConfiguration() {
        return languageConfiguration;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public GunConfigurationFolder getWeaponFolder() {
        return weaponFolder;
    }

    public GunManager getGunManager() {
        return gunManager;
    }

    public ShooterManager getShooterManager() {
        return shooterManager;
    }
}
