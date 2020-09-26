package com.playernguyen.weaponist.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class PlayerInventoryHandlerListener extends WeaponistListener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
//        getDebugger().info("========================");
//        getDebugger().info("Clicking inventory...");
//        getDebugger().info("Action: " + event.getAction());
//        getDebugger().info("Click Type: " + event.getClick());
//        getDebugger().info("Current item: " + event.getCurrentItem());
//        getDebugger().info("Cursor: " + event.getCursor());
//
//        // Is player
//        if (event.getWhoClicked() instanceof Player) {
//            Player player = (Player) event.getWhoClicked();
//
//            // Placing down
//            ItemStack cursor = event.getCursor();
//            // This is a weapon
//            if (cursor != null && Tag.isWeapon(cursor)) {
//
//                // Just on stack | placing one
//                if (event.getAction() == InventoryAction.PLACE_ONE) {
//                    getDebugger().info("Action: PLACE_ONE");
//                    event.setCancelled(true);
//
//                }
//
//                // Drop one cursor
//                if (event.getAction() == InventoryAction.DROP_ONE_CURSOR) {
//                    getDebugger().info("Action: DROP_ONE_CURSOR");
//                    event.setCancelled(true);
//
//                }
//
//            }
//
//            // Picking up
//            ItemStack current = event.getCurrentItem();
//            if (current != null && Tag.isWeapon(current)) {
//                // Pick a half
//                if (event.getAction() == InventoryAction.PICKUP_HALF) {
//                    event.setCancelled(true);
//                }
//
//            }

//        }

    }


    @EventHandler
    public void drag(InventoryDragEvent event) {
//        if (event.getWhoClicked() instanceof Player) {
//            Player player = (Player) event.getWhoClicked();
//
//            ItemStack cursor = event.getCursor();
//            if (cursor != null && Tag.isWeapon(cursor)) {
//                event.setCancelled(true);
//            }
//        }
    }

}
