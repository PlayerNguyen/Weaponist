package com.playernguyen.weaponist.util;

import com.playernguyen.weaponist.asset.ItemTagEnum;
import com.playernguyen.weaponist.asset.gun.Gun;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;


public class WeaponistUtil {

    public static void knockBack(Player player, double rate) {
        Vector vector = player.getEyeLocation().getDirection();
        vector.setY(0);

        player.setVelocity(vector.multiply(rate/10).multiply(-1));
    }

    public static void decreaseItemStack(ItemStack itemStack) {
        int currentAmount = itemStack.getAmount();
        itemStack.setAmount(currentAmount-1);
    }

    public static void increaseItemStack(ItemStack itemStack) {
        int currentAmount = itemStack.getAmount();
        itemStack.setAmount(currentAmount+1);
    }

    public static ItemStack updateItemMeta(ItemStack stack, Gun gun) {
        // Refresh meta data
        ItemMeta itemMeta = stack.getItemMeta();
        if (itemMeta != null) {
            String currentDisplayName = gun.getDisplayName()
                    .replace("%ammo%", String.valueOf(Tag.getGunAmmo(stack)))
                    .replace("%max_ammo%", String.valueOf(gun.getMaxStackSize()));
            itemMeta.setDisplayName(currentDisplayName);
        }

        stack.setItemMeta(itemMeta);
        return stack;
    }



}
