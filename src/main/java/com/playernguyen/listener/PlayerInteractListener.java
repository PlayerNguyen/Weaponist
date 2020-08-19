package com.playernguyen.listener;

import com.playernguyen.ray.RayTrace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        RayTrace rayTrace = new RayTrace(player, 500);

        rayTrace.ray(null, 2);
    }

}
