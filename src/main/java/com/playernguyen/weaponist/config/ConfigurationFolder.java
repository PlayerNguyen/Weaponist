package com.playernguyen.weaponist.config;

import com.playernguyen.weaponist.WeaponistInstance;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class ConfigurationFolder extends WeaponistInstance {

    private final File file;

    public ConfigurationFolder(String name) {
        this.file = new File(getWeaponist().getDataFolder(), name);
        // Make a dir if not exist
        getDebugger().info("Make a new directory because of the existence...");
        if (!file.exists()) {
            if (!file.mkdir()) {
                getDebugger().err("Error to mkdir()...");
            } else {
                getDebugger().fine("Created the new folder...");
            }
        } else {
            // Check this folder
            if (!file.isDirectory()) {
                getDebugger().err("Validation: Not an directory!");
                throw new IllegalStateException(String.format("The url: %s wasn't a directory", file.getName()));
            }
        }

        load();
    }

    public File getFolder() {
        return file;
    }

    public Set<File> listFiles() {
        return new HashSet<>(Arrays.asList(file.listFiles()));
    }

    public Set<String> listFilesName() {
        Set<String> _temp = new HashSet<>();
        for (File listFile : file.listFiles()) {
            _temp.add(listFile.getName());
        }
        return _temp;
    }

    protected abstract void load();

}
