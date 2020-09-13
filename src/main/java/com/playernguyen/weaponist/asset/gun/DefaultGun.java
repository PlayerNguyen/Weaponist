package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.asset.ItemTagEnum;
import com.playernguyen.weaponist.asset.ItemType;
import com.playernguyen.weaponist.asset.ammunition.AmmunitionEnum;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.entity.Target;
import com.playernguyen.weaponist.event.WeaponistPlayerShootEntityEvent;
import com.playernguyen.weaponist.language.LanguageFlag;
import com.playernguyen.weaponist.ray.RayResult;
import com.playernguyen.weaponist.ray.RayTrace;
import com.playernguyen.weaponist.runnable.ActionPerformRunnable;
import com.playernguyen.weaponist.sound.SoundConfiguration;
import com.playernguyen.weaponist.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.List;

public abstract class DefaultGun implements Gun {

    private static final String PARENT_FOLDER = "gun";

    private final GunEnum gunEnum;
    private final GunConfiguration gunConfiguration;


    public DefaultGun(GunEnum gunEnum) {
        this.gunEnum = gunEnum;
        this.gunConfiguration = new GunConfiguration(gunEnum, GunFlags.values(), PARENT_FOLDER);
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
    public AmmunitionEnum getAmmunitionType() {
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
        return generateBullet(shooter, plugin, (getFireAccuracy() * ((double)shooter.getStackShoot()/2))/10);
    }

    private Vector getDirection(Shooter shooter) {
        return shooter.getEyeLocation().getDirection();
    }


    @Override
    public ItemStack toItem(Player owner, int amount) {
        ItemStack stack = new ItemStack(getMaterial(), 1);
        ItemMeta itemMeta = stack.getItemMeta();

        // Set metadata
        if (itemMeta != null) {
            itemMeta.setDisplayName(getDisplayName());
            itemMeta.setLore(getDescription());
        }
        stack.setItemMeta(itemMeta);

        // Add nms data into the item
        stack = new Tag.Builder(stack)
                .initData(ItemTagEnum.IS_WEAPON)
                .setData(ItemTagEnum.ITEM_ID, gunEnum.getId())
                .setData(ItemTagEnum.WEAPON_AMMO_TYPE, getAmmunitionType())
                .setData(ItemTagEnum.GUN_AMMO, amount)
                .setData(ItemTagEnum.ITEM_TYPE, ItemType.GUN.toString())
                .setData(ItemTagEnum.WEAPON_ACCESSORY_1, "")
                .setData(ItemTagEnum.WEAPON_ACCESSORY_2, "")
                .setData(ItemTagEnum.WEAPON_ACCESSORY_3, "")
                .setData(ItemTagEnum.WEAPON_ACCESSORY_4, "")
                .clearAttribute()
                .build();
        return WeaponistUtil.updateItemMeta(stack, this);
    }

    @Override
    public void reload(Shooter shooter, Weaponist plugin) {
        Player player = shooter.asPlayer();
        if (shooter.isScoping()) {
            shooter.setScoping(false);
        }
        // Search for ammo
        for (ItemStack i : player.getInventory().getContents()) {
            if (Tag.isAmmunition(i)
                    && Tag.getAmmunitionType(i).equalsIgnoreCase(getAmmunitionType().getId())) {
                shooter.setReloading(true);

                // Do reload
                ActionPerformRunnable bukkitRunnable = ActionBar.performCountdown(shooter, getReloadTime());

                // On tick
                bukkitRunnable.setOnTick(() -> {
                    if (!shooter.isCanReload()) {
                        // Set properties
                        shooter.setCanReload(true);
                        shooter.setReloading(false);

                        // Send cancel reload
                        new ActionBar(plugin.getLanguageConfiguration()
                                .getLanguage(LanguageFlag.GENERAL_GUN_RELOAD_CANCELLED))
                                .send(player);

                        bukkitRunnable.cancel();
                    }
                });

                // On done
                bukkitRunnable.setOnDone(() -> {
                    ItemStack manHand = player.getInventory().getItemInMainHand();

                    // Decrease the item
//                    if (manHand.getAmount() < getMaxStackSize()) {
//                        WeaponistUtil.decreaseItemStack(i);
//                        manHand.setAmount(getMaxStackSize());
//                    }
                    // Using Tag
                    if (Tag.getGunAmmo(manHand) < getMaxStackSize()) {
                        //Tag.setData(manHand, ItemTagEnum.GUN_AMMO, getMaxStackSize());
                        player.getInventory()
                                .setItemInMainHand(
                                        WeaponistUtil
                                                .updateItemMeta(
                                                        Tag.setData(manHand,
                                                            ItemTagEnum.GUN_AMMO,
                                                            this.getMaxStackSize()
                                                        ),
                                                        this
                                                )
                                );

                        WeaponistUtil.decreaseItemStack(i);
                    }

                    // Play sound
                    for (SoundConfiguration soundConfiguration : getReloadSoundList()) {
                        soundConfiguration.play(player.getEyeLocation());
                    }
                    shooter.setReloading(false);
                });

                // Call new tasks
                plugin.getTaskManager().put(shooter, bukkitRunnable);
                // Execute the task
                plugin.getTaskManager().get(shooter).runTaskTimerAsynchronously(plugin, 0, 0);
                break;
            }
        }
    }

    @Override
    public ShootType getShootType() {
        return gunEnum.getShootType();
    }

    @Override
    public String getGlobalId() {
        return gunEnum.getGlobalId();
    }

    protected RayResult generateBullet(Shooter shooter, Plugin plugin, double rate) {
        if (Tag.getGunAmmo(shooter.asPlayer().getInventory().getItemInMainHand()) <= 0) {
            return null;
        }

        if (!shooter.isShooting()) {
            shooter.setStackShoot(0);
        }

        Player player = shooter.asPlayer();

        // Raytrace the block and entities
        RayTrace rayTrace = new RayTrace(shooter, getMaxDistance(), rate);
        RayResult rayResult = rayTrace.ray(Particle.VILLAGER_HAPPY, getMaxPenetrate());
        // Play start shooting particle
        new ParticleBuilder(Particle.SMOKE_NORMAL, 1)
                .offset(getDirection(shooter)
                        .multiply(0.2))
                .extra(0.1)
                .play(shooter.getEyeLocation().add(getDirection(shooter).multiply(1.2)));

        // Damage
        for (Target target : rayResult.getTargets()) {
            if (target.isHeadshot()) {
                Bukkit.getScheduler().runTask(plugin, () ->
                        target.asEntity().damage(getDamage()*2, shooter.asPlayer()));
                WeaponistPlayerShootEntityEvent event =
                        new WeaponistPlayerShootEntityEvent(
                                shooter,
                                target.asEntity(),
                                getDamage()*2,
                                true
                        );
                Bukkit.getPluginManager().callEvent(event);
            } else {
                Bukkit.getScheduler().runTask(plugin, () ->
                        target.asEntity().damage(getDamage(), shooter.asPlayer()));
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
            new ParticleBuilder(Particle.BLOCK_CRACK, 60)
                    .offset(new Vector(0.1, 0.1, 0.1))
                    .play(block.getLocation(), new MaterialData(block.getType()));
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

            // Refresh trigger
            BukkitRunnable runnable = new BukkitRunnable() {
                double d = getDelayPerShootTime();
                //                int i = getDelayPerShootTime();
                @Override
                public void run() {
                    // Update tick
                    d = d - (0.1d);

                    if (d <= 0) {
                        shooter.setCanTrigger(true);
                        cancel();
                    }
                }
            };
            runnable.runTaskTimerAsynchronously(plugin, 0, 0);
        }
        // Stacking the shoot
        shooter.setShooting(true);
        shooter.setStackShoot(shooter.getStackShoot() + 1);
//        BukkitRunnable runnable = new BukkitRunnable() {
//            final int currentStack = shooter.getStackShoot();
//
//
//            double d = 1;
//            @Override
//            public void run() {
//                d = d - (0.05);
//                if (d <= 0) {
//                    shooter.setShooting(false);
//                    cancel();
//                }
//            }
//        };
//        runnable.runTaskTimerAsynchronously(plugin, 0, 0);
        // Play effect
        //WeaponistUtil.knockBack(player, 0.5f);
        //WeaponistUtil.decreaseItemStack(player.getInventory().getItemInMainHand());
        // Create accuracy
        LocationUtil.createNoise(player, (float) getFireAccuracy());
        // Decrease ammo
        ItemStack handStack = player.getInventory().getItemInMainHand();
        ItemStack updateStack = WeaponistUtil
                .updateItemMeta(
                        Tag.setData(handStack, ItemTagEnum.GUN_AMMO, Tag.getGunAmmo(handStack)-1),
                        this
                );
        player.getInventory()
                .setItemInMainHand(updateStack);
        return rayResult;
    }
}
