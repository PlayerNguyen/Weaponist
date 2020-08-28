package com.playernguyen.weaponist.location;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocationIterator {

    private final Location start;
    private final Vector direction;
    private final int maxDistance;

    private final Location current;

    public LocationIterator(Location start, Vector direction, int maxDistance) {
        this.start = start;
        this.direction = direction.multiply(0.25);
        this.maxDistance = maxDistance;

        // Begin with current
        this.current = new Location(
                start.getWorld(),
                start.getX(),
                start.getY(),
                start.getZ(),
                start.getYaw(),
                start.getPitch()
        );
    }

    public boolean hasNext() {
        Location precise = current.add(direction);
        // Out of distance
        return !(precise.distance(start) >= maxDistance);
    }

    public boolean outOfLimit() {
        return (current.distance(start) >= maxDistance);
    }

    public double distanceCompare(Location next) {
        return next.distance(start);
    }

    public Location next () {
        return current.add(direction);
    }

}
