package com.playernguyen.config;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.flag.Flagable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public abstract class ConfigurationWeaponist extends WeaponistInstance {

    private final File file;
    private final FileConfiguration fileConfiguration;

    /**
     * Instance of the configuration
     * @param fileName The file name
     * @param flagables Default flagables to save
     */
    public ConfigurationWeaponist(@NotNull String fileName, @NotNull Flagable[] flagables) throws IOException {
        this.file = new File(getWeaponist().getDataFolder(), fileName);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        for (Flagable flagable : flagables) {
            if (!fileConfiguration.contains(flagable.getPath())) {
                fileConfiguration.set(flagable.getPath(), flagable.getDefinite());
            }
        }

        save();
    }

    public void save() throws IOException {
        this.fileConfiguration.save(this.file);
    }

    public String getString(Flagable flagable) {
        return this.fileConfiguration.getString(flagable.getPath());
    }

    public int getInt(Flagable flagable) {
        return this.fileConfiguration.getInt(flagable.getPath());
    }

    public boolean getBoolean(Flagable flagable) {
        return this.fileConfiguration.getBoolean(flagable.getPath());
    }

}
