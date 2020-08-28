package com.playernguyen.weaponist.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public interface Command extends TabExecutor {

    String getCommand();

    String getDescription();

    String getParameter();

    CommandResult onCommand(CommandSender sender, List<String> params);

    List<String> onTab(CommandSender sender, List<String> params);

    String toHelp();

    default void sendHelp(CommandSender sender) {
        sender.sendMessage(toHelp());
    }

}
