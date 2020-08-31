package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemHeldListener extends WeaponistListener {

    @EventHandler
    public synchronized void onHeld(PlayerItemHeldEvent event) {

        getDebugger().info("Change hand");
        Player player = event.getPlayer();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);
        ItemStack stack = player.getInventory().getItemInMainHand();

        if (shooter != null) {
            // If reloading
            if (shooter.isReloading() && shooter.isCanReload()) {
                getDebugger().info("Change while reloading");
                shooter.setCanReload(false);
                //getTaskManager().swipeTask(shooter);
            }
            // If scoping
            if (shooter.isScoping()) {
                getDebugger().info("Change while scoping");
                shooter.setScoping(false);
            }

            shooter.setCurrentItem(stack);
        }
    }


}
