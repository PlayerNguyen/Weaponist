package com.playernguyen;

import com.playernguyen.asset.ammunition.*;
import com.playernguyen.command.Command;
import com.playernguyen.command.ammunition.CommandAmmunition;
import com.playernguyen.command.CommandManager;
import com.playernguyen.config.ConfigurationFolder;
import com.playernguyen.debugger.Debugger;
import com.playernguyen.language.LanguageConfiguration;
import com.playernguyen.listener.ListenerManager;
import com.playernguyen.listener.PlayerInteractListener;
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

    }

    private void setupCommand() {
        this.commandManager = new CommandManager();
        // Append data
        getCommandManager().add(new CommandAmmunition());

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
        // Load ammunition
        this.ammunitionFolder = new AmmunitionConfigurationFolder(getWeaponistSetting()
                .getString(SettingFlag.AMMUNITION_FOLDER));

        this.ammunitionManager = new AmmunitionManager();
        try {
            getAmmunitionManager().add(new AmmunitionPistol());
            getAmmunitionManager().add(new AmmunitionCommunistRifle());
            getAmmunitionManager().add(new AmmunitionRocket());
        } catch (IOException e) {
            debugger.err("Cannot save ammunition...");
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

    public LanguageConfiguration getLanguageConfiguration() {
        return languageConfiguration;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
