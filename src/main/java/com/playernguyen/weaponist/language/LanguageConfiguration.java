package com.playernguyen.weaponist.language;

import com.playernguyen.weaponist.config.ConfigurationWeaponist;
import org.bukkit.ChatColor;

import java.io.IOException;

public class LanguageConfiguration extends ConfigurationWeaponist {
    /**
     * Instance of the configuration
     */
    public LanguageConfiguration() throws IOException {
        super("language.yml", LanguageFlag.values());
    }

    public String getLanguage(LanguageFlag languageFlag) {
        return ChatColor.translateAlternateColorCodes('&', getString(languageFlag));
    }

    public String getLanguageWithPrefix(LanguageFlag flag) {
        return getLanguage(LanguageFlag.PREFIX) + " " + getLanguage(flag);
    }

}
