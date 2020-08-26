package com.playernguyen.listener;

import com.playernguyen.event.WeaponistPlayerShootEntityEvent;
import org.bukkit.Material;
import org.bukkit.Particle;
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
                        150,
                        materialData
                );
    }

}
