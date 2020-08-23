package com.playernguyen.event;

import com.playernguyen.entity.DefaultShooter;
import com.playernguyen.entity.Shooter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponistPlayerReloadEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();

    private final Shooter shooter;
    private boolean cancelled;

    public WeaponistPlayerReloadEvent(Player who) {
        super(who);
        this.shooter = new DefaultShooter(who);
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

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
