package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class PlayerOpenInventoryListener extends WeaponistListener {

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);

        if (shooter != null && shooter.isScoping()) {
            shooter.setScoping(false);
        }
    }

}
