package com.playernguyen.entity;

import com.playernguyen.weapon.Weapon;
import org.bukkit.entity.Player;

public class DefaultShooter implements Shooter {

    private Player player;
    private Weapon currentWeapon;

    // TODO: init the class with player and weapon in hand

    public Player asPlayer() {
        return player;
    }

    @Override
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
}
