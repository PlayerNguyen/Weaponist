package com.playernguyen.asset;

import org.bukkit.Material;

import java.util.List;

public interface ItemMetadata {

    String getDisplayName();

    List<String> getDescription();

    Material getMaterial();

    int getMaxStackSize();

}
