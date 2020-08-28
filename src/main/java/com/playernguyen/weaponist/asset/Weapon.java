package com.playernguyen.weaponist.asset;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Weapon extends ItemMetadata {

    double getDamage();

    ItemStack toItem(Player owner, int amount);

}
