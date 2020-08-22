package com.playernguyen.command.ammunition;

import com.playernguyen.asset.ammunition.Ammunition;
import com.playernguyen.command.Command;
import com.playernguyen.command.CommandResult;
import com.playernguyen.command.DefaultSubCommand;
import com.playernguyen.language.LanguageFlag;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class CommandAmmunitionGive extends DefaultSubCommand {

    public CommandAmmunitionGive(Command previous) {
        super("give", "<item> <player>", "Give ammo for player", previous);
    }

    @Override
    public CommandResult onCommand(CommandSender sender, List<String> params) {

        if (sender instanceof Player) {
            // args size to 0
            if (params.size() == 0) {
                sendHelp(sender);
                return CommandResult.MISSING_ARGUMENTS;
            }

            Player target;
            // Only player
            if (params.size() == 1) {
                target = (Player) sender;
            } else {
                String _target = params.get(1);
                Player _exactSearch = Bukkit.getPlayerExact(_target);
                if (_exactSearch == null) {
                   sender.sendMessage(getLanguageConfiguration()
                           .getLanguageWithPrefix(LanguageFlag.GENERAL_PLAYER_NOT_FOUND));
                   return CommandResult.NOTHING;
                }

                target = _exactSearch;
            }

            // Give bullet to target
            String id = params.get(0);
            Ammunition registeredAmmunition = getAmmunitionManager().getRegisteredAmmunition(id);
            if (registeredAmmunition == null) {
                sender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.GENERAL_AMMO_NOT_FOUND));
                return CommandResult.NOTHING;
            }

            // Accept and give
            target.getInventory().addItem(registeredAmmunition.toItem(target));

        }

        return CommandResult.NOTHING;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> params) {
        return getAmmunitionManager().getContainer().stream().map(Ammunition::getId).collect(Collectors.toList());
    }
}
