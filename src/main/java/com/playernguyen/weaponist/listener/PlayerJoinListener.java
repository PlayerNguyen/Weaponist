package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.DefaultShooter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends WeaponistListener{
    @EventHandler
    public void join(PlayerJoinEvent event) {

        if (getShooterManager().getShooterAsPlayer(event.getPlayer()) == null) {
            getShooterManager().add(new DefaultShooter(event.getPlayer()));
        }
    }
}
