package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.manager.ManagerSet;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerManager extends ManagerSet<Listener> {

    public void register(Plugin plugin) {
        getContainer().forEach(e -> {
            Weaponist.getDebugger().info("Registering listener: " + e.toString());
            Bukkit.getPluginManager().registerEvents(e, plugin);
        });
    }

}
