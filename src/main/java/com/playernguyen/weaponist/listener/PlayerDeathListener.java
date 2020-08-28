package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends WeaponistListener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);

        // Set stop scoping
        if (shooter.isScoping()) {
            shooter.setScoping(false);
        }
    }


}
