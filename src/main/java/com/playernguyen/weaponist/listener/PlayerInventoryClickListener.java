package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryClickListener extends WeaponistListener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
//        getDebugger().info("Clicking inventory...");
//        getDebugger().info("Action: " + event.getAction());
//        getDebugger().info("Current item: " + event.getCurrentItem());
//        getDebugger().info("Cursor: " + event.getCursor());

        // Is player
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getAction() == InventoryAction.PLACE_ALL) {
                ItemStack cursor = event.getCursor();
                // This is a weapon
                if (Tag.isWeapon(cursor)) {
                    getDebugger().warn("Placing...");

                    ItemStack itemStack = event.getCurrentItem();

                    event.setCurrentItem(cursor);
                    event.setCursor(event.getCurrentItem());

                    event.setCancelled(true);
                }
            }
        }

    }


}
