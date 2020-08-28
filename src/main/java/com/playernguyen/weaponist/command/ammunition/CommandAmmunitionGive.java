package com.playernguyen.weaponist.command.ammunition;

import com.playernguyen.weaponist.asset.ammunition.Ammunition;
import com.playernguyen.weaponist.command.Command;
import com.playernguyen.weaponist.command.CommandResult;
import com.playernguyen.weaponist.command.DefaultSubCommand;
import com.playernguyen.weaponist.language.LanguageFlag;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class CommandAmmunitionGive extends DefaultSubCommand {

    public CommandAmmunitionGive(Command previous) {
        super("give", "<item> <amount> <player>", "Give ammo for player", previous);
    }

    @Override
    public CommandResult onCommand(CommandSender sender, List<String> params) {

        if (sender instanceof Player) {

            System.out.println(params);
            // args size to 0
            if (params.size() == 0) {
                sendHelp(sender);
                return CommandResult.MISSING_ARGUMENTS;
            }

            // Give bullet to target
            String id = params.get(0);
            int amount = (params.size() >= 2) ? Integer.parseInt(params.get(1)) : 1;

            Player target = (params.size() >= 3) ?
                    Bukkit.getPlayerExact(params.get(2)) : ((Player) sender).getPlayer();

            // Player not found
            if (target == null) {
                sender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.GENERAL_PLAYER_NOT_FOUND));
                return CommandResult.NOTHING;
            }

            Ammunition registeredAmmunition = getAmmunitionManager().getRegisteredAmmunition(id);
            if (registeredAmmunition == null) {
                sender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.GENERAL_AMMO_NOT_FOUND));
                return CommandResult.NOTHING;
            }

            // Accept and give
            target.getInventory().addItem(registeredAmmunition.toItem(target, amount));
            sender.sendMessage(getLanguageConfiguration()
                    .getLanguageWithPrefix(LanguageFlag.GENERAL_AMMO_GIVEN));
        }

        return CommandResult.NOTHING;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> params) {
        return getAmmunitionManager().getContainer().stream().map(Ammunition::getId).collect(Collectors.toList());
    }
}
