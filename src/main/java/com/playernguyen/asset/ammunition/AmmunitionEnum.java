package com.playernguyen.asset.ammunition;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

/**
 * Immutable at first registry
 */
public enum AmmunitionEnum {

    PISTOL("pistol", "9mm", Material.COAL, 2, 16, Arrays.asList("9mm", "Bullet"));

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
