package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener extends WeaponistListener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        // ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);


        if (Tag.isWeapon(event.getItemDrop().getItemStack())) {

            // Normal

            // Drop full stack if using Shift - Drop
//            if (player.isSneaking()) {
//                new ActionBar(getLanguageConfiguration()
//                        .getLanguage(LanguageFlag.GENERAL_TIPS_DROP))
//                        .send(player);
//                // TODO: show accessories GUI
//            }

            // Cancel reloading
            if (shooter != null && shooter.isReloading()) {
                getDebugger().info("Drop while reloading.");
                shooter.setCanReload(false);
                //getTaskManager().swipeTask(shooter);
            }


        }
    }

}
