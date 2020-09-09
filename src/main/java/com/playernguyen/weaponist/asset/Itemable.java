package com.playernguyen.weaponist.asset;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Itemable {

    ItemStack toItem(Player owner, int amount);

    default ItemStack toItem(Player owner) {
        return toItem(owner, 1);
    }

    default ItemStack toItem(int amount) {
        return toItem(null, amount);
    }

}
