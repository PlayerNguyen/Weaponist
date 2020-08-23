package com.playernguyen.entity;

import com.playernguyen.asset.Weapon;
import org.bukkit.entity.Player;

public interface Shooter {

    Weapon getCurrentWeapon();

    Player asPlayer();

}
