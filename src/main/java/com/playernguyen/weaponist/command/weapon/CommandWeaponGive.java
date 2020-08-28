package com.playernguyen.weaponist.command.weapon;

import com.playernguyen.weaponist.asset.Weapon;
import com.playernguyen.weaponist.command.Command;
import com.playernguyen.weaponist.command.CommandResult;
import com.playernguyen.weaponist.command.DefaultSubCommand;
import com.playernguyen.weaponist.language.LanguageFlag;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class CommandWeaponGive extends DefaultSubCommand {

    public CommandWeaponGive(Command previous) {
        super("give", "<item>", "Give weapon to player", previous);
    }

    @Override
    public CommandResult onCommand(CommandSender sender, List<String> params) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (params.size() <= 0 ) {
                return CommandResult.MISSING_ARGUMENTS;
            }
            // Search
            String weaponId = params.get(0);
            if (!getGunManager().hasWeapon(weaponId)) {
                sender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.GENERAL_WEAPON_NOT_FOUND));
                return CommandResult.NOTHING;
            }
            // Give to player
            player.getInventory()
                    .addItem(getGunManager()
                            .getRegisteredWeapon(weaponId)
                            .toItem(player, (params.size() == 2)
                                    ? Integer.parseInt(params.get(1)) : 1)
                    );
        }
        return CommandResult.NOTHING;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> params) {
        if (params.size() == 1) {
            return getGunManager()
                    .getContainer()
                    .stream()
                    .map(Weapon::getId)
                    .collect(Collectors.toList());
        }
        return null;
    }
}