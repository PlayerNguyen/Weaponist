package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.asset.ItemMetadata;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Ammunition extends ItemMetadata {

    int getMaxPenetrate();

    ItemStack toItem(Player owner);

    ItemStack toItem(Player owner, int amount);

    AmmunitionEnum getType();

    default String getId() {
        return getType().getId();
    }

}
