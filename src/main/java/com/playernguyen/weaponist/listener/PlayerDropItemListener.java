package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.language.LanguageFlag;
import com.playernguyen.weaponist.util.ActionBar;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropItemListener extends WeaponistListener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);

        if (Tag.isWeapon(itemInMainHand)) {

            // Normal

            // Drop full stack if using Shift - Drop
            if (!player.isSneaking()) {
                new ActionBar(getLanguageConfiguration()
                        .getLanguage(LanguageFlag.GENERAL_TIPS_DROP))
                        .send(player);
                // TODO: show accessories GUI
            } else {
                player.getWorld().dropItemNaturally(player.getEyeLocation(), itemInMainHand);
                player.getInventory().setItemInMainHand(null);
            }

            // Cancel reloading
            if (shooter != null
                    && shooter.isReloading()
                    && shooter.isCanReload()) {
                shooter.setCanReload(false);
                getTaskManager().swipeTask(shooter);
            }

            event.setCancelled(true);
        }
    }

}