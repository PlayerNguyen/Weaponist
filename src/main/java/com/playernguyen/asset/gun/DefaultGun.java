package com.playernguyen.asset.gun;

import com.playernguyen.asset.ItemTagEnum;
import com.playernguyen.entity.Shooter;
import com.playernguyen.entity.Target;
import com.playernguyen.event.WeaponistPlayerShootEntityEvent;
import com.playernguyen.ray.RayResult;
import com.playernguyen.ray.RayTrace;
import com.playernguyen.sound.SoundConfiguration;
import com.playernguyen.util.ActionBar;
import com.playernguyen.util.Tag;
import com.playernguyen.util.WeaponistUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.List;

public abstract class DefaultGun implements Gun {

    private final GunEnum gunEnum;
    private final GunConfiguration gunConfiguration;

    public DefaultGun(GunEnum gunEnum) throws IOException {
        this.gunEnum = gunEnum;
        this.gunConfiguration = new GunConfiguration(gunEnum);
    }

    @Override
    public String getId() {
        return gunEnum.getId();
    }

    @Override
    public int getMaxStackSize() {
        return gunConfiguration.getMaxMagazine();
    }

    @Override
    public String getDisplayName() {
        return gunConfiguration.getLoadedItemMetadata().getDisplayName();
    }

    @Override
    public List<String> getDescription() {
        return gunConfiguration.getLoadedItemMetadata().getDescription();
    }

    @Override
    public Material getMaterial() {
        return gunConfiguration.getLoadedItemMetadata().getMaterial();
    }

    @Override
    public String getAmmunitionType() {
        return gunConfiguration.getAmmoType();
    }

    @Override
    public double getReloadTime() {
        return gunConfiguration.getReloadTime();
    }

    @Override
    public double getDamage() {
        return gunConfiguration.getDamage();
    }

    @Override
    public List<SoundConfiguration> getReloadSoundList() {
        return gunConfiguration.getReloadSound();
    }

    @Override
    public List<SoundConfiguration> getShootSoundList() {
        return gunConfiguration.getShootSound();
    }

    @Override
    public double getDelayPerShootTime() {
        return gunConfiguration.getDelayPerShoot();
    }

    @Override
    public double getFireAccuracy() {
        return gunConfiguration.getFireAccuracy();
    }

    @Override
    public int getMaxDistance() {
        return gunConfiguration.getMaxDistance();
    }

    @Override
    public int getMaxPenetrate() {
        return gunConfiguration.getMaxPenetrate();
    }

    @Override
    public RayResult shoot(Shooter shooter, Plugin plugin) {

        // Shooting checker
        if (!shooter.isCanTrigger()) { return null; }

        Player player = shooter.asPlayer();

        RayTrace rayTrace = new RayTrace(shooter, getMaxDistance(), getFireAccuracy() * shooter.getStackShoot());
        RayResult rayResult = rayTrace.ray(Particle.VILLAGER_HAPPY, getMaxPenetrate());

        // Damage
        for (Target target : rayResult.getTargets()) {
            if (target.isHeadshot()) {
                target.asEntity().damage(getDamage()*2, shooter.asPlayer());
                WeaponistPlayerShootEntityEvent event =
                        new WeaponistPlayerShootEntityEvent(
                                shooter,
                                target.asEntity(),
                                getDamage()*2,
                                true
                        );
                Bukkit.getPluginManager().callEvent(event);
            } else {
                target.asEntity().damage(getDamage(), shooter.asPlayer());
                WeaponistPlayerShootEntityEvent event =
                        new WeaponistPlayerShootEntityEvent(
                                shooter,
                                target.asEntity(),
                                getDamage()
                        );
                Bukkit.getPluginManager().callEvent(event);
            }
        }

        // Play break particle
        if (rayResult.getHitBlock() != null) {
            // Get block
            Block block = rayResult.getHitBlock();
            block.getLocation().getWorld()
                    .spawnParticle(
                            Particle.BLOCK_CRACK,
                            block.getLocation(),
                            350,
                            0.5f,
                            0.5f,
                            0.5f,
                            new MaterialData(block.getType())
                    );
        }

        // Play sound
        for (SoundConfiguration soundConfiguration : getShootSoundList()) {
            soundConfiguration.play(player.getLocation());
        }

        // Lock the trigger
        if (getDelayPerShootTime() > 0) {
            shooter.setCanTrigger(false);

            if (this instanceof Cockable) {
                // Play cock sound
                ((Cockable) this).getCockSound().play(player.getLocation());
            }
            BukkitRunnable runnable = new BukkitRunnable() {
                double d = getDelayPerShootTime();
                @Override
                public void run() {
                    d = d - (0.05);
                    if (d <= 0) {
                        shooter.setCanTrigger(true);
                        cancel();
                    }
                }
            };
            runnable.runTaskTimerAsynchronously(plugin, 0, 0);
        }
        // Stacking the shoot
        shooter.setStackShoot(shooter.getStackShoot() + 1);
        BukkitRunnable runnable = new BukkitRunnable() {
            double d = 1;
            @Override
            public void run() {
                d = d - (0.05);
                if (d <= 0) {
                    shooter.setStackShoot(0);
                    cancel();
                }
            }
        };
        runnable.runTaskTimerAsynchronously(plugin, 0, 0);
        // Play effect
        //WeaponistUtil.knockBack(player, 0.5f);
        WeaponistUtil.decreaseItemStack(player.getInventory().getItemInMainHand());
        return rayResult;
    }


    @Override
    public ItemStack toItem(Player owner, int amount) {
        ItemStack stack = new ItemStack(getMaterial(), amount);
        ItemMeta itemMeta = stack.getItemMeta();

        // Set metadata
        if (itemMeta != null) {
            itemMeta.setDisplayName(getDisplayName());
            itemMeta.setLore(getDescription());
        }
        stack.setItemMeta(itemMeta);

        // Add nms data into the item
        stack = new Tag.Builder(stack)
                .appendInitialKeyEnum(ItemTagEnum.IS_WEAPON)
                .appendData(ItemTagEnum.WEAPON_ID, gunEnum.getId())
                .appendData(ItemTagEnum.WEAPON_AMMO_TYPE, getAmmunitionType())
                .build();
        return stack;
    }

    @Override
    public void reload(Shooter shooter, Plugin plugin) {
        Player player = shooter.asPlayer();
        // Search for ammo
        for (ItemStack i : player.getInventory().getContents()) {
            if (Tag.isAmmunition(i)
                    && Tag.getAmmunitionType(i).equalsIgnoreCase(getAmmunitionType())) {
                shooter.setReloading(true);
                // Do reload
                ActionBar.performCountdown(plugin, player, getReloadTime(), () -> {
                    ItemStack stack = player.getInventory().getItemInMainHand();

                    // Decrease the item
                    if (stack.getAmount() < getMaxStackSize()) {
                        WeaponistUtil.decreaseItemStack(i);
                        stack.setAmount(getMaxStackSize());
                    }

                    // Play sound
                    for (SoundConfiguration soundConfiguration : getReloadSoundList()) {
                        soundConfiguration.play(player.getEyeLocation());
                    }
                    shooter.setReloading(false);
                });
            }
        }
    }

}
