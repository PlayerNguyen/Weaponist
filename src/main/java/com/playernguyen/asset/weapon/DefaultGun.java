package com.playernguyen.asset.weapon;

import com.playernguyen.asset.ItemTagEnum;
import com.playernguyen.sound.SoundConfiguration;
import com.playernguyen.util.ActionBar;
import com.playernguyen.util.Tag;
import com.playernguyen.util.WeaponistUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
    public ItemStack toItem(Player owner, int amount) {
        ItemStack stack = new ItemStack(getMaterial(), amount);
        ItemMeta itemMeta = stack.getItemMeta();

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
    public void reload(Player player) {
        // Search for ammo
        for (ItemStack i : player.getInventory().getContents()) {
            if (Tag.isAmmunition(i)
                    && Tag.getAmmunitionType(i).equalsIgnoreCase(getAmmunitionType())) {

                // Do reload
                ActionBar.performCountdown(gunConfiguration.getWeaponist(), player, getReloadTime(), () -> {
                    player.getInventory().getItemInMainHand().setAmount(getMaxStackSize());
                    // Decrease the item
                    WeaponistUtil.decreaseItemStack(i);
                    // Play sound
                    for (SoundConfiguration soundConfiguration : getReloadSoundList()) {
                        soundConfiguration.play(player.getEyeLocation());
                    }
                });
            }
        }
        // Can't reload
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
}
