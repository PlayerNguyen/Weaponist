package com.playernguyen.listener;

import com.playernguyen.asset.gun.Gun;
import com.playernguyen.asset.gun.Scopeable;
import com.playernguyen.entity.Shooter;
import com.playernguyen.event.WeaponistPlayerShootEvent;
import com.playernguyen.language.LanguageFlag;
import com.playernguyen.util.ActionBar;
import com.playernguyen.util.Tag;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener extends WeaponistListener {

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandStack = player.getInventory().getItemInMainHand();

        if (Tag.isWeapon(mainHandStack)) {

            String weaponId = Tag.getWeaponId(mainHandStack);
            Gun weapon = getGunManager().getRegisteredWeapon(weaponId);
            Shooter shooter = getShooterManager().getShooterAsPlayer(player);

            // Left click
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (weapon instanceof Scopeable) {
                    shooter.scopeToggle();
                }
                return;
            }

            // Right click
            if (mainHandStack.getAmount() == 1) {
                ActionBar actionBar = new ActionBar();
                actionBar.setContent(getLanguageConfiguration()
                        .getLanguage(LanguageFlag.GENERAL_WEAPON_OUT_OF_AMMO));
                actionBar.send(player);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_SNARE, 0.5f, 1f);
                return ;
            }


            // Call event and handler
            WeaponistPlayerShootEvent shootEvent = new WeaponistPlayerShootEvent(player);
            Bukkit.getPluginManager().callEvent(shootEvent);

            //If not cancel
            if (!shootEvent.isCancelled()) {
                weapon.shoot(shooter, getWeaponist());
            }
        }

    }

}
