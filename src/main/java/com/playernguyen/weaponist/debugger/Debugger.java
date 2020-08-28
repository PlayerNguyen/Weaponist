package com.playernguyen.weaponist.debugger;

import com.playernguyen.weaponist.setting.SettingFlag;
import com.playernguyen.weaponist.setting.WeaponistSetting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class Debugger {

    private final boolean active;

    public Debugger (WeaponistSetting weaponistSetting) {
        // Debug mode is on
        this.active = weaponistSetting.getBoolean(SettingFlag.DEBUG_MODE);

        if (active) {
            Bukkit.getLogger().info("Weaponist debug mode has turned on!");
        }
    }

    public boolean isActive() {
        return active;
    }

    private void log(ChatColor c, String string, Object ...args) {
        if (isActive()) {
            Bukkit.getConsoleSender().sendMessage(c + "[Weaponist::Debugger] ".concat(String.format(string, args)));
        }
    }

    public void err(String string, Object ...args) {
        this.log(ChatColor.RED, string, args);
    }

    public void warn(String string, Object ...args) {
        this.log(ChatColor.YELLOW, string, args);
    }

    public void fine(String string, Object ...args) {
        this.log(ChatColor.GREEN, string, args);
    }

    public void info(String string, Object ...args) {
        this.log(ChatColor.DARK_GRAY, string, args);
    }

    public void arr(List<?> arr) {
        this.info("Array (%s) {", arr.size());
            for (Object o : arr) {
                this.info("  " + o.toString());
            }
        this.info("}");
    }
}
