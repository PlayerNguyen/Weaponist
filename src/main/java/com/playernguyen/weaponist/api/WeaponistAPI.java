package com.playernguyen.weaponist.api;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.manager.ManagerSet;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class WeaponistAPI extends WeaponistInstance {

    private ManagerSet<? extends Item> getManagerFromId(String id) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(id.split("_")));
        if (list.size() < 2) {
            throw new IllegalArgumentException("Wrong id format: " + id);
        }

        if (list.get(0).toLowerCase().equalsIgnoreCase("gun")) {
            return getGunManager();
        }
        if (list.get(0).toLowerCase().equalsIgnoreCase("ammo")) {
            return getAmmunitionManager();
        }
        return null;
    }

    public ItemStack getItemFromId(String globalId, Player player, int amount) {
        ManagerSet<? extends Item> manager = getManagerFromId(globalId);
        if (manager == null) return null;
        for (Item itemMetadata : manager) {
            if (itemMetadata.getGlobalId().toLowerCase().trim().equalsIgnoreCase(globalId)) {
                return itemMetadata.toItem(player, amount);
            }
        }
        return null;
    }

    public ItemStack getItemFromId(String globalId, int amount) {
        return getItemFromId(globalId, null, amount);
    }

    public ItemStack getItemFromId(String globalId, Player player) {
        return getItemFromId(globalId, player, 1);
    }

    public ItemStack getItemFromId(String globalId) {
        return getItemFromId(globalId, null);
    }
}
