package com.playernguyen.weaponist.asset;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.asset.ammunition.AmmunitionEnum;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AssetConfig<T extends AssetEnum, F extends AssetKeyFlag> extends WeaponistInstance {

    private final T assetEnum;

    private File file;
    private FileConfiguration fileConfiguration;
    private final File fileParent;

    public AssetConfig(T assetEnum, F[] defaultLoader, String parent) {
        this.assetEnum = assetEnum;
        this.fileParent = new File(getWeaponist().getDataFolder(), parent);
        if (!fileParent.exists() && !fileParent.mkdir())
            throw new NullPointerException("Not found parent " + parent);
        // Load the file, first load
        load();

        // Generate the loadDefault if not found the key :D
//        for (F f : defaultLoader) {
//            loadDefault(f);
//        }
        // Save
//        save();
    }

    public void loadDefault(F keyFlag) {
        if (!hasFlag(keyFlag)) {
            getDebugger().info(
                    String.format("Load default %s => %s",
                    keyFlag.getPath(),
                    keyFlag.getDefine())
            );
            set(keyFlag, keyFlag.getDefine());
        }
    }

    public void addDefault(F keyFlag, Object value) {
        if (!hasFlag(keyFlag)) {
            set(keyFlag, value);
        }
    }

    public void set(F keyFlag, Object value) {
        if (value instanceof Material) {
            getFileConfiguration().set(keyFlag.getPath(), value.toString());
            return;
        }
        if (value instanceof AssetEnum) {
            getFileConfiguration().set(keyFlag.getPath(), ((AssetEnum) value).getId());
        }
        getFileConfiguration().set(keyFlag.getPath(), value);
    }

    private boolean hasFlag(F flag) {
        return getFileConfiguration().contains(flag.getPath());
    }

    private void load() {
        this.file = new File(fileParent, assetEnum.getId().concat(".yml"));
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }


    protected File getFile() {
        return file;
    }

    protected FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void save() {
        try {
            this.fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(F flag) {
        Object value = fileConfiguration.get(flag.getPath());
        //Weaponist.getDebugger().warn("Getting " + flag.getPath() + " with value " + value.toString());
//        System.out.println(flag.getPath() + " => " + value);
        return value;
    }

    public String getString(F flag) {
        return (String) get(flag);
    }

    public String getFormattedColorString(F flag) {
        return ChatColor.translateAlternateColorCodes('&', getString(flag));
    }

    public List<String> getFormattedStringList(F flag) {
        return getStringList(flag)
                .stream()
                .map(e -> ChatColor.translateAlternateColorCodes('&', e))
                .collect(Collectors.toList());
    }

    public int getInt(F flag) {
        return (int) get(flag);
    }

    public double getDouble(F flag) {
        return (double) get(flag);
    }

    public float getFloat(F flag) {
        return (float) getDouble(flag);
    }

    public List<String> getStringList(F flag) {
        return fileConfiguration.getStringList(flag.getPath());
    }

    @Nullable
    public Material getMaterial(F flag) {
        return Material.matchMaterial(getString(flag));
    }

    public AmmunitionEnum getAmmoType(F flag) {
        return AmmunitionEnum.fromId(getString(flag));
    }
}
