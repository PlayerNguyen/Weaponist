package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.asset.ItemMetadata;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Ammunition extends Item {

    int getMaxPenetrate();

    AmmunitionEnum getType();

    default String getId() {
        return getType().getId();
    }



}
