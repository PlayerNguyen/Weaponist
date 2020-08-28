package com.playernguyen.weaponist.entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultShooter implements Shooter {

    private final Player player;
    private ItemStack currentItem;
    private boolean canTrigger;
    private boolean reloading;
    private int stackShoot;
    private boolean scoping;
    private boolean shooting;
    private boolean canReload;
    private ItemStack currentHelmet;
    private long lastShoot;


    public DefaultShooter(Player player) {
        this.player = player;
        this.currentItem = player.getInventory().getItemInMainHand();
        this.canTrigger = true;
        this.reloading = false;
        this.stackShoot = 0;
        this.scoping = false;
        this.shooting = false;
        this.canReload = true;
        this.currentHelmet = player.getInventory().getHelmet();
        this.lastShoot = 0;
    }

    @Override
    public ItemStack getCurrentHelmet() {
        return currentHelmet;
    }

    @Override
    public void setCurrentHelmet(ItemStack currentHelmet) {
        this.currentHelmet = currentHelmet;
    }

    @Override
    public boolean isReloading() {
        return reloading;
    }

    @Override
    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    @Override
    public void setCanTrigger(boolean canTrigger) {
        this.canTrigger = canTrigger;
    }

    @Override
    public boolean isCanTrigger() {
        return canTrigger;
    }

    public Player asPlayer() {
        return player;
    }

    @Override
    public int getStackShoot() {
        return stackShoot;
    }

    @Override
    public void setStackShoot(int stackShoot) {
        this.stackShoot = stackShoot;
    }

    @Override
    public boolean isScoping() {
        return scoping;
    }

    @Override
    public void setScoping(boolean scoping) {
        if (scoping) {
            // Storage current helmet
            this.setCurrentHelmet(asPlayer().getInventory().getHelmet());
            // Zoom in
            asPlayer().setWalkSpeed(-0.5f);
            asPlayer().getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
        } else {
            // Zoom out
            asPlayer().setWalkSpeed(0.2f);
            asPlayer().getInventory().setHelmet(getCurrentHelmet());
        }

        this.scoping = scoping;
    }

    @Override
    public void setCurrentItem(ItemStack currentItem) {
        this.currentItem = currentItem;
    }

    @Override
    public ItemStack getCurrentItem() {
        return currentItem;
    }

    @Override
    public void scopeToggle() {
        this.setScoping(!scoping);
    }

    @Override
    public Location getLocation() {
        return asPlayer().getLocation();
    }

    @Override
    public Location getEyeLocation() {
        return asPlayer().getEyeLocation();
    }

    @Override
    public boolean isShooting() {
        return shooting;
    }

    @Override
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    @Override
    public long getLastShoot() {
        return lastShoot;
    }

    @Override
    public void setLastShoot(long lastShoot) {
        this.lastShoot = lastShoot;
    }

    @Override
    public boolean isCanReload() {
        return canReload;
    }

    @Override
    public void setCanReload(boolean canReload) {
        this.canReload = canReload;
    }
}
