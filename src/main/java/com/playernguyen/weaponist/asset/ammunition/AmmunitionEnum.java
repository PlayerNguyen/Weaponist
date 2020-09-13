package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.asset.AssetEnum;
import com.playernguyen.weaponist.asset.ItemType;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * Immutable at first registry
 */
public enum AmmunitionEnum implements AssetEnum {

    PISTOL("pistol", "&cPistol Clip", Material.GOLD_NUGGET, 2, 16, Collections.singletonList("")),
    SMG("smg", "&cSMG Clip", Material.PAPER, 2, 16, Collections.singletonList("")),
    SNIPER("sniper", "&cSniper Clip", Material.SLIME_BALL, 2, 16, Collections.singletonList("")),
    RIFLE("rifle", "&cAssault Rifle Clip", Material.FLINT, 2, 16, Collections.singletonList("")),
    ROCKET("rocket", "&6Rocket", Material.FEATHER, 1, 16, Collections.singletonList("")),
    LMG("lmg", "&6LMG Clip", Material.DIAMOND, 1, 16, Collections.singletonList("")),
    SHOTGUN("shotgun", "&6Shotgun Rounds", Material.IRON_NUGGET, 1, 16, Collections.singletonList(""))
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

    public String getDisplay() {
        return defaultDisplay;
    }

    public List<String> getDescription() {
        return defaultDescription;
    }

    public String getFileName() {
        return getId().concat(".yml");
    }

    public Material getMaterial() {
        return defaultMaterial;
    }

    public int getDefaultMaxPenetrate() {
        return defaultMaxPenetrate;
    }

    public int getDefaultMaxStackSize() {
        return defaultMaxStackSize;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.AMMO;
    }

    @Nullable
    public static AmmunitionEnum fromId(String id) {
        for (AmmunitionEnum value : AmmunitionEnum.values()) {
            if (value.getId().equalsIgnoreCase(id))
                return value;
        }
        throw new NullPointerException("Not found the AmmunitionEnum id " + id);
    }
}
