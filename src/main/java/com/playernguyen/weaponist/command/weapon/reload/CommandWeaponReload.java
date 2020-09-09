package com.playernguyen.weaponist.command.weapon.reload;

import com.playernguyen.weaponist.command.Command;
import com.playernguyen.weaponist.command.CommandResult;
import com.playernguyen.weaponist.command.DefaultSubCommand;
import com.playernguyen.weaponist.language.LanguageFlag;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandWeaponReload extends DefaultSubCommand {


    public CommandWeaponReload(Command pre) {
        super("reload", "", "Reload weaponist plugin", pre);
    }

    @Override
    public CommandResult onCommand(CommandSender sender, List<String> params) {

        reloadWeaponist();
        sender.sendMessage(getLanguageConfiguration().getLanguageWithPrefix(LanguageFlag.COMMAND_RELOAD_SUCCEED));

        return CommandResult.NOTHING;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> params) {
        return null;
    }
}
