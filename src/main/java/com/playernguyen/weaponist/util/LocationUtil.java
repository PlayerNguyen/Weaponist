package com.playernguyen.weaponist.util;

import com.comphoenix.packetwrapper.WrapperPlayServerPosition;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public class LocationUtil {

    public static boolean hasLivingEntity(Location location, double radius, List<Entity> ignore) {
        Collection<Entity> entitiesList
                = location.getWorld().getNearbyEntities(location, radius, radius, radius);

        if (ignore != null) {
            for (Entity ignoreEntity : ignore) {
                entitiesList.remove(ignoreEntity);
            }
        }

        entitiesList.removeIf(e -> !(e instanceof LivingEntity));

        return entitiesList.size() > 0;
    }

    public static void createNoise(Player player, float level) {
        WrapperPlayServerPosition position
                = new WrapperPlayServerPosition();

        Set<WrapperPlayServerPosition.PlayerTeleportFlag> flagsSet = new HashSet<>();
        flagsSet.add(WrapperPlayServerPosition.PlayerTeleportFlag.X);
        flagsSet.add(WrapperPlayServerPosition.PlayerTeleportFlag.Y);
        flagsSet.add(WrapperPlayServerPosition.PlayerTeleportFlag.Z);
        flagsSet.add(WrapperPlayServerPosition.PlayerTeleportFlag.X_ROT);
        flagsSet.add(WrapperPlayServerPosition.PlayerTeleportFlag.Y_ROT);

        position.setFlags(flagsSet);


        position.setX(0);
        position.setY(0);
        position.setZ(0);
        // X axis
        position.setYaw(random(-level/10, level/10));

        // Y axis
        position.setPitch(-(level+random(-0.25f, 0.25f))/3);

        position.sendPacket(player);
    }

    private static float random(float min, float max) {
        return min + (max - min) * new Random().nextFloat();
    }

}
