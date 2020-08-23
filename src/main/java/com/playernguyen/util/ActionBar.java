package com.playernguyen.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

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

    public static void performCountdown(Plugin plugin, Player player, double second, Runnable onDone) {

        BukkitRunnable runnable = new BukkitRunnable() {
            double i = second;
            @Override
            public void run() {

                // Cancel the schedule
                if (i <= 0) {
                    onDone.run();
                    this.cancel();
                    return;
                } i=i-0.05;
                // Create countdown and add
                ActionBar bar = new ActionBar();
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                String progressBar; double point = (i/second)*100;

                if (point >= 90) {
                    progressBar = ChatColor.GREEN + "=" + ChatColor.GRAY + "=========";
                } else if (point >= 80) {
                    progressBar = ChatColor.GREEN + "==" + ChatColor.GRAY + "========";
                }else if (point >= 70) {
                    progressBar = ChatColor.GREEN + "===" + ChatColor.GRAY + "=======";
                }else if (point >= 60) {
                    progressBar = ChatColor.GREEN + "====" + ChatColor.GRAY + "======";
                }else if (point >= 50) {
                    progressBar = ChatColor.GREEN + "=====" + ChatColor.GRAY + "=====";
                }else if (point >= 40) {
                    progressBar = ChatColor.GREEN + "======" + ChatColor.GRAY + "====";
                }else if (point >= 30) {
                    progressBar = ChatColor.GREEN + "=======" + ChatColor.GRAY + "===";
                }else if (point >= 20) {
                    progressBar = ChatColor.GREEN + "========" + ChatColor.GRAY + "==";
                }else if (point >= 10) {
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
                bar.send(player);
            }
        };

        runnable.runTaskTimerAsynchronously(plugin, 0, 0);
    }
}
