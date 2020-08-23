package com.playernguyen.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


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



}
