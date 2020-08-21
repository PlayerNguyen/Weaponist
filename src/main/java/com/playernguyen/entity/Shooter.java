package com.playernguyen.entity;

import com.playernguyen.weapon.Weapon;
import org.bukkit.entity.Player;

public interface Shooter {

    Weapon getCurrentWeapon();

    Player asPlayer();

}
