package com.playernguyen.weaponist.asset;

import org.bukkit.Material;

import java.util.List;

public interface AssetEnum {

    String getId();

    String getDisplay();

    List<String> getDescription();

    Material getMaterial();

    ItemType getItemType();

    default String getGlobalId() {
        return getItemType().getPrefix().concat("_").concat(getId());
    }

}
