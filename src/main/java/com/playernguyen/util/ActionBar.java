package com.playernguyen.util;

import com.playernguyen.entity.Shooter;
import com.playernguyen.runnable.ActionPerformRunnable;
import com.playernguyen.runnable.TaskTimer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.*;
import java.text.DecimalFormat;

public class ActionBar {

    private String content;

    public ActionBar(String content) {
        this.content = content;
    }

    public ActionBar() {
    }

    public void send(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(content));
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ActionPerformRunnable performCountdown(JavaPlugin plugin,
                                                         Shooter shooter,
                                                         double second,
                                                         Runnable tick,
                                                         Runnable onDone
    ) {
        return new ActionPerformRunnable(second, onDone, tick, shooter);
    }

    public static ActionPerformRunnable performCountdown(Shooter shooter, double second) {
        return new ActionPerformRunnable(second, shooter);
    }
}
