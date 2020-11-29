package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.asset.AssetKeyFlag;
import com.playernguyen.weaponist.asset.ammunition.AmmunitionEnum;
import org.bukkit.Material;

public enum  GunFlags implements AssetKeyFlag {

    METADATA_DISPLAY_NAME("Metadata.Display", "gun_display"),
    METADATA_MATERIAL("Metadata.Material", Material.STICK),
    METADATA_DESCRIPTION("Metadata.Description", "custom_description|new_line"),
    METADATA_AMMO_TYPE("Metadata.AmmoType", AmmunitionEnum.PISTOL.getId()),
    METADATA_TIME_RELOAD("Metadata.Time.Reload", 1.0D),
    METADATA_TIME_DELAY("Metadata.Time.Delay", 1.0D),

    SOUND_NON_COMPRESSOR_SOUND_PROJECTILE("Sound.NonCompressor.Projectile", ""),
    SOUND_RELOAD("Sound.Reload", ""),

    ATTRIBUTE_GENERIC_DAMAGE("Attribute.GenericDamage", 0.0D),
    ATTRIBUTE_GENERIC_DISTANCE("Attribute.Distance", 100),
    ATTRIBUTE_GENERIC_PENETRATE("Attribute.Penetrate", 1),
    ATTRIBUTE_MAX_MAGAZINE("Attribute.MaxMagazine", 1),
    ATTRIBUTE_GENERIC_FIRE_RATE("Attribute.FireRate", 0.0D)


    ;

    private final String path;
    private final Object define;


    GunFlags(String path, Object define) {
        this.path = path;
        this.define = define;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }
}
