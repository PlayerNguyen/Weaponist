package com.playernguyen.weaponist.asset;

import org.bukkit.Material;

import java.util.List;

public interface ItemMetadata {

    String getId();

    String getGlobalId();

    String getDisplayName();

    List<String> getDescription();

    Material getMaterial();

    @Deprecated
    int getMaxStackSize();

}
