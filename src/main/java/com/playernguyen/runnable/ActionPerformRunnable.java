package com.playernguyen.runnable;

import com.playernguyen.Weaponist;
import com.playernguyen.entity.Shooter;
import com.playernguyen.util.ActionBar;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class ActionPerformRunnable extends BukkitRunnable {

    private final double second;
    private final Shooter shooter;

    private Runnable onDone;
    private Runnable onTick;
    private double i;

    public ActionPerformRunnable(double second, Runnable onDone, Runnable onTick, Shooter shooter) {
        this.second = second;
        this.onTick = onTick;
        this.onDone = onDone;
        this.shooter = shooter;

        this.i = second;

        Weaponist.getDebugger().info("Initial the ActionPerformRunnable");
    }

    public ActionPerformRunnable(double second, Shooter shooter) {
        this.second = second;
        this.shooter = shooter;
        this.onTick = null;
        this.onDone = null;

        this.i = second;

        Weaponist.getDebugger().info("Initial the ActionPerformRunnable");
    }

    public void setOnDone(Runnable onDone) {
        this.onDone = onDone;
    }

    public void setOnTick(Runnable onTick) {
        this.onTick = onTick;
    }

    @Override
    public void run() {

        // On tick
        if (onTick != null) onTick.run();

        // Cancel the schedule
        if (i <= 0) {
            if (onDone != null) onDone.run();
            this.cancel();
            return;
        }
        i = i - 0.05;

        // Create countdown and add
        ActionBar bar = new ActionBar();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String progressBar;
        double point = (i / second) * 100;

        if (point >= 90) {
            progressBar = ChatColor.GREEN + "=" + ChatColor.GRAY + "=========";
        } else if (point >= 80) {
            progressBar = ChatColor.GREEN + "==" + ChatColor.GRAY + "========";
        } else if (point >= 70) {
            progressBar = ChatColor.GREEN + "===" + ChatColor.GRAY + "=======";
        } else if (point >= 60) {
            progressBar = ChatColor.GREEN + "====" + ChatColor.GRAY + "======";
        } else if (point >= 50) {
            progressBar = ChatColor.GREEN + "=====" + ChatColor.GRAY + "=====";
        } else if (point >= 40) {
            progressBar = ChatColor.GREEN + "======" + ChatColor.GRAY + "====";
        } else if (point >= 30) {
            progressBar = ChatColor.GREEN + "=======" + ChatColor.GRAY + "===";
        } else if (point >= 20) {
            progressBar = ChatColor.GREEN + "========" + ChatColor.GRAY + "==";
        } else if (point >= 10) {
            progressBar = ChatColor.GREEN + "=========" + ChatColor.GRAY + "=";
        } else {
            progressBar = ChatColor.GREEN + "==========" + ChatColor.GRAY + "";
        }

        bar.setContent(String.format(
                ChatColor.translateAlternateColorCodes('&',
                        "&c[&7*&c]&7%s&c[&7*&c] &7(%s)"),
                progressBar,
                decimalFormat.format(i).replace("-", "0.")
        ));
        bar.send(shooter.asPlayer());
    }
}
