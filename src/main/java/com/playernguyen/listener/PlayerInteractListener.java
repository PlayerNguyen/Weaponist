package com.playernguyen.listener;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.asset.ammunition.AmmunitionEnum;
import com.playernguyen.asset.ItemTagEnum;
import com.playernguyen.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener extends WeaponistInstance implements Listener {

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack mainHandStack = player.getInventory().getItemInMainHand();

        Tag.Reader reader = new Tag.Reader(mainHandStack);
        if (reader.hasCompound() && reader.getBoolean(ItemTagEnum.AMMUNITION_VALID)) {
            player.sendMessage("This is an ammunition");
            return;
        }

        getDebugger().warn("Trying to give player an weapon");
        ItemStack stack = getAmmunitionManager().getRegisteredAmmunition(AmmunitionEnum.COMMUNIST_RIFLE)
                .toItem(player);
        player.getInventory().addItem(stack);
    }

}
