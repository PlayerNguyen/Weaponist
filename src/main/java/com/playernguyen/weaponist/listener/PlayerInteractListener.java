package com.playernguyen.weaponist.listener;

import com.playernguyen.weaponist.asset.gun.Gun;
import com.playernguyen.weaponist.asset.gun.Scopeable;
import com.playernguyen.weaponist.asset.gun.ShootType;
import com.playernguyen.weaponist.asset.throwItem.Throw;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.event.WeaponistPlayerShootEvent;
import com.playernguyen.weaponist.language.LanguageFlag;
import com.playernguyen.weaponist.util.ActionBar;
import com.playernguyen.weaponist.util.LocationUtil;
import com.playernguyen.weaponist.util.MathHelper;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractListener extends WeaponistListener {

    @EventHandler
    public void onInteracting(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandStack = player.getInventory().getItemInMainHand();

        // Guns
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
            // No ammo
            if (Tag.getGunAmmo(mainHandStack) <= 0) {
                ActionBar actionBar = new ActionBar();
                actionBar.setContent(getLanguageConfiguration()
                        .getLanguage(LanguageFlag.GENERAL_WEAPON_OUT_OF_AMMO));
                actionBar.send(player);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_SNARE, 0.5f, 1f);
                return;
            }

            // Call event and handler
            WeaponistPlayerShootEvent shootEvent = new WeaponistPlayerShootEvent(player);
            Bukkit.getPluginManager().callEvent(shootEvent);

            //If not cancel
            if (!shootEvent.isCancelled()) {
                if (System.currentTimeMillis() - shooter.getLastShoot() > 400) {
                    shooter.setShooting(false);
                }

                if (shooter.isReloading()) {
                    shooter.setCanReload(false);
                }

                // Shooting checker
                if (!shooter.isCanTrigger()) {
                    return;
                }

                shooter.setLastShoot(System.currentTimeMillis());
                if (weapon.getShootType() == ShootType.MULTIPLE) {
                    BukkitRunnable runnable = new BukkitRunnable() {
                        int i = 0;

                        @Override
                        public void run() {
                            ++i;
                            // Shoot
                            weapon.shoot(shooter, getWeaponist());
                            // Cancel
//                            System.out.println(weapon.getDelayPerShootTime() / 60);
                            if (i >= MathHelper.randomInt(2, 4)) {
                                cancel();
                            }
                        }
                    };

                    runnable.runTaskTimerAsynchronously(getWeaponist(), 0, 0);
                } else {
                    weapon.shoot(shooter, getWeaponist());
                }
            }
        }

        // Throwable
        if (Tag.isThrowable(mainHandStack)) {
            String itemId = Tag.getItemId(mainHandStack);
            Throw thr = getThrowManager().getRegisteredWeapon(itemId);
            Shooter shooter = getShooterManager().getShooterAsPlayer(player);

            thr.onThrow(shooter);
        }

    }

    // @EventHandler
    public void testInteract(PlayerInteractEvent event) {
        LocationUtil.createNoise(event.getPlayer(), 15);
    }

}
