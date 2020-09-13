package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.DefaultShooter;
import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener extends WeaponistListener{
    @EventHandler
    public void join(PlayerJoinEvent event) {

        if (getShooterManager().getShooterAsPlayer(event.getPlayer()) == null) {
            getShooterManager().add(new DefaultShooter(event.getPlayer()));
        }
    }

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        Shooter shooter = getShooterManager().getShooterAsPlayer(event.getPlayer());

        if (shooter != null) {
            shooter.setScoping(false);
            shooter.setReloading(false);
            shooter.setBreathing(false);
        }
    }
}
