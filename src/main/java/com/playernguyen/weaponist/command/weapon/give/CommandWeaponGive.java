package com.playernguyen.weaponist.command.weapon.give;

import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.command.Command;
import com.playernguyen.weaponist.command.CommandResult;
import com.playernguyen.weaponist.command.DefaultSubCommand;
import com.playernguyen.weaponist.language.LanguageFlag;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandWeaponGive extends DefaultSubCommand {

    public CommandWeaponGive(Command previous) {
        super("give", "<item>", "Give weapon to player", previous);
    }

    @Override
    public CommandResult onCommand(CommandSender sender, List<String> params) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (params.size() <= 0 ) {
                player.sendMessage(ChatColor.DARK_GRAY + "---------------------");
                for (String s : getItemManager().keySet()) {
                    //player.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.RED + s);
                    ComponentBuilder componentBuilder = new ComponentBuilder("");
                    componentBuilder.append(" - ").color(net.md_5.bungee.api.ChatColor.DARK_GRAY);
                    componentBuilder.append(s + " ").color(net.md_5.bungee.api.ChatColor.RED);
                    componentBuilder.append("(" + getLanguageConfiguration().getLanguage(LanguageFlag.COMMAND_CLICK_ME) + ")")
                            .color(net.md_5.bungee.api.ChatColor.RED).color(net.md_5.bungee.api.ChatColor.UNDERLINE);
                    // Event grant
                    componentBuilder.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                            String.format("/%s %s %s", getPreviousCommand().getCommand(), getCommand(), s)));
                    componentBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder("Click for prepare command").color(net.md_5.bungee.api.ChatColor.RED).create()
                            ));

                    player.spigot().sendMessage(componentBuilder.create());
                }
                player.sendMessage(ChatColor.DARK_GRAY + "---------------------");
                player.sendMessage("");
                return CommandResult.MISSING_ARGUMENTS;
            }
            // Search
            String weaponId = params.get(0);
            Item registeredGlobalItem = getItemManager().getRegisteredGlobalItem(weaponId);
            if (registeredGlobalItem == null) {
                sender.sendMessage(getLanguageConfiguration()
                        .getLanguageWithPrefix(LanguageFlag.GENERAL_WEAPON_NOT_FOUND));
                return CommandResult.NOTHING;
            }
            // Give to player
            player.getInventory()
                    .addItem(registeredGlobalItem.toItem(player, (params.size() == 2)
                                    ? Integer.parseInt(params.get(1)) : 1)
                    );
        }
        return CommandResult.NOTHING;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> params) {
        if (params.size() == 1) {
            return new ArrayList<>(getItemManager().keySet());
        }
        return null;
    }


}
