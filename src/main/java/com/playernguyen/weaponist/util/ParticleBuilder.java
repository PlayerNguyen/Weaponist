package com.playernguyen.weaponist.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class ParticleBuilder {

    private final Particle particle;
    private int count;
    private double offsetX = 0.0D;
    private double offsetY = 0.0D;
    private double offsetZ = 0.0D;
    private double extra = 1;

    public ParticleBuilder(Particle particle, int count) {
        if (particle == null) {
            throw new NullPointerException("The particle mustn't be null");
        }
        this.particle = particle;
        this.count = count;
    }

    public ParticleBuilder count(int i) {
        this.count = i;
        return this;
    }

    public ParticleBuilder offsetX(double x) {
        this.offsetX = x;
        return this;
    }

    public ParticleBuilder offsetY(double y) {
        this.offsetY = y;
        return this;
    }

    public ParticleBuilder offsetZ(double z) {
        this.offsetZ = z;
        return this;
    }

    public ParticleBuilder offset(Location location) {
        offsetX(location.getX());
        offsetY(location.getY());
        offsetZ(location.getZ());
        return this;
    }

    public ParticleBuilder offset(Vector vector) {
        offsetX(vector.getX());
        offsetY(vector.getY());
        offsetZ(vector.getZ());
        return this;
    }

    public ParticleBuilder extra(double extra) {
        this.extra = extra;
        return this;
    }

    public <T> void play(Location location, T data) {
        location.getWorld().spawnParticle(
                particle,
                location.getX(),
                location.getY(),
                location.getZ(),
                count,
                offsetX,
                offsetY,
                offsetZ,
                extra,
                data
        );
    }

    public void play(Location location) {
        this.play(location, null);
    }


}
