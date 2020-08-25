package com.playernguyen.listener;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.entity.DefaultShooter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends WeaponistInstance implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent event) {

        if (getShooterManager().getShooterAsPlayer(event.getPlayer()) == null) {
            getShooterManager().add(new DefaultShooter(event.getPlayer()));
        }
    }
}