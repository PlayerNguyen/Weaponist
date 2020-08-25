package com.playernguyen.listener;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.asset.gun.Gun;
import com.playernguyen.entity.Shooter;
import com.playernguyen.event.WeaponistPlayerReloadEvent;
import com.playernguyen.util.Tag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSwapHandListener extends WeaponistInstance implements Listener {

    @EventHandler
    public void swapHand(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        // Main hand item
        if (Tag.isWeapon(itemInMainHand)) {
            // Shooter check reloading or not for not repeating
            Shooter shooter = getShooterManager().getShooterAsPlayer(player);
            if (shooter.isReloading()) {
                event.setCancelled(true);
                return;
            }

            // Reload perform
            String weaponId = Tag.getWeaponId(itemInMainHand);
            Gun gun = getGunManager().getRegisteredWeapon(weaponId);

            // perform
            // event calling
            WeaponistPlayerReloadEvent weaponistPlayerReloadEvent = new WeaponistPlayerReloadEvent(player);
            Bukkit.getServer().getPluginManager().callEvent(weaponistPlayerReloadEvent);

            if (!weaponistPlayerReloadEvent.isCancelled()) {
                gun.reload(shooter, getWeaponist());
                event.setCancelled(true);
            }
        }
    }

}
