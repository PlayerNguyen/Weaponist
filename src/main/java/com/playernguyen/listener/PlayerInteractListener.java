package com.playernguyen.listener;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.asset.ammunition.AmmunitionEnum;
import com.playernguyen.util.Tag;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractListener extends WeaponistInstance implements Listener {

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack mainHandStack = player.getInventory().getItemInMainHand();

        if (Tag.isAmmunition(mainHandStack)) {
            player.sendMessage("This is an ammunition");
            ItemStack stack = mainHandStack.clone();

            Item itemEntity = player.getWorld().dropItem(player.getLocation(), stack);
            itemEntity.setVelocity(player.getEyeLocation().getDirection().multiply(1.2));

            new BukkitRunnable() {
                @Override
                public void run() {
                    itemEntity.getLocation().getWorld().createExplosion(itemEntity.getLocation(), 5);
                }
            }.runTaskLater(getWeaponist(), 100);

            return;
        }

        getDebugger().warn("Trying to give player an weapon");
        ItemStack stack = getAmmunitionManager().getRegisteredAmmunition(AmmunitionEnum.COMMUNIST_RIFLE)
                .toItem(player);
        player.getInventory().addItem(stack);
    }

}
