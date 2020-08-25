package com.playernguyen.entity;

import com.playernguyen.manager.ManagerSet;
import org.bukkit.entity.Player;

public class ShooterManager extends ManagerSet<Shooter> {

    public Shooter getShooterAsPlayer(Player player) {
        return getContainer()
                .stream()
                .filter(e -> e.asPlayer() == player)
                .findAny()
                .orElse(null);
    }

}
