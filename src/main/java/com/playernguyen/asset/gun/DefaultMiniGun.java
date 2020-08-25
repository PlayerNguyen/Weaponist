package com.playernguyen.asset.gun;

import com.playernguyen.entity.Shooter;
import com.playernguyen.ray.RayResult;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public abstract class DefaultMiniGun extends DefaultGun {

    public DefaultMiniGun(GunEnum gunEnum) throws IOException {
        super(gunEnum);
    }

    @Override
    public RayResult shoot(Shooter shooter, Plugin plugin) {

        return super.shoot(shooter, plugin);
    }


}
