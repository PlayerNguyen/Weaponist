package com.playernguyen.util;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class EffectGenerateUtil {

    public static void knockBack(Player player, double rate) {
        Vector vector = player.getEyeLocation().getDirection();
        vector.setY(0);

        player.setVelocity(vector.multiply(rate/10).multiply(-1));
    }

}
