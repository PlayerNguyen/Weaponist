package com.playernguyen.weaponist.runnable;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class TaskTimer implements Runnable {

    private final int id;
    private boolean cancel;

    public TaskTimer(JavaPlugin plugin, long l, long l1) {
        this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, l, l1);
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void cancel() {
        this.setCancel(true);

    }


    public int getId() {
        return id;
    }
}
