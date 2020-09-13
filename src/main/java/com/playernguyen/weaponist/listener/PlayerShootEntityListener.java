package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.event.WeaponistPlayerShootEntityEvent;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.material.MaterialData;

public class PlayerShootEntityListener extends WeaponistListener {
    @EventHandler
    public void onHit(WeaponistPlayerShootEntityEvent event) {
        // Play particle
        LivingEntity livingEntity = event.getLivingEntity();
        MaterialData materialData;

        switch (livingEntity.getType()) {
            default: {
                materialData = new MaterialData(Material.REDSTONE_BLOCK);
            }
        }

        livingEntity.getWorld()
                .spawnParticle(Particle.BLOCK_CRACK,
                        livingEntity.getLocation().add(0, 0.5, 0),
                        60,
                        materialData
                );

        if (event.isHeadshot()) {
            livingEntity.getWorld()
                    .spawnParticle(Particle.CRIT,
                            livingEntity.getEyeLocation(),
                            1);
            livingEntity.getWorld()
                    .spawnParticle(Particle.CRIT_MAGIC,
                            livingEntity.getEyeLocation(),
                            1);
        }

        Shooter shooter = event.getShooter();
        shooter.asPlayer().playSound(shooter.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.4f, 1);

        if (livingEntity.isDead()) {
            shooter.asPlayer().playSound(shooter.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 0.3f, 0.7f);
        }
    }

}
