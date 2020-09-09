package com.playernguyen.weaponist.command;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.language.LanguageFlag;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public abstract class DefaultSubCommand extends WeaponistInstance implements SubCommand {


    private final String command;
    private final String parameter;
    private final String description;
    private final Command previous;
    private final PermissionConstructor permissions = new PermissionConstructor();
    private final String primaryPermissions;

    public DefaultSubCommand(String command, String parameter, String description, Command previous) {
        this.command = command;
        this.parameter = parameter;
        this.description = description;
        this.previous = previous;
        this.primaryPermissions = previous.getPrimaryPermission().concat(".").concat(command);
        // Register default command
        permissions.add(CommandPermissionEnum.COMMAND_ALL);
        permissions.add(primaryPermissions);
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getParameter() {
        return parameter;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public PermissionConstructor getPermissions() {
        return permissions;
    }

    @Override
    public String getPrimaryPermission() {
        return primaryPermissions;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        getDebugger().info("SubCmd -> size:" + strings.length);
        return onTab(commandSender, Arrays.asList(strings));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if (!commandSender.hasPermission("weaponist.".concat(getPreviousCommand().getCommand()).concat(getCommand())) || !commandSender.hasPermission("weaponist.*")) {
            commandSender.sendMessage(getLanguageConfiguration()
                    .getLanguageWithPrefix(LanguageFlag.COMMAND_USAGE_NO_PERMISSION));
            return true;
        }

        getDebugger().info("Execute::commandSender: " + commandSender + " / arguments" + Arrays.asList(strings).toString());
        CommandResult result = onCommand(commandSender, Arrays.asList(strings));
        switch (result) {
            case INVALID_SENDER: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.COMMAND_RESULT_INVALID_SENDER));
                return true;
            }
            case MISSING_ARGUMENTS: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.COMMAND_RESULT_MISSING_ARGUMENTS));
                sendHelp(commandSender);
                return true;
            }

            case NOTHING: case NULL: {
                return true;
            }

            case NOT_FOUND: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.COMMAND_RESULT_NOT_FOUND));
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean hasPermissions(CommandSender sender) {
        return false;
    }

    @Override
    public Command getPreviousCommand() {
        return previous;
    }

    @Override
    public String toHelp() {
        return ChatColor.GOLD + "/"
                + getPreviousCommand().getCommand()
                + " " + getCommand()
                + ChatColor.LIGHT_PURPLE + " "
                + getParameter() + ": "
                + ChatColor.GRAY + getDescription();
    }
}
