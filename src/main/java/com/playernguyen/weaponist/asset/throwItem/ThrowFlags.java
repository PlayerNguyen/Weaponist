package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.asset.AssetKeyFlag;
import org.bukkit.Material;

public enum  ThrowFlags implements AssetKeyFlag {

    METADATA_DISPLAY_NAME("Metadata.Display", "gun_display"),
    METADATA_MATERIAL("Metadata.Material",Material.STICK),
    METADATA_DESCRIPTION("Metadata.Description", "custom_description|new_line"),

    ATTRIBUTE_EXPLODE_TIME("Attribute.ExplodeTime", 1),
    ATTRIBUTE_GENERIC_POWER("Attribute.GenericDamage", 20),

    ;

    private final String path;
    private final Object define;
    ThrowFlags(String path, Object define) {
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
