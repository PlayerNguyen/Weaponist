package com.playernguyen.weaponist.command;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.language.LanguageFlag;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public abstract class DefaultCommand extends WeaponistInstance implements Command {

    private final String command;
    private final String parameter;
    private final String description;
    private final PermissionConstructor permissions
            = new PermissionConstructor();
    private final String primaryPermission;

    public DefaultCommand(String command,
                          String parameter,
                          String description,
                          String primaryPermission
    ) {
        this.command = command;
        this.parameter = parameter;
        this.description = description;
        this.primaryPermission = primaryPermission;
        // Register all permission
        permissions.add(CommandPermissionEnum.COMMAND_ALL);
        permissions.add(primaryPermission);
    }

    public DefaultCommand(String command,
                          String parameter,
                          String description,
                          CommandPermissionEnum primaryPermission
    ) {
        this.command = command;
        this.parameter = parameter;
        this.description = description;
        this.primaryPermission = primaryPermission.getNode();
        // Register all permission
        permissions.add(CommandPermissionEnum.COMMAND_ALL);
        permissions.add(primaryPermission);
    }

    @Override
    public String getPrimaryPermission() {
        return primaryPermission;
    }

    @Override
    public PermissionConstructor getPermissions() {
        return permissions;
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
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return onTab(commandSender, Arrays.asList(strings));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if (!hasPermissions(commandSender)) {
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
                commandSender.sendMessage(ChatColor.GRAY + "[+] ------------------ [+]");
                sendHelp(commandSender);
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.COMMAND_RESULT_MISSING_ARGUMENTS));

                return true;
            }

            case NOTHING: {
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
    public String toHelp() {
        return ChatColor.GOLD +  "/" + getCommand() + " " + ChatColor.DARK_PURPLE + getParameter() + ": " + ChatColor.GRAY + getDescription();
    }

}
