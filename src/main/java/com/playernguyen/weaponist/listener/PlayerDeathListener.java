package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.language.LanguageFlag;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener extends WeaponistListener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Shooter shooter = getShooterManager().getShooterAsPlayer(player);

        // Set stop scoping
        if (shooter.isScoping()) {
            shooter.setScoping(false);
        }
    }

    @EventHandler
    public void onDeathNotification(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Player deathPlayer = event.getEntity();
        // Main hand check
        ItemStack itemInMainHand = killer.getInventory().getItemInMainHand();
        if (Tag.isWeapon(itemInMainHand)) {
            event.setDeathMessage(
                    getLanguageConfiguration().getLanguage(LanguageFlag.DEATH_MESSAGE)
                            .replace("%killer%", killer.getDisplayName())
                            .replace("%player%", deathPlayer.getDisplayName())
                            .replace("%weapon%", getGunManager().getRegisteredWeapon(Tag.getWeaponId(itemInMainHand))
                                    .getDisplayName().replace("%ammo%", "").replace("%max_ammo%", "")
                                    .replace("(", "").replace(")", "").replace("/", "")
                            )
            );
        }
    }

}
