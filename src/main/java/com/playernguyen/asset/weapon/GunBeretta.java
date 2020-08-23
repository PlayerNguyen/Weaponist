package com.playernguyen.asset.weapon;

import com.playernguyen.entity.DefaultShooter;
import com.playernguyen.entity.Shooter;
import com.playernguyen.entity.Target;
import com.playernguyen.ray.RayResult;
import com.playernguyen.ray.RayTrace;
import com.playernguyen.sound.SoundConfiguration;
import com.playernguyen.util.WeaponistUtil;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.io.IOException;

public class GunBeretta extends DefaultGun {

    public GunBeretta() throws IOException {
        super(GunEnum.BERETTA);
    }

    @Override
    public RayResult shoot(Player player) {
        // Shooting effect
        Shooter shooter = new DefaultShooter(player);
        RayTrace rayTrace = new RayTrace(shooter, 100);
        RayResult rayResult = rayTrace.ray(Particle.VILLAGER_HAPPY, 1);

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
        // Play effect
        WeaponistUtil.knockBack(player, 0.5f);
        WeaponistUtil.decreaseItemStack(player.getInventory().getItemInMainHand());
        return rayResult;
    }
}
