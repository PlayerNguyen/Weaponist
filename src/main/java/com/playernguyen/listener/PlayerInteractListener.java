package com.playernguyen.listener;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.asset.weapon.Gun;
import com.playernguyen.event.WeaponistPlayerShootEvent;
import com.playernguyen.language.LanguageFlag;
import com.playernguyen.util.ActionBar;
import com.playernguyen.util.Tag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener extends WeaponistInstance implements Listener {

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack mainHandStack = player.getInventory().getItemInMainHand();

        if (Tag.isWeapon(mainHandStack)) {

            String weaponId = Tag.getWeaponId(mainHandStack);
            Gun weapon = getGunManager().getRegisteredWeapon(weaponId);

            if (mainHandStack.getAmount() == 1) {
                ActionBar actionBar = new ActionBar();
                actionBar.setContent(getLanguageConfiguration()
                        .getLanguage(LanguageFlag.GENERAL_WEAPON_OUT_OF_AMMO));
                actionBar.send(player);
                return ;
            }


            // Call event and handler
            WeaponistPlayerShootEvent shootEvent = new WeaponistPlayerShootEvent(player);
            Bukkit.getPluginManager().callEvent(shootEvent);
            if (!shootEvent.isCancelled()) {
                weapon.shoot(player);
            }
        }

        //TODO single shoot system
    }

}
