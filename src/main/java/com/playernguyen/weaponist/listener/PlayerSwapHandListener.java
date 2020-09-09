package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.asset.gun.Gun;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.event.WeaponistPlayerReloadEvent;
import com.playernguyen.weaponist.util.Tag;
import com.playernguyen.weaponist.util.WeaponistUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSwapHandListener extends WeaponistListener {

    @EventHandler
    public void swapHand(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        // Main hand item
        if (Tag.isWeapon(itemInMainHand)) {
            // Shooter check reloading or not for not repeating
            Shooter shooter = getShooterManager().getShooterAsPlayer(player);
            if (shooter.isReloading()) {
                getDebugger().info("Is reloading...");
                event.setCancelled(true);
                return;
            }

            // Reload perform
            String weaponId = Tag.getWeaponId(itemInMainHand);
            Gun gun = getGunManager().getRegisteredWeapon(weaponId);

            if (Tag.getGunAmmo(itemInMainHand) == gun.getMaxStackSize()) {
                return;
            }

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
