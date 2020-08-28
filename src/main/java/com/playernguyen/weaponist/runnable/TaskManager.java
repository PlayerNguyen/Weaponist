package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.manager.ManagerMap;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskManager<T extends BukkitRunnable> extends ManagerMap<Shooter, T> {

    public void swipeTask(Shooter shooter) {
        T runnable = this.get(shooter);
        if (runnable == null) {
            throw new NullPointerException("The runnable is null-check");
        }

        if (!runnable.isCancelled()) {
            Bukkit.getScheduler().cancelTask(runnable.getTaskId());
            runnable.cancel();
        }

        remove(shooter);
    }

}
