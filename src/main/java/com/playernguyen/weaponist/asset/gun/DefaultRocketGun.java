package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.asset.ItemTagEnum;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.ray.RayResult;
import com.playernguyen.weaponist.runnable.RocketProjectileRunnable;
import com.playernguyen.weaponist.sound.SoundConfiguration;
import com.playernguyen.weaponist.util.Tag;
import com.playernguyen.weaponist.util.WeaponistUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public abstract class DefaultRocketGun extends DefaultGun {

    public DefaultRocketGun(GunEnum gunEnum) {
        super(gunEnum);
    }

    @Override
    public void shoot(Shooter shooter, Plugin plugin) {
        Player player = shooter.asPlayer();

        BukkitRunnable r = new RocketProjectileRunnable(
                Weaponist.getWeaponist(),
                shooter,
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                2,
                getMaxDistance(),
                (float) getDamage()
        );
        r.runTaskTimerAsynchronously(Weaponist.getWeaponist(), 0, 0);

        // Take ammo
        ItemStack handStack = player.getInventory().getItemInMainHand();
        ItemStack updateStack = WeaponistUtil
                .updateItemMeta(
                        Tag.setData(handStack, ItemTagEnum.GUN_AMMO, Tag.getGunAmmo(handStack)-1),
                        this
                );
        player.getInventory()
                .setItemInMainHand(updateStack);

        // Play sound
        for (SoundConfiguration soundConfiguration : getShootSoundList()) {
            soundConfiguration.play(shooter.asPlayer().getLocation());
        }
        // Knock back
        WeaponistUtil.knockBack(player, 5);
    }
}
