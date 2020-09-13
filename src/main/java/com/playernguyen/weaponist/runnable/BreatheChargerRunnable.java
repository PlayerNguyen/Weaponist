package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.ActionBar;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class BreatheChargerRunnable extends BukkitRunnable {
    private final Shooter shooter;
    private final ScopeRunnable scopeRunnable;

    public BreatheChargerRunnable(Shooter shooter, ScopeRunnable scopeRunnable) {
        this.shooter = shooter;
        this.scopeRunnable = scopeRunnable;
    }

    @Override
    public void run() {
        if (shooter.isBreathing() || shooter.getBreathLevel() >= shooter.getMaxBreathLevel()) {
            scopeRunnable.setCurrentRunnable(null);
            cancel();
            return;
        }
        // Update
        shooter.setBreathLevel(shooter.getBreathLevel() + 0.1d);
        // Perform
        // Always display breath level
        new ActionBar.Percentage(shooter.getMaxBreathLevel(), shooter.getBreathLevel())
                .activeColor(ChatColor.RED)
                .backgroundColor(ChatColor.GRAY)
                .prefix(ChatColor.RED + ": [-]")
                .suffix(ChatColor.RED + "[-] :")
                .sendActionBar(shooter.asPlayer());

    }
}
