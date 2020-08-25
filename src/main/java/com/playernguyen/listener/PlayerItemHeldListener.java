package com.playernguyen.listener;

import com.playernguyen.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemHeldListener extends WeaponistListener {

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);
        ItemStack stack = player.getInventory().getItemInMainHand();

        if (shooter != null) {
            // Check reloading
            if (shooter.isReloading()) {
                event.setCancelled(true);
            }
            // If scoping
            if (shooter.isScoping()) {
                shooter.setScoping(false);
            }

            shooter.setCurrentItem(stack);
        }
    }


}
