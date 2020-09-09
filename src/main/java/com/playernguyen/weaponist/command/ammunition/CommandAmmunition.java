package com.playernguyen.weaponist.command.ammunition;

import com.playernguyen.weaponist.command.*;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandAmmunition extends DefaultCommand {

    private final CommandManager subCommandManager = new CommandManager();

    public CommandAmmunition() {
        super("ammunition", "<command>", "Ammunition command", CommandPermissionEnum.COMMAND_AMMO);
        // Register sub command
        subCommandManager.add(new CommandAmmunitionGive(this));
        // Register group permissions
        getPermissions().add(CommandPermissionEnum.COMMAND_GROUP_ADMIN);
    }

    @Override
    public CommandResult onCommand(CommandSender sender, List<String> params) {

        // Missing arguments
        if (params.size() <= 0) {
            for (Command subCommand : subCommandManager.getContainer()) {
                sender.sendMessage(subCommand.toHelp());
            }
            return CommandResult.MISSING_ARGUMENTS;
        }

        String subCommand = params.get(0);
        if (!subCommandManager.hasCommand(subCommand)) {
            return CommandResult.NOT_FOUND;
        }

        // Call sub-command
        subCommandManager.getCommand(subCommand).onCommand(sender,params.subList(1, params.size()));
        return CommandResult.NOTHING;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> params) {
        // If seeing some sub
        if (params.size() > 1) {
            String getter = params.get(0);
            if (!subCommandManager.hasCommand(getter)) {
                return null;
            }
            return subCommandManager.getCommand(getter).onTab(sender, params.subList(1, params.size()));
        } else
        // Or no
        return subCommandManager.toList();
    }
}
