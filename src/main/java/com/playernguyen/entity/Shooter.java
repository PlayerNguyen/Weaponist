package com.playernguyen.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Shooter {

    ItemStack getCurrentItem();

    void setCurrentItem(ItemStack itemStack);

    Player asPlayer();

    boolean isCanTrigger();

    void setCanTrigger(boolean b);

    boolean isReloading();

    void setReloading(boolean b);

    int getStackShoot();

    void setStackShoot(int i);

    boolean isScoping();

    void setScoping(boolean b);

    void scopeToggle();

    ItemStack getCurrentHelmet();

    void setCurrentHelmet(ItemStack i);

    Location getLocation();

    Location getEyeLocation();

}
