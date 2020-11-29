package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.ActionBar;
import com.playernguyen.weaponist.util.LocationUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class ScopeRunnable extends BukkitRunnable {

    private final Shooter shooter;

    private BreatheChargerRunnable currentRunnable = null;

    public ScopeRunnable(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void run() {
        if (!shooter.isScoping()) {
            // Charge
            shooter.setBreathing(false);
            new BreatheChargerRunnable(shooter, this)
                    .runTaskTimerAsynchronously(Weaponist.getWeaponist(), 0, 0);
            // Cancel the task
            cancel();
            return;
        }
        // If sneaking
        if (shooter.asPlayer().isSneaking()) {
            shooter.setBreathing(true);
            shooter.setBreathLevel(shooter.getBreathLevel() - 0.1d);
        } else {
            shooter.setBreathing(false);
            if (shooter.getBreathLevel() < shooter.getMaxBreathLevel())
                new BreatheChargerRunnable(shooter, this)
                        .runTaskTimerAsynchronously(Weaponist.getWeaponist(), 20, 20);
        }

        if (shooter.isBreathing() && shooter.getBreathLevel() <= 0) {
            shooter.setBreathing(false);
        }

        // Always display breath level
        new ActionBar.Percentage(shooter.getMaxBreathLevel(), shooter.getBreathLevel())
                .activeColor(ChatColor.AQUA)
                .backgroundColor(ChatColor.GRAY)
                .prefix(ChatColor.AQUA + ": [-]")
                .suffix("[-] :")
                .sendActionBar(shooter.asPlayer());


        if (!shooter.isBreathing())
            LocationUtil.createScopeNoise(shooter.asPlayer(), 3);
    }

    public void setCurrentRunnable(BreatheChargerRunnable currentRunnable) {
        this.currentRunnable = currentRunnable;
    }
}
