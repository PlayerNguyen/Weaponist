package com.playernguyen.ray;

import com.playernguyen.Weaponist;
import com.playernguyen.WeaponistInstance;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class RayTrace extends WeaponistInstance {

    private Player player;
    private int range;

    public RayTrace(Player player, int range) {
        this.player = player;
        this.range = range;
    }

    public RayResult ray() {
        RayResult result = new RayResult();

        BlockIterator blockIterator =
                new BlockIterator(
                        getPlayer(), getRange()
                );

        double offset = 0.5;

        while (blockIterator.hasNext()) {
            Block currentBlock = blockIterator.next();

            player.getLocation().getWorld().spawnParticle(Particle.HEART, currentBlock.getLocation(), 1);

            for (Entity nearbyEntity : currentBlock.getLocation().getWorld()
                    .getNearbyEntities(currentBlock.getLocation(), offset, offset, offset)) {
                if (nearbyEntity instanceof LivingEntity && nearbyEntity != getPlayer()) {
                    // Headshot detect
                    LivingEntity livingEntity = ((LivingEntity) nearbyEntity);
                    double headDistance = livingEntity.getEyeLocation().distance(currentBlock.getLocation());

                    Weaponist.getDebugger().info(livingEntity.getType() + "=> " + headDistance);

                    result.getHeadshotEntities().add(livingEntity);
                }
            }

            // Stop when hit some block that cannot penetrate
            if (!currentBlock.getType().isTransparent()) {
                result.setHitBlock(currentBlock);
                break;
            }
        }

        return result;
    }

    public int getRange() {
        return range;
    }

    public Player getPlayer() {
        return player;
    }
}

