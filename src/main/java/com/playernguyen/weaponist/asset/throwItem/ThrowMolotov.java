package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.runnable.ThrowableTriggerCountdownRunnable;
import com.playernguyen.weaponist.util.ParticleBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ThrowMolotov extends DefaultThrow {

    public ThrowMolotov() {
        super(ThrowEnum.MOLOTOV);
    }

    @Override
    public void onThrow(Shooter shooter) {
        Player player = shooter.asPlayer();
        ItemStack mainHandStack = player.getInventory().getItemInMainHand();
        Item item = player.getWorld().dropItem(player.getLocation(), mainHandStack);
        // Set item data
        item.setVelocity(player.getEyeLocation().getDirection()
                .normalize()
                .multiply(1)
                .add(new Vector(0, .5d, 0))
        );
        item.setPickupDelay(999999);

        // Take item
        mainHandStack.setAmount(mainHandStack.getAmount() - 1);
        // Run countdown task
        new ThrowableTriggerCountdownRunnable(this, item, shooter)
                .runTaskTimerAsynchronously(getWeaponist(), 0, 0);
    }

    @Override
    public void onExplode(Shooter shooter, Location location) {
        BukkitRunnable runnable = new BukkitRunnable() {
            double duration = getExplodingTime();
            @Override
            public void run() {
                duration = duration - (1.0/20.0);

                // Create flame
                new ParticleBuilder(Particle.FLAME, 50)
                        .offset(new Vector(2, 0.2, 2))
                        .extra(0.1)
                        .play(location);

                // Get location
                location.getWorld().getNearbyEntities(location, 5, 5, 5)
                        .forEach(entity -> {
                            if (entity instanceof LivingEntity) {
                                Bukkit.getScheduler().runTask(getWeaponist(), () -> {
                                    entity.setFireTicks(1);
                                    ((LivingEntity) entity).damage(getPower()/10);
                                });
                            }
                        });

                if (duration <= 0) {
                    cancel();
                }
            }
        };

        runnable.runTaskTimerAsynchronously(getWeaponist(), 0, 0);
    }
}
