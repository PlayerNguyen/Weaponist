package com.playernguyen.event;

import com.playernguyen.entity.Shooter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WeaponistPlayerShootEntityEvent extends Event {



    private final Shooter shooter;
    private final LivingEntity livingEntity;
    private final double damage;
    private static final HandlerList handlerList = new HandlerList();

    public WeaponistPlayerShootEntityEvent(Shooter shooter, LivingEntity livingEntity, double damage) {
        this.shooter = shooter;
        this.livingEntity = livingEntity;
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
