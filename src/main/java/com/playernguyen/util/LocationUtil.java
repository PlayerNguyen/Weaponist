package com.playernguyen.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;
import java.util.List;

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

}
