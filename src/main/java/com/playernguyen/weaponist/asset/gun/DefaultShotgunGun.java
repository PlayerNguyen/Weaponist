package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.MathHelper;
import org.bukkit.plugin.Plugin;

public abstract class DefaultShotgunGun  extends DefaultGun {

    public DefaultShotgunGun(GunEnum gunEnum) {
        super(gunEnum);
    }

    @Override
    public void shoot(Shooter shooter, Plugin plugin) {
        generateBullet(shooter, plugin, 10f, MathHelper.randomInt(4, 7), 1);
    }
}
