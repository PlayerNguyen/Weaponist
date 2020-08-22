package com.playernguyen.asset.ammunition;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

/**
 * Immutable at first registry
 */
public enum AmmunitionEnum {

    PISTOL("pistol", "&c9mm &8Bullet", Material.COAL, 2, 16, Arrays.asList("&c9mm &7Bullet is one of", "&7the most ammunition in server")),
    COMMUNIST_RIFLE("communist_rifle", "&c7.62mm", Material.BLAZE_ROD, 2, 16, Arrays.asList("&c7.62mm &7for rifle with ", "&7a huge damage")),
    ROCKET("rocket", "&cRocket", Material.STICK, 1, 16, Arrays.asList("&cRocket &7is an bullet type with", "&7super explode when it triggered")),
    ;

    private final String id;
    private final String defaultDisplay;
    private final List<String> defaultDescription;
    private final int defaultMaxPenetrate;
    private final int defaultMaxStackSize;
    private final Material defaultMaterial;

    AmmunitionEnum(String id,
                   String defaultDisplay,
                   Material defaultMaterial,
                   int defaultMaxPenetrate,
                   int defaultMaxStackSize,
                   List<String> defaultDescription) {
        this.id = id;
        this.defaultDisplay = defaultDisplay;
        this.defaultDescription = defaultDescription;
        this.defaultMaxPenetrate = defaultMaxPenetrate;
        this.defaultMaxStackSize = defaultMaxStackSize;
        this.defaultMaterial = defaultMaterial;

    }

    public String getId() {
        return id;
    }

    public String getDefaultDisplay() {
        return defaultDisplay;
    }

    public List<String> getDefaultDescription() {
        return defaultDescription;
    }

    public String getFileName() {
        return getId().concat(".yml");
    }

    public Material getDefaultMaterial() {
        return defaultMaterial;
    }

    public int getDefaultMaxPenetrate() {
        return defaultMaxPenetrate;
    }

    public int getDefaultMaxStackSize() {
        return defaultMaxStackSize;
    }
}
