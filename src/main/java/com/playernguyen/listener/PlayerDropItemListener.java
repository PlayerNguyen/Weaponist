package com.playernguyen.listener;

import com.playernguyen.entity.Shooter;
import com.playernguyen.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropItemListener extends WeaponistListener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        Shooter shooter = getShooterManager().getShooterAsPlayer(player);

        if (Tag.isWeapon(itemInMainHand)) {

            if (shooter != null && !shooter.isReloading()) {
                
            }

            event.setCancelled(true);
        }
    }

}
