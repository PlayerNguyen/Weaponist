package com.playernguyen.entity;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.asset.Weapon;
import com.playernguyen.util.Tag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultShooter extends WeaponistInstance implements Shooter {

    private final Player player;
    private final Weapon currentWeapon;

    public DefaultShooter(Player player) {
        this.player = player;
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (Tag.isWeapon(stack)) {
            this.currentWeapon = getGunManager()
                    .getRegisteredWeapon(Tag.getWeaponId(stack));
        } else {
            this.currentWeapon = null;
        }
    }

    public DefaultShooter(Player player, Weapon currentWeapon) {
        this.player = player;
        this.currentWeapon = currentWeapon;
    }

    public Player asPlayer() {
        return player;
    }

    @Override
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
}
