package com.playernguyen.listener;

import com.playernguyen.manager.ManagerSet;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerManager extends ManagerSet<Listener> {

    public void register(Plugin plugin) {
        getContainer().forEach(e -> Bukkit.getPluginManager().registerEvents(e, plugin));
    }

}
