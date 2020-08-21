package com.playernguyen.asset.ammunition;

import com.playernguyen.asset.ItemMetadata;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Ammunition extends ItemMetadata {

    int getMaxPenetrate();

    ItemStack toItem(Player owner);

    AmmunitionEnum getType();


}
