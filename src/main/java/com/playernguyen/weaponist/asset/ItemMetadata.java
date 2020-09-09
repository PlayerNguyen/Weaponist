package com.playernguyen.weaponist.asset;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
