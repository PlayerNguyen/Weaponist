package com.playernguyen.weaponist.event;

import com.playernguyen.weaponist.entity.DefaultShooter;
import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponistPlayerShootEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();

    private final Shooter shooter;
    private boolean cancelled;

    public WeaponistPlayerShootEvent(Player who) {
        super(who);
        this.shooter = new DefaultShooter(who);
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public ItemStack getHandItem() {
        return getShooter().asPlayer().getInventory().getItemInMainHand();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
