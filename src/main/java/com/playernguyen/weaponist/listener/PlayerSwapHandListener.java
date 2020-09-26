package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.asset.gun.Gun;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.event.WeaponistPlayerReloadEvent;
import com.playernguyen.weaponist.setting.SettingFlag;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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

            // Lock the trigger when swap hand
            shooter.setCanTrigger(false);
            new BukkitRunnable() {
                double _duration = getWeaponistSetting().getDouble(SettingFlag.SWAP_HAND_DURATION);
                @Override
                public void run() {
                    _duration = _duration - (1.0/20.0);

                    if (_duration <= 0) {
                        shooter.setCanTrigger(true);
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(getWeaponist(), 0, 0);

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

            }

            event.setCancelled(true);
        }
    }

}
