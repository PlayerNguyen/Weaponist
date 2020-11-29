package com.playernguyen.weaponist.util;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.asset.gun.Gun;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.event.WeaponistPlayerShootEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Collection;


public class WeaponistUtil {

    public static void knockBack(Player player, double rate) {
        Vector vector = player.getEyeLocation().getDirection();
        vector.setY(0);

        player.setVelocity(vector.multiply(rate / 10).multiply(-1));
    }

    public static void decreaseItemStack(ItemStack itemStack) {
        int currentAmount = itemStack.getAmount();
        itemStack.setAmount(currentAmount - 1);
    }

    public static void increaseItemStack(ItemStack itemStack) {
        int currentAmount = itemStack.getAmount();
        itemStack.setAmount(currentAmount + 1);
    }

    public static ItemStack updateItemMeta(ItemStack stack, Gun gun) {
        // Refresh meta data
        ItemMeta itemMeta = stack.getItemMeta();
        if (itemMeta != null) {
            String currentDisplayName = gun.getDisplayName()
                    .replace("%ammo%", String.valueOf(Tag.getGunAmmo(stack)))
                    .replace("%max_ammo%", String.valueOf(gun.getMaxStackSize()));
            itemMeta.setDisplayName(currentDisplayName);
        }

        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static void fakeExplosion(Location location, float power, Shooter shooter) {
        // Play particle
        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, Math.round(power / 5), .2, .2, .2, 0.2);
        location.getWorld().spawnParticle(Particle.SMOKE_LARGE, location, Math.round(power / 5), .2, .2, .2, 0.2);
        // Get living entities
        Collection<Entity> entities = location.getWorld().getNearbyEntities(location, power / 5, power / 5, power / 5);
        for (Entity entity : entities) {
            // Iterate all entities
            if (entity instanceof LivingEntity) {
                Location entityLocation = entity.getLocation();

                Bukkit.getScheduler().runTask(Weaponist.getWeaponist(), () -> {
                    double d = power - (entityLocation.distance(location));
                    //System.out
                    // .println(entity.toString() + " -> damage: " + d + " distance: " +
                    // entityLocation.distance(location));

                    ((LivingEntity) entity).damage(d, shooter.asPlayer());

                    WeaponistPlayerShootEntityEvent event =
                            new WeaponistPlayerShootEntityEvent(shooter, (LivingEntity) entity, d);
                    Bukkit.getPluginManager().callEvent(event);
                });

                Vector direction = new Vector(
                        entityLocation.getX() - location.getX(),
                        entityLocation.getY() - location.getY(),
                        entityLocation.getZ() - location.getZ()
                );

                entity.setVelocity(direction.normalize());
            }
        }
        // Play sound
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);

    }

}
