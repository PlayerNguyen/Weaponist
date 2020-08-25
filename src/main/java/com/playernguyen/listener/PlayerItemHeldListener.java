package com.playernguyen.listener;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldListener extends WeaponistInstance implements Listener {

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);

        if (shooter != null && shooter.isReloading()) {
            event.setCancelled(true);
        }
    }


}
