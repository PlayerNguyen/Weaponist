package com.playernguyen.weaponist.util;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.runnable.ActionPerformRunnable;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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

    public static class Percentage {
        private double maxValue;
        private double currentValue;

        private ChatColor backgroundColor = ChatColor.DARK_GRAY;
        private ChatColor activeColor = ChatColor.RED;
        private String prefixText = "";
        private String suffixText = "";

        public Percentage(double maxValue, double currentValue) {
            this.maxValue = maxValue;
            this.currentValue = currentValue;
            init();
        }


        // private String content = "";
        private String barContent = "";

        @Deprecated
        public Percentage() {
            this.maxValue = 1;
            this.currentValue = 1;
            // Init the barContent
            init();
        }

        private void init() {
            // Set full active
            for (int i = 0; i < 10; i++) {
                this.barContent = barContent.concat("=");
            }
            // Then minus to cursor

            this.barContent = insert(this.barContent, cursorAt(), "*");
        }

        private String insert(String rawString, int position, String insertString) {

            return rawString.substring(0, position) +
                    insertString +
                    rawString.substring(position);
        }

        private double percentage() {
            return (currentValue / maxValue) * 100;
        }

        private int cursorAt() {
            double _currentPercentage = percentage();
            for (int i = 0; i < 10; i++) {
                int leftBlock = i * 10;
                int rightBlock = ((i+1) * 10);
                if (leftBlock <= _currentPercentage && _currentPercentage < rightBlock) {
                    return i;
                }
            }
            if (percentage() >= 100) return 10;
            else if (percentage() <= 0) return 0;
            throw new
                    IllegalStateException("The percentage out of range: [0 ~ 100] instead of "
                    + _currentPercentage);
        }


        public Percentage backgroundColor(ChatColor backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Percentage activeColor(ChatColor activeColor) {
            this.activeColor = activeColor;
            return this;
        }

        public Percentage prefix(String prefixText) {
            this.prefixText = prefixText;
            return this;
        }

        public Percentage suffix(String suffixText) {
            this.suffixText = suffixText;
            return this;
        }

        public Percentage maxValue(double maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Percentage currentValue(double currentValue) {
            this.currentValue = currentValue;
            return this;
        }

        public String build() {
            // Now with the prefix build
            this.barContent = prefixText + " " + this.barContent;
            // And then suffix build
            this.barContent = this.barContent + " " + suffixText;
            return barContent.replace("*", backgroundColor.toString());
        }

        public void sendActionBar(Player player) {
            new ActionBar(build()).send(player);
        }
    }


}
