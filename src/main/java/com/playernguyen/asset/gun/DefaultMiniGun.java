package com.playernguyen.asset.gun;

import com.playernguyen.entity.Shooter;
import com.playernguyen.entity.Target;
import com.playernguyen.ray.RayResult;
import com.playernguyen.ray.RayTrace;
import com.playernguyen.sound.SoundConfiguration;
import com.playernguyen.util.WeaponistUtil;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public abstract class DefaultMiniGun extends DefaultGun {

    public DefaultMiniGun(GunEnum gunEnum) throws IOException {
        super(gunEnum);
    }

    @Override
    public RayResult shoot(Shooter shooter, Plugin plugin) {

        // Shooting checker
        if (!shooter.isCanTrigger()) { return null; }

        Player player = shooter.asPlayer();

        RayTrace rayTrace = new RayTrace(shooter, 100, getFireAccuracy());
        RayResult rayResult = rayTrace.ray(Particle.VILLAGER_HAPPY, 2);

        // Damage
        for (Target target : rayResult.getTargets()) {
            if (target.isHeadshot()) {
                target.asEntity().damage(getDamage()*2, shooter.asPlayer());
            } else {
                target.asEntity().damage(getDamage(), shooter.asPlayer());
            }
        }

        // Play break particle
        if (rayResult.getHitBlock() != null) {
            // Get block
            Block block = rayResult.getHitBlock();
            block.getLocation().getWorld()
                    .spawnParticle(
                            Particle.BLOCK_CRACK,
                            block.getLocation(),
                            650,
                            0.2f,
                            0.2f,
                            0.2f,
                            new MaterialData(block.getType())
                    );
        }

        // Play sound
        for (SoundConfiguration soundConfiguration : getShootSoundList()) {
            soundConfiguration.play(player.getLocation());
        }

        // Lock the trigger
        if (getDelayPerShootTime() > 0) {
            shooter.setCanTrigger(false);
            BukkitRunnable runnable = new BukkitRunnable() {
                double d = getDelayPerShootTime();

                @Override
                public void run() {
                    d = d - (0.05);
                    if (d <= 0) {
                        shooter.setCanTrigger(true);
                        cancel();
                    }
                }
            };
            runnable.runTaskTimerAsynchronously(plugin, 0, 0);
        }
        // Play effect
        //WeaponistUtil.knockBack(player, 0.5f);
        WeaponistUtil.decreaseItemStack(player.getInventory().getItemInMainHand());
        return rayResult;
    }


}
